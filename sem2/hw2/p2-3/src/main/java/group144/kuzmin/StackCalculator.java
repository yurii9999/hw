package group144.kuzmin;

public class StackCalculator {
    Parser parser;

    public StackCalculator(String postfixExpression) {
        parser = new Parser(postfixExpression);
    }

    public int calculate() {
        Stack<String> stack = new ArrayStack();
        while (parser.hasNext()) {
            String token = parser.nextToken();
            if (isSign(token)) {
                stack.push(operate(stack.pop(), stack.pop(), token));
            }
            else
                stack.push(token);
        }

        return Integer.valueOf(stack.pop());
    }

    private static String operate(String first, String second, String sign) {
        int a = Integer.valueOf(first);
        int b = Integer.valueOf(second);

        switch (sign) {
            case "+":
                return String.valueOf(a + b);
            case "-":
                return String.valueOf(a - b);
            case "*":
                return String.valueOf(a * b);
            case "/":
                return String.valueOf(a / b);
        }

        return "0";
    }

    private static boolean isSign(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private class Parser {
        private char[] expression;
        private int i;

        public Parser(String postfixExpression) {
            expression = postfixExpression.toCharArray();
            i = 0;
        }

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

        public boolean hasNext() {
            return i < expression.length;
        }

        private char peek() {
            if (i >= expression.length)
                return ' ';

            return expression[i];
        }

        private char next() {
            if (i >= expression.length)
                return ' ';
            char result = expression[i];
            i++;

            return result;
        }
    }
}
