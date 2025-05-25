/**
 * LeetCode 105. Construct Binary Tree from Preorder and Inorder Traversal
 * 
 * Problem Statement:
 *     Given two integer arrays preorder and inorder where preorder is the preorder 
 *     traversal of a binary tree and inorder is the inorder traversal of the same tree,
 *     construct and return the binary tree.
 * 
 * Input:
 *     - preorder: int[] (1 <= preorder.length <= 3000)
 *     - inorder: int[] (inorder.length == preorder.length)
 *     - Each value in the arrays is unique and in range [-3000, 3000]
 * 
 * Output:
 *     - Return the root node of the constructed binary tree
 * 
 * Example:
 *     Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 *     Output: [3,9,20,null,null,15,7]
 * 
 *     Explanation:
 *          3
 *         / \
 *        9  20
 *           / \
 *          15  7
 */

import java.util.*;

public class j02ConstructFromPreorderInorder {

    /**
     * TreeNode class representing a node in the binary tree
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Approach: HashMap + Recursion
     * 
     * Intuition:
     * - Preorder traversal gives us the root node first (root-left-right)
     * - Inorder traversal gives left-root-right pattern
     * - Using the root from preorder, we can split inorder array into left and right subtrees
     * - HashMap stores inorder values and their indices for O(1) lookup
     * 
     * Explanation:
     * - Step 1: Create HashMap to store inorder indices for O(1) lookup
     * - Step 2: First element in preorder is the root
     * - Step 3: Find root's position in inorder to determine left and right subtrees
     * - Step 4: Recursively build left and right subtrees with updated indices
     * - Step 5: Return constructed tree
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(n) for HashMap and recursion stack
     * 
     * @param preorder Array containing preorder traversal (1 <= length <= 3000)
     * @param inorder Array containing inorder traversal (same length as preorder)
     * @return Root node of constructed binary tree
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // Create HashMap to store inorder values and their indices
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = preorder.length;
        
        // Populate HashMap with inorder indices
        for (int i = 0; i < length; i++) {
            map.put(inorder[i], i);
        }
        
        // Start recursive construction
        return buildTreePreIn(preorder, inorder, map, 0, length - 1, 0);
    }

    /**
     * Helper Method: Recursive Tree Construction
     * 
     * Intuition:
     * - Uses array indices to determine subtree boundaries
     * - Calculates new preorder index based on subtree size
     * 
     * Explanation:
     * - left, right: boundaries in inorder array
     * - index: current position in preorder array
     * - Uses map to find root position and split subtrees
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h) where h is height of tree
     * 
     * @param preorder Preorder traversal array
     * @param inorder Inorder traversal array
     * @param map HashMap containing inorder indices
     * @param left Left boundary in inorder array
     * @param right Right boundary in inorder array
     * @param index Current index in preorder array
     * @return Root of current subtree
     */
    private static TreeNode buildTreePreIn(int[] preorder, int[] inorder, 
            HashMap<Integer, Integer> map, int left, int right, int index) {
        // Base case: invalid boundaries
        if (left > right)
            return null;

        // Create current root node
        int value = preorder[index];
        TreeNode root = new TreeNode(value);

        // Leaf node case
        if (left == right)
            return root;

        // Find position of root in inorder array
        int mid = map.get(value);

        // Recursively construct left and right subtrees
        root.left = buildTreePreIn(preorder, inorder, map, left, mid - 1, index + 1);
        root.right = buildTreePreIn(preorder, inorder, map, mid + 1, right, 
                index + 1 + (mid - left));

        return root;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] preorder1 = {3,9,20,15,7};
        int[] inorder1 = {9,3,15,20,7};
        TreeNode result1 = buildTree(preorder1, inorder1);
        System.out.println("Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]");
        System.out.println("Output: Tree with root " + result1.val);
        printTree(result1);

        // Test Case 2: Single node
        System.out.println("\nSingle Node Case:");
        int[] preorder2 = {1};
        int[] inorder2 = {1};
        TreeNode result2 = buildTree(preorder2, inorder2);
        System.out.println("Input: preorder = [1], inorder = [1]");
        System.out.println("Output: Tree with root " + result2.val);
        printTree(result2);

        // Test Case 3: Left-skewed tree
        System.out.println("\nLeft-skewed Tree Case:");
        int[] preorder3 = {1,2,3};
        int[] inorder3 = {3,2,1};
        TreeNode result3 = buildTree(preorder3, inorder3);
        System.out.println("Input: preorder = [1,2,3], inorder = [3,2,1]");
        System.out.println("Output: Tree with root " + result3.val);
        printTree(result3);
    }

    // Helper method to print tree structure
    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        
        // Level order traversal to print tree
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            System.out.println();
        }
    }
    
}
