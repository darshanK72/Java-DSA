/**
 * Problem Statement:
 *     Children Sum Property Conversion (Striver SDE Sheet)
 * 
 *     Given a binary tree, convert it into a tree that follows children sum property:
 *     - Parent node's value = Sum of left child + right child
 *     - Can only increment node values, not decrement
 *     - Can update nodes any number of times
 * 
 * Input:
 *     - Root node of binary tree
 * 
 * Output:
 *     - Modified tree following children sum property
 * 
 * Example:
 *     Input: 
 *           2
 *          / \
 *         35  10
 *        /  \
 *       2    3
 *     
 *     Output:
 *           45
 *          /  \
 *         35  10
 *        /  \
 *       2    3
 */

public class j06ChildrenSumProperty {

    /**
     * TreeNode class represents a node in binary tree
     */
    public static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int x) {
            data = x;
        }
    }

     public static void main(String[] args) {
        // Test Case 1: Basic tree
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(35);
        root1.right = new TreeNode(10);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(3);

        System.out.println("Before modification:");
        printInorder(root1);
        changeTree(root1);
        System.out.println("\nAfter modification:");
        printInorder(root1);

        // Test Case 2: Single path tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(4);
        root2.left.left = new TreeNode(6);

        System.out.println("\n\nBefore modification:");
        printInorder(root2);
        changeTree(root2);
        System.out.println("\nAfter modification:");
        printInorder(root2);
    }

    /**
     * Helper method to print tree inorder
     */
    private static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    /**
     * Two-Phase DFS Approach
     * 
     * Intuition:
     * Phase 1 (Top-down):
     * - If child sum > node value: Update node to child sum
     * - If node value > child sum: Update children to node value
     * - This ensures we only increment values
     * 
     * Phase 2 (Bottom-up):
     * - After processing children recursively
     * - Update current node to sum of children
     * - This ensures parent = sum of children
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * 
     * @param root Root node of binary tree to modify
     */
    public static void changeTree(TreeNode root) {
        if (root == null)
            return;

        // Calculate current child sum
        int childSum = 0;
        if (root.left != null) {
            childSum += root.left.data;
        }
        if (root.right != null) {
            childSum += root.right.data;
        }

        // Phase 1: Update values downwards
        if (root.data < childSum) {
            root.data = childSum;
        } else {
            // Propagate parent value to children
            if (root.left != null)
                root.left.data = root.data;
            if (root.right != null)
                root.right.data = root.data;
        }

        // Process children recursively
        changeTree(root.left);
        changeTree(root.right);

        // Phase 2: Update parent with new child sum
        int total = 0;
        if (root.left != null)
            total += root.left.data;
        if (root.right != null)
            total += root.right.data;
        if (total > 0)  // Update only if not leaf
            root.data = total;
    }
}
