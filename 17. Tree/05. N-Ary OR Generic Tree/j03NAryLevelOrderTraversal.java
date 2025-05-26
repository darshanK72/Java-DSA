/**
 * LeetCode 429. N-ary Tree Level Order Traversal
 * 
 * Problem Statement:
 *     Given an n-ary tree, return the level order traversal of its nodes' values.
 *     N-ary tree input serialization is represented in their level order traversal,
 *     each group of children is separated by null.
 * 
 * Input:
 *     - Node root (root node of N-ary tree)
 *     - Each node has a value and list of children nodes
 *     - Node values are in range [0, 10^4]
 *     - Number of nodes is in range [0, 10^4]
 * 
 * Output:
 *     - List<List<Integer>> (level order traversal values)
 * 
 * Example:
 *     Input: root = [1,null,3,2,4,null,5,6]
 *     Output: [[1],[3,2,4],[5,6]]
 * 
 *     Explanation:
 *           1
 *         / | \
 *        3  2  4
 *       / \
 *      5   6
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class j03NAryLevelOrderTraversal {
    
    /**
     * N-ary Tree Node Definition
     * Represents a node in an N-ary tree with a value and list of children.
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
     * Performs level order traversal of N-ary tree
     * 
     * Intuition:
     * - Use queue to process nodes level by level
     * - Each level's nodes are processed before moving to next level
     * - Store each level's values in separate list
     * 
     * Explanation:
     * - Initialize result list for all levels
     * - Use queue to maintain level order
     * - Process each level separately using queue size
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(w) where w is maximum width of tree
     * 
     * @param root    Root node of N-ary tree
     * @return       List of lists containing level order traversal values
     */
    public static List<List<Integer>> levelOrder(Node root) {
        // Initialize result list
        List<List<Integer>> out = new ArrayList<>();
        
        // Handle null tree case
        if(root == null) 
            return out;
            
        // Perform level order traversal
        nArrayLevelOrderTraversal(root, out);
        return out;
    }

    /**
     * Helper method for level order traversal
     * 
     * Intuition:
     * - Queue helps maintain FIFO order for level processing
     * - Process all nodes at current level before moving to next
     * - Add all children to queue for next level processing
     * 
     * Explanation:
     * - Use queue to store nodes to be processed
     * - Track level size to process level by level
     * - Add children to queue for next level
     * 
     * Time Complexity: O(n) for visiting each node once
     * Space Complexity: O(w) where w is maximum width of tree
     * 
     * @param root    Current node being processed
     * @param out     List of lists storing level order result
     */
    private static void nArrayLevelOrderTraversal(Node root, List<List<Integer>> out) {
        // Initialize queue with root node
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        
        // Process nodes level by level
        while(!queue.isEmpty()) {
            // Get size of current level
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            
            // Process all nodes at current level
            for(int i = 0; i < size; i++) {
                // Get and process current node
                Node node = queue.poll();
                level.add(node.val);
                
                // Add all children to queue for next level
                for(Node child : node.children) {
                    queue.add(child);
                }
            }
            // Add current level to result
            out.add(level);
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
        System.out.println("Level Order: " + levelOrder(root1));

        // Test Case 2: Single node
        System.out.println("\nSingle Node Test:");
        Node root2 = new Node(1);
        System.out.println("Level Order: " + levelOrder(root2));

        // Test Case 3: Linear tree (each node has one child)
        System.out.println("\nLinear Tree Test:");
        Node root3 = new Node(1);
        root3.children.add(new Node(2));
        root3.children.get(0).children.add(new Node(3));
        System.out.println("Level Order: " + levelOrder(root3));
    }
}
