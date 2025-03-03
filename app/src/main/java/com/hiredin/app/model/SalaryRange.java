package com.hiredin.app.model;

import java.math.BigDecimal;

import com.hiredin.app.model.enums.Period;

public class SalaryRange {
    private BigDecimal minimum;
    private BigDecimal maximum;
    private String currency;
    private Period period;

    // Default constructor
    public SalaryRange() {}

    // Constructor with parameters
    public SalaryRange(BigDecimal minimum, BigDecimal maximum, String currency) {
        if (minimum != null && maximum != null && minimum.compareTo(maximum) > 0) {
            throw new IllegalArgumentException("Minimum salary cannot be greater than maximum salary");
        }
        this.minimum = minimum;
        this.maximum = maximum;
        this.currency = currency;
    }

    // Getters and Setters
    public BigDecimal getMinimum() {
        return minimum;
    }

    public void setMinimum(BigDecimal minimum) {
        if (maximum != null && minimum.compareTo(maximum) > 0) {
            throw new IllegalArgumentException("Minimum salary cannot be greater than maximum salary");
        }
        this.minimum = minimum;
    }

    public BigDecimal getMaximum() {
        return maximum;
    }

    public void setMaximum(BigDecimal maximum) {
        if (minimum != null && minimum.compareTo(maximum) > 0) {
            throw new IllegalArgumentException("Minimum salary cannot be greater than maximum salary");
        }
        this.maximum = maximum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "SalaryRange [minimum=" + minimum + ", maximum=" + maximum + ", currency=" + currency + "]";
    }

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}
}
