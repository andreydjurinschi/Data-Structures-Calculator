package org.logic.utils;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RPNConverter {

    private static final Map<String, Integer> priority;

    static {
        priority = new HashMap<>();
        priority.put("+", 1);
        priority.put("-", 1);
        priority.put("*", 2);
        priority.put("/", 2);
        priority.put("(", 0);
        priority.put(")", 0);
    }

    public Map<String, Integer> getPriority() {
        return priority;
    }


    public static String convert(String expression) {
        String[] elems = expression.split(" ");
        StringBuilder result = new StringBuilder();
        Stack<String> operatorsStack = new Stack<>();

        for(var s : elems){
            if(s.matches("\\d+")){
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

        return result.toString().trim();
    }

/*    public static void main(String[] args) {
        String expression = "33 + 4 * 2";
        System.out.println(convert(expression));
    }*/
}
