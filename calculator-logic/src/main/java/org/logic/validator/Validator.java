package org.logic.validator;

import org.logic.exceptions.IllegalSymbolException;

import java.util.*;

public class Validator {

    private static final Set<String> OPERATORS;
    private static final Set<String> BRACES;

    static {
        OPERATORS = Set.of("+", "-", "/", "*", "^");
        BRACES = Set.of("(", ")");
    }

    public static void validate(String expression) throws IllegalSymbolException {
        if (!validateBraces(expression)) {
            throw new IllegalSymbolException("Mismatched parentheses");
        }
        if (!validateCharSequence(expression)) {
            throw new IllegalSymbolException("Only numbers or operators required!");
        }
        if (!validateOperatorsCount(expression)) {
            throw new IllegalSymbolException("Incorrect number of operators or operands");
        }
    }

    private static boolean validateCharSequence(String exp){
        return !exp.matches("[a-zA-Z]+");
    }


    private static boolean validateOperatorsCount(String exp){
        String[] symbols = exp.split(" ");
        int numsCount = 0;
        int operatorsCount = 0;
        for(var s : symbols){
            if(s.matches("-?\\d*\\.?\\d+")){
                numsCount++;
            } else if (OPERATORS.contains(s)) {
                operatorsCount++;
            }
        }

        return numsCount - 1 == operatorsCount;
    }

    private static boolean validateBraces(String exp) {
        Stack<String> temp = new Stack<>();
        String[] symbols = exp.split(" ");

        for(var s : symbols){
            if(s.equals("(")){
                temp.push(s);
            } else if(s.equals(")")){
                if(temp.isEmpty()) return false;
                temp.pop();
            }
        }
        return temp.isEmpty();
    }

}
