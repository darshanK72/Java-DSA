import java.util.Stack;

public class j11BasicCalculatorII {

    public static void main(String args[]) {
        // Test cases
        String[] testExpressions = {
                "1 + 1", // Basic addition
                " 2-1 + 2 ", // Spaces and mixed operations
                "(1+(4+5+2)-3)+(6+8)", // Nested parentheses
                "2 + 3 + (4 + 5)", // Simple parentheses
                "1 + (2 + 3) - (4 - 5)", // Multiple parentheses
                "0", // Single number
                "-(2 + 3)", // Negative expression
                "(1)", // Single number in parentheses
                "1 + 2 + 3 + 4 + 5" // Multiple additions
        };

        for (String exp : testExpressions) {
            System.out.println("Expression: " + exp);
            System.out.println("Result: " + calculate(exp));
            System.out.println();
        }
    }

    public static int calculate(String s) {
        Stack<Character> operators = new Stack<>();
        Stack<Integer> operands = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ')
                continue;
            else if (c <= '9' && c >= '0') {
                int n = 0;
                while (i < s.length() && (c >= '0' && c <= '9')) {
                    n = n * 10 + (c - '0');
                    i++;
                    if (i < s.length())
                        c = s.charAt(i);
                }
                i--;
                operands.push(n);
            } else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    int second = operands.pop();
                    int first = operands.pop();
                    char op = operators.pop();
                    int res = operate(first, second, op);
                    operands.push(res);
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            int second = operands.pop();
            int first = operands.pop();
            char op = operators.pop();
            int res = operate(first, second, op);
            operands.push(res);
        }

        return operands.peek();
    }

    public static boolean isOperator(char c) {
        return (c == '-' || c == '+' || c == '*' || c == '/');
    }

    public static int precedence(char c) {
        if (c == '+' || c == '-')
            return 1;
        if (c == '*' || c == '/')
            return 2;
        return -1;
    }

    public static int operate(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                return -1;
        }
    }
}
