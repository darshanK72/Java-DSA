/**
 * LeetCode 150. Evaluate Binary Expression Tree
 * 
 * Problem Statement:
 *     Given a binary expression tree, evaluate the expression and return the result.
 *     The tree is a binary tree where each internal node is an operator (+, -, *, /)
 *     and each leaf node is an operand (integer).
 * 
 * Input:
 *     - BinaryTreeNode<String> root: Root of the expression tree
 *     - Internal nodes contain operators ("+", "-", "*", "/")
 *     - Leaf nodes contain operands (string representation of integers)
 * 
 * Output:
 *     - int: Result of evaluating the expression
 * 
 * Example:
 *     Input: 
 *            *
 *          /   \
 *         +     +
 *        / \   / \
 *       3   4 5   6
 * 
 *     Output: 77
 * 
 *     Explanation:
 *     The expression is: (3 + 4) * (5 + 6)
 *     First evaluate (3 + 4) = 7
 *     Then evaluate (5 + 6) = 11
 *     Finally evaluate 7 * 11 = 77
 */

public class j02EvaluateBinaryExpressionTree {

    /**
     * BinaryTreeNode class represents a node in the Expression Tree
     * Each node contains:
     * - data: Generic type value (operator or operand)
     * - left: Reference to the left child
     * - right: Reference to the right child
     * 
     * @param <T> Type of data stored in the node
     */
    static class BinaryTreeNode<T> {
        T data;
        BinaryTreeNode<T> left;
        BinaryTreeNode<T> right;

        BinaryTreeNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Approach: Recursive Postorder Traversal
     * 
     * Intuition:
     * - Use postorder traversal (left-right-root)
     * - Evaluate left and right subtrees first
     * - Apply operator to results
     * 
     * Explanation:
     * - For leaf nodes: Return the integer value
     * - For internal nodes: 
     *   1. Recursively evaluate left subtree
     *   2. Recursively evaluate right subtree
     *   3. Apply operator to results
     * 
     * Time Complexity: O(n) where n is number of nodes
     * - Each node is visited exactly once
     * 
     * Space Complexity: O(h) where h is height of tree
     * - Space used by recursion stack
     * 
     * @param root Root of the expression tree
     * @return Result of evaluating the expression
     */
    public static int evaluateExpression(BinaryTreeNode<String> root) {
        // Handle null case
        if (root == null)
            return 0;

        // Handle leaf node (operand)
        if (root.left == null && root.right == null) {
            return Integer.parseInt(root.data);  // Convert string to integer
        }

        // Recursively evaluate left and right subtrees
        int left = evaluateExpression(root.left);
        int right = evaluateExpression(root.right);

        // Apply operator to results
        String operator = root.data;
        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                return left / right;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic expression
        System.out.println("\nBasic Test Case:");
        BinaryTreeNode<String> root1 = new BinaryTreeNode<>("*");
        root1.left = new BinaryTreeNode<>("+");
        root1.right = new BinaryTreeNode<>("+");
        root1.left.left = new BinaryTreeNode<>("3");
        root1.left.right = new BinaryTreeNode<>("4");
        root1.right.left = new BinaryTreeNode<>("5");
        root1.right.right = new BinaryTreeNode<>("6");

        System.out.println("Expression: (3 + 4) * (5 + 6)");
        System.out.println("Result: " + evaluateExpression(root1));  // Expected: 77

        // Test Case 2: Simple expression
        System.out.println("\nSimple Expression:");
        BinaryTreeNode<String> root2 = new BinaryTreeNode<>("+");
        root2.left = new BinaryTreeNode<>("5");
        root2.right = new BinaryTreeNode<>("3");

        System.out.println("Expression: 5 + 3");
        System.out.println("Result: " + evaluateExpression(root2));  // Expected: 8

        // Test Case 3: Complex expression
        System.out.println("\nComplex Expression:");
        BinaryTreeNode<String> root3 = new BinaryTreeNode<>("*");
        root3.left = new BinaryTreeNode<>("+");
        root3.right = new BinaryTreeNode<>("-");
        root3.left.left = new BinaryTreeNode<>("10");
        root3.left.right = new BinaryTreeNode<>("5");
        root3.right.left = new BinaryTreeNode<>("8");
        root3.right.right = new BinaryTreeNode<>("3");

        System.out.println("Expression: (10 + 5) * (8 - 3)");
        System.out.println("Result: " + evaluateExpression(root3));  // Expected: 75
    }
}
