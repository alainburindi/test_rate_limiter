package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Data {
    static List<Customer> customers = new ArrayList<>();
    static HashMap<String, Customer> keyStore = new HashMap<>();
    static HashMap<String, Long> apiUsage = new HashMap<>();

    static {
        addData();
    }

    private static void addData() {
        customers.add(new Customer("Alain", 10L, 30L, "Kacyiru" ));
        customers.add(new Customer("Mireille", 2L, 30L, "Kacyiru"));
        customers.add(new Customer("Zachary", 5L, 30L, "Kacyiru"));
        customers.add(new Customer("Abel", 1L, 20L, "Kacyiru"));
        customers.forEach(customer -> keyStore.put(customer.getName(), customer));
    }

}
