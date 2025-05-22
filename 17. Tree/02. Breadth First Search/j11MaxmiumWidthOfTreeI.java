/**
 * GeeksForGeeks: Maximum Width of Tree
 * 
 * Problem Statement:
 *     Given a binary tree, write a function to get the maximum width of the given
 *     tree. Width of a tree is maximum of widths of all levels.
 *     Width of one level is defined as the number of nodes present at that level.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - Maximum width among all levels in the tree
 * 
 * Example:
 *     Input: 
 *           1
 *          /  \
 *         2    3
 *        / \    \
 *       4   5    8
 *     
 *     Output: 3
 *     
 *     Explanation:
 *     Level 0: Width = 1 (Node 1)
 *     Level 1: Width = 2 (Nodes 2, 3)
 *     Level 2: Width = 3 (Nodes 4, 5, 8)
 *     Maximum width = 3
 */

import java.util.LinkedList;
import java.util.Queue;

public class j11MaxmiumWidthOfTreeI {

    /**
     * Node class representing each node in the binary tree
     */
    static class Node {
        int val;
        Node left, right;

        Node(int x) {
            val = x;
        }
    }

    /**
     * Main method to test the implementation with multiple test cases
     */
    public static void main(String[] args) {
        // Test Case 1: Regular binary tree
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);
        root1.right.right = new Node(8);
        System.out.println("Test Case 1 - Regular binary tree");
        System.out.println("Expected: 3, Output: " + getMaxWidth(root1));

        // Test Case 2: Left skewed tree
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.left.left = new Node(3);
        root2.left.left.left = new Node(4);
        System.out.println("\nTest Case 2 - Left skewed tree");
        System.out.println("Expected: 1, Output: " + getMaxWidth(root2));

        // Test Case 3: Right skewed tree
        Node root3 = new Node(1);
        root3.right = new Node(2);
        root3.right.right = new Node(3);
        System.out.println("\nTest Case 3 - Right skewed tree");
        System.out.println("Expected: 1, Output: " + getMaxWidth(root3));

        // Test Case 4: Complete binary tree
        Node root4 = new Node(1);
        root4.left = new Node(2);
        root4.right = new Node(3);
        root4.left.left = new Node(4);
        root4.left.right = new Node(5);
        root4.right.left = new Node(6);
        root4.right.right = new Node(7);
        System.out.println("\nTest Case 4 - Complete binary tree");
        System.out.println("Expected: 4, Output: " + getMaxWidth(root4));

        // Test Case 5: Empty tree
        Node root5 = null;
        System.out.println("\nTest Case 5 - Empty tree");
        System.out.println("Expected: 0, Output: " + getMaxWidth(root5));

        // Test Case 6: Single node tree
        Node root6 = new Node(1);
        System.out.println("\nTest Case 6 - Single node tree");
        System.out.println("Expected: 1, Output: " + getMaxWidth(root6));
    }

    /**
     * Approach: Level Order Traversal
     * 
     * Intuition:
     * - To find maximum width, we need to count nodes at each level
     * - Level order traversal (BFS) naturally processes nodes level by level
     * - Keep track of maximum nodes seen at any level
     * 
     * Explanation:
     * 1. Use queue for level order traversal:
     *    - Initialize queue with root
     *    - Process nodes level by level
     * 
     * 2. For each level:
     *    - Get current level size (number of nodes)
     *    - Update maxWidth if current level is wider
     *    - Process all nodes in current level:
     *      * Add their children to queue for next level
     * 
     * 3. Return the maximum width found
     * 
     * Time Complexity:
     * - O(n) where n is the number of nodes in the tree
     * - We visit each node exactly once
     * 
     * Space Complexity:
     * - O(w) where w is the maximum width of the tree
     * - Queue will store at most width of the tree at any level
     * 
     * @param root The root node of the binary tree
     * @return Maximum width among all levels
     */
    public static int getMaxWidth(Node root) {
        if (root == null)
            return 0;

        Queue<Node> queue = new LinkedList<>();    // Queue for BFS traversal
        queue.add(root);                          // Start with root node
        int maxWidth = 0;                         // Track maximum width seen

        while (!queue.isEmpty()) {
            int size = queue.size();              // Number of nodes at current level
            maxWidth = Math.max(maxWidth, size);  // Update max width if current level is wider

            // Process all nodes at current level
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();         // Get next node from queue

                // Add left child to queue if exists
                if (node.left != null) {
                    queue.add(node.left);
                }
                // Add right child to queue if exists
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return maxWidth;    // Return maximum width found
    }
}
