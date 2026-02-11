package com.exp.sdlc;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private final ProductService service = new ProductService();

    @Test
    void finalPrice_shouldApplyFoodTax_whenFoodCategory() {
        Product p = new Product("Banana", new BigDecimal("100.00"), "FOOD");
        BigDecimal result = service.finalPrice(p);
        assertEquals(new BigDecimal("105.00"), result);
    }

    @Test
    void finalPrice_shouldApplyNoTax_whenBookCategory() {
        Product p = new Product("Book A", new BigDecimal("50.00"), "BOOK");
        BigDecimal result = service.finalPrice(p);
        assertEquals(new BigDecimal("50.00"), result);
    }

    @Test
    void finalPrice_shouldApplyDiscountBeforeTax_whenPriceAtLeast200() {
        // 200 -> 10% off => 180
        // ELEC tax 12% => 201.60
        Product p = new Product("Headphones", new BigDecimal("200.00"), "ELEC");
        BigDecimal result = service.finalPrice(p);
        assertEquals(new BigDecimal("201.60"), result);
    }

    @Test
    void finalPrice_shouldUseDefaultTax_whenUnknownCategory() {
        // default 8%
        Product p = new Product("Generic", new BigDecimal("100.00"), "OTHER");
        BigDecimal result = service.finalPrice(p);
        assertEquals(new BigDecimal("108.00"), result);
    }

    @Test
    void finalPrice_shouldThrow_whenProductIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.finalPrice(null));
        assertEquals("product is null", ex.getMessage());
    }

    @Test
    void finalPrice_shouldThrow_whenNameIsInvalid() {
        Product p = new Product("  ", new BigDecimal("10.00"), "FOOD");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.finalPrice(p));
        assertEquals("invalid name", ex.getMessage());
    }

    @Test
    void finalPrice_shouldThrow_whenPriceIsZeroOrNegative() {
        Product p1 = new Product("OkName", new BigDecimal("0"), "FOOD");
        assertThrows(IllegalArgumentException.class, () -> service.finalPrice(p1));

        Product p2 = new Product("OkName", new BigDecimal("-1"), "FOOD");
        assertThrows(IllegalArgumentException.class, () -> service.finalPrice(p2));
    }

    @Test
    void finalPrice_shouldThrow_whenCategoryIsBlank() {
        Product p = new Product("OkName", new BigDecimal("10.00"), " ");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.finalPrice(p));
        assertEquals("invalid category", ex.getMessage());
    }
}
