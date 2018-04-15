package com.group144.kuzmin;

public class ExpressionParser {
    private String expression;
    private int i;

    public ExpressionParser(String expression) {
        this.expression = expression;
        i = 0;
    }

    /**
     * Method search first token ( "(" or number)
     *
     * @return token
     */
    public String nextToken() {
        char currentChar;
        do {
            currentChar = next();
        } while (currentChar == ' ' || currentChar == ')');

        if (peek() == ' ' || currentChar == '(')
            return "" + currentChar;

        StringBuffer result = new StringBuffer("");
            do {
                result.append(currentChar);
                currentChar = next();
            } while(currentChar != ' ' && currentChar != ')');

        return result.toString();
    }

    /**
     * Method show next char in parsing string
     *
     * @return next char of string
     */
    private char peek() {
        if (i >= expression.length())
            return ' ';

        return expression.charAt(i);
    }

    /**
     * Method show next char in parsing string and move pointer forward
     *
     * @return next char of string
     */
    private char next() {
        if (i >= expression.length())
            return ' ';
        char result = expression.charAt(i);
        i++;

        return result;
    }
}
