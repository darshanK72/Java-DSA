/**
 * Problem Statement:
 *     Nodes at Distance K (GeeksForGeeks)
 * 
 *     Given a binary tree and a number K, print all nodes at distance K from root.
 *     Distance is counted as number of edges between root and target node.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Distance K (non-negative integer)
 *     - Tree can have any number of nodes
 * 
 * Output:
 *     - ArrayList containing values of nodes at distance K
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         3   2
 *        /     \
 *       4       8
 *     K = 2
 *     
 *     Output: [4, 8]
 *     Explanation: 
 *     - Nodes 4 and 8 are at distance 2 from root
 *     - Path to 4: 1 -> 3 -> 4
 *     - Path to 8: 1 -> 2 -> 8
 */

import java.util.ArrayList;

public class j01NodesAtKDistanceI {

    /**
     * Node class represents a node in binary tree
     * Contains data and references to left and right children
     */
    public static class Node {
        int data;
        Node left, right;

        Node(int x) {
            data = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree
        Node root1 = new Node(1);
        root1.left = new Node(3);
        root1.right = new Node(2);
        root1.left.left = new Node(4);
        root1.right.right = new Node(8);
        
        System.out.println("Test Case 1 (K=2): " + 
            Kdistance(root1, 2));  // Expected: [4, 8]

        // Test Case 2: All nodes at same level
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        
        System.out.println("Test Case 2 (K=1): " + 
            Kdistance(root2, 1));  // Expected: [2, 3]

        // Test Case 3: K greater than tree height
        System.out.println("Test Case 3 (K=4): " + 
            Kdistance(root1, 4));  // Expected: []
    }

    /**
     * Main method to find nodes at distance K
     * 
     * Approach:
     * - Use DFS to traverse tree and track current depth
     * - When depth equals K, add node value to result
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * 
     * @param root Root node of binary tree
     * @param k Target distance from root
     * @return ArrayList of node values at distance k
     */
    public static ArrayList<Integer> Kdistance(Node root, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        findNodesAtKFromRoot(root, k, result);
        return result;
    }

    /**
     * Helper method to find nodes using DFS
     * 
     * Algorithm:
     * 1. Handle null node
     * 2. If current depth equals K, add node value
     * 3. Recursively process children with decremented K
     * 
     * @param root Current node being processed
     * @param k Remaining distance to target
     * @param result List to store node values at distance K
     */
    private static void findNodesAtKFromRoot(Node root, int k, ArrayList<Integer> result) {
        if (root == null)
            return;                     // Base case
            
        if (k == 0) {
            result.add(root.data);      // Found node at target distance
            return;
        }
        
        findNodesAtKFromRoot(root.left, k - 1, result);   // Process left subtree
        findNodesAtKFromRoot(root.right, k - 1, result);  // Process right subtree
    }
}
