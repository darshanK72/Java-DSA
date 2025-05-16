/**
 * Problem Statement:
 *     LeetCode 112. Path Sum
 * 
 *     Given the root of a binary tree and an integer targetSum, return true if the tree has a 
 *     root-to-leaf path such that adding up all the values along the path equals targetSum.
 *     A leaf is a node with no children.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Target sum (integer)
 *     - Node values can be negative
 * 
 * Output:
 *     - Boolean indicating if path exists with target sum
 * 
 * Example:
 *     Input: 
 *           5
 *          / \
 *         4   8
 *        /   / \
 *       11  13  4
 *      /  \      \
 *     7    2      1
 *     targetSum = 22
 *     
 *     Output: true
 *     Explanation: Path 5->4->11->2 sums to 22
 */

public class j01PathSumI{
    
    /**
     * TreeNode class represents a node in binary tree
     */
    public static class TreeNode {
        int val;
        TreeNode left, right;
        
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Path exists
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.right.right.right = new TreeNode(1);
        
        System.out.println("Test Case 1 (Target=22): " + 
            hasPathSumEfficient(root1, 22));  // Expected: true

        // Test Case 2: No path exists
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        
        System.out.println("Test Case 2 (Target=5): " + 
            hasPathSumEfficient(root2, 5));  // Expected: false

        // Test Case 3: Negative values
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(-2);
        root3.right = new TreeNode(3);
        
        System.out.println("Test Case 3 (Target=-1): " + 
            hasPathSumEfficient(root3, -1));  // Expected: true
    }

    /**
     * Approach 1: DFS with Running Sum
     * 
     * Intuition:
     * - Use DFS to traverse all root-to-leaf paths
     * - Maintain running sum while traversing down the path
     * - At leaf node, check if running sum + leaf value equals target
     * - Use boolean array to track if path found (to handle multiple paths)
     * 
     * Algorithm:
     * 1. Create boolean array to store result
     * 2. Start DFS with initial sum = 0
     * 3. At each node:
     *    - If leaf node, check if path sum equals target
     *    - If not leaf, explore both children with updated sum
     * 4. Return true if any path matches
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - Each node requires constant time operations
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * - boolean array is O(1) extra space
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        boolean[] hasPath = new boolean[1];        // Track if path found
        findPathSum(root, 0, targetSum, hasPath);  // Start DFS with sum = 0
        return hasPath[0];
    }

    /**
     * Helper method for DFS path sum calculation
     * @param root: Current node being processed
     * @param currSum: Running sum from root to current node
     * @param targetSum: Target sum we're looking for
     * @param hasPath: Boolean array to track if path found
     */
    private static void findPathSum(TreeNode root, int currSum, int targetSum, boolean[] hasPath) {
        if (root == null)
            return;                                // Base case: empty tree

        currSum += root.val;                       // Add current node to path sum

        if (root.left == null && root.right == null) {
            if (currSum == targetSum) {            // Found path with target sum
                hasPath[0] = true;
            }
            return;
        }

        findPathSum(root.left, currSum, targetSum, hasPath);   // Try left path
        findPathSum(root.right, currSum, targetSum, hasPath);  // Try right path
    }

    /**
     * Approach 2: DFS with Target Reduction (Efficient)
     * 
     * Intuition:
     * - Subtract node values from target while traversing down
     * - At leaf, check if remaining target equals leaf value
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static boolean hasPathSumEfficient(TreeNode root, int targetSum) {
        if (root == null)
            return false;                   // Empty tree
            
        if (root.left == null && root.right == null)
            return root.val == targetSum;   // Leaf node check
            
        return hasPathSumEfficient(root.left, targetSum - root.val)   // Check left path
            || hasPathSumEfficient(root.right, targetSum - root.val); // Check right path
    }
}