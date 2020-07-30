package main.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Product {

    private String name;
    private Stock stock;
    private Price price;
    private List<Price> prices;

    // What if product exists? Use constructor to add to lists?
    public Product(String name, int quantity, BigDecimal price) {
        this.name = name;
        this.stock = new Stock(name, quantity);
        this.price = new Price(name, price, LocalDate.now());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}