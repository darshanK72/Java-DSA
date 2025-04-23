/*-
 * Problem Statement:
 * 
 *     Convert an infix expression to postfix expression. In infix expressions,
 *     operators are written between operands (e.g., a+b), while in postfix
 *     expressions, operators are written after operands (e.g., ab+).
 * 
 * Input:
 *     - A string containing a valid infix expression with operators (+,-,*,/,^),
 *       operands (a-z, A-Z, 0-9) and parentheses.
 * 
 * Output:
 *     - The equivalent postfix expression as a string.
 * 
 * Example:
 *     Input: "a+b*(c^d-e)^(f+g*h)-i"
 *     Output: "abcd^e-fgh*+^*+i-"
 * 
 *     Explanation:
 *     The infix expression is converted to postfix notation while maintaining
 *     operator precedence and parentheses rules.
 */

import java.util.Stack;

public class j02InfixToPostfix {

    public static void main(String[] args) {
        String infix = "a+b*(c^d-e)^(f+g*h)-i";
        String postfix = infixToPostfix(infix);
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + postfix);
    }

    /*-
     * Approach: Using Stack Data Structure
     * 
     * Intuition:
     * - Use stack to maintain operator precedence while processing
     * - For operands: directly add to output
     * - For operators: compare precedence with stack top
     * - Higher precedence operators should be processed first
     * - Parentheses require special handling to maintain expression grouping
     * 
     * Time Complexity:
     * - O(n) where n is the length of input string
     * - Each character is processed exactly once
     * - Stack operations (push/pop) are O(1)
     * 
     * Space Complexity:
     * - O(n) where n is the length of input string
     * - Stack can store at most n/2 operators
     * - StringBuilder for output storage
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
                operators.pop(); // Remove opening parenthesis
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
