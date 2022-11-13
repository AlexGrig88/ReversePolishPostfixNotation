package ru.alexgrig;

import java.util.Deque;
import java.util.LinkedList;

public class CalculatorMathExpr {
    private ReversePolscaNotation postfixNotation;

    public CalculatorMathExpr(String mathExpr) {
        postfixNotation = new ReversePolscaNotation(mathExpr);
    }

    public double calculate() {
        String expr = postfixNotation.get();
        Deque<Double> stackNums = new LinkedList<>();
        for (String el : expr.split(" ")) {
            if (el.matches("\\d+")) {
                stackNums.addLast(Double.valueOf(el));
            }
            else {
                var result = solve(stackNums, el);
                stackNums.addLast(result);
            }
        }
        return stackNums.removeLast();
    }

    private double solve(Deque<Double> stackNums, String el) {
        double rightOperand = stackNums.removeLast();
        double leftOperand = stackNums.removeLast();
        switch (el) {
            case "+" :
                return leftOperand + rightOperand;
            case "-" :
                return leftOperand - rightOperand;
            case "*" :
                return leftOperand * rightOperand;
            case "/" :
                return leftOperand / rightOperand;
            default:
                throw new RuntimeException("No such operator!");
        }
    }
}
