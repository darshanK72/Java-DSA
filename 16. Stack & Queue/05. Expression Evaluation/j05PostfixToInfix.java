/**
 * Problem Statement:
 * 
 *     Convert a postfix expression to infix expression. In postfix notation,
 *     operators follow their operands (e.g., AB+), while in infix notation,
 *     operators are written between operands (e.g., A+B).
 * 
 * Input:
 *     - String containing a valid postfix expression with operators (+,-,*,/)
 *       and operands (uppercase letters A-Z)
 * 
 * Output:
 *     - The equivalent infix expression as a string with proper parentheses
 * 
 * Example:
 *     Input: "ABC/-AK/L-*"
 *     Output: "((A-(B/C))*((A/K)-L))"
 * 
 *     Explanation:
 *     The postfix expression is converted to infix notation while maintaining
 *     operator precedence using parentheses.
 */

import java.util.Stack;

public class j05PostfixToInfix {

    public static void main(String[] args) {
        String postfix = "ABC/-AK/L-*";
        String infix = postToInfix(postfix);
        System.out.println("Postfix: " + postfix);
        System.out.println("Infix: " + infix);
    }

    /**
     * Approach: Using Stack Data Structure
     * 
     * Intuition:
     * - Process postfix expression from left to right
     * - When encountering an operand, push it to stack as is
     * - When encountering an operator:
     *   1. Pop two operands (second then first)
     *   2. Create infix expression: (first operator second)
     *   3. Push resulting expression back to stack
     * - Use parentheses to maintain proper operator precedence
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
    public static String postToInfix(String exp) {
        Stack<String> operands = new Stack<>();

        for (char c : exp.toCharArray()) {
            if (isOperator(c)) {
                String second = operands.pop();
                String first = operands.pop();
                operands.push("(" + first + c + second + ")");
            } else {
                operands.push("" + c);
            }
        }
        return operands.peek();
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
