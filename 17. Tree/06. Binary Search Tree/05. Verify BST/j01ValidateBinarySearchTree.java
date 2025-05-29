/**
 * LeetCode 98. Validate Binary Search Tree
 * 
 * Problem Statement:
 *     Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *     A valid BST is defined as follows:
 *     - The left subtree of a node contains only nodes with keys less than the node's key.
 *     - The right subtree of a node contains only nodes with keys greater than the node's key.
 *     - Both the left and right subtrees must also be binary search trees.
 * 
 * Input:
 *     - root: Root node of binary tree
 *     - Number of nodes in range [1, 10^4]
 *     - Node values in range [-2^31, 2^31 - 1]
 * 
 * Output:
 *     - true if valid BST, false otherwise
 * 
 * Example 1:
 *     Input:     2
 *               / \
 *              1   3
 *     Output: true
 * 
 * Example 2:
 *     Input:     5
 *               / \
 *              1   4
 *                 / \
 *                3   6
 *     Output: false
 *     Explanation: Value 3 is less than root value 5
 */

public class j01ValidateBinarySearchTree {

    /**
     * Definition for binary tree node
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Approach 1: Inorder Traversal with Previous Value Tracking
     * 
     * Intuition:
     * - In BST, inorder traversal gives sorted sequence
     * - Track previous value to verify ascending order
     * - Use array to maintain state across recursive calls
     * 
     * Explanation:
     * - Step 1: Initialize tracking array
     *          - prev[0]: stores previous value (initialized to MIN_VALUE)
     *          - prev[1]: flag to indicate invalid BST (0: valid, 1: invalid)
     * 
     * - Step 2: Perform inorder traversal
     *          - Visit left subtree
     *          - Compare current value with previous
     *          - Update previous value
     *          - Visit right subtree
     * 
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack height
     * 
     * @param root Root of binary tree
     * @return true if valid BST, false otherwise
     */
    public static boolean isValidBSTI(TreeNode root) {
        long[] prev = new long[2];
        prev[0] = Long.MIN_VALUE;
        validateBST(root, prev);
        return prev[1] == 0;
    }

    /**
     * Helper Method: Inorder Traversal Validation
     * 
     * Intuition:
     * - Process nodes in inorder (left -> root -> right)
     * - Verify ascending order property
     * 
     * Explanation:
     * - Step 1: Base case - return on null node
     * - Step 2: Process left subtree
     * - Step 3: Validate current node's value
     * - Step 4: Update previous value
     * - Step 5: Process right subtree
     * 
     * @param root Current node being processed
     * @param prev Array storing [previousValue, invalidFlag]
     */
    public static void validateBST(TreeNode root, long[] prev) {
        if (root == null)
            return;
        validateBST(root.left, prev);
        if (prev[0] >= root.val) {
            prev[1] = 1;
            return;
        }
        prev[0] = root.val;
        validateBST(root.right, prev);
    }

    /**
     * Approach 2: Range Based Validation
     * 
     * Intuition:
     * - Each node must lie within valid range
     * - Range updates as we traverse down the tree
     * - Left subtree values must be less than current node
     * - Right subtree values must be greater than current node
     * 
     * Explanation:
     * - Step 1: Initialize with full range [-∞, +∞]
     * - Step 2: For left child, update max limit to current value
     * - Step 3: For right child, update min limit to current value
     * - Step 4: Recursively validate both subtrees
     * 
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack height
     * 
     * @param root Root of binary tree
     * @return true if valid BST, false otherwise
     */
    public static boolean isValidBSTII(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Helper Method: Range Based BST Validation
     * 
     * Intuition:
     * - Each node's value must fall within a valid range
     * - Range narrows down as we traverse deeper
     * - Use long to handle integer edge cases
     * 
     * Explanation:
     * - Step 1: Handle base case
     *          - Empty tree (null node) is valid BST
     *          - Return true for null nodes
     * 
     * - Step 2: Validate current node's value
     *          - Check if value lies within valid range (min, max)
     *          - Return false if value <= min or value >= max
     * 
     * - Step 3: Recursively validate subtrees
     *          - Left subtree: all values must be in range (min, current value)
     *          - Right subtree: all values must be in range (current value, max)
     *          - Both subtrees must be valid for current node to be valid
     * 
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack height
     * 
     * @param node Current node being validated
     * @param min Minimum allowed value for current subtree
     * @param max Maximum allowed value for current subtree
     * @return true if current subtree is valid BST, false otherwise
     */
    private static boolean isValidBST(TreeNode node, long min, long max) {
        // Base case: empty tree is valid BST
        if (node == null) {
            return true;
        }

        // Check if current node's value is within valid range
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Recursively validate left and right subtrees with updated ranges
        return isValidBST(node.left, min, node.val) && 
               isValidBST(node.right, node.val, max);
    }
}