/**
 * Problem Statement:
 *     Given a valid mathematical expression with parentheses, determine if it contains
 *     redundant parentheses. A set of parentheses is redundant if it encloses no operators.
 * 
 * Input:
 *     String containing parentheses, operators (+,-,*,/), and lowercase letters
 * 
 * Output:
 *     1 if redundant parentheses exist, 0 otherwise
 * 
 * Example 1:
 *     Input: "((a+b))"
 *     Output: 1
 *     Explanation: Outer parentheses are redundant: (a+b) is valid alone
 * 
 * Example 2:
 *     Input: "(a+(b*c))"
 *     Output: 0
 *     Explanation: No redundant parentheses, all are needed
 */

import java.util.Stack;

public class j05RedundentParenthesis {

    /**
     * Main method to demonstrate redundant parentheses detection
     */
    public static void main(String args[]) {
        String[] tests = {
            "((a+b))",     // 1 - outer parentheses redundant
            "(a+b)",       // 0 - no redundant parentheses
            "((a*b)+c)",   // 0 - all parentheses needed
            "(a)",         // 1 - redundant around single operand
            "((a+b)+(c+d))"// 0 - all parentheses needed
        };

        for (String test : tests) {
            System.out.printf("Expression: %s -> Redundant: %d%n", 
                            test, removeRedundentParenthesis(test));
        }
    }

    /**
     * Check for redundant parentheses in expression
     * 
     * Intuition:
     * 1. Use stack to track operators and opening parentheses
     * 2. When closing parenthesis found:
     *    - If top of stack is opening parenthesis → redundant
     *    - Otherwise pop until matching opening parenthesis
     * 3. Valid parentheses must enclose at least one operator
     * 
     * Example Walkthrough:
     * "((a+b))" →
     * ( → stack:[(]
     * ( → stack:[(,(]
     * a → continue
     * + → stack:[(,(,+]
     * b → continue
     * ) → pop until '(' → stack:[(]
     * ) → stack empty = redundant!
     * 
     * Time Complexity: O(n) - single pass through expression
     * Space Complexity: O(n) - stack can store all operators
     * 
     * @param s input mathematical expression
     * @return 1 if redundant parentheses exist, 0 otherwise
     */
    public static int removeRedundentParenthesis(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            // Push operators and opening brackets
            if (isOperator(c) || c == '(') {
                stack.push(c);
            }
            // Handle closing bracket
            else if (c == ')') {
                // No operators between brackets = redundant
                if (stack.peek() == '(') {
                    return 1;
                }
                
                // Pop all operators until opening bracket
                while (!stack.isEmpty() && stack.peek() != '(') {
                    stack.pop();
                }
                stack.pop();  // Remove opening bracket
            }
            // Skip operands
        }
        
        return 0;  // No redundant parentheses found
    }

    /**
     * Helper method to check if character is operator
     */
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
