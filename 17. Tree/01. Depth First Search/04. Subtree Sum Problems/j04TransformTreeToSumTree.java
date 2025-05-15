/**
 * Problem Statement:
 *     Transform to Sum Tree (GeeksForGeeks)
 * 
 *     Given a Binary Tree, transform it into a Sum Tree where each node contains
 *     the sum of the left and right subtrees in the original tree. The values of
 *     leaf nodes are changed to 0.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 *     - Node values can be any integer
 * 
 * Output:
 *     - Original tree modified to sum tree
 * 
 * Example:
 *     Input: 
 *           10
 *          /  \
 *         -2   6
 *        / \  / \
 *       8  -4 7  5
 *     
 *     Output:
 *           20
 *          /  \
 *         4   12
 *        / \  / \
 *       0   0 0  0
 */

public class j04TransformTreeToSumTree {

    /**
     * Node class represents a node in binary tree
     */
    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree
        Node root1 = new Node(10);
        root1.left = new Node(-2);
        root1.right = new Node(6);
        root1.left.left = new Node(8);
        root1.left.right = new Node(-4);
        root1.right.left = new Node(7);
        root1.right.right = new Node(5);

        System.out.println("Test Case 1:");
        System.out.println("Before transformation:");
        printInorder(root1);
        toSumTreeEfficient(root1);
        System.out.println("\nAfter transformation:");
        printInorder(root1); // Expected: 0 4 0 20 0 12 0

        // Test Case 2: Single node
        Node root2 = new Node(5);
        System.out.println("\n\nTest Case 2:");
        System.out.println("Before transformation:");
        printInorder(root2);
        toSumTreeEfficient(root2);
        System.out.println("\nAfter transformation:");
        printInorder(root2); // Expected: 0

        // Test Case 3: Linear tree
        Node root3 = new Node(1);
        root3.right = new Node(2);
        root3.right.right = new Node(3);
        System.out.println("\n\nTest Case 3:");
        System.out.println("Before transformation:");
        printInorder(root3);
        toSumTreeEfficient(root3);
        System.out.println("\nAfter transformation:");
        printInorder(root3); // Expected: 0 3 0
    }

    /**
     * Approach 1: Two-Pass Transformation (Inefficient)
     * 
     * Time Complexity: O(n²)
     * - For each node, calculate subtree sum separately
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static void toSumTree(Node root) {
        if (root == null)
            return;

        int leftSum = subtreeSum(root.left);
        int rightSum = subtreeSum(root.right);

        root.data = leftSum + rightSum;

        toSumTree(root.left);
        toSumTree(root.right);
    }

    /**
     * Helper method to calculate subtree sum
     */
    public static int subtreeSum(Node root) {
        if (root == null)
            return 0;

        return root.data + subtreeSum(root.left) + subtreeSum(root.right);
    }

    /**
     * Approach 2: Single-Pass Transformation (Efficient)
     * 
     * Intuition:
     * - Use post-order traversal to:
     *   1. Calculate subtree sums bottom-up
     *   2. Transform nodes during the same pass
     * - Store original value before updating node
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static void toSumTreeEfficient(Node root) {
        subtreeSumEfficient(root);
    }

    private static int subtreeSumEfficient(Node root) {
        if (root == null)
            return 0;

        int left = subtreeSumEfficient(root.left); // Get left sum
        int right = subtreeSumEfficient(root.right); // Get right sum

        int originalVal = root.data; // Store original value
        root.data = left + right; // Transform node

        return left + right + originalVal; // Return total sum
    }

    /**
     * Helper method to print tree in-order
     */
    private static void printInorder(Node root) {
        if (root == null)
            return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }
}