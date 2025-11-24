package core;



import exceptions.IllegalSymbolException;
import normalizer.Normalizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

public class RPNConverter {

    private static Map<String, Integer> priorityMap;
    private static final Logger logger = Logger.getLogger("RPN Converter");

    static {
        priorityMap = new HashMap<>();
        priorityMap.put("^", 1);
        priorityMap.put("*", 2);
        priorityMap.put("/", 2);
        priorityMap.put("+", 3);
        priorityMap.put("-", 3);
    }

    public static String convert(String expression) throws IllegalSymbolException {
        expression = Normalizer.normalize(expression);
        String[] elems = expression.split(" ");
        StringBuilder result = new StringBuilder();
        Stack<String> operatorStack = new Stack<>();

        for(var elem : elems){
            if(elem.matches("-?\\d*\\.?\\d+")){
                result.append(elem).append(" ");
            } else if (elem.equals("(")) {
                operatorStack.push(elem);
            } else if (elem.equals(")")){
                  while (!operatorStack.empty() && !operatorStack.peek().equals("(")){
                      result.append(operatorStack.pop()).append(" ");
                  }
                  operatorStack.pop();
            } else if (priorityMap.containsKey(elem)) {
                while(
                                !operatorStack.empty()
                                && !operatorStack.peek().equals("(")
                                && priorityMap.get(operatorStack.peek()) <= priorityMap.get(elem)
                ){
                    result.append(operatorStack.pop()).append(" ");
                }
                operatorStack.push(elem);
            }
        }
        while(!operatorStack.empty()){
            result.append(operatorStack.pop()).append(" ");
        }
        logger.info(result.toString());

        return result.toString();
    }

    public static void main(String[] args) throws IllegalSymbolException {
        System.out.println(RPNConverter.convert("55+10      - 12 * 99 / 7 - 53 + (28*9)"));
    }

}
