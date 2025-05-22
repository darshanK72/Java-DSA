/**
 * GeeksForGeeks: Vertical Sum in Binary Tree
 * 
 * Problem Statement:
 *     Given a binary tree, find the vertical sum of nodes that are in the same vertical
 *     line. Print all vertical sums, starting from leftmost to rightmost vertical line.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - ArrayList containing vertical sums from left to right
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        / \   \
 *       4   5   6
 *     
 *     Output: [4, 2, 1+5, 3, 6] = [4, 2, 6, 3, 6]
 *     
 *     Explanation:
 *     Column -2: 4
 *     Column -1: 2
 *     Column  0: 1+5 = 6
 *     Column  1: 3
 *     Column  2: 6
 */

import java.util.ArrayList;
import java.util.TreeMap;

public class j05VerticalTreeSum {

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
        System.out.println("Expected: [4, 2, 6, 3, 6]");
        System.out.println("Output: " + verticalSum(root1));

        // Test Case 2: Complex tree
        /**
         *           1
         *          / \
         *         2   3
         *        /     \
         *       4       5
         *      /         \
         *     6           7
         */
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.left.left = new Node(4);
        root2.right.right = new Node(5);
        root2.left.left.left = new Node(6);
        root2.right.right.right = new Node(7);

        System.out.println("\nTest Case 2 - Complex tree");
        System.out.println("Expected: [6, 4, 2, 1, 3, 5, 7]");
        System.out.println("Output: " + verticalSum(root2));

        // Test Case 3: Empty tree
        System.out.println("\nTest Case 3 - Empty tree");
        System.out.println("Expected: []");
        System.out.println("Output: " + verticalSum(null));
    }
    

    /**
     * Approach: DFS with TreeMap
     * 
     * Intuition:
     * - Use TreeMap to maintain vertical lines sorted by column number
     * - Each column stores list of nodes to be summed
     * - Process map to calculate sums for each column
     * 
     * Time Complexity: O(n*log(w)) - n nodes, w is width of tree
     * Space Complexity: O(n) - to store all nodes in map
     */
    public static ArrayList<Integer> verticalSum(Node root) {
        ArrayList<Integer> out = new ArrayList<>();             // Result list
        if (root == null)                                      // Handle empty tree
            return out;

        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();  // Column -> nodes map
        verticalTraversal(root, map, 0, 0);                   // Collect nodes by column

        for (int key : map.keySet()) {                        // Process each column
            int sum = 0;
            for (int e : map.get(key)) {                      // Sum nodes in column
                sum += e;
            }
            out.add(sum);                                     // Add column sum to result
        }
        return out;
    }

    /**
     * Helper method to perform DFS traversal
     * @param node Current node being processed
     * @param map TreeMap storing nodes by column
     * @param x Column number (horizontal distance)
     * @param y Row number (not used in this version)
     */
    public static void verticalTraversal(Node node, TreeMap<Integer, ArrayList<Integer>> map, 
                                       int x, int y) {
        if (node == null)                                     // Base case
            return;

        map.putIfAbsent(x, new ArrayList<>());               // Create column if needed
        map.get(x).add(node.data);                           // Add node to its column

        verticalTraversal(node.left, map, x - 1, y + 1);     // Process left subtree
        verticalTraversal(node.right, map, x + 1, y + 1);    // Process right subtree
    }
}
