package group144.kuzmin;

public class StackCalculator {
    Parser parser;
    Integer value;

    public StackCalculator(String postfixExpression) {
        parser = new Parser(postfixExpression);
        value = null;
    }

    /**
     * Method calculate expression if you call it first time, and remember
     *
     * @return result of loaded expression
     */
    public int calculate() {
        if (value != null)
            return value;

        Stack<String> stack = new ArrayStack();
        while (parser.hasNext()) {
            String token = parser.nextToken();
            if (isSign(token)) {
                stack.push(operate(stack.pop(), stack.pop(), token));
            }
            else
                stack.push(token);
        }

        value = Integer.valueOf(stack.pop());
        return value;
    }

    /**
     * Method does mini calculate
     *
     * @param first first operand
     * @param second second operand
     * @param sign operator
     * @return result of calculation
     */
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

    /**
     * Method check is String sign-token
     *
     * @param token current token
     * @return true if token is sign and false otherwise
     */
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

        /**
         * Method returns next token
         *
         * @return next token
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
         * Method check has parser next token
         *
         * @return true if it has, and false otherwise
         */
        public boolean hasNext() {
            return i < expression.length;
        }

        /**
         * Method peek next token but don't move pointer forward
         *
         * @return current token
         */
        private char peek() {
            if (i >= expression.length)
                return ' ';

            return expression[i];
        }

        /** Method peek next token and move pointer forward
         *
         * @return current token
         */
        private char next() {
            if (i >= expression.length)
                return ' ';
            char result = expression[i];
            i++;

            return result;
        }
    }
}
