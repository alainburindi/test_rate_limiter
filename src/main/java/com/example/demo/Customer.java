package com.example.demo;

public class Customer {
    private String name;
    private Long burstCapacity;
    private Long monthlyLimit;
    private String address;

    public Customer(String name, Long burstCapacity, Long monthlyLimit, String address ) {
        this.name = name;
        this.burstCapacity = burstCapacity;
        this.address = address;
        this.monthlyLimit = monthlyLimit;
    }

    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public Long getBurstCapacity() {
        return burstCapacity;
    }

    public Long getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBurstCapacity(Long burstCapacity) {
        this.burstCapacity = burstCapacity;
    }

    public void setMonthlyLimit(Long monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }
}
