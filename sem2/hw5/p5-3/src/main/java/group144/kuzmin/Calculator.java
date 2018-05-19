package group144.kuzmin;

public class Calculator {
    /**
     * Calculate expression, don't check is expression correct, don't know about brackets
     *
     * @param expression correct expression you want to calculate
     * @return
     */
    public static String calculate(String expression) {
        String[] tokens = split(expression);

        for (int i = 0; i < tokens.length; i++) {
            if (isFirstPriorityOperation(tokens[i])) {
                tokens[i + 1] = String.valueOf(calculate(tokens[i - 1], tokens[i + 1], tokens[i]));
                tokens[i - 1] = null;
                tokens[i] = null;
            }
        }

        tokens = removeNulls(tokens);

        for (int i = 0; i < tokens.length; i++) {
            if (isSecondPriorityOperation(tokens[i])) {
                tokens[i + 1] = String.valueOf(calculate(tokens[i - 1], tokens[i + 1], tokens[i]));
                tokens[i - 1] = null;
                tokens[i] = null;
            }
        }

        return tokens[tokens.length - 1];
    }

    /**
     * Method remove all nulls' tokens from Tokens array
     *
     * @param tokens array that you want to clear
     * @return tokens array without nulls' elements
     */
    private static String[] removeNulls(String[] tokens) {
        int amountNulls = 0;
        for (int i = 0; i < tokens.length; i++)
            if (tokens[i] == null)
                amountNulls++;

        final int newLength = tokens.length - amountNulls;
        String[] newTokens = new String[newLength];

        int j = 0;
        for (int i = 0; i < newLength; i++) {
            while (tokens[j] == null)
            j++;

            newTokens[i] = tokens[j];
            j++;
        }

        return newTokens;
    }

    /**
     * Method split expression to tokens
     *
     * @param expression correct expression
     * @return array of tokens of this expression
     */
    private static String[] split(String expression) {
        int amountSigns = 0;
        for (int i = 0; i < expression.length(); i++)
            if (isSign(expression.charAt(i)))
                amountSigns++;

        final int amountTokens = amountSigns * 2 + 1;
        String[] tokens = new String[amountTokens];
        int tokensPointer = 0;

        StringBuilder currentToken = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (isSign(expression.charAt(i))) {
                tokens[tokensPointer] = currentToken.toString();
                tokens[tokensPointer + 1] = String.valueOf(expression.charAt(i));
                tokensPointer += 2;

                currentToken = new StringBuilder();
            }
            else
                currentToken.append(expression.charAt(i));
        }

        tokens[tokensPointer] = currentToken.toString();

        return tokens;
    }

    /**
     * Method check is char is sign
     *
     * @param c char you want to check
     * @return true if it's sign, false otherwise
     */
    private static boolean isSign(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * Method check is tokens first priority operation(* /)
     * @param token current token
     * @return true if it's, false otherwise
     */
    private static boolean isFirstPriorityOperation(String token) {
        return token.equals("*") || token.equals("/");
    }

    /**
     * Method check is tokens second priority operation(+ -)
     * @param token current token
     * @return true if it's, false otherwise
     */
    private static boolean isSecondPriorityOperation(String token) {
        return token.equals("+") || token.equals("-");
    }

    /**
     * Method makes minicalculate, don't check situation st/0
     *
     * @param firstOperand a
     * @param secondOperand b
     * @param operator sign
     * @return a 'sign' b
     */
    private static String calculate(String firstOperand, String secondOperand, String operator) {
        final double a = Double.valueOf(firstOperand);
        final double b = Double.valueOf(secondOperand);

        switch (operator) {
            case "+":
                return String.valueOf(a + b);
            case "-":
                return String.valueOf(a - b);
            case "*":
                return String.valueOf(a * b);
            case "/":
                return String.valueOf(a / b);
        }

        return String.valueOf(Double.NaN);
    }
}
