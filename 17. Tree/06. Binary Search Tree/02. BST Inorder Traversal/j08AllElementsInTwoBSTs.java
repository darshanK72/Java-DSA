/**
 * LeetCode 1305. All Elements in Two Binary Search Trees
 * 
 * Problem Statement:
 *     Given two binary search trees root1 and root2, return a list containing all
 *     the integers from both trees sorted in ascending order.
 * 
 * Input:
 *     - TreeNode root1 (root node of first BST)
 *     - TreeNode root2 (root node of second BST)
 *     - Node values are integers
 * 
 * Output:
 *     - List<Integer> containing all elements in sorted order
 * 
 * Example:
 *     Input: root1 = [2,1,4], root2 = [1,0,3]
 *     Output: [0,1,1,2,3,4]
 * 
 *     Explanation:
 *     Tree 1:    Tree 2:
 *        2          1
 *       / \        / \
 *      1   4      0   3
 */

import java.util.ArrayList;
import java.util.List;

public class j08AllElementsInTwoBSTs {

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
     * Gets all elements from two BSTs in sorted order
     * 
     * Intuition:
     * - Get sorted elements from each tree using inorder traversal
     * - Merge the two sorted lists
     * 
     * Time Complexity: O(n1 + n2) where n1, n2 are sizes of trees
     * Space Complexity: O(n1 + n2) for storing results
     * 
     * @param root1   Root of first BST
     * @param root2   Root of second BST
     * @return       List containing all elements in sorted order
     */
    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = getInorder(root1);
        List<Integer> list2 = getInorder(root2);
        return mergeTwoSortedList(list1, list2);
    }

    /**
     * Helper method for inorder traversal
     * 
     * Intuition:
     * - Use recursive inorder traversal to get sorted elements
     * - Combine results using ArrayList's addAll method
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree
     * 
     * @param root    Root node of BST
     * @return       List containing elements in sorted order
     */
    private static List<Integer> getInorder(TreeNode root) {
        ArrayList<Integer> out = new ArrayList<>();
        if (root == null)
            return out;
        out.addAll(getInorder(root.left));
        out.add(root.val);
        out.addAll(getInorder(root.right));
        return out;
    }

    /**
     * Helper method to merge two sorted lists
     * 
     * Intuition:
     * - Use two pointers to track positions in lists
     * - Compare elements and add smaller one
     * - Handle duplicates and remaining elements
     * 
     * Time Complexity: O(n1 + n2) where n1, n2 are list sizes
     * Space Complexity: O(n1 + n2) for result list
     * 
     * @param list1   First sorted list
     * @param list2   Second sorted list
     * @return       Merged sorted list
     */
    private static List<Integer> mergeTwoSortedList(List<Integer> list1, List<Integer> list2) {
        int i = 0, j = 0;
        List<Integer> out = new ArrayList<>();
        
        // Merge while both lists have elements
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) < list2.get(j)) {
                out.add(list1.get(i++));
            } else if (list1.get(i) > list2.get(j)) {
                out.add(list2.get(j++));
            } else {
                out.add(list1.get(i++));
                out.add(list2.get(j++));
            }
        }

        // Add remaining elements
        while (i < list1.size()) out.add(list1.get(i++));
        while (j < list2.size()) out.add(list2.get(j++));

        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic trees
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right = new TreeNode(3);

        List<Integer> result = getAllElements(root1, root2);
        System.out.println("Merged elements: " + result);  // Expected: [0,1,1,2,3,4]

        // Test Case 2: Empty trees
        System.out.println("\nEmpty trees test:");
        List<Integer> result2 = getAllElements(null, null);
        System.out.println("Result: " + result2);  // Expected: []

        // Test Case 3: One empty tree
        System.out.println("\nOne empty tree test:");
        List<Integer> result3 = getAllElements(root1, null);
        System.out.println("Result: " + result3);  // Expected: [1,2,4]
    }
}
