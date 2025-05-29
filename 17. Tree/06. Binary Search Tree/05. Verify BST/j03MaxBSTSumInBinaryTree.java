/**
 * LeetCode 1373. Maximum Sum BST in Binary Tree
 * 
 * Problem Statement:
 *     Given a binary tree root, return the maximum sum of all keys of any sub-tree
 *     which is also a Binary Search Tree (BST). A subtree of a binary tree is a
 *     tree that consists of a node in the original tree and all of this node's
 *     descendants.
 * 
 * Input:
 *     - root: TreeNode (root of the binary tree)
 * 
 * Output:
 *     - int: Maximum sum of any valid BST subtree
 * 
 * Example:
 *     Input: [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 *     Output: 20
 * 
 *     Explanation:
 *     The subtree rooted at node 3 is a BST with sum = 20
 *     The subtree rooted at node 4 is a BST with sum = 15
 *     The subtree rooted at node 5 is a BST with sum = 6
 */

public class j03MaxBSTSumInBinaryTree {

    /**
     * TreeNode class to represent nodes in the binary tree
     * Each node contains:
     * - val: integer value
     * - left: reference to left child
     * - right: reference to right child
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Result class to store information about a subtree
     * - sum: sum of all nodes in the subtree
     * - min: minimum value in the subtree
     * - max: maximum value in the subtree
     */
    static class Result {
        int sum;
        long min;
        long max;

        Result() {
            this.sum = 0;
            this.min = Integer.MAX_VALUE;
            this.max = Integer.MIN_VALUE;
        }

        Result(int sum, long min, long max) {
            this.sum = sum;
            this.min = min;
            this.max = max;
        }
    }

    /**
     * Approach: Post-order Traversal with BST Validation
     * 
     * Intuition:
     * - We need to check if each subtree is a valid BST
     * - For a valid BST, left subtree's max < root.val < right subtree's min
     * - We track min, max, and sum for each subtree
     * - Use post-order traversal to process children before parent
     * 
     * Explanation:
     * - Step 1: Create a Result class to track sum, min, and max of each subtree
     * - Step 2: Use post-order traversal to process each node
     * - Step 3: For each node, validate BST property using min/max from children
     * - Step 4: Update global maxSum if current subtree is valid BST
     * 
     * Time Complexity: O(N) where N is number of nodes in the tree
     * Space Complexity: O(H) where H is height of the tree (recursion stack)
     * 
     * @param root    Root node of the binary tree
     * @return        Maximum sum of any valid BST subtree
     */
    public static int maxSumBST(TreeNode root) {
        // Initialize array to store maxSum (using array for pass-by-reference)
        int[] maxSum = new int[1];
        maxSum[0] = 0;
        maxSumBSTInBT(root, maxSum);
        return maxSum[0];
    }

    /**
     * Helper Method: Recursive function to process each subtree
     * 
     * Intuition:
     * - Process left and right subtrees first
     * - Validate BST property at current node
     * - Update maxSum if valid BST found
     * 
     * Explanation:
     * - Step 1: Base case returns empty Result for null nodes
     * - Step 2: Get results from left and right subtrees
     * - Step 3: Check if current subtree is valid BST
     * - Step 4: Update maxSum and return appropriate Result
     * 
     * Time Complexity: O(1) per node
     * Space Complexity: O(H) for recursion stack
     * 
     * @param root    Current node being processed
     * @param maxSum  Array to store maximum sum found
     * @return        Result containing sum, min, and max of current subtree
     */
    public static Result maxSumBSTInBT(TreeNode root, int[] maxSum) {
        // Base case: return empty Result for null nodes
        if (root == null)
            return new Result();

        // Process left and right subtrees
        Result left = maxSumBSTInBT(root.left, maxSum);
        Result right = maxSumBSTInBT(root.right, maxSum);

        // Check if current subtree is valid BST
        if (root.val > left.max && root.val < right.min) {
            // Calculate sum of valid BST
            int sum = (left.sum + right.sum + root.val);
            // Update global maximum sum
            maxSum[0] = Math.max(maxSum[0], sum);
            // Return Result with updated min and max
            return new Result(sum, Math.min(root.val, left.min), Math.max(root.val, right.max));
        }

        // Return invalid BST Result
        return new Result(Math.max(left.sum, right.sum), Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(2);
        root1.right.right = new TreeNode(5);
        root1.right.right.left = new TreeNode(4);
        root1.right.right.right = new TreeNode(6);
        System.out.println("Input: [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]");
        System.out.println("Expected: 20, Output: " + maxSumBST(root1));

        // Test Case 2: Empty tree
        System.out.println("\nEdge Case - Empty Tree:");
        System.out.println("Input: null");
        System.out.println("Expected: 0, Output: " + maxSumBST(null));

        // Test Case 3: Single node
        System.out.println("\nEdge Case - Single Node:");
        TreeNode root3 = new TreeNode(5);
        System.out.println("Input: [5]");
        System.out.println("Expected: 5, Output: " + maxSumBST(root3));
    }
}
