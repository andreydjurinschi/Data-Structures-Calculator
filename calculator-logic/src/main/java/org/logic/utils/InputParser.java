/*
package org.logic.utils;

import java.util.ArrayList;
import java.util.List;



public class InputParser {
    private static final List<Character> OPERATIONS = List.of('+', '-', '*', '/', '^');
    private static final List<Character> BRACKETS = List.of('(', ')');
*/
/*    private static final List<Double> NUMBERS = List.of('+', '-', '*', '/');*//*


    public static String extract(String problem){
*/
/*
        String normalized = normalizeProblem(problem);
        StringBuilder result = new StringBuilder();

        List<Double> nums = new ArrayList<>();
        List<Character> operations = new ArrayList<>();
        List<Character> brackets = new ArrayList<>();

        char[] symbols = normalized.toCharArray();
        StringBuilder numsBuilder = new StringBuilder();

        for (var c : symbols) {
            if(Character.isDigit(c)){
                numsBuilder.append(c);
                result.append(c);
            }else{
                if(!numsBuilder.isEmpty()){
                    nums.add(Double.parseDouble(numsBuilder.toString()));
                    result.append(numsBuilder);
                    numsBuilder.setLength(0);
                }
                if(OPERATIONS.contains(c)){
                    operations.add(c);
                }
                else if(BRACKETS.contains(c)){
                    brackets.add(c);
                }
            }
        }
        if(!numsBuilder.isEmpty()){
            nums.add(Double.parseDouble(numsBuilder.toString()));
        }
            Response.printNums(nums);
            Response.printOperations(operations);
            Response.printBrackets(brackets);

            return normalized;
    }

    private static String normalizeProblem(String problem){
        StringBuilder normalized = new StringBuilder();
        for(int i =  0; i < problem.length(); i++){
            if(OPERATIONS.contains(problem.charAt(i)) || BRACKETS.contains(problem.charAt(i)) || Character.isDigit(problem.charAt(i))){
                normalized.append(problem.charAt(i));
            }else{
                if(!Character.isWhitespace(problem.charAt(i))){
                    System.out.println("Unrecognized character: " +  problem.charAt(i));
                }
            }
        }
        System.out.println("Normalized: " + normalized);
        return normalized.toString();
    }*//*


}
*/
