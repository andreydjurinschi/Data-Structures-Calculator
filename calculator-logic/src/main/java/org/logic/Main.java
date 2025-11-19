package org.logic;


import org.logic.utils.InputParser;

public class Main {
    public static void main(String[] args) {
        String pt = "(11 + 18) * 20 - 2";
        InputParser.parser(pt);
    }
}