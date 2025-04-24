/**
 * Problem Statement:
 * 
 *     Given a string s representing a valid expression, implement a basic 
 *     calculator to evaluate it. The expression string may contain:
 *     - Open ( and closing ) parentheses
 *     - Plus + or minus - signs
 *     - Non-negative integers
 *     - Spaces
 * 
 * Input:
 *     - String s containing a valid mathematical expression
 * 
 * Output:
 *     - Integer result after evaluating the expression
 * 
 * Example:
 *     Input: "1 + 1"
 *     Output: 2
 * 
 *     Input: " 2-1 + 2 "
 *     Output: 3
 * 
 *     Input: "(1+(4+5+2)-3)+(6+8)"
 *     Output: 23
 */

import java.util.Stack;

public class j10BasicCalculatorI {

    public static void main(String args[]) {
        // Test cases
        String[] testExpressions = {
            "1 + 1",                    // Basic addition
            " 2-1 + 2 ",               // Spaces and mixed operations
            "(1+(4+5+2)-3)+(6+8)",     // Nested parentheses
            "2 + 3 + (4 + 5)",         // Simple parentheses
            "1 + (2 + 3) - (4 - 5)",   // Multiple parentheses
            "0",                        // Single number
            "-(2 + 3)",                // Negative expression
            "(1)",                      // Single number in parentheses
            "1 + 2 + 3 + 4 + 5"        // Multiple additions
        };

        // Test each expression
        for (String exp : testExpressions) {
            System.out.println("Expression: " + exp);
            System.out.println("Result: " + calculate(exp));
            System.out.println();
        }
    }

    /**
     * Approach: Using Stack and Linear Scan
     * 
     * Intuition:
     * - Use stack to handle parentheses by storing previous results and signs
     * - Process character by character:
     *   - Skip spaces
     *   - For numbers, build them digit by digit
     *   - For operators (+/-), update sign and add previous number
     *   - For opening parenthesis, save current state
     *   - For closing parenthesis, evaluate subexpression
     * 
     * Time Complexity: O(n) where n is length of string
     * Space Complexity: O(n) for stack in worst case of nested parentheses
     */
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int currNum = 0;
        int ans = 0;
        int sign = 1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ' ') continue;
            else if(isOperator(c)){
                ans += (sign) * currNum;
                currNum = 0;
                sign = c == '-' ? -1 : 1;
            }else if(c == '('){
                stack.push(ans);
                stack.push(sign);
                ans = 0;
                sign = 1;
                currNum = 0;
            }else if(c == ')'){
                ans += (sign) * currNum;
                currNum = 0;
                ans = stack.pop() * ans + stack.pop();
            }else if(c >= '0' && c <= '9'){
                currNum = currNum * 10 + (c - '0');
            } 
        }
        ans += (sign) * currNum;

        return ans;
    }

    /**
     * Helper method to check if character is an operator
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static boolean isOperator(char c){
        return c == '+' || c == '-';
    }
}
