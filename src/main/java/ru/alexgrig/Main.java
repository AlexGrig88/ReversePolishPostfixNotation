package ru.alexgrig;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String mathExpr = "(6 + 8)*(23-(3 + 5)))";

        System.out.println("Введите математическое выражение [0-9,+,-,*,-,(,)] : ");
        var scanner = new Scanner(System.in);
        mathExpr = scanner.nextLine();

        ReversePolscaNotation polscaNotation = new ReversePolscaNotation(mathExpr);
        System.out.println("================================================================");
        System.out.println(polscaNotation.get());
        System.out.println("================================================================");
        CalculatorMathExpr calculator = new CalculatorMathExpr(mathExpr);
        System.out.println("result  = " + calculator.calculate());

    }
}
//1 3 4 * + 2 - 5 6 * 3 / - 28 125 5 / - 4 2 / 6 * 7 * * + 8 -