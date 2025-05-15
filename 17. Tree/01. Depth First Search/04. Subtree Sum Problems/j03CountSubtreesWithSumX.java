/**
 * Problem Statement:
 *     Count Number of Subtrees having given Sum
 *     (GeeksForGeeks)
 * 
 *     Given a binary tree and an integer X. Find the number of subtrees having
 *     total node's data sum equal to X. A subtree is a tree that consists of
 *     a node and all of its descendants.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Target sum X
 *     - Tree can have any number of nodes
 *     - Node values can be negative
 * 
 * Output:
 *     - Number of subtrees with sum equal to X
 * 
 * Example:
 *     Input: 
 *     Tree:
 *           5
 *          / \
 *        -10  3
 *        /   / \
 *       9   8   7
 *     X = 7
 *     
 *     Output: 2
 *     Explanation: 
 *     Subtrees with sum 7:
 *     1. [3, 8, -4]
 *     2. [7]
 */

public class j03CountSubtreesWithSumX {

    /**
     * Node class represents a node in binary tree
     * Contains data and references to left and right children
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

        // Test Case 1: Example tree
        Node root1 = new Node(5);
        root1.left = new Node(-10);
        root1.right = new Node(3);
        root1.left.left = new Node(9);
        root1.right.left = new Node(8);
        root1.right.right = new Node(7);
        System.out.println("Test Case 1: " + countSubtreesWithSumX(root1, 7)); // Expected: 2

        // Test Case 2: All positive values
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        System.out.println("Test Case 2: " + countSubtreesWithSumX(root2, 3)); // Expected: 1

        // Test Case 3: Single node matching sum
        Node root3 = new Node(5);
        System.out.println("Test Case 3: " + countSubtreesWithSumX(root3, 5)); // Expected: 1

        // Test Case 4: Empty tree
        System.out.println("Test Case 4: " + countSubtreesWithSumX(null, 0)); // Expected: 0
    }

    /**
     * Approach: Post-order DFS with Sum Tracking
     * 
     * Intuition:
     * - Use post-order traversal to calculate subtree sums bottom-up
     * - Track count of matching sums using array
     * - At each node:
     *   1. Get left and right subtree sums
     *   2. Calculate current subtree sum
     *   3. Increment count if sum equals target
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - Constant time operations at each node
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static int countSubtreesWithSumX(Node root, int X) {
        if (root == null)
            return 0;
        int[] count = new int[1]; // Store count of matching subtrees
        subtreeSum(root, X, count); // Calculate subtree sums
        return count[0];
    }

    /**
     * Helper method to calculate subtree sums and track count
     * Returns: sum of current subtree
     * Updates: count[0] with number of matching subtrees
     */
    private static int subtreeSum(Node root, int x, int[] count) {
        if (root == null)
            return 0;

        int left = subtreeSum(root.left, x, count); // Get left subtree sum
        int right = subtreeSum(root.right, x, count); // Get right subtree sum
        int sum = left + right + root.data; // Calculate current sum

        if (sum == x) {
            count[0]++; // Increment if matches target
        }
        return sum;
    }
}
