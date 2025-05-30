/**
 * Expression Tree Construction from Infix Expression
 * 
 * Problem Statement:
 *     Given an infix expression string, construct a binary expression tree where
 *     each internal node is an operator and each leaf node is an operand.
 * 
 * Input:
 *     - String s: Infix expression (1 <= length <= 10^4)
 *     - Contains digits (0-9), operators (+, -, *, /), and parentheses
 *     - Expression is valid and well-formed
 * 
 * Output:
 *     - BinaryTreeNode: Root of the constructed expression tree
 *     - Internal nodes contain operators
 *     - Leaf nodes contain operands
 * 
 * Example:
 *     Input: "3+4*2/(1-5)"
 *     Output: Binary Expression Tree
 * 
 *     Explanation:
 *     The tree structure represents the expression:
 *            +
 *          /   \
 *         3     /
 *              / \
 *             *   -
 *            / \  / \
 *           4   2 1  5
 */

import java.util.Stack;

public class j01InfixToBinaryExpressionTree {

    /**
     * BinaryTreeNode class represents a node in the Expression Tree
     * Each node contains:
     * - data: Character value (operator or operand)
     * - left: Reference to the left child
     * - right: Reference to the right child
     */
    static class BinaryTreeNode {
        char data;
        BinaryTreeNode left;
        BinaryTreeNode right;

        BinaryTreeNode(char data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Approach: Using Two Stacks
     * 
     * Intuition:
     * - Use one stack for operands and another for operators
     * - Process each character based on its type
     * - Build tree nodes and connect them based on operator precedence
     * 
     * Explanation:
     * - For operands: Create leaf nodes and push to operand stack
     * - For '(': Push to operator stack
     * - For ')': Pop operators until matching '(' is found
     * - For operators: Pop higher precedence operators and build subtrees
     * 
     * Time Complexity: O(n) where n is length of expression
     * - Each character is processed once
     * - Stack operations are O(1)
     * 
     * Space Complexity: O(n) for storing the tree and stacks
     * 
     * @param s Infix expression string
     * @return Root of the constructed expression tree
     */
    public static BinaryTreeNode binaryExpressionTree(String s) {
        Stack<BinaryTreeNode> operands = new Stack<>();
        Stack<BinaryTreeNode> operators = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c <= '9' && c >= '0') {  // Handle operand
                operands.push(new BinaryTreeNode(c));
            } else if (c == '(') {  // Handle opening parenthesis
                operators.push(new BinaryTreeNode(c));
            } else if (c == ')') {  // Handle closing parenthesis
                while (!operators.isEmpty() && operators.peek().data != '(') {
                    evaluateTop(operands, operators);
                }
                operators.pop();  // Remove the matching '('
            } else if (isOperator(c)) {  // Handle operator
                while (!operators.isEmpty() && operators.peek().data != '(' &&
                        precedence(c) <= precedence(operators.peek().data)) {
                    evaluateTop(operands, operators);
                }
                operators.push(new BinaryTreeNode(c));
            }
        }

        // Process remaining operators
        while (!operators.isEmpty()) {
            evaluateTop(operands, operators);
        }

        return operands.peek();  // Return root of expression tree
    }

    /**
     * Helper method to check if a character is an operator
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param c Character to check
     * @return True if character is an operator, false otherwise
     */
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * Helper method to get operator precedence
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param c Operator character
     * @return Precedence value (2 for * and /, 1 for + and -, 0 for others)
     */
    public static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    /**
     * Helper method to evaluate top operator and build subtree
     * 
     * Intuition:
     * - Pop two operands and one operator
     * - Create a subtree with operator as root
     * - Push the subtree back to operand stack
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param operands Stack containing operand nodes
     * @param operators Stack containing operator nodes
     */
    public static void evaluateTop(Stack<BinaryTreeNode> operands, Stack<BinaryTreeNode> operators) {
        BinaryTreeNode right = operands.pop();  // Get right operand
        BinaryTreeNode left = operands.pop();   // Get left operand
        BinaryTreeNode operator = operators.pop();  // Get operator
        operator.left = left;    // Set left child
        operator.right = right;  // Set right child
        operands.push(operator); // Push subtree back to operand stack
    }

    /**
     * Helper method to print expression tree in inorder traversal
     * 
     * Intuition:
     * - Inorder traversal (left-root-right) gives infix expression
     * - Add parentheses based on operator precedence
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree (recursion stack)
     * 
     * @param root Root of the expression tree
     * @param parentPrecedence Precedence of parent operator
     */
    public static void printInorder(BinaryTreeNode root, int parentPrecedence) {
        if (root == null) return;

        boolean needParentheses = false;
        if (isOperator(root.data)) {
            needParentheses = precedence(root.data) < parentPrecedence;
        }

        if (needParentheses) System.out.print("(");
        
        // Process left subtree
        if (root.left != null) {
            printInorder(root.left, precedence(root.data));
        }
        
        // Process current node
        System.out.print(root.data);
        
        // Process right subtree
        if (root.right != null) {
            printInorder(root.right, precedence(root.data));
        }

        if (needParentheses) System.out.print(")");
    }

    public static void main(String[] args) {
        // Test Case 1: Basic expression
        System.out.println("\nBasic Test Case:");
        String expr1 = "3+4*2";
        BinaryTreeNode root1 = binaryExpressionTree(expr1);
        System.out.println("Expression: " + expr1);
        System.out.print("Inorder traversal: ");
        printInorder(root1, 0);
        System.out.println();
        // Expected tree structure:
        //      +
        //    /   \
        //   3     *
        //        / \
        //       4   2

        // Test Case 2: Expression with parentheses
        System.out.println("\nExpression with Parentheses:");
        String expr2 = "3+4*2/(1-5)";
        BinaryTreeNode root2 = binaryExpressionTree(expr2);
        System.out.println("Expression: " + expr2);
        System.out.print("Inorder traversal: ");
        printInorder(root2, 0);
        System.out.println();
        // Expected tree structure:
        //        +
        //      /   \
        //     3     /
        //          / \
        //         *   -
        //        / \  / \
        //       4   2 1  5

        // Test Case 3: Simple expression
        System.out.println("\nSimple Expression:");
        String expr3 = "1+2";
        BinaryTreeNode root3 = binaryExpressionTree(expr3);
        System.out.println("Expression: " + expr3);
        System.out.print("Inorder traversal: ");
        printInorder(root3, 0);
        System.out.println();
        // Expected tree structure:
        //      +
        //    /   \
        //   1     2
    }
}