package group144.kuzmin;

public class ExpressionReader {
    StringBuilder expression;

    public ExpressionReader() {
        expression = new StringBuilder();
    }

    /**
     * Method add char at the end of expression, it checks double sign and sing at the begin of expression
     * @param c readed char
     */
    public void readNext(char c) {
        if (isSign(c)) {
            if (expression.length() == 0 || isSign(expression.charAt(expression.length() - 1)))
                return;

            expression.append(c);
        }

        if (Character.isDigit(c))
            expression.append(c);
    }

    /**
     * Method returns what he already read
     * @return current expression
     */
    public String getExpression() {
        return expression.toString();
    }

    /** Method clear expression */
    public void clear() {
        expression = new StringBuilder();
    }

    /** Method removes last char */
    public void backspase() {
        if (expression.length() == 0)
            return;

        expression.deleteCharAt(expression.length() - 1);
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
}
