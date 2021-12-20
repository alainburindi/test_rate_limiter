package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HelloController {

	@GetMapping("/test")
	public String index() {
		return "OK!!!!!!!!!!!!!";
	}

	@GetMapping("/customers/keys")
	public ResponseEntity<HashMap<String, Customer>> getCustomerKeyResponse() {
		return ResponseEntity.ok(Data.keyStore);
	}

	@GetMapping("/api_usage")
	public ResponseEntity<HashMap<String, Long>> getCustomerResponse() {
		return ResponseEntity.ok(Data.apiUsage);
	}
}

