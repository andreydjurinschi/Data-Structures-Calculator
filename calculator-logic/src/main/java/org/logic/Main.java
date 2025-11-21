package org.logic;

import org.logic.exceptions.IllegalSymbolException;
import org.logic.utils.Calculator;

public class Main {
    public static void main(String[] args) {

        String expression = "5 ^ 2 - 19 ";
        try {
            System.out.println(Calculator.calculate(expression));
        } catch (IllegalSymbolException e) {
            System.out.println(e.getMessage());
        }

    }
}