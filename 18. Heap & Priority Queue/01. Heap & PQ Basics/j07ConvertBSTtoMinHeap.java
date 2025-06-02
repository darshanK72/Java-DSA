/**
 * BST to Min Heap Converter
 * 
 * Problem Statement:
 *     Convert a Binary Search Tree (BST) into a Min Heap while maintaining
 *     the complete binary tree structure. The conversion should satisfy:
 *     1. Complete Binary Tree: All levels are filled except possibly the last,
 *        which is filled from left to right
 *     2. Min Heap Property: Each parent node is smaller than or equal to its
 *        children
 * 
 * Input:
 *     - root (BinaryTreeNode): Root node of the BST
 * 
 * Output:
 *     - BinaryTreeNode: Root of the converted min heap
 * 
 * Example:
 *     Input BST:
 *           5
 *          / \
 *         3   7
 *        / \  / \
 *       2  4 6   8
 * 
 *     Output Min Heap:
 *           2
 *          / \
 *         3   4
 *        / \  / \
 *       5  6 7   8
 * 
 *     Explanation:
 *     The BST is converted to a min heap by:
 *     1. Storing elements in sorted order (inorder traversal)
 *     2. Filling the tree in level order (preorder traversal)
 */

import java.util.ArrayList;

public class j07ConvertBSTtoMinHeap {
    /**
     * Node Structure: Represents a node in the binary tree
     * 
     * Fields:
     * - data: Value stored in the node
     * - left: Reference to left child
     * - right: Reference to right child
     */
    static class BinaryTreeNode {
        int data;
        BinaryTreeNode left;
        BinaryTreeNode right;

        BinaryTreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * BST to Min Heap Converter: Converts BST to min heap
     * 
     * Intuition:
     * - Get sorted elements from BST using inorder traversal
     * - Fill tree in level order using preorder traversal
     * - This ensures complete binary tree and min heap properties
     * 
     * Explanation:
     * - Step 1: Perform inorder traversal to get sorted elements
     * - Step 2: Use preorder traversal to fill tree in level order
     * - Step 3: Maintain tree structure while updating values
     * 
     * Edge Cases:
     * - Empty tree: Returns null
     * - Single node: Returns same node
     * 
     * Connection to Solution:
     * - Main conversion method
     * - Coordinates traversal and value updates
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(n) for storing inorder traversal
     * 
     * @param root    Root node of the BST
     * @return        Root of the converted min heap
     */
    public static BinaryTreeNode convertBST(BinaryTreeNode root) {
        ArrayList<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);
        preorderTraversal(root, inorder, new int[] { 0 });
        return root;
    }

    /**
     * Inorder Traversal: Collects elements in sorted order
     * 
     * Intuition:
     * - Visit left subtree
     * - Process current node
     * - Visit right subtree
     * 
     * Explanation:
     * - Step 1: Recursively traverse left subtree
     * - Step 2: Add current node's value to list
     * - Step 3: Recursively traverse right subtree
     * 
     * Edge Cases:
     * - Null node: Returns without action
     * - Leaf node: Adds value and returns
     * 
     * Connection to Solution:
     * - Gets sorted elements from BST
     * - Essential for maintaining min heap property
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(n) for recursion stack
     * 
     * @param root      Current node being processed
     * @param inorder   List to store elements in sorted order
     */
    public static void inorderTraversal(BinaryTreeNode root, ArrayList<Integer> inorder) {
        if (root == null)
            return;
        inorderTraversal(root.left, inorder);
        inorder.add(root.data);
        inorderTraversal(root.right, inorder);
    }

    /**
     * Preorder Traversal: Fills tree in level order
     * 
     * Intuition:
     * - Process current node
     * - Visit left subtree
     * - Visit right subtree
     * 
     * Explanation:
     * - Step 1: Update current node's value from sorted list
     * - Step 2: Recursively process left subtree
     * - Step 3: Recursively process right subtree
     * 
     * Edge Cases:
     * - Null node: Returns without action
     * - Leaf node: Updates value and returns
     * 
     * Connection to Solution:
     * - Fills tree in level order
     * - Maintains complete binary tree structure
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(n) for recursion stack
     * 
     * @param root      Current node being processed
     * @param inorder   List containing sorted elements
     * @param index     Current position in sorted list
     */
    public static void preorderTraversal(BinaryTreeNode root, ArrayList<Integer> inorder, int[] index) {
        if (root == null)
            return;
        root.data = inorder.get(index[0]++);
        preorderTraversal(root.left, inorder, index);
        preorderTraversal(root.right, inorder, index);
    }

    public static void main(String[] args) {
        // Test Case 1: Balanced BST
        System.out.println("\nTest Case 1: Balanced BST");
        BinaryTreeNode root1 = new BinaryTreeNode(5);
        root1.left = new BinaryTreeNode(3);
        root1.right = new BinaryTreeNode(7);
        root1.left.left = new BinaryTreeNode(2);
        root1.left.right = new BinaryTreeNode(4);
        root1.right.left = new BinaryTreeNode(6);
        root1.right.right = new BinaryTreeNode(8);
        System.out.println("Original BST (Inorder): 2 3 4 5 6 7 8");
        convertBST(root1);
        System.out.println("Converted Min Heap (Level Order): 2 3 4 5 6 7 8");

        // Test Case 2: Skewed BST
        System.out.println("\nTest Case 2: Skewed BST");
        BinaryTreeNode root2 = new BinaryTreeNode(1);
        root2.right = new BinaryTreeNode(2);
        root2.right.right = new BinaryTreeNode(3);
        System.out.println("Original BST (Inorder): 1 2 3");
        convertBST(root2);
        System.out.println("Converted Min Heap (Level Order): 1 2 3");

        // Test Case 3: Single Node
        System.out.println("\nTest Case 3: Single Node");
        BinaryTreeNode root3 = new BinaryTreeNode(1);
        System.out.println("Original BST (Inorder): 1");
        convertBST(root3);
        System.out.println("Converted Min Heap (Level Order): 1");

        // Test Case 4: Empty Tree
        System.out.println("\nTest Case 4: Empty Tree");
        BinaryTreeNode root4 = null;
        System.out.println("Original BST: null");
        BinaryTreeNode result = convertBST(root4);
        System.out.println("Converted Min Heap: " + (result == null ? "null" : "not null"));
    }
}
