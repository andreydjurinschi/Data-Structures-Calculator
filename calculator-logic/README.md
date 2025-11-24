
## Task
_______________
Duration: <1 week

Goal: To be able to use stack to evaluate a compound expression

Task:
Use the stack to evaluate arithmetic expressions.
The program will be tested with compound expressions with multiple operators and parentheses. For simplicity assume that the operands are integers, and the operators are of four types: +, -, *, /.
Catch all the errors that you can find.

input: (11 + 18) * 20 - 2
output: 578

Evaluation: Code review with questions. Explain the algorithm step by step.
_______________

## Solving

## Step 1. [Normalizer Class](src/main/java/org/logic/normalizer/Normalizer.java)

firstly we need to normalize and validate our expression. the input of user must contain a lot of unnecessary symbols, that we need to validate.
Normalizer Class contain only one static method, that`s:

1) we create a local variable that`s will stock the result of expression. And two local instances of Patter class and Matcher class

`regex` - `"-?\\d*\\.?\\d+|[()+*/^\\-]"`:

`-?` - 0 or 1 occurrence in expression of `-`

-?`\\d*` - zero or more occurrences of preceding element `d` (example: -8, -9, 10, 155423, etc.)

-?\\d*\\`.?\\d+` - zero or more occurrences of preceding element `.`, and one or more occurrences of (`d`) (example: -8.2, -9, 10.1323, 155423.0, etc.)

-?\\d*\\.?\\d+`|[()+*/^\\-]` - `|` - pipe's role is to separate different patterns in one regex str. `[...]` defines a character class ( a single character of the followed list `[()+*/^\\-]`)
___

matcher class connect the pattern and expression

```java
    public static String normalize(String expression) throws IllegalSymbolException {
    log.info("Normalizer called, caught data: " + expression);
    StringBuilder result = new StringBuilder();
    Pattern pattern = Pattern.compile("-?\\d*\\.?\\d+|[()+*/^\\-]");
    Matcher matcher = pattern.matcher(expression);
}
```

if the following str element corresponds the pattern, we add it in result string,
after that we need to validate the result, to prepare it for the next step. [Validator class](src/main/java/org/logic/validator/Validator.java)  

```java
        while(matcher.find()){
            result.append(matcher.group()).append(" ");
        }

        log.info("Normalizer finished it`s work, result: " + result);
        Validator.validate(result.toString());

        return result.toString();
```

### WORK EXAMPLE 

```java
    public static void main(String[] args) throws IllegalSymbolException {
    Normalizer.normalize("5565 - - 5");
    Normalizer.normalize("50 - 5+8 +");
    Normalizer.normalize("(27 * 88) -                   99 + 23 - (120/80)");
}
```

```bash
INFO: Normalizer called, caught data: 5565 - - 5
INFO: Normalizer finished it`s work, result: 5565 - - 5 
________________________________________________________

INFO: Normalizer called, caught data: 50 - 5+8 +
Exception in thread "main" org.logic.exceptions.IllegalSymbolException: Incorrect number of operators or operands
	at org.logic.validator.Validator.validate(Validator.java:25)
	at org.logic.normalizer.Normalizer.normalize(Normalizer.java:30)
	at org.logic.normalizer.Normalizer.main(Normalizer.java:36) 
____________________________________________________________
INFO: Normalizer called, caught data: (27 * 88) -                 99 + 23 - (120/80)
INFO: Normalizer finished it`s work, result: ( 27 * 88 ) - 99 + 23 - ( 120 / 80 ) 
```

## Step 2. [RPN converter class](src/main/java/org/logic/utils/RPNConverter.java)

RPN calculators operate based on a stack data structure. The stack holds operands and intermediate results as the calculator processes the input expression.

because of that after normalising I need to convert normalised expression into an RPN expression.

firstly, I created a priority map for every operation ()

```java
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
```

An example of an RPN converter is the Shunting-Yard algorithm, which converts an infix expression (like (3+4)) to RPN (like (3 4 +)) using a stack and an output queue. A simple example is converting the infix expression ((3+4)*5) to its RPN equivalent, which is (3 4 + 5 *). The converter uses a stack to temporarily hold operators and a queue to build the final RPN expression

Example

![](https://i.imgur.com/ZOr2Xoa.png)

_____

firstly, lets normalize our expression, split it into String array by the space, and prepare the operators stack and result, that will stock the converted result 

```java
public static String convert(String expression) throws IllegalSymbolException {
        expression = Normalizer.normalize(expression);
        String[] elems = expression.split(" ");
        StringBuilder result = new StringBuilder();
        Stack<String> operatorStack = new Stack<>();
    }
