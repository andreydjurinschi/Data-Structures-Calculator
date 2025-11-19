package org.logic.utils;

import java.util.Arrays;
import java.util.List;

public class Response {
    public static void printOutput(String message){
        System.out.println(message);
    }

    public static void printNums(List<Double> nums){
        System.out.print("Nums: ");
        nums.forEach(num -> {
            System.out.print(num + " ");
        });
    }

    public static void printOperations(List<Character> operations){
        System.out.print("Operations: ");
        operations.forEach(num -> {
            System.out.print(num + " ");
        });
    }

    public static void printBrackets(List<Character> brackets){
        System.out.print("Brackets: ");
        brackets.forEach(num -> {
            System.out.print(num + " ");
        });
    }




}
