/*-
 * Problem Statement:
 * 
 *     Evaluate a postfix expression. In postfix notation (also known as Reverse
 *     Polish Notation), operators follow their operands. For example, "2 3 +" 
 *     means "2 + 3" = 5.
 * 
 * Input:
 *     - String array tokens containing operands (integers) and operators (+,-,*,/)
 * 
 * Output:
 *     - Integer result after evaluating the expression
 * 
 * Example:
 *     Input: ["2", "3", "+", "4", "*"]
 *     Output: 20
 * 
 *     Explanation:
 *     ((2 + 3) * 4) = 20
 */

import java.util.Stack;

public class j04PostfixEvaluation {

    public static void main(String[] args) {
        String[] tokens = { "2", "3", "+", "4", "*" };
        int result = evaluatePostfixExpression(tokens);
        System.out.println("Postfix Expression: " + String.join(" ", tokens));
        System.out.println("Result: " + result);
    }

    /*-
     * Approach: Using Stack Data Structure
     * 
     * Intuition:
     * - Use stack to store operands while processing expression
     * - When encountering an operator, pop two operands and apply operation
     * - Push result back to stack for further operations
     * - Final result will be the only element remaining in stack
     * 
     * Time Complexity:
     * - O(n) where n is the number of tokens
     * - Each token is processed exactly once
     * - Stack operations (push/pop) are O(1)
     * 
     * Space Complexity:
     * - O(n) where n is the number of tokens
     * - Stack can store at most n/2 numbers (in case of all operands)
     */
    public static int evaluatePostfixExpression(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (isOperator(s)) {
                int second = stack.pop();
                int first = stack.pop();
                int ans = operate(first, second, s);
                stack.push(ans);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.peek();
    }

    /*-
     * Helper method to check if token is an operator
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    /*-
     * Helper method to perform arithmetic operation
     * 
     * Note: Order of operands matters for - and / operations
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static int operate(int a, int b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        return -1;
    }
}
