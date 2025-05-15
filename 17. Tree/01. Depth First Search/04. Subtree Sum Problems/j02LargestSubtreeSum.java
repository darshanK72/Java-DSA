/**
 * Problem Statement:
 *     Largest Subtree Sum
 * 
 *     Given a binary tree, find the largest subtree sum in the tree.
 *     A subtree is a tree that consists of a node and all of its descendants.
 *     The subtree sum is the sum of all node values in the subtree.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 *     - Node values can be negative
 * 
 * Output:
 *     - Integer representing largest subtree sum
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        / \   \
 *       4   5   6
 *     
 *     Subtree Sums:
 *     - Node 4: sum = 4
 *     - Node 5: sum = 5
 *     - Node 6: sum = 6
 *     - Node 2: sum = 2 + 4 + 5 = 11
 *     - Node 3: sum = 3 + 6 = 9
 *     - Root 1: sum = 1 + 11 + 9 = 21
 *     Output: 21 (entire tree has largest sum)
 */

public class j02LargestSubtreeSum {

    /**
     * Generic TreeNode class represents a node in binary tree
     * Contains data of type T and references to left and right children
     */
    static class TreeNode<T> {
        public T data;
        public TreeNode<T> left;
        public TreeNode<T> right;

        TreeNode(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree from example
        TreeNode<Integer> root1 = new TreeNode<>(1);
        root1.left = new TreeNode<>(2);
        root1.right = new TreeNode<>(3);
        root1.left.left = new TreeNode<>(4);
        root1.left.right = new TreeNode<>(5);
        root1.right.right = new TreeNode<>(6);
        System.out.println("Test Case 1: " + largestSubtreeSum(root1));  // Expected: 21

        // Test Case 2: Tree with negative values
        TreeNode<Integer> root2 = new TreeNode<>(-1);
        root2.left = new TreeNode<>(4);
        root2.right = new TreeNode<>(-5);
        root2.left.left = new TreeNode<>(2);
        System.out.println("Test Case 2: " + largestSubtreeSum(root2));  // Expected: 6

        // Test Case 3: Single node
        TreeNode<Integer> root3 = new TreeNode<>(5);
        System.out.println("Test Case 3: " + largestSubtreeSum(root3));  // Expected: 5

        // Test Case 4: Empty tree
        System.out.println("Test Case 4: " + largestSubtreeSum(null));   // Expected: Integer.MIN_VALUE
    }

    /**
     * Approach: Post-order DFS with Maximum Tracking
     * 
     * Intuition:
     * - Use post-order traversal to calculate subtree sums bottom-up
     * - Track maximum sum seen so far using array
     * - At each node:
     *   1. Get left and right subtree sums
     *   2. Calculate current subtree sum
     *   3. Update maximum if current sum is larger
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - Constant time operations at each node
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static int largestSubtreeSum(TreeNode<Integer> root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        largestSum(root, max);
        return max[0];
    }

    /**
     * Helper method to calculate subtree sums and track maximum
     * Returns: sum of current subtree
     * Updates: max[0] with largest sum seen so far
     */
    public static int largestSum(TreeNode<Integer> root, int[] max) {
        if (root == null)
            return 0;
        int left = largestSum(root.left, max);
        int right = largestSum(root.right, max);
        int currSum = left + right + root.data;
        if (currSum > max[0]) {
            max[0] = currSum;
        }
        return currSum;
    }
}
