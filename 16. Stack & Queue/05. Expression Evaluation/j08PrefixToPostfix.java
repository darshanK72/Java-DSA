/**
 * Problem Statement:
 * 
 *     Convert a prefix expression to postfix expression. In prefix notation,
 *     operators precede their operands (e.g., +AB), while in postfix notation,
 *     operators follow their operands (e.g., AB+).
 * 
 * Input:
 *     - String containing a valid prefix expression with operators (+,-,*,/)
 *       and operands (uppercase letters A-Z)
 * 
 * Output:
 *     - The equivalent postfix expression as a string
 * 
 * Example:
 *     Input: "*-A/BC-/AKL"
 *     Output: "ABC/-AK/L-*"
 * 
 *     Explanation:
 *     The prefix expression is converted to postfix notation while maintaining
 *     operator precedence.
 */

import java.util.Stack;

public class j08PrefixToPostfix {

    public static void main(String[] args) {
        String pre_exp = "*-A/BC-/AKL";
        String post_exp = preToPost(pre_exp);
        System.out.println("Prefix: " + pre_exp);
        System.out.println("Postfix: " + post_exp);
    }

    /**
     * Approach: Using Stack Data Structure
     * 
     * Intuition:
     * - Process prefix expression from right to left
     * - When encountering an operand, push it to stack as is
     * - When encountering an operator:
     *   1. Pop two operands (first then second)
     *   2. Create postfix expression: first + second + operator
     *   3. Push resulting expression back to stack
     * - Order matters: operands first, then operator
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
    static String preToPost(String pre_exp) {
        Stack<String> stack = new Stack<>();
        
        // Process from right to left for prefix expression
        for(int i = pre_exp.length() - 1; i >= 0; i--) {
            char c = pre_exp.charAt(i);
            if(isOperator(c)) {
                String first = stack.pop();
                String second = stack.pop();
                String res = first + second + c;
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
