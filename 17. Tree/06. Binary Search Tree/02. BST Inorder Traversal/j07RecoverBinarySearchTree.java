/**
 * LeetCode 99. Recover Binary Search Tree
 * 
 * Problem Statement:
 *     You have a BST in which exactly two nodes were swapped by mistake.
 *     Recover the tree without changing its structure.
 * 
 * Input:
 *     - TreeNode root (root node of incorrect BST)
 *     - Node values are integers
 * 
 * Output:
 *     - void (modify tree in-place)
 * 
 * Example:
 *     Input: [1,3,null,null,2]  (incorrect BST)
 *     Output: [3,1,null,null,2]  (corrected BST)
 * 
 *     Explanation:
 *     Before:        After:
 *        1             3
 *         \           /
 *          3         1
 *         /           \
 *        2             2
 */

public class j07RecoverBinarySearchTree {

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
     * Helper class to track nodes during traversal
     */
    static class Result {
        TreeNode prev;    // Previously visited node
        TreeNode first;   // First node that needs swapping
        TreeNode mid;     // Potential second node (adjacent case)
        TreeNode last;    // Second node that needs swapping (non-adjacent case)
    }

    /**
     * Main method to recover incorrect BST
     * 
     * Intuition:
     * - Use inorder traversal to detect violations
     * - Track nodes involved in violations
     * - Swap values of incorrect nodes
     * 
     * Explanation:
     * - Two cases possible:
     *   1. Adjacent nodes swapped: only one violation
     *   2. Non-adjacent nodes swapped: two violations
     * - Use Result class to track nodes across recursion
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree
     * 
     * @param root    Root node of incorrect BST
     */
    public static void recoverTree(TreeNode root) {
        Result result = new Result();
        recover(root, result);
        
        // Handle node swapping
        if (result.last != null) {
            // Non-adjacent nodes case
            int temp = result.first.val;
            result.first.val = result.last.val;
            result.last.val = temp;
        } else {
            // Adjacent nodes case
            int temp = result.first.val;
            result.first.val = result.mid.val;
            result.mid.val = temp;
        }
    }

    /**
     * Helper method for inorder traversal and violation detection
     * 
     * Intuition:
     * - Compare current node with previous node
     * - Mark violations when order is incorrect
     * 
     * Explanation:
     * - First violation: store both nodes (first and mid)
     * - Second violation: update last node
     * - Track previous node for comparisons
     * 
     * @param root     Current node being processed
     * @param result   Object tracking violation nodes
     */
    private static void recover(TreeNode root, Result result) {
        if (root == null) return;
        
        // Process left subtree
        recover(root.left, result);
        
        // Check for violation
        if (result.prev != null && result.prev.val > root.val) {
            if (result.first == null) {
                // First violation
                result.first = result.prev;
                result.mid = root;
            } else {
                // Second violation
                result.last = root;
            }
        }
        
        // Update previous node
        result.prev = root;
        
        // Process right subtree
        recover(root.right, result);
    }

    public static void main(String[] args) {
        // Test Case 1: Adjacent nodes swapped
        System.out.println("\nAdjacent Nodes Test:");
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.right.left = new TreeNode(2);  // Swapped with 4
        System.out.println("Before recovery:");
        printTree(root1, "", "Root: ");
        recoverTree(root1);
        System.out.println("After recovery:");
        printTree(root1, "", "Root: ");

        // Test Case 2: Non-adjacent nodes swapped
        System.out.println("\nNon-adjacent Nodes Test:");
        TreeNode root2 = new TreeNode(1);  // Should be 3
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);  // Should be 1
        System.out.println("Before recovery:");
        printTree(root2, "", "Root: ");
        recoverTree(root2);
        System.out.println("After recovery:");
        printTree(root2, "", "Root: ");
    }

    /**
     * Helper method to print BST structure
     */
    private static void printTree(TreeNode root, String indent, String prefix) {
        if (root == null) {
            System.out.println(indent + prefix + "null");
            return;
        }
        System.out.println(indent + prefix + root.val);
        printTree(root.left, indent + "    ", "L── ");
        printTree(root.right, indent + "    ", "R── ");
    }
}
