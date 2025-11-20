package org.logic;

public class Main {
    public static void main(String[] args) {
        String expression = "11 + 18 * ( 20 - 2 + ( 15 - 9 ) )";
        System.out.println(Calculator.calculate(expression));
    }
}