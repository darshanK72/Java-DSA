/*-
 * Problem Statement:
 * 
 *     Convert a prefix expression to infix expression. In prefix notation,
 *     operators precede their operands (e.g., +AB), while in infix notation,
 *     operators are written between operands (e.g., A+B).
 * 
 * Input:
 *     - String containing a valid prefix expression with operators (+,-,*,/)
 *       and operands (uppercase letters A-Z)
 * 
 * Output:
 *     - The equivalent infix expression as a string with proper parentheses
 * 
 * Example:
 *     Input: "*-A/BC-/AKL"
 *     Output: "((A-(B/C))*((A/K)-L))"
 * 
 *     Explanation:
 *     The prefix expression is converted to infix notation while maintaining
 *     operator precedence using parentheses.
 */

import java.util.Stack;

public class j07PrefixToInfix {

    public static void main(String[] args) {
        String prefix = "*-A/BC-/AKL";
        String infix = prefixToInfixConversion(prefix);
        System.out.println("Prefix: " + prefix);
        System.out.println("Infix: " + infix);
    }

    /*-
     * Approach: Using Stack Data Structure
     * 
     * Intuition:
     * - Process prefix expression from right to left
     * - When encountering an operand, push it to stack as is
     * - When encountering an operator:
     *   1. Pop two operands (first and second)
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
    public static String prefixToInfixConversion(String exp) {
        Stack<String> operands = new Stack<>();

        // Process from right to left
        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);
            if (isOperator(c)) {
                String first = operands.pop();
                String second = operands.pop();
                // Add parentheses to maintain precedence
                operands.push("(" + first + c + second + ")");
            } else {
                operands.push("" + c);
            }
        }
        return operands.peek();
    }

    /*-
     * Helper method to check if character is an operator
     * 
     * Time Complexity: O(1) - Simple boolean comparison
     * Space Complexity: O(1) - No extra space used
     */
    public static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }
}
