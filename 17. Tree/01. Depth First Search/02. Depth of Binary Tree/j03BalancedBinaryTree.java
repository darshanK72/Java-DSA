/**
 * Problem Statement:
 *     LeetCode 110. Balanced Binary Tree
 * 
 *     Given a binary tree, determine if it is height-balanced. A height-balanced
 *     binary tree is defined as a binary tree in which the left and right subtrees
 *     of every node differ in height by no more than 1.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can be empty or have any number of nodes
 * 
 * Output:
 *     - Boolean indicating if tree is height-balanced
 * 
 * Example:
 *     Input: root = [3,9,20,null,null,15,7]
 *           3
 *          / \
 *         9  20
 *            / \
 *           15  7
 *     Output: true
 * 
 *     Input: root = [1,2,2,3,3,null,null,4,4]
 *           1
 *          / \
 *         2   2
 *        / \
 *       3   3
 *      / \
 *     4   4
 *     Output: false
 */

public class j03BalancedBinaryTree {

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
        System.out.println("Approach 1 (Top-down): " + isBalanced(root1));
        System.out.println("Approach 2 (Global Var): " + isBalancedGlobalVariable(root1));
        System.out.println("Approach 3 (Pair): " + isBalancedUsingPair(root1));
        System.out.println("Approach 4 (Array): " + isBalancedUsingArray(root1));
        System.out.println();

        // Test Case 2: Single Node
        System.out.println("Test Case 2: Single Node");
        TreeNode root2 = new TreeNode(1);
        System.out.println("Approach 1 (Top-down): " + isBalanced(root2));
        System.out.println("Approach 2 (Global Var): " + isBalancedGlobalVariable(root2));
        System.out.println("Approach 3 (Pair): " + isBalancedUsingPair(root2));
        System.out.println("Approach 4 (Array): " + isBalancedUsingArray(root2));
        System.out.println();

        // Test Case 3: Balanced Tree
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        System.out.println("Test Case 3: Balanced Tree");
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.left.left = new TreeNode(4);
        root3.left.right = new TreeNode(5);
        System.out.println("Approach 1 (Top-down): " + isBalanced(root3));
        System.out.println("Approach 2 (Global Var): " + isBalancedGlobalVariable(root3));
        System.out.println("Approach 3 (Pair): " + isBalancedUsingPair(root3));
        System.out.println("Approach 4 (Array): " + isBalancedUsingArray(root3));
        System.out.println();

