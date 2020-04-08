package Models;

import java.time.LocalDate;

public class Stock {

    private String productName;
    private int quantity;
    private LocalDate date;

    public Stock(String productName, int quantity, LocalDate date) {
        this.productName = productName;
        this.quantity = quantity;
        this.date = date;
    }

    public Stock(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
        this.date = LocalDate.now();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
