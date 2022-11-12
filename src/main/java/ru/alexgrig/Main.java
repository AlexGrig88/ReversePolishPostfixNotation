package ru.alexgrig;

public class Main {

    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println(ReversePolscaNotation.of("(-1+3-(4+9-2)-12-34)*2*7+9-1/2"));
        System.out.println("================================================================");
    }
}
//1 3 4 * + 2 - 5 6 * 3 / - 28 125 5 / - 4 2 / 6 * 7 * * + 8 -