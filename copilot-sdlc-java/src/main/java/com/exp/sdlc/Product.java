package com.exp.sdlc;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final String name;
    private final BigDecimal basePrice; // preço base
    private final String category;

    public Product(String name, BigDecimal basePrice, String category) {
        this.name = name;
        this.basePrice = basePrice;
        this.category = category;
    }

    public String getName() { return name; }
    public BigDecimal getBasePrice() { return basePrice; }
    public String getCategory() { return category; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name)
                && Objects.equals(basePrice, product.basePrice)
                && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, basePrice, category);
    }
}
