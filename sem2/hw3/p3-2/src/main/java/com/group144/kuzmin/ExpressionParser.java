package com.group144.kuzmin;

public class ExpressionParser {
    private String expression;
    private int i;

    public ExpressionParser(String expression) {
        this.expression = expression;
        i = 0;
    }

    public String nextToken() {
        char currentChar;
        do {
            currentChar = next();
        } while (currentChar == ' ' || currentChar == ')');

        if (peek() == ' ' || currentChar == '(')
            return "" + currentChar;

        String result = "";
            do {
                result += currentChar;
                currentChar = next();
            } while(currentChar != ' ' && currentChar != ')');

        return result;
    }

    private char peek() {
        if (i >= expression.length())
            return ' ';

        return expression.charAt(i);
    }

    private char next() {
        if (i >= expression.length())
            return ' ';
        char result = expression.charAt(i);
        i++;

        return result;
    }

    private static class Checker {
        public static boolean isSign(char c) {
            return c == '+' || c == '-' || c == '*' || c == '/';
        }

        public static boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }
    }
}
