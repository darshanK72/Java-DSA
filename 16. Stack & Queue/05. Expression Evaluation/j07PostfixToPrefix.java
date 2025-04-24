/**
 * Problem Statement:
 * 
 *     Convert a postfix expression to prefix expression. In postfix notation,
 *     operators follow their operands (e.g., AB+), while in prefix notation,
 *     operators precede their operands (e.g., +AB).
 * 
 * Input:
 *     - String containing a valid postfix expression with operators (+,-,*,/)
 *       and operands (uppercase letters A-Z)
 * 
 * Output:
 *     - The equivalent prefix expression as a string
 * 
 * Example:
 *     Input: "ABCD/-AKL/+*"
 *     Output: "*-A/BC+/AKL"
 * 
 *     Explanation:
 *     The postfix expression is converted to prefix notation while maintaining
 *     operator precedence.
 */

import java.util.Stack;

public class j07PostfixToPrefix {

    public static void main(String[] args) {
        String postfix = "ABCD/-AKL/+*";
        String prefix = postfixToPrefix(postfix);
        System.out.println("Postfix: " + postfix);
        System.out.println("Prefix: " + prefix);
    }

    /**
     * Approach: Using Stack Data Structure
     * 
     * Intuition:
     * - Process postfix expression from left to right
     * - When encountering an operand, push it to stack as is
     * - When encountering an operator:
     *   1. Pop two operands (second then first)
     *   2. Create prefix expression: operator + first + second
     *   3. Push resulting expression back to stack
     * - Order matters: operator goes first, then operands
     * - Final result will be the only expression remaining in stack
     * 
     * Time Complexity:
     * - O(n) where n is the length of input string
     * - Each character is processed exactly once
     * - String concatenation operations are O(1)
     * 
     * Space Complexity:
     * - O(n) where n is the length of input string
     * - Stack stores partial expressions
     * - Additional space for string concatenation
     */
    public static String postfixToPrefix(String exp) {
        Stack<String> stack = new Stack<>();
        
        for (char c : exp.toCharArray()) {
            if (isOperator(c)) {
                String second = stack.pop();
                String first = stack.pop();
                String res = "" + c + first + second;
                stack.push(res);
            } else {
                stack.push("" + c);
            }
        }
        return stack.peek();
    }

    /**
     * Helper method to check if character is an operator
     * 
     * Time Complexity: O(1) - Simple boolean comparison
     * Space Complexity: O(1) - No extra space used
     */
    public static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }
}
