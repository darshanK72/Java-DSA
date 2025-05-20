/**
 * Problem Statement:
 *     Nodes without Siblings (GeeksForGeeks)
 * 
 *     Given a binary tree, print all nodes that don't have a sibling (a sibling is 
 *     defined as a node that shares the same parent). Print all such nodes in sorted 
 *     order. Return -1 if no such nodes exist.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 * 
 * Output:
 *     - ArrayList of node values without siblings in sorted order
 *     - Return [-1] if no such nodes exist
 * 
 * Example:
 *     Input: 
 *           37
 *          /  \
 *         20   40
 *        /    
 *       13    
 *     
 *     Output: [13]
 *     Explanation: 
 *     Only node 13 has no sibling (node 20 has right sibling 40)
 */

import java.util.ArrayList;
import java.util.Collections;

public class j02NodesWithoutSiblings {

    /**
     * Node class represents a node in binary tree
     */
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Tree with single node without sibling
        Node root1 = new Node(37);
        root1.left = new Node(20);
        root1.right = new Node(40);
        root1.left.left = new Node(13);
        
        System.out.println("Test Case 1: " + noSibling(root1));  // Expected: [13]

        // Test Case 2: No nodes without siblings
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        
        System.out.println("Test Case 2: " + noSibling(root2));  // Expected: [-1]

        // Test Case 3: Multiple nodes without siblings
        Node root3 = new Node(1);
        root3.left = new Node(5);
        root3.left.left = new Node(2);
        root3.right = new Node(6);
        root3.right.right = new Node(8);
        
        System.out.println("Test Case 3: " + noSibling(root3));  // Expected: [2, 8]
    }

    /**
     * DFS with List Collection Approach
     * 
     * Intuition:
     * - Use DFS to traverse tree and check each node's children
     * - If node has exactly one child, that child has no sibling
     * - Collect such nodes and sort them at the end
     * 
     * Time Complexity: O(n log n)
     * - O(n) for tree traversal
     * - O(n log n) for sorting result list
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * - O(n) in worst case for result list
     * 
     * @param root Root node of binary tree
     * @return List of nodes without siblings, sorted
     */
    public static ArrayList<Integer> noSibling(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        findNodesWithoutSiblings(root, result);
        Collections.sort(result);
        
        // Return [-1] if no nodes found
        if (result.isEmpty()) {
            result.add(-1);
        }
        return result;
    }

    /**
     * Helper method to find nodes using DFS
     * 
     * @param root Current node being processed
     * @param result List to store nodes without siblings
     */
    private static void findNodesWithoutSiblings(Node root, ArrayList<Integer> result) {
        if (root == null)
            return;

        // Check for nodes without siblings
        if (root.left != null && root.right == null) {
            result.add(root.left.data);   // Left child has no sibling
        }
        if (root.left == null && root.right != null) {
            result.add(root.right.data);  // Right child has no sibling
        }

        // Process children
        findNodesWithoutSiblings(root.left, result);
        findNodesWithoutSiblings(root.right, result);
    }
}
