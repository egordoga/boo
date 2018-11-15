package ua.booking.model;

import java.math.BigDecimal;

public class UUserTest {
    private String name;
    private BigDecimal a;

    public UUserTest(String name, BigDecimal a) {
        this.name = name;
        this.a = a;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getA() {
        return a;
    }
}
