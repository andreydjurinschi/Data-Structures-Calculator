package org.logic.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    private static final List<Character> operations = List.of('+', '-', '*', '/');
    private static final List<Character> brackets = List.of('(', ')');

    public static void parser(String problem){
        char[] symbols = problem.trim().toCharArray();
        List<Double> digits = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        List<Character> brackets = new ArrayList<>();
        List<Character> other = new  ArrayList<>();

        StringBuilder numberBuffer = new StringBuilder();

        for (int i = 0; i < symbols.length; i++) {
            char s = symbols[i];

            if (Character.isDigit(s) || s == '.') {
                numberBuffer.append(s);
            }
            else {

                if (numberBuffer.length() > 0) {
                    digits.add(Double.parseDouble(numberBuffer.toString()));
                    numberBuffer.setLength(0);
                }

                if (operations.contains(s)) {
                    operators.add(s);
                } else if (InputParser.brackets.contains(s)) {
                    brackets.add(s);
                } else if (!Character.isWhitespace(s)) {
                    other.add(s);
                }
            }
        }

        if (!numberBuffer.isEmpty()) {
            digits.add(Double.parseDouble(numberBuffer.toString()));
        }

        Response.printOutput(problem);
        Response.printNums(digits);
        Response.printOperations(operators);
        Response.printBrackets(brackets);

        if (!other.isEmpty()){
            System.out.print("Unknown chars: ");
            other.forEach(o -> System.out.print(o + " "));
        }
    }



}
