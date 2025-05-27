/**
 * LeetCode 501. Find Mode in Binary Search Tree
 * 
 * Problem Statement:
 *     Given the root of a binary search tree (BST) with duplicates, return all the
 *     mode(s) (i.e., the most frequently occurred element) in the BST.
 *     If the tree has more than one mode, return them in any order.
 * 
 * Input:
 *     - TreeNode root (root node of BST)
 *     - Node values are integers
 * 
 * Output:
 *     - int[] containing all modes (most frequent elements)
 * 
 * Example:
 *     Input: root = [1,null,2,2]
 *     Output: [2]
 * 
 *     Explanation:
 *          1
 *           \
 *            2
 *           /
 *          2
 *     
 *     Mode is 2, appearing twice
 */

import java.util.ArrayList;
import java.util.HashMap;

public class j06FindModeInBinarySearchTree {

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
     * Approach 1: Using HashMap
     * 
     * Intuition:
     * - Use HashMap to store frequency of each value
     * - Find values with maximum frequency
     * 
     * Explanation:
     * - First pass: count frequency of each value
     * - Second pass: find values with max frequency
     * - Convert ArrayList to array for return
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(n) for HashMap storage
     * 
     * @param root    Root node of BST
     * @return       Array containing all modes
     */
    public static int[] findMode(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        fillMap(root, map);
        
        int maxFreq = 0;
        ArrayList<Integer> out = new ArrayList<>();
        
        // Find values with maximum frequency
        for (int key : map.keySet()) {
            if (map.get(key) > maxFreq) {
                maxFreq = map.get(key);
                out = new ArrayList<>();
                out.add(key);
            } else if (map.get(key) == maxFreq) {
                out.add(key);
            }
        }

        // Convert to array
        return listToArray(out);
    }

    /**
     * Helper method to fill HashMap with frequencies
     */
    private static void fillMap(TreeNode root, HashMap<Integer, Integer> map) {
        if (root == null) return;
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        fillMap(root.left, map);
        fillMap(root.right, map);
    }

    /**
     * Approach 2: Using Inorder Traversal (Space Efficient)
     * 
     * Intuition:
     * - Use BST property to get values in sorted order
     * - Track current and maximum frequencies during traversal
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree
     */
    static class Result {
        int currVal;
        int currFreq;
        int maxFreq;

        Result(int val, int f, int max) {
            currVal = val;
            currFreq = f;
            maxFreq = max;
        }
    }

    /**
     * Efficient mode finding using inorder traversal
     */
    public static int[] findModeEfficient(TreeNode root) {
        if (root == null) return new int[]{};
        
        Result result = new Result(root.val, 0, 0);
        ArrayList<Integer> out = new ArrayList<>();
        fillMaxFreqArray(root, out, result);

        // Handle last element
        if (result.currFreq > result.maxFreq) {
            result.maxFreq = result.currFreq;
            out.clear();
            out.add(result.currVal);
        } else if (result.currFreq == result.maxFreq) {
            out.add(result.currVal);
        }

        return listToArray(out);
    }

    /**
     * Helper method for inorder traversal and frequency tracking
     */
    private static void fillMaxFreqArray(TreeNode root, ArrayList<Integer> out, Result result) {
        if (root == null) return;
        
        fillMaxFreqArray(root.left, out, result);
        
        // Process current node
        if (result.currVal == root.val) {
            result.currFreq++;
        } else {
            // Update modes if needed
            if (result.currFreq > result.maxFreq) {
                result.maxFreq = result.currFreq;
                out.clear();
                out.add(result.currVal);
            } else if (result.currFreq == result.maxFreq) {
                out.add(result.currVal);
            }
            // Reset for new value
            result.currVal = root.val;
            result.currFreq = 1;
        }
        
        fillMaxFreqArray(root.right, out, result);
    }

    /**
     * Helper method to convert ArrayList to array
     */
    private static int[] listToArray(ArrayList<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic BST with single mode
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.left = new TreeNode(2);
        int[] result1 = findMode(root1);
        System.out.println("Mode for test case 1: " + result1[0]);  // Expected: 2

        // Test Case 2: BST with multiple modes
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        root2.right.right = new TreeNode(2);
        root2.left = new TreeNode(1);
        int[] result2 = findModeEfficient(root2);
        System.out.println("\nModes for test case 2:");
        for (int mode : result2) {
            System.out.print(mode + " ");  // Expected: 1 2
        }
    }
}
