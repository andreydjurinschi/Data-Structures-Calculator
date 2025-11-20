package org.logic;


public class Calculator {

    private final char[] OPERATORS = {'+', '-', '*', '/', '^'};
    private final char[] BRACKETS = {'(', ')'};

    public static void calculate(String expression){

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

