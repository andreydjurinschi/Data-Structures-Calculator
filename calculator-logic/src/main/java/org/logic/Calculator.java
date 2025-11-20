package org.logic;


import org.logic.utils.InputParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {

    private final char[] OPERATORS = {'+', '-', '*', '/', '^'};
    private final char[] BRACKETS = {'(', ')'};

    public void calculate(String expression){
        String normalized = InputParser.extract(expression);

        // List<Double> nums = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        Stack<Character> stack = new Stack<>();
        Stack<Double> numbers = new Stack<>();

        for(var c : normalized.toCharArray()){
            if(Character.isDigit(c)){
                builder.append(c);
        //        nums.add(Double.parseDouble(builder.toString()));
            }else{
                if(!builder.isEmpty()){
                    numbers.push(Double.parseDouble(builder.toString()));
                    builder.setLength(0);
                }else {
                    switch (c){
                        case '+' -> {
                            stack.push('+');
                            numbers.push(numbers.pop() + numbers.pop());
                        }
                        case '-' -> {
                            stack.push('-');
                            numbers.push(numbers.pop() - numbers.pop());
                        }
                        case '*' -> {
                            stack.push('*');
                            numbers.push(numbers.pop() * numbers.pop());
                        }
                        case '/' -> {
                            stack.push('/');
                            numbers.push(numbers.pop() / numbers.pop());
                        }
                        case '^' -> {
                            stack.push('^');
                            numbers.push(Math.pow(numbers.pop(), numbers.pop()));
                        }
                    }
                }
            }
        }

        System.out.println(numbers.peek());

        if(!builder.isEmpty()){
            numbers.push(Double.parseDouble(builder.toString()));
         //   builder.setLength(0);
        }

    }


    private boolean isOperator(char ch){
        for (var c : OPERATORS){
            if (c == ch){
                return true;
            }
        }
        return false;
    }

    private boolean isBracket(char ch){
        for (var c : BRACKETS){
            if (c == ch){
                return true;
            }
        }
        return false;
    }

    public char[] getOperators() {
        return OPERATORS;
    }

    public char[] getBRACKETS() {
        return BRACKETS;
    }

}

