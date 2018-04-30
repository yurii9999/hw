package group144.kuzmin;

public class Calculator {
    public static double calculate(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return b == 0 ? Double.POSITIVE_INFINITY * b * a : Double.valueOf(a) / b;
        }

        return Double.NaN;
    }
}
