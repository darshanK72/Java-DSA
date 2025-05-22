/**
 * GeeksForGeeks: Vertical Width of Binary Tree
 * 
 * Problem Statement:
 *     Given a binary tree, find its vertical width. Vertical width is the 
 *     difference between the maximum and minimum horizontal distance of any node 
 *     from the root. Horizontal distance is measured from the root with initial 
 *     value as 0. Left child decrements it by 1, right child increments it by 1.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - Integer representing vertical width of the tree
 * 
 * Example:
 *     Input: 
 *           1
 *          /  \
 *         2    3
 *        / \    \
 *       4   5    6
 *     
 *     Output: 5
 *     
 *     Explanation:
 *     - Node 4 has horizontal distance -2
 *     - Node 2 has horizontal distance -1
 *     - Node 1,5 has horizontal distance 0
 *     - Node 3 has horizontal distance 1
 *     - Node 6 has horizontal distance 2
 *     Therefore, vertical width = 2 - (-2) + 1 = 5
 */

import java.util.HashSet;

public class j07VerticalWidthOfTree {

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
     * Main method to test the implementation with multiple test cases
     */
    public static void main(String[] args) {
        // Test Case 1: Regular binary tree
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);
        root1.right.right = new Node(6);
        System.out.println("Test Case 1 - Regular binary tree");
        System.out.println("Expected: 5, Output: " + verticalWidth(root1));

        // Test Case 2: Left skewed tree
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.left.left = new Node(3);
        System.out.println("\nTest Case 2 - Left skewed tree");
        System.out.println("Expected: 3, Output: " + verticalWidth(root2));

        // Test Case 3: Right skewed tree
        Node root3 = new Node(1);
        root3.right = new Node(2);
        root3.right.right = new Node(3);
        System.out.println("\nTest Case 3 - Right skewed tree");
        System.out.println("Expected: 3, Output: " + verticalWidth(root3));

        // Test Case 4: Empty tree
        System.out.println("\nTest Case 4 - Empty tree");
        System.out.println("Expected: 0, Output: " + verticalWidth(null));

        // Test Case 5: Single node tree
        Node root5 = new Node(1);
        System.out.println("\nTest Case 5 - Single node tree");
        System.out.println("Expected: 1, Output: " + verticalWidth(root5));
    }

    /**
     * Approach: DFS with HashSet
     * 
     * Intuition:
     * - Each node has a horizontal distance from root
     * - Left child decrements distance by 1, right child increments by 1
     * - Using HashSet to store unique horizontal distances
     * - Size of HashSet gives count of unique distances
     * 
     * Explanation:
     * 1. Use DFS to traverse the tree:
     *    - Pass current horizontal distance to each recursive call
     *    - Add each distance to HashSet
     * 
     * 2. For each node:
     *    - Add current distance to set
     *    - Left: Recurse with (distance - 1)
     *    - Right: Recurse with (distance + 1)
     * 
     * 3. HashSet size gives vertical width
     *    - Contains all unique horizontal distances
     *    - Automatically handles overlapping distances
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(w) - w is vertical width of tree
     * 
     * @param root The root node of the binary tree
     * @return Vertical width of the tree
     */
    public static int verticalWidth(Node root) {
        HashSet<Integer> set = new HashSet<>();           // Store unique horizontal distances
        findVerticalWidth(root, set, 0);                 // Start DFS from root with distance 0
        return set.size();                               // Return count of unique distances
    }
    
    /**
     * Helper method to perform DFS traversal and collect horizontal distances
     */
    public static void findVerticalWidth(Node root, HashSet<Integer> set, int distance) {
        if (root == null) return;                        // Base case: empty tree
        
        set.add(distance);                               // Add current distance to set
        findVerticalWidth(root.left, set, distance - 1); // Left child: decrease distance
        findVerticalWidth(root.right, set, distance + 1);// Right child: increase distance
    }
}
