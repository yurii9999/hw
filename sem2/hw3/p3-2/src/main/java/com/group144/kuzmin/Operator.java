package com.group144.kuzmin;

public class Operator extends Node {

    public Operator(ExpressionParser parser) {
        String token = parser.nextToken();
        data = token;

        token = parser.nextToken();
        if (token.equals("("))
            left = new Operator(parser);
        else
            left = new Operand(token);

        token = parser.nextToken();
        if (token.equals("("))
            right = new Operator(parser);
        else
            right = new Operand(token);
    }

    @Override
    public String toString() {
        return ("(" + data + " " + left + " " + right + ")");
    }

    /**
     * Method calculate node recurrently
     *
     * @return result of calculate
     */
    @Override
    public int calculate() {
        switch (data) {
            case "+":
                return left.calculate() + right.calculate();
            case "-":
                return left.calculate() - right.calculate();
            case "*":
                return left.calculate() * right.calculate();
            case "/":
                return left.calculate() / right.calculate();
        }

        return -1;
    }
}
