package com.test.MockingTest.domain;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String description;
    private BigDecimal size;
    private int price;

    public Product() {
    }

    public Product(int id, String description, BigDecimal size, int price) {
        this.id = id;
        this.description = description;
        this.size = size;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }
}
