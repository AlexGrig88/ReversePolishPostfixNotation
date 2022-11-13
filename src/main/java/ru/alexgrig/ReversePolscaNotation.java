package ru.alexgrig;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;


//https://www.semestr.online/informatics/polish.php  - некорректная работа с 0
//http://primat.org/news/obratnaja_polskaja_zapis/2016-04-09-1181

public class ReversePolscaNotation {

    private String mathExpr;
    private Map<Character, Integer> priorityMap;
    {
        priorityMap = Map.of(
                '+', 1,
                '-', 1,
                '/', 2,
                '*', 2,
                '(', -1);
    }

    public ReversePolscaNotation(String mathExpr) {
        this.mathExpr = clearSpaces(mathExpr);
    }

    private String clearSpaces(String expression) {
        var symbols = expression.trim().split("");
        return Arrays.stream(symbols)
                .filter(s -> !s.isBlank())
                .collect(Collectors.joining(""));
    }

    private boolean isValidBrackets() {
        int balance = 0;
        for (char symbol : mathExpr.toCharArray()) {
            if (symbol == '(') {
                balance++;
            }
            if (symbol == ')') {
                balance--;
            }
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    // (8+20*5)/(1+3*2-4)
    // 1+3*4-2-5*6/3
    // infix to postfix
    public String get() {

        if (!isValidBrackets()) {
            throw new RuntimeException("Ошибка ввода! Проверьте количество и порядок скобок.");
        }

        var resultExpr = new StringBuilder();
        Deque<Character> stack = new LinkedList<>(); // стек для хранения операторов и открывающейся скобки
        int number = 0;         // начальное состояние - отсуствие числа, дополнительно вводится флаг ниже
        boolean isZero = false; // начальная установка : флаг, определяющий 0 - это число или отсутствие числа

        for (Character symbol : mathExpr.toCharArray()) {
            if (Character.isDigit(symbol)) {
                number *= 10;               // если у нас число больше чем одна цифра собираем его
                number += (symbol - '0');
                isZero = number == 0;
            } else {
                if (isZero || number != 0) {
                    resultExpr.append(number).append(" ");
                    number = 0;
                    isZero = false;
                }
                if (symbol == '(') {
                    stack.addLast(symbol);
                }
                else if (symbol == ')') {
                    while (stack.getLast() != '(') {
                        resultExpr.append(stack.removeLast()).append(" ");
                    }
                    stack.removeLast();
                }
                else if (priorityMap.containsKey(symbol)) {
                    while (!stack.isEmpty() && priorityMap.get(symbol) <= priorityMap.get(stack.getLast())) {  // если текущий оператор меньше или равен приоритету и стек непустой
                        resultExpr.append(stack.removeLast()).append(" ");  // тогда удаляем из стека и добавляем с строку
                    }
                    stack.addLast(symbol);                   // текущий операть помещаем в стек
                }
                else {
                    throw new RuntimeException("Ошибка ввода! Проверьте знаки, содержащиеся в математическом выражении." +
                            " Должны присутсвовать только положительные целые числа, знаки: +, -, *, /, (, )");
                }
            }

        }
        if (number != 0 || isZero) resultExpr.append(number).append(" ");

        while (!stack.isEmpty()) {
            resultExpr.append(stack.removeLast()).append(" ");
        }

        return resultExpr.toString();
    }
}