        // Test Case 4: Unbalanced Tree
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        //  /
        // 6
        System.out.println("Test Case 4: Unbalanced Tree");
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        root4.left.left = new TreeNode(4);
        root4.left.right = new TreeNode(5);
        root4.left.left.left = new TreeNode(6);
        System.out.println("Approach 1 (Top-down): " + isBalanced(root4));
        System.out.println("Approach 2 (Global Var): " + isBalancedGlobalVariable(root4));
        System.out.println("Approach 3 (Pair): " + isBalancedUsingPair(root4));
        System.out.println("Approach 4 (Array): " + isBalancedUsingArray(root4));
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
        System.out.println("Approach 1 (Top-down): " + isBalanced(root5));
        System.out.println("Approach 2 (Global Var): " + isBalancedGlobalVariable(root5));
        System.out.println("Approach 3 (Pair): " + isBalancedUsingPair(root5));
        System.out.println("Approach 4 (Array): " + isBalancedUsingArray(root5));
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
        System.out.println("Approach 1 (Top-down): " + isBalanced(root6));
        System.out.println("Approach 2 (Global Var): " + isBalancedGlobalVariable(root6));
        System.out.println("Approach 3 (Pair): " + isBalancedUsingPair(root6));
        System.out.println("Approach 4 (Array): " + isBalancedUsingArray(root6));
    }

    /**
     * Approach 1: Top-down Recursion
     * 
     * Intuition:
     * - For each node, check if its left and right subtrees are balanced
     * - Calculate height of left and right subtrees
     * - If height difference > 1, tree is not balanced
     * - Recursively check balance for left and right subtrees
     * 
     * Time Complexity: O(n²) - For each node, we calculate height which is O(n)
     * Space Complexity: O(h) - Recursion stack depth, where h is height of tree
     */
    public static boolean isBalanced(TreeNode root) {
        // Base case: empty tree is balanced
        if (root == null)
            return true;
        
        // Calculate heights of left and right subtrees
        int left = height(root.left);
        int right = height(root.right);
        
        // Check if current node is balanced
        if (Math.abs(left - right) > 1)
            return false;
        
        // Recursively check if left and right subtrees are balanced
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public static int height(TreeNode root) {
        // Base case: height of empty tree is 0
        if (root == null)
            return 0;
        
        // Calculate heights of left and right subtrees
        int left = height(root.left);
        int right = height(root.right);
        
        // Return maximum height plus 1 for current node
        return Math.max(left, right) + 1;
    }

    /**
     * Approach 2: Bottom-up Recursion with Global Variable
     * 
     * Intuition:
     * - Use a global variable to track if tree is balanced
     * - Calculate height and check balance in single pass
     * - If height difference > 1, set global variable to false
     * - More efficient than Approach 1 as we avoid recalculating heights
     * 
     * Time Complexity: O(n) - Single pass through tree
     * Space Complexity: O(h) - Recursion stack depth
     */
    public static boolean isBalanced = true;  // Global variable to track balance status

    public  static boolean isBalancedGlobalVariable(TreeNode root) {
        // Reset global variable before checking
        isBalanced = true;
        // Calculate height and check balance
        heightGlobalVariable(root);
        return isBalanced;
    }

    public static int heightGlobalVariable(TreeNode root) {
        // Base case: height of empty tree is 0
        if (root == null)
            return 0;
        
        // Calculate heights of left and right subtrees
        int left = heightGlobalVariable(root.left);
        int right = heightGlobalVariable(root.right);
        
        // Check if current node is balanced
        if (Math.abs(left - right) > 1) {
            isBalanced = false;  // Set global variable to false
            return -1;           // Return -1 to indicate imbalance
        }
        
        // Return maximum height plus 1 for current node
        return Math.max(left, right) + 1;
    }

    /**
     * Approach 3: Bottom-up Recursion with Pair Class
     * 
     * Intuition:
     * - Use a Pair class to store both height and balance status
     * - Return both height and balance status from each recursive call
     * - More elegant than using global variable
     * - Early return if any subtree is unbalanced
     * 
     * Time Complexity: O(n) - Single pass through tree
     * Space Complexity: O(h) - Recursion stack depth
     */
    static class Pair {
        int height;      // Height of the subtree
        boolean isBalanced;  // Balance status of the subtree

        public Pair(int h, boolean b) {
            this.height = h;
            this.isBalanced = b;
        }
    }

    public static boolean isBalancedUsingPair(TreeNode root) {
        // Get balance status from the root
        return heightUsingPair(root).isBalanced;
    }

    public static Pair heightUsingPair(TreeNode root) {
        // Base case: empty tree is balanced with height 0
        if (root == null)
            return new Pair(0, true);
        
        // Get balance status and height of left subtree
        Pair left = heightUsingPair(root.left);
        // Get balance status and height of right subtree
        Pair right = heightUsingPair(root.right);
        
        // Early return if left subtree is unbalanced
        if (!left.isBalanced)
            return left;
        // Early return if right subtree is unbalanced
        if (!right.isBalanced)
            return right;
        
        // Check if current node is balanced
        if (Math.abs(left.height - right.height) > 1) {
            return new Pair(-1, false);  // Return unbalanced status
        }
        
        // Return balanced status with current height
        return new Pair(Math.max(left.height, right.height) + 1, true);
    }

    /**
     * Approach 4: Bottom-up Recursion with Array
     * 
     * Intuition:
     * - Use array to store balance status (similar to global variable)
     * - More memory efficient than Pair class
     * - Single pass through tree
     * - Early return if tree is unbalanced
     * 
     * Time Complexity: O(n) - Single pass through tree
     * Space Complexity: O(h) - Recursion stack depth
     */
    public static boolean isBalancedUsingArray(TreeNode root) {
        // Array to store balance status (using array to pass by reference)
        boolean[] isBalanced = new boolean[1];
        // Calculate height and check balance
        heightUsingArray(root, isBalanced);
        // Return opposite of isBalanced[0] as we set it to true when unbalanced
        return !isBalanced[0];
    }

    public static int heightUsingArray(TreeNode root, boolean[] isBalanced) {
        // Base case: height of empty tree is 0
        if (root == null)
            return 0;
        
        // Calculate heights of left and right subtrees
        int left = heightUsingArray(root.left, isBalanced);
        int right = heightUsingArray(root.right, isBalanced);
        
        // Check if current node is balanced
        if (Math.abs(left - right) > 1) {
            isBalanced[0] = true;  // Set imbalance flag
            return -1;             // Return -1 to indicate imbalance
        }
        
        // Return maximum height plus 1 for current node
        return Math.max(left, right) + 1;
    }
}