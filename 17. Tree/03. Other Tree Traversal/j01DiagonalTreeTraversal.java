/**
 * GeeksForGeeks: Diagonal Tree Traversal
 * 
 * Problem Statement:
 *     Given a Binary Tree, print all diagonal elements in a binary tree belonging to
 *     same line. A diagonal path contains nodes connected by right edges or a path
 *     containing one left edge followed by any number of right edges.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - ArrayList containing diagonal traversal of the tree
 * 
 * Example:
 *     Input: 
 *           8
 *          / \
 *         3   10
 *        / \    \
 *       1   6    14
 *          / \   /
 *         4   7 13
 *     
 *     Output: [8, 10, 14, 3, 6, 7, 13, 1, 4]
 *     
 *     Explanation:
 *     Diagonal 1 (From root): 8 -> 10 -> 14
 *     Diagonal 2 (From 3): 3 -> 6 -> 7
 *     Diagonal 3 (From 13): 13
 *     Diagonal 4 (From 1): 1
 *     Diagonal 5 (From 4): 4
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class j01DiagonalTreeTraversal {

    /**
     * Node class representing each node in the binary tree
     */
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach: Level Order Traversal with Right Path Processing
     * 
     * Intuition:
     * - Each diagonal path starts with a node and follows right edges
     * - Left child starts a new diagonal path
     * - Use queue to store starting nodes of new diagonal paths
     * 
     * Explanation:
     * 1. Process right path of current node:
     *    - Add current node to result
     *    - Store left child in queue for later processing
     *    - Move to right child
     * 
     * 2. When right path ends:
     *    - Get next starting node from queue
     *    - Process its right path
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(w) - w is width of tree
     * 
     * @param root Root node of binary tree
     * @return ArrayList containing diagonal traversal
     */
    public static ArrayList<Integer> diagonal(Node root) {
        if (root == null)                             // Handle empty tree
            return new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();        // Queue for left children
        ArrayList<Integer> out = new ArrayList<>();    // Store diagonal traversal
        queue.add(root);                              // Start with root node

        while (!queue.isEmpty()) {
            Node node = queue.peek();                  // Get next starting node
            
            while (node != null) {                     // Process right path
                if (node.left != null) {               // Store left child for later
                    queue.add(node.left);
                }
                out.add(node.data);                    // Add current node to result
                node = node.right;                     // Move to right child
            }
            queue.poll();                             // Remove processed node
        }
        return out;
    }

    /**
     * Main method to test the implementation
     */
    public static void main(String[] args) {
        // Test Case 1: Regular binary tree
        Node root1 = new Node(8);
        root1.left = new Node(3);
        root1.right = new Node(10);
        root1.left.left = new Node(1);
        root1.left.right = new Node(6);
        root1.right.right = new Node(14);
        root1.left.right.left = new Node(4);
        root1.left.right.right = new Node(7);
        root1.right.right.left = new Node(13);
        
        System.out.println("Test Case 1 - Regular binary tree");
        System.out.println("Expected: [8, 10, 14, 3, 6, 7, 13, 1, 4]");
        System.out.println("Output: " + diagonal(root1));

        // Test Case 2: Empty tree
        System.out.println("\nTest Case 2 - Empty tree");
        System.out.println("Expected: []");
        System.out.println("Output: " + diagonal(null));

        // Test Case 3: Single node tree
        Node root3 = new Node(1);
        System.out.println("\nTest Case 3 - Single node tree");
        System.out.println("Expected: [1]");
        System.out.println("Output: " + diagonal(root3));
    }
}
