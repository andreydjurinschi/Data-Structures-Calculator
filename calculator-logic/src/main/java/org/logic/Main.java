package org.logic;


import org.logic.utils.InputParser;

public class Main {
    public static void main(String[] args) {
        String expression = "(33 + 4) * 2";
        Calculator calculator = new Calculator();
        calculator.calculate(expression);

    }
}