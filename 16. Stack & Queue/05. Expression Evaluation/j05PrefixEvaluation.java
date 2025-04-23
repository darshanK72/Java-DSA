/*-
 * Problem Statement:
 * 
 *     Evaluate a prefix expression. In prefix notation (also known as Polish
 *     Notation), operators precede their operands. For example, "+ 2 3" 
 *     means "2 + 3" = 5.
 * 
 * Input:
 *     - String array tokens containing operands (integers) and operators (+,-,*,/)
 * 
 * Output:
 *     - Integer result after evaluating the expression
 * 
 * Example:
 *     Input: ["-", "+", "7", "*", "4", "5", "+", "2", "0"]
 *     Output: 25
 * 
 *     Explanation:
 *     The expression evaluates to: (7 + (4 * 5)) - (2 + 0) = (7 + 20) - 2 = 25
 */

import java.util.Stack;

public class j05PrefixEvaluation {

    public static void main(String[] args) {
        String[] tokens = { "-", "+", "7", "*", "4", "5", "+", "2", "0" };
        int result = evaluatePrefixExpression(tokens);
        System.out.println("Prefix Expression: " + String.join(" ", tokens));
        System.out.println("Result: " + result);
    }

    /*-
     * Approach: Using Stack Data Structure
     * 
     * Intuition:
     * - Process prefix expression from right to left (unlike postfix which goes left to right)
     * - When we encounter an operand, push it onto stack
     * - When we encounter an operator:
     *   1. Pop first operand (will be left operand)
     *   2. Pop second operand (will be right operand)
     *   3. Perform operation and push result back
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
    public static int evaluatePrefixExpression(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        // Process from right to left for prefix expression
        for (int i = tokens.length - 1; i >= 0; i--) {
            String s = tokens[i];
            if (isOperator(s)) {
                int first = stack.pop();   // Left operand
                int second = stack.pop();  // Right operand
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
     * Time Complexity: O(1) - Simple string comparison
     * Space Complexity: O(1) - No extra space used
     */
    public static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    /*-
     * Helper method to perform arithmetic operation
     * 
     * Note: Order of operands matters for - and / operations
     * first is treated as left operand and second as right operand
     * 
     * Time Complexity: O(1) - Simple switch case operation
     * Space Complexity: O(1) - No extra space used
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
            default:
                return -1;  // Invalid operator
        }
    }
}