```

after that I iterate through every element in array, and:

____
if this elem is a number with or without floating point, this element needs to be added directly in result expression

```java
 for(var elem : elems){
            if(elem.matches("-?\\d*\\.?\\d+")){
                result.append(elem).append(" ");
```
____

if this element is equal with `(`, I just push him in operator stack

```java

else if (elem.equals("(")) {
                operatorStack.push(elem);
} 

```
____

if this element is equal with `)`, I iterate through every element in stack till it will not be empty or peeked element will not be equal with `)`.
I add every element in result and pop the `(` at the end

```java
 else if (elem.equals(")")){
                  while (!operatorStack.empty() && !operatorStack.peek().equals("(")){
                      result.append(operatorStack.pop()).append(" ");
                  }
                  operatorStack.pop();
```
____

if this element is the key in priority map (+,-,*,/,^),  iterate through every element in stack till it will not be empty and peeked element will not be equal with `)`,
and the value of this key needs to be lower (highest priority) than the operator in stack, after that I add all this elems in result String and push the current symbol in operator stack  

```java

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
```

____

after that till stack will not be empty I just add all its elements in result String

```java
        while(!operatorStack.empty()){
            result.append(operatorStack.pop()).append(" ");
        }
        logger.info(result.toString());
        return result.toString();
```
____

### full code:

```java
package org.logic.utils;


import org.logic.exceptions.IllegalSymbolException;
import org.logic.normalizer.Normalizer;

import java.util.Arrays;
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
}

```

### Work example:

```java
    public static void main(String[] args) throws IllegalSymbolException {
        System.out.println(RPNConverter.convert("55+10 - 12 * 99 / 7 - 53 + (28*9)"));
    }
```

```bash
Nov 24, 2025 11:39:12 AM org.logic.normalizer.Normalizer normalize
INFO: Normalizer called, caught data: 55+10      - 12 * 99 / 7 - 53 + (28*9)
Nov 24, 2025 11:39:12 AM org.logic.normalizer.Normalizer normalize
INFO: Normalizer finished it`s work, result: 55 + 10 - 12 * 99 / 7 - 53 + ( 28 * 9 ) 
Nov 24, 2025 11:39:12 AM org.logic.utils.RPNConverter convert
INFO: 55 10 + 12 99 * 7 / - 53 - 28 9 * + 
55 10 + 12 99 * 7 / - 53 - 28 9 * + 
```

lets check if `55 10 + 12 99 * 7 / - 53 - 28 9 * +` is converted correctly

![](https://i.imgur.com/vCKRq9g.png)

## Step 3. [Calculator class](src/main/java/org/logic/utils/Calculator.java)

Here after converting into the RPN notation
by this notation every operation needs to be executed with two popped numbers from stack, and adding the result of operation back to stack
the result is the last popped element from stack
```java
package org.logic.utils;

import org.logic.exceptions.IllegalSymbolException;

import java.util.Stack;
import java.util.logging.Logger;

public class Calculator {
    private static final Logger log = Logger.getLogger(Calculator.class.getName());

    public static double calculate(String expression) throws IllegalSymbolException {
        expression = RPNConverter.convert(expression);

        log.info("Calculator called, caught data: " + expression);

        String[] elems =  expression.split(" ");

        Stack<Double> stack = new Stack<>();

        for (String elem : elems) {
            switch (elem) {
                case "+" -> {
                    stack.push(stack.pop() + stack.pop());
                }
                case "-" -> {
                    double sec =  stack.pop();
                    double prev = stack.pop();
                    stack.push(prev - sec);
                }
                case "*" -> {
                    stack.push(stack.pop() * stack.pop());
                }
                case "/" -> {
                    double sec =  stack.pop();
                    double prev = stack.pop();
                    double result = prev / sec;

                    if(Double.isInfinite(result) || Double.isNaN(result)){
                        throw new ArithmeticException("Dividing by zero is very bad");
                    }
                }
                case "^" -> {
                    double sec = stack.pop();
                    double prev = stack.pop();
                    stack.push(Math.pow(prev, sec));
                }

                default -> stack.push(Double.parseDouble(elem));
            }
        }
        log.info("Calculator result: " + stack.peek());
        return stack.pop();
    }
}
```

## TESTS

```java
    public static void main(String[] args) {

        String expression = "(11 + 18) * 20 - 2";
        try {
            System.out.println(Calculator.calculate(expression));
        } catch (IllegalSymbolException e) {
            System.out.println(e.getMessage());
        }

    }
```

```bash
    Nov 24, 2025 11:45:22 AM org.logic.normalizer.Normalizer normalize
    INFO: Normalizer called, caught data: (11 + 18) * 20 - 2
    Nov 24, 2025 11:45:22 AM org.logic.normalizer.Normalizer normalize
    INFO: Normalizer finished it`s work, result: ( 11 + 18 ) * 20 - 2 
    Nov 24, 2025 11:45:22 AM org.logic.utils.RPNConverter convert
    INFO: 11 18 + 20 * 2 - 
    Nov 24, 2025 11:45:22 AM org.logic.utils.Calculator calculate
    INFO: Calculator called, caught data: 11 18 + 20 * 2 - 
    Nov 24, 2025 11:45:22 AM org.logic.utils.Calculator calculate
    INFO: Calculator result: 578.0
    578.0
```

```java
package org.logic;

import org.logic.exceptions.IllegalSymbolException;
import org.logic.utils.Calculator;

public class Main {
    public static void main(String[] args) {

        String expression = "50 * 20 / ( 53 - 33) + 12 - 5 * 22 - 500";
        try {
            System.out.println(Calculator.calculate(expression));
        } catch (IllegalSymbolException e) {
            System.out.println(e.getMessage());
        }

    }
}
```

```bash
Nov 24, 2025 11:51:28 AM org.logic.normalizer.Normalizer normalize
INFO: Normalizer called, caught data: 50 * 20 / ( 53 - 33) + 12 - 5 * 22 - 500
Nov 24, 2025 11:51:28 AM org.logic.normalizer.Normalizer normalize
INFO: Normalizer finished it`s work, result: 50 * 20 / ( 53 - 33 ) + 12 - 5 * 22 - 500 
Nov 24, 2025 11:51:28 AM org.logic.utils.RPNConverter convert
INFO: 50 20 * 53 33 - / 12 + 5 22 * - 500 - 
Nov 24, 2025 11:51:28 AM org.logic.utils.Calculator calculate
INFO: Calculator called, caught data: 50 20 * 53 33 - / 12 + 5 22 * - 500 - 
Nov 24, 2025 11:51:28 AM org.logic.utils.Calculator calculate
INFO: Calculator result: -548.0
-548.0
```