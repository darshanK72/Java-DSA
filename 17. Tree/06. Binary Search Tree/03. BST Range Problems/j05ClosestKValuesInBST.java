/**
 * LeetCode 272. Closest Binary Search Tree Value II
 * 
 * Problem Statement:
 *     Given a Binary Search Tree (BST), a target value and an integer k, return
 *     k values in the BST that are closest to the target in sorted order.
 * 
 * Input:
 *     - root: Root node of BST
 *     - target: Target value to find closest elements (can be decimal)
 *     - k: Number of closest values to return
 * 
 * Output:
 *     - ArrayList<Integer> containing k closest values in sorted order
 * 
 * Example:
 *     Input: 
 *              4
 *            /   \
 *           2     5
 *          / \
 *         1   3
 *         target = 3.714286, k = 2
 *     
 *     Output: [3, 4]
 *     
 *     Explanation:
 *     3 and 4 are the two values closest to target 3.714286
 */

import java.util.ArrayList;

public class j05ClosestKValuesInBST {
    
    static class TreeNode<T> {
        T val;
        TreeNode<T> left, right;

        TreeNode(T item) {
            val = item;
            left = right = null;
        }
    }
    
    /**
     * Approach: Inorder Traversal with Sliding Window
     * 
     * Intuition:
     * - Use inorder traversal to get values in sorted order
     * - Maintain k closest values in a sliding window
     * - Update window when finding closer values
     * 
     * Explanation:
     * - Step 1: Traverse BST in inorder fashion
     * - Step 2: For first k elements, simply add to result
     * - Step 3: For subsequent elements, compare with current window
     * - Step 4: If new element is closer than window start, update window
     * 
     * Time Complexity: O(N) where N is number of nodes
     * Space Complexity: O(H + k) where H is height and k is window size
     * 
     * @param root      Root node of BST
     * @param target    Target value to find closest elements
     * @param k         Number of closest values to return
     * @return          ArrayList containing k closest values in sorted order
     */
    public static ArrayList<Integer> kClosestValues(TreeNode<Integer> root, double target, int k) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        if (root == null || k < 1)
            return out;
        double[] diff = new double[1];
        diff[0] = Double.MAX_VALUE;
        fillKClosestValues(root, target, k, out, diff);
        return out;
    }

    /**
     * Helper Method: Fill K Closest Values
     * 
     * Intuition:
     * - Use inorder traversal to process nodes in sorted order
     * - Maintain sliding window of size k with closest values
     * 
     * Explanation:
     * - Step 1: Process left subtree
     * - Step 2: If window not full, add current value
     * - Step 3: If window full, compare with current max difference
     * - Step 4: Update window if current value is closer
     * - Step 5: Process right subtree
     * 
     * @param root      Current node being processed
     * @param target    Target value for comparison
     * @param k         Size of sliding window
     * @param out       ArrayList to store result
     * @param maxDiff   Current maximum difference in window
     */
    private static void fillKClosestValues(TreeNode<Integer> root, double target, int k, 
                                         ArrayList<Integer> out, double[] maxDiff) {
        if (root == null)
            return;
            
        // Process left subtree
        fillKClosestValues(root.left, target, k, out, maxDiff);
        
        // If window not full, add current value
        if (out.size() < k) {
            out.add(root.val);
            if (out.size() == k) {
                maxDiff[0] = Math.max(Math.abs(target - out.get(0)), 
                                    Math.abs(target - out.get(out.size() - 1)));
            }
        } 
        // If window full, update if current value is closer
        else {
            double diff = Math.abs(target - root.val);
            if (diff < maxDiff[0]) {
                out.remove(0);
                out.add(root.val);
            }
            maxDiff[0] = Math.max(Math.abs(target - out.get(0)), 
                                Math.abs(target - out.get(out.size() - 1)));
        }
        
        // Process right subtree
        fillKClosestValues(root.right, target, k, out, maxDiff);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        TreeNode<Integer> root1 = new TreeNode<>(4);
        root1.left = new TreeNode<>(2);
        root1.right = new TreeNode<>(5);
        root1.left.left = new TreeNode<>(1);
        root1.left.right = new TreeNode<>(3);
        
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: target=3.714286, k=2");
        System.out.println("Expected: [3, 4], Output: " + 
                          kClosestValues(root1, 3.714286, 2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: null tree, k=2");
        System.out.println("Expected: [], Output: " + 
                          kClosestValues(null, 3.0, 2));

        // Test Case 3: Boundary cases
        TreeNode<Integer> singleNode = new TreeNode<>(5);
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: Single node, k=1");
        System.out.println("Expected: [5], Output: " + 
                          kClosestValues(singleNode, 4.5, 1));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: k=0");
        System.out.println("Expected: [], Output: " + 
                          kClosestValues(root1, 3.0, 0));
    }
}
