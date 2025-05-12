/**
 * Problem Statement:
 *     LeetCode 94. Binary Tree Inorder Traversal
 * 
 *     Given the root of a binary tree, return the inorder traversal of its nodes'
 *     values. In inorder traversal, we traverse the left subtree first, then visit
 *     the root node, and finally traverse the right subtree.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Number of nodes in range [0, 100]
 *     - Node values in range [-100, 100]
 * 
 * Output:
 *     - List of node values in inorder traversal order
 * 
 * Example:
 *     Input: root = [1,2,3,4,5]
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     Output: [4,2,5,1,3]
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class j02InOrderTraversal {

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
        // Test Case: Binary tree example
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Perform inorder traversal
        List<Integer> result = inorderTraversal(root);
        System.out.println("Inorder Traversal: " + result); // Output: [4, 2, 5, 1, 3]
    }

    /**
     * Approach: Recursive Inorder Traversal
     * 
     * Intuition:
     * - Follow Left->Root->Right pattern recursively
     * - Traverse left subtree first
     * - Process current node
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
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    /**
     * Helper method to perform recursive inorder traversal
     * Traverses left subtree, processes current node, then traverses right subtree
     */
    public static void inorder(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        inorder(root.left, list); // Traverse left subtree
        list.add(root.val); // Process root
        inorder(root.right, list); // Traverse right subtree
    }

    /**
     * Approach: Iterative Inorder Traversal using Stack
     * 
     * Intuition:
     * - Use stack to simulate recursion
     * - Keep going left while possible, pushing nodes to stack
     * - When can't go left, pop node, process it, then go right
     * - This ensures Left->Root->Right order
     * 
     * Algorithm:
     * 1. Create empty list for result and stack for processing
     * 2. Start from root and initialize temp pointer
     * 3. While traversing:
     *    - If current node exists:
     *      * Push to stack
     *      * Move to left child
     *    - If current node is null:
     *      * If stack empty, we're done
     *      * Pop node from stack, process it
     *      * Move to right child
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
    public static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> list = new ArrayList<>(); // Store result
        Stack<TreeNode> stack = new Stack<>(); // Process nodes
        if (root == null)
            return list; // Handle empty tree

        TreeNode temp = root; // Initialize current pointer
        while (true) {
            if (temp != null) { // If can go left
                stack.push(temp); // Save current node
                temp = temp.left; // Go left
            } else { // If can't go left
                if (stack.isEmpty())
                    break; // If done processing
                temp = stack.pop(); // Get last saved node
                list.add(temp.val); // Process current node
                temp = temp.right; // Go right
            }
        }
        return list;
    }
}