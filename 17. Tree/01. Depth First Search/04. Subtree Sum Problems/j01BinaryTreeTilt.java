/**
 * Problem Statement:
 *     LeetCode 563. Binary Tree Tilt
 * 
 *     Given the root of a binary tree, return the sum of every tree node's tilt value.
 *     The tilt of a tree node is the absolute difference between the sum of all
 *     left subtree node values and all right subtree node values.
 *     If a node has no children, its tilt is 0.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 *     - Node values can be negative
 * 
 * Output:
 *     - Sum of tilt values for all nodes
 * 
 * Example:
 *     Input: 
 *           4
 *          / \
 *         2   9
 *        / \   \
 *       3   5   7
 *     
 *     Tilt calculations:
 *     Leaf nodes 3, 5, 7: tilt = 0
 *     Node 2: tilt = |3 - 5| = 2
 *     Node 9: tilt = |0 - 7| = 7
 *     Root 4: tilt = |(3+5+2) - (7+9)| = |10 - 16| = 6
 *     Total tilt = 0 + 0 + 0 + 2 + 7 + 6 = 15
 */

public class j01BinaryTreeTilt {

    /**
     * TreeNode class represents a node in binary tree
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Example tree
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(9);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(7);
        System.out.println("Test Case 1: " + findTilt(root1));  // Expected: 15

        // Test Case 2: Linear tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        System.out.println("Test Case 2: " + findTilt(root2));  // Expected: 3

        // Test Case 3: Single node
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test Case 3: " + findTilt(root3));  // Expected: 0

        // Test Case 4: Empty tree
        System.out.println("Test Case 4: " + findTilt(null));   // Expected: 0
    }

    /**
     * Approach: Post-order DFS with Sum Tracking
     * 
     * Intuition:
     * - Use post-order traversal to calculate:
     *   1. Sum of subtrees (returned by recursive calls)
     *   2. Tilt at each node (stored in array)
     * - Process left and right subtrees before calculating current node's tilt
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - Constant time operations at each node
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * - Using array to store total tilt
     */
    public static int findTilt(TreeNode root) {
        if (root == null)
            return 0;                           // Handle empty tree
        int[] totalTilt = new int[1];          // Store cumulative tilt
        findTiltWithSum(root, totalTilt);      // Calculate tilt
        return totalTilt[0];
    }

    /**
     * Helper method to calculate subtree sums and accumulate tilt
     * Returns: sum of values in current subtree
     * Updates: totalTilt array with cumulative tilt
     */
    private static int findTiltWithSum(TreeNode root, int[] totalTilt) {
        if (root == null)
            return 0;                           // Base case
            
        int leftSum = findTiltWithSum(root.left, totalTilt);    // Get left sum
        int rightSum = findTiltWithSum(root.right, totalTilt);  // Get right sum
        
        totalTilt[0] += Math.abs(leftSum - rightSum);          // Add current tilt
        
        return leftSum + rightSum + root.val;                  // Return total sum
    }
}
