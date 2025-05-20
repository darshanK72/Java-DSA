/**
 * Problem Statement:
 *     LeetCode 671. Second Minimum Node In a Binary Tree
 * 
 *     Given a binary tree where:
 *     1. Each node has either 0 or 2 children
 *     2. The values of root's children are equal to or greater than root's value
 *     Find the second minimum value in the tree. Return -1 if no such value exists.
 * 
 * Input:
 *     - Root node of special binary tree
 *     - Tree follows given properties
 * 
 * Output:
 *     - Second minimum value in tree
 *     - -1 if no second minimum exists
 * 
 * Example:
 *     Input: 
 *           2
 *          / \
 *         2   5
 *        / \
 *       5   7
 *     
 *     Output: 5
 *     Explanation: 
 *     - Minimum value is 2
 *     - Second minimum value is 5
 */

public class j03SecondMinNodeInBinaryTree {

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
        // Test Case 1: Basic tree
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(5);
        root1.left.right = new TreeNode(7);
        
        System.out.println("Test Case 1: " + 
            findSecondMinimumValue(root1));  // Expected: 5

        // Test Case 2: No second minimum
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        
        System.out.println("Test Case 2: " + 
            findSecondMinimumValue(root2));  // Expected: -1

        // Test Case 3: Multiple same values
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(3);
        root3.left.left = new TreeNode(1);
        root3.left.right = new TreeNode(2);
        
        System.out.println("Test Case 3: " + 
            findSecondMinimumValue(root3));  // Expected: 2
    }

    /**
     * Approach : DFS with Special Property Approach
     * 
     * Intuition:
     * 1. The root always contains the minimum value in tree
     * 2. We only need to look for values different from root
     * 3. When a node equals root value:
     *    - Need to search deeper for different value
     * 4. When a node differs from root:
     *    - Found a candidate for second minimum
     *    - No need to search deeper (due to property 2)
     * 
     * Time Complexity: O(n)
     * - Visit each node at most once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * 
     * @param root Root node of binary tree
     * @return Second minimum value in tree, -1 if not found
     */
    public static int findSecondMinimumValue(TreeNode root) {
        // Handle base cases
        if (root == null || root.left == null)
            return -1;
            
        // Get values of children
        int left = root.left.val;
        int right = root.right.val;
        
        // If left child equals root, search deeper
        if (left == root.val)
            left = findSecondMinimumValue(root.left);
            
        // If right child equals root, search deeper
        if (right == root.val)
            right = findSecondMinimumValue(root.right);
            
        // Both subtrees have valid values
        if (left != -1 && right != -1)
            return Math.min(left, right);
            
        // Return the valid value if any
        return (left != -1) ? left : right;
    }
}
