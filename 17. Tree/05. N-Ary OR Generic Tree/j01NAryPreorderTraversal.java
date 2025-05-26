/**
 * LeetCode 589. N-ary Tree Preorder Traversal
 * 
 * Problem Statement:
 *     Given the root node of an N-ary tree, return the preorder traversal of its
 *     nodes' values. N-ary tree input serialization is represented in their level
 *     order traversal, each group of children is separated by null.
 * 
 * Input:
 *     - Node root (root node of N-ary tree)
 *     - Each node has a value and list of children nodes
 *     - Node values are in range [0, 10^4]
 *     - Number of nodes is in range [0, 10^4]
 * 
 * Output:
 *     - List<Integer> (preorder traversal values)
 * 
 * Example:
 *     Input: root = [1,null,3,2,4,null,5,6]
 *     Output: [1,3,5,6,2,4]
 * 
 *     Explanation:
 *           1
 *         / | \
 *        3  2  4
 *       / \
 *      5   6
 */

import java.util.ArrayList;
import java.util.List;

public class j01NAryPreorderTraversal {

    /*
     * N-ary Tree Node Definition
     * * Represents a node in an N-ary tree with a value and list of children.
     * - val : Integer value of the node
     * - children : List of child nodes
     */
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<>();
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * Performs preorder traversal of N-ary tree
     * 
     * Intuition:
     * - Visit root first, then children from left to right
     * - Use recursive approach to traverse all children
     * - ArrayList to store traversal result
     * 
     * Explanation:
     * - Create ArrayList to store result
     * - Call recursive helper method
     * - Return final list of traversal values
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree (recursion stack)
     * 
     * @param root    Root node of N-ary tree
     * @return       List containing preorder traversal values
     */
    public static List<Integer> preorder(Node root) {
        // Initialize result list
        ArrayList<Integer> preorder = new ArrayList<>();
        // Start recursive traversal
        nArrayPreorderTraversal(root, preorder);
        return preorder;
    }

    /*-
     * Helper method for recursive preorder traversal
     * 
     * Intuition:
     * - Process current node, then recursively process children
     * - Follow root-children pattern of preorder traversal
     * 
     * Explanation:
     * - Add current node value to result list
     * - Recursively traverse each child in order
     * 
     * Time Complexity: O(n) for visiting each node once
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root    Current node being processed
     * @param out     ArrayList storing traversal result
     */
    private static void nArrayPreorderTraversal(Node root, ArrayList<Integer> out) {
        // Base case: null node
        if (root == null)
            return;
            
        // Add current node value (preorder)
        out.add(root.val);
        
        // Process all children recursively
        for (Node child : root.children) {
            nArrayPreorderTraversal(child, out);
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic N-ary tree
        System.out.println("\nBasic N-ary Tree Test:");
        Node root1 = new Node(1);
        root1.children.add(new Node(3));
        root1.children.add(new Node(2));
        root1.children.add(new Node(4));
        root1.children.get(0).children.add(new Node(5));
        root1.children.get(0).children.add(new Node(6));
        System.out.println("Preorder: " + preorder(root1));

        // Test Case 2: Single node
        System.out.println("\nSingle Node Test:");
        Node root2 = new Node(1);
        System.out.println("Preorder: " + preorder(root2));

        // Test Case 3: Linear tree (each node has one child)
        System.out.println("\nLinear Tree Test:");
        Node root3 = new Node(1);
        root3.children.add(new Node(2));
        root3.children.get(0).children.add(new Node(3));
        System.out.println("Preorder: " + preorder(root3));
    }
}
