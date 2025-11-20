package org.logic;


import org.logic.utils.InputParser;

public class Main {
    public static void main(String[] args) {
        String expression = "(3 + 4) * 2";
        Calculator.calculate(expression);
        /*System.out.println(Calculator.calculate(expression));*/
    }
}