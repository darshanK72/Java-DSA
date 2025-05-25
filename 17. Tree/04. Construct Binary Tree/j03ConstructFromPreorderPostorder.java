/**
 * LeetCode 889. Construct Binary Tree from Preorder and Postorder Traversal
 * 
 * Problem Statement:
 *     Given two integer arrays preorder and postorder where preorder is the preorder
 *     traversal of a binary tree and postorder is the postorder traversal of the 
 *     same tree, construct and return the binary tree.
 * 
 * Input:
 *     - int[] preorder (array containing preorder traversal, values unique)
 *     - int[] postorder (array containing postorder traversal, values unique)
 * 
 * Output:
 *     - TreeNode (root of constructed binary tree)
 * 
 * Example:
 *     Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 *     Output: [1,2,3,4,5,6,7]
 * 
 *     Explanation:
 *          1
 *         / \
 *        2   3
 *       / \ / \
 *      4  5 6  7
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class j03ConstructFromPreorderPostorder {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Constructs binary tree from preorder and postorder traversals
     * 
     * Intuition:
     * - First element in preorder is root
     * - Second element in preorder is left subtree root
     * - Find this element in postorder to determine left subtree size
     * - Use HashMap for O(1) lookup in postorder array
     * 
     * Explanation:
     * - Create HashMap to store postorder value to index mapping
     * - Call recursive helper with initial bounds
     * - Helper method constructs tree recursively using array bounds
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(n) for HashMap storage
     * 
     * @param preorder   Array containing preorder traversal
     * @param postorder  Array containing postorder traversal
     * @return          Root node of constructed binary tree
     */
    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // Create map for postorder value to index lookup
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = postorder.length;

        // Populate map with postorder indices
        for (int i = 0; i < length; i++) {
            map.put(postorder[i], i);
        }

        // Start recursive construction
        return buildTreePrePost(preorder, postorder, map, 0, length - 1, 0);
    }

    /**
     * Helper method for recursive tree construction
     * 
     * Intuition:
     * - Use array bounds to identify subtree ranges
     * - Next element in preorder is left child
     * - Find this element in postorder to determine split point
     * 
     * Explanation:
     * - Base case: left > right means empty subtree
     * - Create root from current preorder value
     * - Find left child position in postorder using map
     * - Calculate sizes for recursive calls
     * 
     * Time Complexity: O(n) for visiting each node once
     * Space Complexity: O(h) for recursion stack
     * 
     * @param preorder   Preorder traversal array
     * @param postorder  Postorder traversal array
     * @param map       HashMap for postorder value lookup
     * @param left      Left bound of current subtree
     * @param right     Right bound of current subtree
     * @param index     Current index in preorder array
     * @return         Root of current subtree
     */
    private static TreeNode buildTreePrePost(int[] preorder, int[] postorder,
            HashMap<Integer, Integer> map, int left, int right, int index) {
        // Base case: empty subtree
        if (left > right)
            return null;

        // Create current root node
        int value = preorder[index];
        TreeNode root = new TreeNode(value);

        // Leaf node case
        if (left == right)
            return root;

        // Find left child's position in postorder
        int mid = map.get(preorder[index + 1]);

        // Build left subtree
        root.left = buildTreePrePost(preorder, postorder, map,
                left, mid, index + 1);

        // Build right subtree (adjust index by left subtree size)
        root.right = buildTreePrePost(preorder, postorder, map,
                mid + 1, right - 1, index + 1 + (mid - left + 1));

        return root;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree
        System.out.println("\nBasic Test Case:");
        int[] preorder1 = { 1, 2, 4, 5, 3, 6, 7 };
        int[] postorder1 = { 4, 5, 2, 6, 7, 3, 1 };
        TreeNode result1 = constructFromPrePost(preorder1, postorder1);
        printTree(result1);

        // Test Case 2: Single node tree
        System.out.println("\nSingle Node Case:");
        int[] preorder2 = { 1 };
        int[] postorder2 = { 1 };
        TreeNode result2 = constructFromPrePost(preorder2, postorder2);
        printTree(result2);

        // Test Case 3: Left-skewed tree
        System.out.println("\nLeft-skewed Tree Case:");
        int[] preorder3 = { 1, 2, 3 };
        int[] postorder3 = { 3, 2, 1 };
        TreeNode result3 = constructFromPrePost(preorder3, postorder3);
        printTree(result3);

        // Test Case 4: Right-skewed tree
        System.out.println("\nRight-skewed Tree Case:");
        int[] preorder4 = { 1, 2, 3 };
        int[] postorder4 = { 2, 3, 1 };
        TreeNode result4 = constructFromPrePost(preorder4, postorder4);
        printTree(result4);
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
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            System.out.println();
        }
    }
}
