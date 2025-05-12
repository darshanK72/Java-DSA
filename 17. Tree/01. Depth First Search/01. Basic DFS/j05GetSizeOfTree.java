/**
 * Problem Statement:
 *     Get Size of Binary Tree
 * 
 *     Given the root of a binary tree, return the total number of nodes in the tree.
 *     The size of a tree is the total count of nodes present in that tree.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can be empty or have any number of nodes
 * 
 * Output:
 *     - Integer representing total number of nodes in the tree
 * 
 * Example:
 *     Input: root = [1,2,3,4,5]
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     Output: 5
 */

public class j05GetSizeOfTree {

    /**
     * Node class represents a node in binary tree
     * Each node contains a value and references to left and right children
     */
    static class Node {
        int val;
        Node left;
        Node right;

        Node(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree (size = 5)
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);
        System.out.println("Test Case 1 (Basic Tree):");
        System.out.println("Size: " + getSize(root1));  // Expected: 5

        // Test Case 2: Single node tree (size = 1)
        Node root2 = new Node(1);
        System.out.println("\nTest Case 2 (Single Node):");
        System.out.println("Size: " + getSize(root2));  // Expected: 1

        // Test Case 3: Empty tree (size = 0)
        System.out.println("\nTest Case 3 (Empty Tree):");
        System.out.println("Size: " + getSize(null));   // Expected: 0

        // Test Case 4: Left-skewed tree (size = 3)
        Node root4 = new Node(1);
        root4.left = new Node(2);
        root4.left.left = new Node(3);
        System.out.println("\nTest Case 4 (Left-skewed Tree):");
        System.out.println("Size: " + getSize(root4));  // Expected: 3
    }

    /**
     * Approach: Recursive Size Calculation
     * 
     * Intuition:
     * - Size of tree = 1 (current node) + size of left subtree + size of right subtree
     * - Recursively calculate size of left and right subtrees
     * - Base case: empty tree has size 0
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
    public static int getSize(Node node) {
        if (node == null)
            return 0;                    // Base case: empty tree
        int leftSize = getSize(node.left);    // Get size of left subtree
        int rightSize = getSize(node.right);  // Get size of right subtree
        return 1 + leftSize + rightSize;      // Total size = 1 + left + right
    }
}