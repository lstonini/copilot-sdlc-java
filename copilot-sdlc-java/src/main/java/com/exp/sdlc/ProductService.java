package com.exp.sdlc;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductService {

    /**
     * Regra:
     * - nome obrigatório (>= 3 chars)
     * - preço base > 0
     * - imposto por categoria:
     *    "FOOD"  = 5%
     *    "BOOK"  = 0%
     *    "ELEC"  = 12%
     *    default = 8%
     * - desconto promocional: se basePrice >= 200 aplica 10% antes do imposto
     */
    public BigDecimal finalPrice(Product p) {
        validate(p);

        BigDecimal price = p.getBasePrice();

        if (price.compareTo(new BigDecimal("200")) >= 0) {
            price = price.multiply(new BigDecimal("0.90")); // 10% off
        }

        BigDecimal taxRate = taxRate(p.getCategory());
        BigDecimal taxed = price.multiply(BigDecimal.ONE.add(taxRate));

        return taxed.setScale(2, RoundingMode.HALF_UP);
    }

    private void validate(Product p) {
        if (p == null) throw new IllegalArgumentException("product is null");
        if (p.getName() == null || p.getName().trim().length() < 3)
            throw new IllegalArgumentException("invalid name");
        if (p.getBasePrice() == null || p.getBasePrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("invalid price");
        if (p.getCategory() == null || p.getCategory().isBlank())
            throw new IllegalArgumentException("invalid category");
    }

    private BigDecimal taxRate(String category) {
        return switch (category) {
            case "FOOD" -> new BigDecimal("0.05");
            case "BOOK" -> BigDecimal.ZERO;
            case "ELEC" -> new BigDecimal("0.12");
            default -> new BigDecimal("0.08");
        };
    }
}
