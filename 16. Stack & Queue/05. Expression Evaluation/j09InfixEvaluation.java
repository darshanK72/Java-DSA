/**
 * Problem Statement:
 *     Evaluate an infix arithmetic expression containing integers and operators (+,-,*,/)
 *     with parentheses. Return the final result of the expression.
 * 
 * Input:
 *     String containing digits, operators and parentheses
 * 
 * Output:
 *     Integer result of evaluating the expression
 * 
 * Example:
 *     Input: "2+3*(4-1)"
 *     Output: 11
 *     Explanation: 
 *     1. Evaluate (4-1) = 3
 *     2. Evaluate 3*3 = 9
 *     3. Evaluate 2+9 = 11
 */

import java.util.Stack;

public class j09InfixEvaluation {

    /**
     * Main Method:
     * Purpose: Demonstrate expression evaluation with various test cases
     * 
     * Test Cases Cover:
     * 1. Basic arithmetic with precedence: "2+3*(4-1)"
     * 2. Nested parentheses: "(1+2)*(3+4)"
     * 3. Multi-digit numbers: "10-2*3"
     * 4. Division operation: "(5+3)/2"
     * 
     * Time Complexity: O(1) - fixed test cases
     * Space Complexity: O(1) - fixed storage
     */
    public static void main(String[] args) {
        String[] tests = {
            "2+3*(4-1)",     // 11
            "(1+2)*(3+4)",   // 21
            "10-2*3",        // 4
            "(5+3)/2"        // 4
        };

        for (String test : tests) {
            System.out.printf("Expression: %s = %d%n", 
                            test, evaluateArithmeticExpression(test));
        }
    }

    /**
     * Evaluate Arithmetic Expression Method:
     * Purpose: Convert and evaluate infix expression using two stacks
     * 
     * Detailed Algorithm:
     * 1. Number Processing:
     *    - Build multi-digit numbers
     *    - Push directly to operands stack
     *    Time: O(d) where d is number of digits
     * 
     * 2. Opening Parenthesis:
     *    - Push to operators stack
     *    - Marks start of sub-expression
     *    Time: O(1)
     * 
     * 3. Closing Parenthesis:
     *    - Evaluate all operators until matching '('
     *    - Pop the '(' without evaluating
     *    Time: O(k) where k is operators in parentheses
     * 
     * 4. Operator Processing:
     *    - Compare precedence with top of operator stack
     *    - Evaluate higher/equal precedence operators
     *    - Push current operator
     *    Time: O(m) where m is operators to evaluate
     * 
     * Overall:
     * Time Complexity: O(n) - single pass through expression
     * Space Complexity: O(n) - two stacks can store n/2 elements each
     * 
     * Example Walkthrough:
     * Expression: "2+3*4"
     * 1. Push 2 → operands:[2]
     * 2. Push + → operators:[+]
     * 3. Push 3 → operands:[2,3]
     * 4. Push * → operators:[+,*]
     * 5. Push 4 → operands:[2,3,4]
     * 6. Evaluate * → operands:[2,12]
     * 7. Evaluate + → operands:[14]
     */
    public static int evaluateArithmeticExpression(String expression) {
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Case 1: Process numbers
            if (c >= '0' && c <= '9') {
                int number = 0;
                // Build multi-digit number
                while (i < expression.length() && (c >= '0' && c <= '9')) {
                    number = number * 10 + (c - '0');
                    i++;
                    if (i < expression.length()) {
                        c = expression.charAt(i);
                    }
                }
                i--;  // Adjust for loop increment
                operands.push(number);
            }
            // Case 2: Open parenthesis
            else if (c == '(') {
                operators.push(c);
            }
            // Case 3: Close parenthesis
            else if (c == ')') {
                // Evaluate until matching '('
                while (!operators.isEmpty() && operators.peek() != '(') {
                    evaluateTop(operands, operators);
                }
                operators.pop();  // Remove '('
            }
            // Case 4: Operator
            else if (isOperator(c)) {
                // Evaluate higher/equal precedence operators
                while (!operators.isEmpty() && operators.peek() != '(' && 
                       precedence(c) <= precedence(operators.peek())) {
                    evaluateTop(operands, operators);
                }
                operators.push(c);
            }
        }

        // Process remaining operators
        while (!operators.isEmpty()) {
            evaluateTop(operands, operators);
        }

        return operands.peek();
    }

    /**
     * Helper Method: evaluateTop
     * Purpose: Evaluate single operation with top operator and operands
     * 
     * Process:
     * 1. Pop two operands (in reverse order)
     * 2. Pop operator
     * 3. Perform operation
     * 4. Push result back
     * 
     * Time Complexity: O(1) - constant operations
     * Space Complexity: O(1) - no extra storage
     */
    private static void evaluateTop(Stack<Integer> operands, Stack<Character> operators) {
        int secondOperand = operands.pop();
        int firstOperand = operands.pop();
        char operator = operators.pop();
        int result = operate(firstOperand, secondOperand, operator);
        operands.push(result);
    }

    /**
     * Helper Method: precedence
     * Purpose: Determine operator precedence for evaluation order
     * 
     * Precedence Rules:
     * 1. Multiplication, Division: 2 (Higher)
     * 2. Addition, Subtraction: 1 (Lower)
     * 3. Others: 0 (Invalid)
     * 
     * Time Complexity: O(1) - switch statement
     * Space Complexity: O(1) - no storage
     */
    private static int precedence(char c) {
        switch (c) {
            case '+': 
            case '-': return 1;
            case '*': 
            case '/': return 2;
            default: return 0;
        }
    }

    /**
     * Helper Method: operate
     * Purpose: Perform single arithmetic operation
     * 
     * Operations:
     * +: Addition
     * -: Subtraction
     * *: Multiplication
     * /: Division (Integer division)
     * 
     * Error Handling:
     * - Throws IllegalArgumentException for invalid operators
     * - Note: Doesn't handle division by zero
     * 
     * Time Complexity: O(1) - single operation
     * Space Complexity: O(1) - no storage
     */
    private static int operate(int a, int b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }

    /**
     * Helper Method: isOperator
     * Purpose: Validate if character is arithmetic operator
     * 
     * Supported Operators: +, -, *, /
     * 
     * Time Complexity: O(1) - constant checks
     * Space Complexity: O(1) - no storage
     */
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}