/**
 * Problem Statement:
 *     Check Leaves at Same Level (GeeksForGeeks)
 * 
 *     Given a binary tree, check if all leaves are at same level or not.
 *     Return true if all leaf nodes are at same level, else return false.
 * 
 * Input:
 *     - Root node of binary tree
 * 
 * Output:
 *     - true if all leaves at same level
 *     - false otherwise
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        /     
 *       4      
 *     
 *     Output: false
 *     Explanation: 
 *     - Leaf node 4 is at level 3
 *     - Leaf node 3 is at level 2
 *     - Leaves are at different levels
 */

import java.util.LinkedList;
import java.util.Queue;

public class j07AllLeavesAtSameLevel {

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Leaves at different levels
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        
        System.out.println("Test Case 1: " + 
            check(root1));  // Expected: false

        // Test Case 2: Leaves at same level
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.left.right = new Node(4);
        root2.right.left = new Node(5);
        
        System.out.println("Test Case 2: " + 
            check(root2));  // Expected: true
    }

    /**
     * BFS with Level Flag Approach
     * 
     * Intuition:
     * 1. Use BFS to process level by level
     * 2. If we find a leaf node at current level:
     *    - Mark level as containing leaf (flag = true)
     *    - Check no more nodes exist in queue
     * 3. If leaf found and more nodes exist:
     *    - Means leaves at different levels
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(w)
     * - w is maximum width of tree (queue storage)
     * 
     * @param root Root node of binary tree
     * @return true if all leaves at same level
     */
    public static boolean check(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean leafFound = false;

            // Process current level
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();

                // Check if current node is leaf
                if (node.left == null && node.right == null) {
                    leafFound = true;
                }

                // Add non-null children to queue
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            // If leaf found at this level and more nodes exist
            // means leaves at different levels
            if (leafFound && queue.size() > 0) {
                return false;
            }
        }
        return true;
    }
}
