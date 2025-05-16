/**
 * Problem Statement:
 *     LeetCode 687. Longest Univalue Path
 * 
 *     Given the root of a binary tree, return the length of the longest path where each node
 *     in the path has the same value. This path may or may not pass through the root.
 *     The length of the path is the number of edges in the path.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 *     - Node values can be any integer
 * 
 * Output:
 *     - Length of longest path with same values (number of edges)
 * 
 * Example:
 *     Input: 
 *           5
 *          / \
 *         4   5
 *        / \   \
 *       1   1   5
 *     
 *     Output: 2
 *     Explanation: 
 *     Longest path is right side: 5 -> 5 -> 5
 *     Path length is 2 (number of edges)
 */

public class j07LongestUniValuePath {

    /**
     * TreeNode class represents a node in binary tree
     * Contains value and references to left and right children
     */
    public static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Path through right side
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(1);
        root1.right.right = new TreeNode(5);
        System.out.println("Test Case 1: " + longestUnivaluePath(root1));  // Expected: 2

        // Test Case 2: Path through both children
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(1);
        root2.right.right = new TreeNode(1);
        System.out.println("Test Case 2: " + longestUnivaluePath(root2));  // Expected: 4

        // Test Case 3: No path (all different values)
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        System.out.println("Test Case 3: " + longestUnivaluePath(root3));  // Expected: 0
    }

    /**
     * Main method to find longest univalue path
     * 
     * Approach:
     * - Use DFS to traverse tree and track longest path
     * - At each node, check paths through left and right children
     * - Update global maximum when finding longer path
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * 
     * @param root Root node of binary tree
     * @return Length of longest univalue path
     */
    public static int longestUnivaluePath(TreeNode root) {
        int[] maxLength = new int[1];
        findMaxPath(root, maxLength);
        return maxLength[0];
    }

    /**
     * Helper method to find maximum univalue path length using DFS
     * 
     * Algorithm:
     * 1. Handle null node (return 0)
     * 2. Recursively process left and right subtrees
     * 3. Check if current node matches children values
     * 4. Update longest path through current node
     * 5. Return maximum length of path ending at current node
     * 
     * @param root Current node being processed
     * @param maxLength Array to store maximum path length found
     * @return Length of univalue path ending at current node
     */
    private static int findMaxPath(TreeNode root, int[] maxLength) {
        if (root == null)
            return 0;

        int left = findMaxPath(root.left, maxLength);     // Process left subtree
        int right = findMaxPath(root.right, maxLength);   // Process right subtree

        int leftPath = 0, rightPath = 0;

        // Check if values match with children
        if (root.left != null && root.val == root.left.val) {
            leftPath = left + 1;
        }
        if (root.right != null && root.val == root.right.val) {
            rightPath = right + 1;
        }

        // Update maximum path length found so far
        maxLength[0] = Math.max(maxLength[0], leftPath + rightPath);

        // Return maximum path length ending at current node
        return Math.max(leftPath, rightPath);
    }
}
