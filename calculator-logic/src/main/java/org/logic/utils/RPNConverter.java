package org.logic.utils;


import org.logic.exceptions.IllegalSymbolException;
import org.logic.normalizer.Normalizer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

public class RPNConverter {

    private static final Map<String, Integer> priority;
    private static final Logger log = Logger.getLogger(RPNConverter.class.getName());


    static {
        priority = new HashMap<>();
        priority.put("+", 1);
        priority.put("-", 1);
        priority.put("*", 2);
        priority.put("/", 2);
        priority.put("^", 3);
    }

    public static String convert(String expression) throws IllegalSymbolException {
        log.info("RPN converter called, caught data: " + expression);

        expression = Normalizer.normalize(expression);
        String[] elems = expression.split(" ");
        Stack<String> operatorsStack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for(var elem : elems){
            if(elem.matches("-?\\d*\\.?\\d+")){
                result.append(elem).append(" ");
            } else if (elem.equals("(")) {
                operatorsStack.push(elem);
            } else if (elem.equals(")")) {
                while (!operatorsStack.empty() && !operatorsStack.peek().equals("(")){
                    result.append(operatorsStack.pop()).append(" ");
                }
                operatorsStack.pop();
            } else if (priority.containsKey(elem)) {
                while(!operatorsStack.empty() &&
                        !operatorsStack.peek().equals("(")
                        && priority.get(operatorsStack.peek()) >= priority.get(elem)
                ){
                    result.append(operatorsStack.pop()).append(" ");
                }
                operatorsStack.push(elem);
            }
        }
        while(!operatorsStack.empty()){
            result.append(operatorsStack.pop()).append(" ");
        }
        log.info("RPN converter result: " + result);
        return result.toString();
    }


















/*    static {
        priority = new HashMap<>();
        priority.put("+", 1);
        priority.put("-", 1);
        priority.put("*", 2);
        priority.put("/", 2);
        priority.put("(", 0);
        priority.put(")", 0);
    }*/



/*    public static String convert(String expression) {
        expression = Normalizer.normalize(expression);

        log.info("RPN convertor called, caught expression: " + expression);

        String[] elems = expression.split(" ");
        StringBuilder result = new StringBuilder();
        Stack<String> operatorsStack = new Stack<>();
        for(var s : elems){
            if(s.matches("-?\\d*\\.?\\d+")){
                result.append(s).append(" ");
            } else if (s.equals("(")) {
                operatorsStack.push(s);
            } else if (s.equals(")")) {
                while(!operatorsStack.isEmpty() && !operatorsStack.peek().equals("(")){
                    result.append(operatorsStack.pop()).append(" ");
                }
                operatorsStack.pop();
            } else if (priority.containsKey(s)) {
                while (
                        !operatorsStack.isEmpty() &&
                        priority.get(operatorsStack.peek()) >= priority.get(s)
                ) {
                    result.append(operatorsStack.pop()).append(" ");
                }
                operatorsStack.push(s);
            }
        }
        while (!operatorsStack.isEmpty()) {
            result.append(operatorsStack.pop()).append(" ");
        }

        log.info("RPN convertor result: " + result);
        return result.toString().trim();
    }*/

}
