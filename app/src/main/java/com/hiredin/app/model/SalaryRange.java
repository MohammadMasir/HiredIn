package com.hiredin.app.model;

public class SalaryRange {
    private Double minimum;
    private Double maximum;
    private String currency;

    // Default constructor
    public SalaryRange() {}

    // Getters and Setters
    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}