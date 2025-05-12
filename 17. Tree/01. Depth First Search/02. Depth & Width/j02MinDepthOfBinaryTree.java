/**
 * Problem Statement:
 *     LeetCode 111. Minimum Depth of Binary Tree
 * 
 *     Given a binary tree, find its minimum depth. The minimum depth is the number
 *     of nodes along the shortest path from the root node down to the nearest leaf node.
 *     Note: A leaf is a node with no children.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can be empty or have any number of nodes
 * 
 * Output:
 *     - Integer representing minimum depth of tree
 * 
 * Example:
 *     Input: root = [3,9,20,null,null,15,7]
 *           3
 *          / \
 *         9  20
 *            / \
 *           15  7
 *     Output: 2 (path: 3->9)
 */

public class j02MinDepthOfBinaryTree {
    
    /**
     * TreeNode class represents a node in binary tree
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
     * Approach 1: Pre-order DFS (Root->Left->Right)
     * 
     * Intuition:
     * - Check base cases first:
     *   * Empty tree: return 0
     *   * Missing left child: only consider right subtree
     *   * Missing right child: only consider left subtree
     * - Otherwise, take minimum of left and right depths
     * 
     * Time: O(n) - visit each node once
     * Space: O(h) - recursion stack depth
     */
    public static int minDepthPree(TreeNode root) {
        if (root == null)
            return 0;                                    // Empty tree
        
        if (root.left == null) {
            return minDepthPree(root.right) + 1;        // Only right subtree
        }
        
        if (root.right == null) {
            return minDepthPree(root.left) + 1;         // Only left subtree
        }
        
        int leftMinDepth = minDepthPree(root.left);     // Get left depth
        int rightMinDepth = minDepthPree(root.right);   // Get right depth
        return Math.min(rightMinDepth, leftMinDepth) + 1;  // Return min + 1
    }

    /**
     * Approach 2: Post-order DFS (Left->Right->Root)
     * 
     * Intuition:
     * - Process children before root
     * - Base case: empty tree returns 0
     * - Get depths of both subtrees
     * - Handle cases where one subtree is empty
     * - Return minimum of non-empty depths
     * 
     * Time: O(n) - visit each node once
     * Space: O(h) - recursion stack depth
     */
    public static int minDepthPost(TreeNode root) {
        if(root == null) return 0;                      // Empty tree
        
        int leftMinDepth = minDepthPost(root.left);     // Get left depth
        int rightMinDepth = minDepthPost(root.right);   // Get right depth
        
        if(leftMinDepth == 0)                          // Left subtree empty
            return rightMinDepth + 1;
        if(rightMinDepth == 0)                         // Right subtree empty
            return leftMinDepth + 1;
            
        return Math.min(rightMinDepth,leftMinDepth) + 1;  // Return min + 1
    }
}
