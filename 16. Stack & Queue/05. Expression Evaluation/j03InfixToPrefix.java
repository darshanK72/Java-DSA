/*-
 * Problem Statement:
 * 
 *     Convert an infix expression to prefix expression. In infix expressions, 
 *     operators are written between operands (e.g., a+b), while in prefix 
 *     expressions, operators are written before operands (e.g., +ab).
 * 
 * Input:
 *     - A string containing a valid infix expression with operators (+,-,*,/,^),
 *       operands (a-z, A-Z, 0-9) and parentheses.
 * 
 * Output:
 *     - The equivalent prefix expression as a string.
 * 
 * Example:
 *     Input: "(a-b/c)*(a/k-l)"
 *     Output: "*-a/bc-/akl"
 * 
 *     Explanation:
 *     The infix expression is converted to prefix notation while maintaining
 *     operator precedence and parentheses rules.
 */

import java.util.Stack;

public class j03InfixToPrefix {

    public static void main(String[] args) {
        String infix = "(a-b/c)*(a/k-l)";
        String prefix = infixToPrefix(infix);
        System.out.println("Infix: " + infix);
        System.out.println("Prefix: " + prefix);
    }

    /*-
     * Approach: Using Stack and String Manipulation
     * 
     * Intuition:
     * - Converting infix to prefix directly is complex, but we can break it down:
     *   1. Reverse the string
     *   2. Swap parentheses
     *   3. Convert to postfix
     *   4. Reverse again
     * - This works because prefix is like a mirror image of postfix
     * 
     * Time Complexity:
     * - O(n) where n is the length of input string
     * - reverse(): O(n)
     * - swap parentheses: O(n)
     * - infixToPostfix(): O(n)
     * - final reverse: O(n)
     * 
     * Space Complexity:
     * - O(n) for StringBuilder and output string storage
     */
    public static String infixToPrefix(String s) {
        // Step 1: Reverse the infix string
        StringBuilder out = new StringBuilder(s);
        out.reverse();

        // Step 2: Swap opening and closing parentheses
        for (int i = 0; i < out.length(); i++) {
            char c = out.charAt(i);
            if (c == '(') {
                out.setCharAt(i, ')');
            } else if (c == ')') {
                out.setCharAt(i, '(');
            }
        }

        // Step 3: Convert to postfix
        String postfix = infixToPostfix(out.toString());

        // Step 4: Reverse again to get prefix
        out = new StringBuilder(postfix);
        return out.reverse().toString();
    }

    /*-
     * Helper method to convert infix to postfix
     * 
     * Intuition:
     * - Use stack to manage operator precedence
     * - Process operands immediately
     * - Handle operators based on precedence rules
     * - Special handling for parentheses
     * 
     * Time Complexity:
     * - O(n) where n is the length of input string
     * - Each character is processed exactly once
     * - Stack operations (push/pop) are O(1)
     * 
     * Space Complexity:
     * - O(n) for:
     *   - Stack to store operators (worst case: all operators)
     *   - StringBuilder for output
     */
    public static String infixToPostfix(String s) {
        Stack<Character> operators = new Stack<>();
        StringBuilder out = new StringBuilder();

        for (char c : s.toCharArray()) {
            // Case 1: If character is operand, add to output
            if ((c >= 'a' && c <= 'z')
                    || (c >= 'A' && c <= 'Z')
                    || (c >= '0' && c <= '9')) {
                out.append(c);
            }
            // Case 2: If opening parenthesis, push to stack
            else if (c == '(') {
                operators.push(c);
            }
            // Case 3: If closing parenthesis, pop until matching opening found
            else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    out.append(operators.pop());
                }
                operators.pop();
            }
            // Case 4: If operator, handle based on precedence
            else {
                while (!operators.isEmpty() && operators.peek() != '(' &&
                        precedence(c) <= precedence(operators.peek())) {
                    out.append(operators.pop());
                }
                operators.push(c);
            }
        }

        // Pop remaining operators from stack
        while (!operators.isEmpty()) {
            out.append(operators.pop());
        }

        return out.toString();
    }

    /*-
     * Helper method for operator precedence
     * 
     * Intuition:
     * - Assign higher values to operators with higher precedence
     * - Exponentiation (^) has highest precedence
     * - Multiplication/division (*,/) have medium precedence
     * - Addition/subtraction (+,-) have lowest precedence
     * 
     * Time Complexity:
     * - O(1) constant time using switch statement
     * 
     * Space Complexity:
     * - O(1) no extra space needed
     */
    public static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
}
