package main.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Price {

    private String productName;
    private BigDecimal value;
    private LocalDate date;

    public Price(String productName, BigDecimal value, LocalDate date) {
        this.productName = productName;
        this.value = value;
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
