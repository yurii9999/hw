package com.group144.kuzmin;

public class Expression {
    private Node root;

    public Expression(String expression) {
        expression = Convertor.convert(expression);
        ExpressionParser parser = new ExpressionParser(expression);
        root = new Operator(parser);
    }
    public int calculate() {
        return root.calculate();
    }

    public void print() {
        root.print();
    }

    private static class Convertor {
        public static String convert(String string) {
            if (string.charAt(0) == '(')
                return string.substring(1, string.length() - 1);

            return string;
        }
    }
}
