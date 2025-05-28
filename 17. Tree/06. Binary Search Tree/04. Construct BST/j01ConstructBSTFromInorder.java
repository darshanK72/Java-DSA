/**
 * LeetCode 108. Convert Sorted Array to Binary Search Tree
 * 
 * Problem Statement:
 *     Given an integer array nums where the elements are sorted in ascending order,
 *     convert it to a height-balanced binary search tree (BST).
 * 
 * Input:
 *     - nums: Integer array sorted in ascending order
 * 
 * Output:
 *     - Root node of height-balanced BST
 * 
 * Example:
 *     Input: [-10,-3,0,5,9]
 *     Output: 
 *              0
 *            /   \
 *          -3     9
 *          /     /
 *        -10    5
 *     
 *     Explanation:
 *     - Middle element 0 becomes root
 *     - Left half [-10,-3] forms left subtree
 *     - Right half [5,9] forms right subtree
 *     - Process repeats recursively for each subtree
 */

import java.util.LinkedList;
import java.util.Queue;

public class j01ConstructBSTFromInorder {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int item) {
            val = item;
            left = right = null;
        }
    }

    /**
     * Explanation:
     * - Convert sorted array to balanced BST:
     *   1. Find middle element to be root
     *   2. Recursively build left subtree from left half
     *   3. Recursively build right subtree from right half
     *   4. Return root node
     * 
     * Time Complexity: O(N)
     * - Process each element exactly once
     * - Create N nodes in total
     * 
     * Space Complexity: O(log N)
     * - Recursion stack depth is tree height
     * - Tree is balanced, so height is log N
     * 
     * @param nums Sorted array of integers
     * @return     Root node of balanced BST
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        return convertInorderToBST(nums, 0, nums.length - 1);
    }

    /**
     * Helper Method: Convert Array Segment to BST
     * 
     * Explanation:
     * - Converts array segment [s,e] to balanced BST:
     *   1. Base case: if start > end, return null
     *   2. Find middle index of current segment
     *   3. Create root node with middle element
     *   4. Recursively build left subtree from [s, mid-1]
     *   5. Recursively build right subtree from [mid+1, e]
     * 
     * @param nums Array containing sorted elements
     * @param s    Start index of current segment
     * @param e    End index of current segment
     * @return     Root node of BST for current segment
     */
    public static TreeNode convertInorderToBST(int[] nums, int s, int e) {
        if (s > e)
            return null;
        
        // Find middle element for root
        int mid = s + (e - s) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        
        // Recursively construct left and right subtrees
        root.left = convertInorderToBST(nums, s, mid - 1);
        root.right = convertInorderToBST(nums, mid + 1, e);
        
        return root;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[] nums1 = {-10, -3, 0, 5, 9};
        System.out.println("\nBasic Test Case:");
        printLevelOrder(sortedArrayToBST(nums1));

        // Test Case 2: Edge case - empty array
        int[] nums2 = {};
        System.out.println("\nEmpty Array Test Case:");
        printLevelOrder(sortedArrayToBST(nums2));

        // Test Case 3: Single element
        int[] nums3 = {1};
        System.out.println("\nSingle Element Test Case:");
        printLevelOrder(sortedArrayToBST(nums3));

        // Test Case 4: Even number of elements
        int[] nums4 = {1, 2, 3, 4};
        System.out.println("\nEven Elements Test Case:");
        printLevelOrder(sortedArrayToBST(nums4));
    }

    // Helper method to print BST level by level
    private static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
       
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                System.out.print(node.val + " ");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                System.out.print("null ");
            }
        }
    }
}
