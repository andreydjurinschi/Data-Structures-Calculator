package org.logic;

import org.logic.utils.RPNConverter;

import java.util.Stack;

public class Calculator {
    public static double calculate(String expression) {
        expression = RPNConverter.convert(expression);
        String[] elems =  expression.split(" ");
        Stack<Double> stack = new Stack<>();

        for (String elem : elems) {
            switch (elem) {
                case "+" -> {
                    stack.push(stack.pop() + stack.pop());
                }
                case "-" -> {
                    double sec =  stack.pop();
                    double prev = stack.pop();
                    stack.push(prev - sec);
                }
                case "*" -> {
                    stack.push(stack.pop() * stack.pop());
                }
                case "/" -> {
                    double sec =  stack.pop();
                    double prev = stack.pop();
                    stack.push(prev / sec);
                }
/*                case "(", ")" -> {
                    stack.pop();
                }*/
                default -> {
                    stack.push(Double.parseDouble(elem));
                }
            }
        }
        return stack.pop();
    }

    private static boolean isNumeric(String op) {
        return op.matches("-?\\d+");
    }
}

