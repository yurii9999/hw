package group144.kuzmin;

public class Calculator {
    private Double aOperand;
    private Double bOperand;
    private String operator;
    private StringBuilder enteringOperand;

    public Calculator() {
        enteringOperand = new StringBuilder();
    }

    /**
     * Method reads token-digit and adds it to operand
     *
     * @param token next token
     * @return operand that it has now
     */
    public String readNextDigit(String token) {
        if (!isDigit(token))
            return "Not a digit";

        enteringOperand.append(token);
        return enteringOperand.toString();
    }

    /**
     * Method reads token-operator
     *
     * @param token token operator
     * @return next operand that it has now (empty string)
     */
    public String readOperator(String token) {
        if (!isOperator(token))
            return "Not an operator";

        if (enteringOperand.length() == 0 && aOperand == null)
            return "Nothing to calculate";

        if (aOperand == null) {
            aOperand = Double.valueOf(enteringOperand.toString());
            enteringOperand = new StringBuilder();
            operator = token;
            return enteringOperand.toString();
        }

        if (enteringOperand.length() == 0) {
            operator = token;
            return enteringOperand.toString();
        }

        String result = calculate();
        operator = token;
        return result;
    }

    /**
     * Method calculate expression a *operand* b = result
     * @return result
     */
    public String calculate() {
        if (!canCalculate())
            return enteringOperand.toString();

        if (enteringOperand.length() != 0) {
            if (aOperand == null) {
                aOperand = Double.valueOf(enteringOperand.toString());
                return aOperand.toString();
            }

            bOperand = Double.valueOf(enteringOperand.toString());
            enteringOperand = new StringBuilder();
        }

        switch (operator) {
            case "+":
                aOperand = aOperand + bOperand;
                break;
            case "-":
                aOperand = aOperand - bOperand;
                break;
            case "*":
                aOperand = aOperand * bOperand;
                break;
            case "/":
                if (bOperand.equals(0)) {
                    bOperand = null;
                    operator = null;
                    return "Divide by 0 is not allowed";
                }

                aOperand = aOperand / bOperand;
                break;
        }

        bOperand = null;
        operator = null;
        return aOperand.toString();
    }

    /**
     * method clears calculator's memory
     *
     * @return new current operator (= empty string)
     */
    public String clear() {
        aOperand = null;
        bOperand = null;
        operator = null;
        enteringOperand = new StringBuilder();

        return enteringOperand.toString();
    }

    private boolean canCalculate() {
        boolean result = false;
        result = result || ((aOperand != null) && (operator != null) && (enteringOperand.length() != 0));
        result = result || ((aOperand != null) && (operator != null) && (bOperand != null));
        return result;
    }

    private static boolean isOperator(String string) {
        return string.equals("+") || string.equals("-") || string.equals("*") || string.equals("/");
    }

    private static boolean isDigit(String string) {
        if (string.length() != 1)
            return false;

        return string.charAt(0) <= '9' && string.charAt(0) >= '0';
    }
}
