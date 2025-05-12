/**
 * Problem Statement:
 *     LeetCode 144. Binary Tree Preorder Traversal
 * 
 *     Given the root of a binary tree, return the preorder traversal of its nodes'
 *     values. In preorder traversal, we visit the root node first, then traverse
 *     the left subtree, and finally traverse the right subtree.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Number of nodes in range [0, 100]
 *     - Node values in range [-100, 100]
 * 
 * Output:
 *     - List of node values in preorder traversal order
 * 
 * Example:
 *     Input: root = [1,2,3,4,5]
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     Output: [1,2,4,5,3]
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class j01PreOrderTraversal {

    /**
     * TreeNode class represents a node in binary tree
     * Each node contains a value and references to left and right children
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);

        // Test Case 2: Left-skewed tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);

        // Test Case 3: Right-skewed tree
        TreeNode root3 = new TreeNode(1);
        root3.right = new TreeNode(2);
        root3.right.right = new TreeNode(3);

        // Test Case 4: Single node
        TreeNode root4 = new TreeNode(1);

        // Test Case 5: Empty tree
        TreeNode root5 = null;

        // Test all cases
        TreeNode[] testCases = { root1, root2, root3, root4, root5 };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            List<Integer> result = preorderTraversal(testCases[i]);
            System.out.println("Preorder Traversal: " + result);
            System.out.println();
        }
    }

    /**
     * Approach 1: Recursive Preorder Traversal
     * 
     * Intuition:
     * - Follow Root->Left->Right pattern recursively
     * - Process current node first
     * - Then traverse left subtree
     * - Finally traverse right subtree
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - n is number of nodes in tree
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * - Worst case O(n) for skewed tree
     * - Best case O(log n) for balanced tree
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    /**
     * Helper method to perform recursive preorder traversal
     * Processes current node, then left subtree, then right subtree
     */
    private static void preorder(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        list.add(root.val); // Process root
        preorder(root.left, list); // Process left subtree
        preorder(root.right, list); // Process right subtree
    }

    /**
     * Approach 2: Iterative Preorder Traversal using Stack
     * 
     * Intuition:
     * - Use stack to simulate recursion
     * - Process current node, then push right child first (to process later)
     * - Then push left child (to process next)
     * - This ensures Root->Left->Right order
     * 
     * Algorithm:
     * 1. Create empty list for result and stack for processing
     * 2. Push root to stack if not null
     * 3. While stack not empty:
     *    - Pop node and add its value to result
     *    - Push right child first (if exists)
     *    - Push left child second (if exists)
     *    This ensures left is processed before right
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - Stack operations (push/pop) are O(1)
     * 
     * Space Complexity: O(h)
     * - h is height of tree
     * - Stack stores at most h nodes
     * - Worst case O(n) for skewed tree
     * - Best case O(log n) for balanced tree
     */
    public static List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> list = new ArrayList<>(); // Store result
        Stack<TreeNode> stack = new Stack<>(); // Process nodes
        if (root == null)
            return list; // Handle empty tree

        stack.push(root); // Start with root
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop(); // Get next node
            list.add(node.val); // Process current node

            if (node.right != null) { // Push right child first
                stack.push(node.right); // (will be processed later)
            }
            if (node.left != null) { // Push left child second
                stack.push(node.left); // (will be processed next)
            }
        }

        return list;
    }
}