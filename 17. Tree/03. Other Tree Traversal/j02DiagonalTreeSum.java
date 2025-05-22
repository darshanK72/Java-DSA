/**
 * GeeksForGeeks: Diagonal Sum In Binary Tree
 * 
 * Problem Statement:
 *     Consider lines of slope -1 passing through nodes. Given a Binary Tree,
 *     print all diagonal sums. For a node, diagonal is the slope of -1 passing
 *     through it, nodes falling on same diagonal have same diagonal distance.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - ArrayList containing sum of all diagonals from top to bottom
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        / \   \
 *       9   6   4
 *          /     \
 *         8       5
 *     
 *     Output: [9, 19, 11]
 *     
 *     Explanation:
 *     Diagonal 1 (From root): 1 + 3 + 4 + 5 = 13
 *     Diagonal 2 (From 2): 2 + 6 = 8
 *     Diagonal 3 (From 9, 8): 9 = 9
 */

import java.util.ArrayList;
import java.util.HashMap;

public class j02DiagonalTreeSum {

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
     * Approach: DFS with HashMap for Diagonal Tracking
     * 
     * Intuition:
     * - Think of the tree as having diagonal lines from top-right to bottom-left
     * - Each diagonal line has a unique distance from the root's rightmost path
     * - When we move right, we stay on the same diagonal (distance doesn't change)
     * - When we move left, we start a new diagonal (distance increases by 1)
     * - Root starts at diagonal 0, each left child creates new diagonal
     * - Right path nodes contribute to same diagonal sum
     * 
     * Example:
     *           1 (d=0)
     *          /        \
     *    (d=1) 2        3 (d=0)
     *        /    \        \
     * (d=2) 9      6(d=1)   4 (d=0)
     *            /            \
     *       (d=2) 8            5 (d=0)
     * 
     * Here:
     * - Diagonal 0: 1 -> 3 -> 4 -> 5 (sum = 13)
     * - Diagonal 1: 2 -> 6 (sum = 8)
     * - Diagonal 2: 9 -> 8 (sum = 17)
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(h) - h is height of tree for recursion stack
     * 
     * @param root Root node of binary tree
     * @return ArrayList containing diagonal sums from top to bottom
     */
    public static ArrayList<Integer> diagonalSum(Node root) {
        HashMap<Integer,Integer> map = new HashMap<>();    // Store diagonal sums
        getDiagonalSum(root, map, 0);                     // Collect sums using DFS
        
        ArrayList<Integer> out = new ArrayList<>();        // Result list
        for(int key : map.keySet()) {                     // Convert map to list
            out.add(map.get(key));
        }
        return out;
    }
    
    /**
     * Helper method to perform DFS and collect diagonal sums
     * 
     * Intuition:
     * - Use DFS to traverse tree and maintain diagonal distance
     * - HashMap key is diagonal distance, value is running sum
     * - Right child inherits parent's diagonal distance
     * - Left child gets new diagonal distance (parent's + 1)
     * 
     * Example for current node:
     * - Current diagonal sum = existing sum + current node value
     * - Left recursion with (diagonal + 1)
     * - Right recursion with same diagonal
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(h) - recursion stack depth
     * 
     * @param root Current node being processed
     * @param map HashMap storing diagonal sums where key is diagonal distance
     * @param diagonal Current diagonal distance from root's rightmost path
     */
    public static void getDiagonalSum(Node root, HashMap<Integer,Integer> map, int diagonal) {
        if(root == null) return;                          // Base case
        
        // Add current node's value to its diagonal sum
        map.put(diagonal, map.getOrDefault(diagonal, 0) + root.data);
        
        getDiagonalSum(root.left, map, diagonal + 1);     // Left child: new diagonal
        getDiagonalSum(root.right, map, diagonal);        // Right child: same diagonal
    }

    /**
     * Main method to test the implementation
     */
    public static void main(String[] args) {
        // Test Case 1: Regular binary tree
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(9);
        root1.left.right = new Node(6);
        root1.right.right = new Node(4);
        root1.left.right.left = new Node(8);
        root1.right.right.right = new Node(5);
        
        System.out.println("Test Case 1 - Regular binary tree");
        System.out.println("Expected: [13, 8, 9]");
        System.out.println("Output: " + diagonalSum(root1));

        // Test Case 2: Empty tree
        System.out.println("\nTest Case 2 - Empty tree");
        System.out.println("Expected: []");
        System.out.println("Output: " + diagonalSum(null));

        // Test Case 3: Single node tree
        Node root3 = new Node(5);
        System.out.println("\nTest Case 3 - Single node tree");
        System.out.println("Expected: [5]");
        System.out.println("Output: " + diagonalSum(root3));
    }
}
