package com.theoreoluwa.property.propertymanagement.Controller;

public class CalculatorMain {
    public static void main(String[] args) {
        CalculatorController cc = new CalculatorController();
        Double result = cc.add(4.5, 6.7, 7.8);
        System.out.println(result);
    }
}
