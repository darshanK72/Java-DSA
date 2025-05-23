/**
 * GeeksForGeeks: Left View of Binary Tree
 * 
 * Problem Statement:
 *     Given a Binary Tree, return list containing nodes that form the left view of the tree.
 *     Left view of a Binary Tree is set of nodes visible when tree is viewed from left side.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - ArrayList containing nodes visible from left side
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        / \   \
 *       4   5   6
 *     
 *     Output: [1, 2, 4]
 *     
 *     Explanation:
 *     Level 0: 1 is visible
 *     Level 1: 2 blocks 3 from left
 *     Level 2: 4 blocks 5,6 from left
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class j07LeftViewOfBinaryTree {

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
     * Approach: Level Order Traversal (BFS)
     * 
     * Intuition:
     * - Left view contains leftmost node at each level
     * - Use BFS to process tree level by level
     * - First node at each level is part of left view
     * 
     * Example visualization:
     *           1*         Level 0: 1 (first node)
     *          / \
     *         2*  3       Level 1: 2 (first node)
     *        / \   \
     *       4*  5   6     Level 2: 4 (first node)
     *     
     *     * indicates nodes in left view
     * 
     * Explanation:
     * 1. Use queue for level order traversal
     * 2. At each level:
     *    - First node is part of left view
     *    - Process all nodes at current level
     *    - Add their children for next level
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(w) - w is maximum width of tree
     * 
     * @param root Root node of binary tree
     * @return ArrayList containing left view nodes
     */
    public static ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> out = new ArrayList<>();              // Store left view nodes
        if (root == null)                                       // Handle empty tree
            return out;

        Queue<Node> queue = new LinkedList<>();                 // Queue for BFS
        queue.add(root);                                        // Start with root

        while (!queue.isEmpty()) {
            int levelSize = queue.size();                       // Nodes at current level
            out.add(queue.peek().data);                        // Add first node of level

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();                       // Get next node

                if (node.left != null) {                       // Add left child
                    queue.add(node.left);
                }
                if (node.right != null) {                      // Add right child
                    queue.add(node.right);
                }
            }
        }
        return out;
    }

    /**
     * Main method to test the implementation
     */
    public static void main(String[] args) {
        // Test Case 1: Regular binary tree
        /**
         *           1
         *          / \
         *         2   3
         *        / \   \
         *       4   5   6
         */
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);
        root1.right.right = new Node(6);
        
        System.out.println("Test Case 1 - Regular binary tree");
        System.out.println("Expected: [1, 2, 4]");
        System.out.println("Output: " + leftView(root1));

        // Test Case 2: Right skewed tree
        /**
         *       1
         *        \
         *         2
         *          \
         *           3
         */
        Node root2 = new Node(1);
        root2.right = new Node(2);
        root2.right.right = new Node(3);
        
        System.out.println("\nTest Case 2 - Right skewed tree");
        System.out.println("Expected: [1]");
        System.out.println("Output: " + leftView(root2));

        // Test Case 3: Empty tree
        System.out.println("\nTest Case 3 - Empty tree");
        System.out.println("Expected: []");
        System.out.println("Output: " + leftView(null));
    }
}
