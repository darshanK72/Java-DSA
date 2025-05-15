/**
 * Problem Statement:
 *     LeetCode 114. Flatten Binary Tree to Linked List
 * 
 *     Given the root of a binary tree, flatten it to a linked list in-place:
 *     - The "linked list" should use the same TreeNode class
 *     - The right pointer should point to the next node
 *     - The left pointer should always be null
 *     - The linked list should be in the same order as a preorder traversal
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 * 
 * Output:
 *     - Tree modified in-place to form linked list
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   5
 *        / \   \
 *       3   4   6
 *     
 *     Output: 
 *     1->2->3->4->5->6
 */

import java.util.LinkedList;
import java.util.Queue;

public class j07FlattenBinaryTreeToLinkedList {
    
    /**
     * TreeNode class represents a node in binary tree
     * Contains value and references to left and right children
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Approach 1: Post-order Traversal with Right Node Storage
     * 
     * Intuition:
     * - Process tree bottom-up using post-order traversal
     * - Store right subtree before modifying connections
     * - Make left subtree the new right subtree
     * - Append stored right subtree at the end
     * 
     * Time: O(n²) - need to traverse to end for each node
     * Space: O(h) - recursion stack depth
     */
    public static void flattenPostOrder(TreeNode root) {
        if (root == null)
            return;

        flattenPostOrder(root.left);     // Process left subtree
        flattenPostOrder(root.right);    // Process right subtree

        TreeNode rightSubtree = root.right;  // Store right subtree
        root.right = root.left;          // Make left subtree right
        root.left = null;                // Clear left pointer

        // Find end of new right subtree
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        current.right = rightSubtree;    // Append original right subtree
    }

    /**
     * Approach 2: Pre-order Traversal with Head-Tail Tracking
     * 
     * Intuition:
     * - Track both head and tail of flattened portions
     * - Process in pre-order to maintain correct ordering
     * - Use array to return both head and tail nodes
     * - Connect parts while returning up the tree
     * 
     * Time: O(n) - visit each node once
     * Space: O(h) - recursion stack depth
     */
    public static void flattenPreOrder(TreeNode root) {
        flattenWithHeadAndTail(root);
    }

    private static TreeNode[] flattenWithHeadAndTail(TreeNode root) {
        if (root == null)
            return new TreeNode[2];               // Return empty head-tail pair
        if (root.left == null && root.right == null)
            return new TreeNode[] { root, root }; // Single node case

        TreeNode[] leftPart = flattenWithHeadAndTail(root.left);
        TreeNode[] rightPart = flattenWithHeadAndTail(root.right);

        if (root.left == null) {                 // Only right subtree exists
            return new TreeNode[] { root, rightPart[1] };
        }
        if (root.right == null) {                // Only left subtree exists
            root.right = leftPart[0];
            root.left = null;
            return new TreeNode[] { root, leftPart[1] };
        }

        // Both subtrees exist
        root.right = leftPart[0];                // Connect left part
        root.left = null;
        leftPart[1].right = rightPart[0];        // Connect right part

        return new TreeNode[] { root, rightPart[1] };
    }

    public static void main(String[] args) {
        // Test Case 1: Example from problem
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(6);
        
        System.out.println("Test Case 1 (Basic Tree):");
        System.out.println("Original:");
        printTree(root1);
        flattenPreOrder(root1);
        System.out.println("After flattening:");
        printLinkedList(root1);  // Expected: 1->2->3->4->5->6
    
        // Test Case 2: Left-skewed tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        
        System.out.println("\nTest Case 2 (Left-skewed Tree):");
        System.out.println("Original:");
        printTree(root2);
        flattenPostOrder(root2);
        System.out.println("After flattening:");
        printLinkedList(root2);  // Expected: 1->2->3
    
        // Test Case 3: Right-skewed tree
        TreeNode root3 = new TreeNode(1);
        root3.right = new TreeNode(2);
        root3.right.right = new TreeNode(3);
        
        System.out.println("\nTest Case 3 (Right-skewed Tree):");
        System.out.println("Original:");
        printTree(root3);
        flattenPreOrder(root3);
        System.out.println("After flattening:");
        printLinkedList(root3);  // Expected: 1->2->3
    
        // Test Case 4: Single node
        TreeNode root4 = new TreeNode(1);
        
        System.out.println("\nTest Case 4 (Single Node):");
        System.out.println("Original:");
        printTree(root4);
        flattenPostOrder(root4);
        System.out.println("After flattening:");
        printLinkedList(root4);  // Expected: 1
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
    
    // Helper method to print flattened tree as linked list
    private static void printLinkedList(TreeNode root) {
        if (root == null) {
            System.out.println("Empty list");
            return;
        }
        
        TreeNode current = root;
        while (current != null) {
            System.out.print(current.val);
            if (current.right != null) {
                System.out.print("->");
            }
            current = current.right;
        }
        System.out.println();
    }
}