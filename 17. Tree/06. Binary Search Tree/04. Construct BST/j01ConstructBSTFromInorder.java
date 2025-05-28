/**
 * LeetCode 108. Convert Sorted Array to Binary Search Tree
 * 
 * Problem Statement:
 *     Given an integer array nums where the elements are sorted in ascending order,
 *     convert it to a height-balanced binary search tree (BST).
 * 
 * Input:
 *     - nums: integer array sorted in ascending order (-10^4 <= nums[i] <= 10^4)
 *     - Array length: 0 <= nums.length <= 10^4
 * 
 * Output:
 *     - Root node of the constructed height-balanced BST
 * 
 * Example:
 *     Input: nums = [-10,-3,0,5,9]
 *     Output: [0,-3,9,-10,null,5]
 * 
 *     Explanation:
 *            0
 *           / \
 *         -3   9
 *        /    /
 *      -10   5
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
     * Approach: Recursive Binary Search
     * 
     * Intuition:
     * - Since the array is sorted, middle element will be the root of BST
     * - Left subarray forms left subtree, right subarray forms right subtree
     * - This naturally creates a height-balanced tree
     * 
     * Explanation:
     * - Step 1: Find middle element as root
     * - Step 2: Recursively construct left subtree from left subarray
     * - Step 3: Recursively construct right subtree from right subarray
     * 
     * Time Complexity: O(n) where n is the length of input array
     * Space Complexity: O(log n) for recursion stack
     * 
     * @param nums    Sorted array of integers (-10^4 <= nums[i] <= 10^4)
     * @return       Root node of height-balanced BST
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        // Handle null or empty input
        if (nums == null || nums.length == 0) {
            return null;
        }
        return convertInorderToBST(nums, 0, nums.length - 1);
    }

    /**
     * Helper Method: Recursive BST Construction
     * 
     * Intuition:
     * - Divides array into two equal halves around middle element
     * - Ensures height-balanced property by design
     * 
     * Explanation:
     * - Uses binary search approach to construct tree
     * - Middle element becomes root of current subtree
     * - Recursively processes left and right subarrays
     * 
     * @param nums    Input sorted array
     * @param s       Start index of current subarray
     * @param e       End index of current subarray
     * @return       Root of current subtree
     */
    private static TreeNode convertInorderToBST(int[] nums, int s, int e) {
        // Base case: invalid range
        if (s > e) {
            return null;
        }
        
        // Find middle element for current root
        int mid = s + (e - s) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        
        // Recursively construct left and right subtrees
        root.left = convertInorderToBST(nums, s, mid - 1);
        root.right = convertInorderToBST(nums, mid + 1, e);
        
        return root;
    }

    /**
     * Helper Method: Level Order Traversal Print
     * 
     * Intuition:
     * - Uses BFS to print tree level by level
     * - Includes null nodes for complete visualization
     * 
     * @param root    Root node of the BST
     */
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
        System.out.println();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case with odd number of elements
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
}
