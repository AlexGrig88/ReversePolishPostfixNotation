package ru.alexgrig;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;


//https://www.semestr.online/informatics/polish.php

public class ReversePolscaNotation {

    private static Map<Character, Integer> priorityMap;

    static {
        priorityMap = Map.of(
                '+', 1,
                '-', 1,
                '/', 2,
                '*', 2,
                '(', -1);
    }

    private ReversePolscaNotation() {

    }

    // (8+20*5)/(1+3*2-4)
    // 1+3*4-2-5*6/3
    public static String of(String mathExpr) {

        var resultExpr = new StringBuilder();
        Deque<Character> stack = new LinkedList<>(); // стек для хранения операторов и скобок

        int number = 0;

        for (Character symbol : mathExpr.toCharArray()) {
            if (Character.isDigit(symbol)) {
                number *= 10;
                number += (symbol - '0');
            } else {
                if (number != 0) {
                    resultExpr.append(number).append(",");
                }
                number = 0;
                if (symbol == '(') {
                    stack.addLast(symbol);
                }
                else if (symbol == ')') {
                    while (stack.getLast() != '(') {
                        resultExpr.append(stack.removeLast()).append(",");
                    }
                    stack.removeLast();
                }
                else if (priorityMap.containsKey(symbol)) {  // если текущий оператор меньше или равен приоритету и стек непустой
                    while (!stack.isEmpty() && priorityMap.get(symbol) <= priorityMap.get(stack.getLast())) {
                        resultExpr.append(stack.removeLast()).append(",");  // тогда удаляем из стека и добавляем с строку
                    }
                    stack.addLast(symbol);                   // текущий операть помещаем в стек
                }
                else {
                    throw new RuntimeException("Ошибка ввода!");
                }
            }

        }
        if (number != 0) resultExpr.append(number).append(",");

        while (!stack.isEmpty()) {
            resultExpr.append(stack.removeLast()).append(",");
        }

        return resultExpr.toString();
    }
}
