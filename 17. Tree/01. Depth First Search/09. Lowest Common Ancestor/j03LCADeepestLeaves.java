/**
 * LeetCode 1123: Lowest Common Ancestor of Deepest Leaves
 * 
 * Problem Statement:
 *     Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
 *     Recall that the lowest common ancestor of a set S of nodes, is the node A with the largest
 *     depth such that every node in S is in the subtree with root A.
 * 
 * Input:
 *     - Root node of binary tree
 * 
 * Output:
 *     - TreeNode that is the LCA of deepest leaves
 * 
 * Example:
 *     Input: 
 *           3
 *          / \
 *         5   1
 *        / \   \
 *       6   2   8
 *          /
 *         7
 *     
 *     Output: 2
 *     Explanation: 
 *     - Deepest leaves are 7 and 8 (at depth 3)
 *     - Node 2 is their lowest common ancestor
 */


public class j03LCADeepestLeaves {

    /**
     * TreeNode class representing each node in the binary tree
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Pair class to store node with its level
     * 
     * Intuition:
     * - Need to track both node and its depth
     * - Helps compare depths of subtrees
     * - Used to find deepest leaves and their LCA
     */
    static class Pair {
        TreeNode node;    // Current tree node
        int level;       // Depth of node from root

        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    /**
     * Approach: Bottom-up DFS with Level Tracking
     * 
     * Intuition:
     * - Use DFS to find deepest leaves
     * - Track depth of each node
     * - Compare depths of left and right subtrees
     * - Return node where deepest leaves meet
     * 
     * Example visualization:
     *           3 (0)
     *          /     \
     *       5 (1)    1 (1)
     *      /     \      \
     *   6 (2)   2 (2)   8 (2)
     *          /
     *       7 (3)
     * 
     * Numbers in brackets show level
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(h) - recursion stack height
     * 
     * @param root Root node of binary tree
     * @return LCA of deepest leaves
     */
    public static TreeNode lcaDeepestLeaves(TreeNode root) {
        Pair out = lcaDeep(root, 0);
        return out.node;
    }

    /**
     * Helper method to perform DFS and find LCA
     * 
     * Intuition:
     * - If node is null, return level-1 (not a valid level)
     * - If leaf node, return current node and level
     * - Compare levels of left and right subtrees:
     *   - If equal, current node is LCA
     *   - If different, return deeper subtree
     * 
     * @param root Current node being processed
     * @param level Current depth in tree
     * @return Pair containing potential LCA and its level
     */
    public static Pair lcaDeep(TreeNode root, int level) {
        if (root == null) return new Pair(root, level - 1);   // Base case: empty tree
        if (root.left == null && root.right == null) {        // Base case: leaf node
            return new Pair(root, level);
        }

        Pair left = lcaDeep(root.left, level + 1);           // Process left subtree
        Pair right = lcaDeep(root.right, level + 1);         // Process right subtree

        // Compare subtree depths
        if (left.level == right.level)                        // Same depth -> current is LCA
            return new Pair(root, left.level);
        else if (left.level > right.level)                    // Left deeper -> return left
            return left;
        else                                                  // Right deeper -> return right
            return right;
    }

    /**
     * Main method to test the implementation
     */
    public static void main(String[] args) {
        // Test Case 1: Regular binary tree
        /**
         *           3
         *          / \
         *         5   1
         *        / \   \
         *       6   2   8
         *          /
         *         7
         */
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(2);
        root1.right.right = new TreeNode(8);
        root1.left.right.left = new TreeNode(7);
        
        System.out.println("Test Case 1 - Regular binary tree");
        System.out.println("Expected: 2");
        System.out.println("Output: " + lcaDeepestLeaves(root1).val);

        // Test Case 2: Empty tree
        System.out.println("\nTest Case 2 - Empty tree");
        System.out.println("Expected: null");
        System.out.println("Output: " + lcaDeepestLeaves(null));

        // Test Case 3: Single node tree
        TreeNode root3 = new TreeNode(1);
        System.out.println("\nTest Case 3 - Single node tree");
        System.out.println("Expected: 1");
        System.out.println("Output: " + lcaDeepestLeaves(root3).val);
    }
}
