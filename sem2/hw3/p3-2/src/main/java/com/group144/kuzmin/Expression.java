package com.group144.kuzmin;

public class Expression {
    private Node root;

    public Expression(String expression) {
        expression = Convertor.convert(expression);
        ExpressionParser parser = new ExpressionParser(expression);
        root = new Operator(parser);
    }

    /** Method calculates expression */
    public int calculate() {
        return root.calculate();
    }

    /** Method converts expression tree to string with relationships */
    @Override
    public String toString() {
        return root.toString();
    }

    /** Method build expression tree by string with expression */
    private static class Convertor {
        public static String convert(String string) {
            if (string.charAt(0) == '(')
                return string.substring(1, string.length() - 1);

            return string;
        }
    }
}
