/**
 * GeeksForGeeks: Bottom View of Binary Tree
 * 
 * Problem Statement:
 *     Given a binary tree, print the bottom view from left to right. A node is included
 *     in bottom view if it can be seen when we look at the tree from bottom. The last
 *     node at each horizontal distance from root in level order traversal will be in
 *     bottom view.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - ArrayList containing nodes visible from bottom view (left to right)
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        / \   \
 *       4   5   6
 *     
 *     Output: [4, 2, 5, 3, 6]
 *     
 *     Explanation:
 *     Column -2: 4 is visible
 *     Column -1: 2 is visible
 *     Column  0: 5 is visible (overlaps with 1)
 *     Column  1: 3 is visible
 *     Column  2: 6 is visible
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class j10BottomViewOfBinaryTree {

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
     * Pair class to store node with its horizontal distance
     * 
     * Intuition:
     * - Need to track both node and its horizontal position
     * - Last node at each horizontal distance forms bottom view
     * 
     * Example:
     * Root: new Pair(node, 0)      // Start at center
     * Left: new Pair(node, x-1)    // Move left
     * Right: new Pair(node, x+1)   // Move right
     */
    static class Pair {
        Node node;    // Tree node
        int x;        // Horizontal distance from root

        Pair(Node node, int x) {
            this.node = node;
            this.x = x;
        }
    }

    /**
     * Approach: Level Order Traversal with Distance Tracking
     * 
     * Intuition:
     * - Use horizontal distance to track vertical lines
     * - Last node seen at each horizontal distance is part of bottom view
     * - Use level order to ensure bottom nodes overwrite top nodes
     * 
     * Example visualization:
     *           1 (0)
     *          /     \
     *     (-1)2       3(1)
     *        /     \       \
     *   (-2)4       5(0)    6(2)
     *     
     * Numbers in brackets show horizontal distance
     * 
     * Explanation:
     * 1. Use queue for level order traversal
     * 2. Use TreeMap to maintain nodes sorted by horizontal distance
     * 3. Always update node at each horizontal distance
     * 4. Last update at each distance will be bottom view node
     * 
     * Time Complexity: O(n*log(w)) - n nodes, w is width of tree
     * Space Complexity: O(w) - w nodes in queue at most
     * 
     * @param root Root node of binary tree
     * @return ArrayList containing bottom view nodes from left to right
     */
    public static ArrayList<Integer> bottomView(Node root) {
        ArrayList<Integer> out = new ArrayList<>();             // Result list
        if(root == null) return out;                           // Handle empty tree

        TreeMap<Integer,Node> map = new TreeMap<>();           // Track last node at each x
        Queue<Pair> queue = new LinkedList<>();                // Queue for BFS
        queue.add(new Pair(root,0));                          // Start with root at x=0

        while(!queue.isEmpty()) {
            Pair pair = queue.poll();                          // Get next node
            Node node = pair.node;
            int x = pair.x;

            map.put(x,node);                                   // Update/add node at this x

            if(node.left != null) {                           // Process left child
                queue.add(new Pair(node.left,x - 1));
            }
            if(node.right != null) {                          // Process right child
                queue.add(new Pair(node.right,x + 1));
            }
        }
        
        for(int key : map.keySet()) {                         // Convert map to list
            out.add(map.get(key).data);
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
        System.out.println("Expected: [4, 2, 5, 3, 6]");
        System.out.println("Output: " + bottomView(root1));

        // Test Case 2: Empty tree
        System.out.println("\nTest Case 2 - Empty tree");
        System.out.println("Expected: []");
        System.out.println("Output: " + bottomView(null));

        // Test Case 3: Single node tree
        Node root3 = new Node(1);
        System.out.println("\nTest Case 3 - Single node tree");
        System.out.println("Expected: [1]");
        System.out.println("Output: " + bottomView(root3));
    }
}
