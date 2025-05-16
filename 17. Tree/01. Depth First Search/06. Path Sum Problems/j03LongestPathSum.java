/**
 * Problem Statement:
 *     Sum of the Longest Path from Root to Leaf Node (GeeksForGeeks)
 * 
 *     Given a binary tree, find the sum of node values along the path from root to leaf
 *     that has the maximum length. If two or more paths compete for the longest length,
 *     the path with the maximum sum of nodes is considered.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 *     - Node values can be any integer
 * 
 * Output:
 *     - Sum of nodes in the longest (or maximum sum) path
 * 
 * Example:
 *     Input:
 *           4
 *          / \
 *         2   5
 *        / \   \
 *       7   1   3
 *          /
 *         6
 *     
 *     Output: 13
 *     Explanation: 
 *     Paths:
 *     - 4->2->7 (length=3, sum=13)
 *     - 4->2->1->6 (length=4, sum=13)
 *     - 4->5->3 (length=3, sum=12)
 *     Path 4->2->1->6 is longest, so return its sum = 13
 */

public class j03LongestPathSum {

    /**
     * Node class represents a node in binary tree
     */
    public static class Node {
        int data;
        Node left, right;

        Node(int x) {
            data = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Path with maximum length
        Node root1 = new Node(4);
        root1.left = new Node(2);
        root1.right = new Node(5);
        root1.left.left = new Node(7);
        root1.left.right = new Node(1);
        root1.left.right.left = new Node(6);
        root1.right.right = new Node(3);
        System.out.println("Test Case 1: " + sumOfLongRootToLeafPath(root1)); // Expected: 13

        // Test Case 2: Two paths with same length but different sums
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.left.left = new Node(4);
        root2.right.right = new Node(5);
        System.out.println("Test Case 2: " + sumOfLongRootToLeafPath(root2)); // Expected: 9

        // Test Case 3: Single path
        Node root3 = new Node(1);
        root3.right = new Node(2);
        root3.right.right = new Node(3);
        System.out.println("Test Case 3: " + sumOfLongRootToLeafPath(root3)); // Expected: 6
    }

    /**
     * Approach: DFS with Length and Sum Tracking
     * 
     * Intuition:
     * - Use DFS to explore all root-to-leaf paths
     * - Track both path length and sum while traversing
     * - Update answer when finding longer path or equal length path with larger sum
     * - Use array to store [maxSum, maxLength]
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static int sumOfLongRootToLeafPath(Node root) {
        int[] maxPath = new int[2]; // [maxSum, maxLength]
        findLongestPathSum(root, 0, 0, maxPath);
        return maxPath[0];
    }

    /**
     * Helper method to find longest path sum using DFS
     * @param root: Current node being processed
     * @param currSum: Sum of nodes in current path
     * @param length: Length of current path
     * @param maxPath: Array storing [maxSum, maxLength]
     */
    public static void findLongestPathSum(Node root, int currSum, int length, int[] maxPath) {
        if (root == null)
            return;

        length++; // Increment path length
        currSum += root.data; // Add current node to sum

        // At leaf node, update maximum if needed
        if (root.left == null && root.right == null) {
            if (length > maxPath[1] || (length == maxPath[1] && currSum > maxPath[0])) {
                maxPath[0] = currSum; // Update maximum sum
                maxPath[1] = length; // Update maximum length
            }
            return;
        }

        // Process children
        findLongestPathSum(root.left, currSum, length, maxPath);
        findLongestPathSum(root.right, currSum, length, maxPath);
    }
}
