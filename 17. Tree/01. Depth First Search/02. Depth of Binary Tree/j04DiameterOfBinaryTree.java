/**
 * Problem Statement:
 *     LeetCode 543. Diameter of Binary Tree
 * 
 *     Given the root of a binary tree, return the length of the diameter of the tree.
 *     The diameter of a binary tree is the length of the longest path between any two
 *     nodes in a tree. This path may or may not pass through the root.
 *     The length of a path between two nodes is represented by the number of edges
 *     between them.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can be empty or have any number of nodes
 * 
 * Output:
 *     - Integer representing the diameter of the tree (length of longest path)
 * 
 * Example:
 *     Input: root = [1,2,3,4,5]
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     Output: 3 (path: 4->2->1->3 or 5->2->1->3)
 * 
 *     Input: root = [1,2]
 *           1
 *          /
 *         2
 *     Output: 1 (path: 2->1)
 */

public class j04DiameterOfBinaryTree {

    /**
     * TreeNode class represents a node in binary tree
     */
    static class TreeNode {
        int val;        // Value stored in the node
        TreeNode left;  // Reference to left child
        TreeNode right; // Reference to right child

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String args[]) {
        // Test Case 1: Empty Tree
        System.out.println("Test Case 1: Empty Tree");
        TreeNode root1 = null;
        System.out.println("Diameter: " + diameterOfBinaryTree(root1));
        System.out.println();

        // Test Case 2: Single Node
        System.out.println("Test Case 2: Single Node");
        TreeNode root2 = new TreeNode(1);
        System.out.println("Diameter: " + diameterOfBinaryTree(root2));
        System.out.println();

        // Test Case 3: Simple Balanced Tree
        //       1
        //      / \
        //     2   3
        System.out.println("Test Case 3: Simple Balanced Tree");
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        System.out.println("Diameter: " + diameterOfBinaryTree(root3));
        System.out.println();

        // Test Case 4: Complex Balanced Tree
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        System.out.println("Test Case 4: Complex Balanced Tree");
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        root4.left.left = new TreeNode(4);
        root4.left.right = new TreeNode(5);
        System.out.println("Diameter: " + diameterOfBinaryTree(root4));
        System.out.println();

        // Test Case 5: Left Skewed Tree
        // 1
        //  \
        //   2
        //    \
        //     3
        System.out.println("Test Case 5: Right Skewed Tree");
        TreeNode root5 = new TreeNode(1);
        root5.right = new TreeNode(2);
        root5.right.right = new TreeNode(3);
        System.out.println("Diameter: " + diameterOfBinaryTree(root5));
        System.out.println();

        // Test Case 6: Complex Unbalanced Tree
        //       1
        //      / \
        //     2   3
        //    /     \
        //   4       5
        //  /         \
        // 6           7
        System.out.println("Test Case 6: Complex Unbalanced Tree");
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(2);
        root6.right = new TreeNode(3);
        root6.left.left = new TreeNode(4);
        root6.right.right = new TreeNode(5);
        root6.left.left.left = new TreeNode(6);
        root6.right.right.right = new TreeNode(7);
        System.out.println("Diameter: " + diameterOfBinaryTree(root6));
        System.out.println();

        // Test Case 7: Maximum Diameter Not Through Root
        //       1
        //      / \
        //     2   3
        //    /     \
        //   4       5
        //  /         \
        // 6           7
        //  \         /
        //   8       9
        System.out.println("Test Case 7: Maximum Diameter Not Through Root");
        TreeNode root7 = new TreeNode(1);
        root7.left = new TreeNode(2);
        root7.right = new TreeNode(3);
        root7.left.left = new TreeNode(4);
        root7.right.right = new TreeNode(5);
        root7.left.left.left = new TreeNode(6);
        root7.right.right.right = new TreeNode(7);
        root7.left.left.left.right = new TreeNode(8);
        root7.right.right.right.left = new TreeNode(9);
        System.out.println("Diameter: " + diameterOfBinaryTree(root7));
    }

    /**
     * Approach: Post-order DFS with Global Variable
     * 
     * Intuition:
     * - The diameter of a tree is the maximum of:
     *   1. Diameter of left subtree
     *   2. Diameter of right subtree
     *   3. Longest path passing through root (left height + right height)
     * - Use post-order traversal to calculate heights and update diameter
     * - Use array to store diameter (similar to global variable) for pass by reference
     * 
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack depth, where h is height of tree
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        // Base case: empty tree has diameter 0
        if (root == null)
            return 0;
        
        // Base case: single node has diameter 0
        if (root.left == null && root.right == null)
            return 0;
        
        // Array to store diameter (using array to pass by reference)
        int[] diameter = new int[1];
        
        // Calculate max depth and update diameter
        maxDepth(root, diameter);
        
        // Return diameter - 1 because diameter is number of edges
        return diameter[0] - 1;
    }

    /**
     * Helper method to calculate max depth and update diameter
     * 
     * @param root Current node
     * @param diameter Array to store maximum diameter found
     * @return Maximum depth of current subtree
     */
    public static int maxDepth(TreeNode root, int[] diameter) {
        // Base case: empty tree has depth 0
        if (root == null)
            return 0;
        
        // Calculate max depth of left subtree
        int leftMaxDepth = maxDepth(root.left, diameter);
        
        // Calculate max depth of right subtree
        int rightMaxDepth = maxDepth(root.right, diameter);
        
        // Update diameter if current path is longer
        // Add 1 for current node
        diameter[0] = Math.max(diameter[0], leftMaxDepth + rightMaxDepth + 1);
        
        // Return max depth of current subtree
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
