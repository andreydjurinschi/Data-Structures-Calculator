package org.logic.utils;

import org.logic.exceptions.IllegalSymbolException;

import java.util.Stack;
import java.util.logging.Logger;

public class Calculator {
    private static final Logger log = Logger.getLogger(Calculator.class.getName());

    public static double calculate(String expression) throws IllegalSymbolException {
        expression = RPNConverter.convert(expression);

        log.info("Calculator called, caught data: " + expression);

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
                    double result = prev / sec;

                    if(Double.isInfinite(result) || Double.isNaN(result)){
                        throw new ArithmeticException("Dividing by zero is very bad");
                    }

                    stack.push(result);
                }
                case "^" -> {
                    double sec = stack.pop();
                    double prev = stack.pop();
                    stack.push(Math.pow(prev, sec));
                }

                default -> stack.push(Double.parseDouble(elem));
            }
        }
        log.info("Calculator result: " + stack.peek());
        return stack.pop();
    }

}

