package org.logic;

import org.logic.exceptions.IllegalSymbolException;
import org.logic.utils.Calculator;

public class Main {
    public static void main(String[] args) {

        String expression = "50 * 20 / ( 53 - 33) + 12 - 5 * 22 - 500";
        try {
            System.out.println(Calculator.calculate(expression));
        } catch (IllegalSymbolException e) {
            System.out.println(e.getMessage());
        }

    }
}