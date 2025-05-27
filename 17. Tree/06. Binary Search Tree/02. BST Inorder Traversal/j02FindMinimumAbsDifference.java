/**
 * LeetCode 530. Minimum Absolute Difference in BST
 * 
 * Problem Statement:
 *     Given the root of a Binary Search Tree (BST), return the minimum absolute 
 *     difference between any two different nodes in the tree.
 * 
 * Input:
 *     - TreeNode root (root node of BST)
 *     - Node values are non-negative integers
 * 
 * Output:
 *     - int (minimum absolute difference between any two nodes)
 * 
 * Example:
 *     Input: root = [4,2,6,1,3]
 *     Output: 1
 * 
 *     Explanation:
 *          4
 *         / \
 *        2   6
 *       / \
 *      1   3
 *     
 *     Minimum absolute difference is 1, between nodes 1 & 2, 2 & 3, or 3 & 4.
 */

public class j02FindMinimumAbsDifference {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    /**
     * Finds minimum absolute difference between any two nodes
     * 
     * Intuition:
     * - Use inorder traversal to get values in sorted order
     * - Track previous value to compare with current
     * - Update minimum difference when needed
     * 
     * Explanation:
     * - Initialize result array with:
     *   - index 0: minimum difference found so far
     *   - index 1: previous value seen in inorder traversal
     * - Perform inorder traversal to compare adjacent values
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree (recursion stack)
     * 
     * @param root    Root node of BST
     * @return       Minimum absolute difference between any two nodes
     */
    public static int getMinimumDifference(TreeNode root) {
        if (root == null) 
            return 0;
            
        // Initialize result array
        // result[0] = minimum difference
        // result[1] = previous value
        int[] result = new int[2];
        result[0] = Integer.MAX_VALUE;  // Initialize min diff
        result[1] = -10000000;          // Initialize previous value
        
        // Perform inorder traversal
        findMinDiff(root, result);
        
        return result[0];
    }

    /**
     * Helper method for inorder traversal
     * 
     * Intuition:
     * - Process nodes in sorted order (inorder)
     * - Compare each node with previous value
     * - Update minimum difference if smaller found
     * 
     * Explanation:
     * - result[0] stores minimum difference
     * - result[1] stores previous value
     * - Update both during traversal
     * 
     * @param root     Current node being processed
     * @param result   Array storing min diff and previous value
     */
    private static void findMinDiff(TreeNode root, int[] result) {
        if (root == null) 
            return;
            
        // Process left subtree
        findMinDiff(root.left, result);
        
        // Compare current value with previous
        result[0] = Math.min(result[0], Math.abs(root.val - result[1]));
        result[1] = root.val;  // Update previous value
        
        // Process right subtree
        findMinDiff(root.right, result);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic BST
        System.out.println("\nBasic BST Test:");
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        System.out.println("Minimum difference: " + getMinimumDifference(root1));  // Expected: 1

        // Test Case 2: Linear BST
        System.out.println("\nLinear BST Test:");
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.right.right = new TreeNode(5);
        System.out.println("Minimum difference: " + getMinimumDifference(root2));  // Expected: 2

        // Test Case 3: Single node
        System.out.println("\nSingle Node Test:");
        TreeNode root3 = new TreeNode(1);
        System.out.println("Minimum difference: " + getMinimumDifference(root3));  // Expected: Integer.MAX_VALUE
    }
}
