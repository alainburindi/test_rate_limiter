package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Calendar;

import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;


@Component
public class RequestInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    private Boolean isAllowedAndIncrement(String key, Long limit) {
//        reading
        Long value = Data.apiUsage.getOrDefault(key, 0L);
        if (value >= limit) {
            return false;
        }
//        writing
        Data.apiUsage.put(key, value + 1);
        return true;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        String apiKey = request.getHeader("API_KEY");
        log.info("[preHandle][" + apiKey + "]");
        Customer customer = Data.keyStore.get(apiKey);
        if (customer == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return false;
        }
        String secondRateLimiterKey = String.format("rl_%s:%s", apiKey, Instant.now().getEpochSecond());

        if (!isAllowedAndIncrement(secondRateLimiterKey, customer.getBurstCapacity())) {
            response.sendError(TOO_MANY_REQUESTS.value(), "Too many requests per second");
            return false;
        }

        String monthRateLimiterKey = String.format("rl_%s:%s_%s", apiKey, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH));
        if (!isAllowedAndIncrement(monthRateLimiterKey, customer.getMonthlyLimit())) {
            response.sendError(TOO_MANY_REQUESTS.value(), "Too many requests per month");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {
        log.info("[postHandle][" + request + "]");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler, Exception ex) {
        if (ex != null) {
            ex.printStackTrace();
        }
        log.info("[afterCompletion][" + request + "][exception: " + ex + "]");
//        update the main database
    }
}
