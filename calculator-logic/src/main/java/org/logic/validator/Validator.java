package org.logic.validator;

import org.logic.exceptions.IllegalSymbolException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {

    private static final Set<String> operators;
    private static final Set<String> braces;

    static {
        operators = Set.of("+", "-", "/", "*", "^");
        braces = Set.of("(", ")");
    }


    public static void validateOperatorsCount(String exp) throws IllegalSymbolException {
        String[] symbols = exp.split(" ");
        int numsCount = 0;
        int operatorsCount = 0;
        int bracesCount = 0;
        for(var s : symbols){
            if(s.matches("-?\\d*\\.?\\d+")){
                numsCount++;
            } else if (operators.contains(s)) {
                operatorsCount++;
            } else if (braces.contains(s)) {
                bracesCount++;
            }
        }
        if(bracesCount % 2 != 0){
            throw new IllegalSymbolException("Invalid braces count...");
        }
        if(numsCount - 1 != operatorsCount){
            throw new IllegalSymbolException("Invalid operators or numbers count...");
        }
    }

    public static void main(String[] ar){

    }
}
