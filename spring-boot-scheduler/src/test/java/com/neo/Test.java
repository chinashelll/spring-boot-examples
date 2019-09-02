package com.neo;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        BigDecimal re = new BigDecimal(0.8);
        BigDecimal re1 = new BigDecimal("0.80");
        System.out.println(re);
        System.out.println(re1);
    }
}
