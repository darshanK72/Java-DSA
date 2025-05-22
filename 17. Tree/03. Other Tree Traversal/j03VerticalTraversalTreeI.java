/**
 * GeeksForGeeks: Vertical Order Traversal of Binary Tree
 * 
 * Problem Statement:
 *     Given a Binary Tree, find the vertical traversal of it starting from the leftmost level
 *     to the rightmost level. If there are multiple nodes passing through a vertical line,
 *     then they should be printed as they appear in level order traversal.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - ArrayList of ArrayLists containing nodes in each vertical line
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        / \   \
 *       4   5   6
 *     
 *     Output: [[4], [2], [1,5], [3], [6]]
 *     
 *     Explanation:
 *     Column -2: Contains [4]
 *     Column -1: Contains [2]
 *     Column  0: Contains [1,5] (root's column)
 *     Column  1: Contains [3]
 *     Column  2: Contains [6]
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class j03VerticalTraversalTreeI {

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
     * Pair class to store node with its coordinates
     * 
     * Intuition:
     * - Need to track both node and its position in 2D grid
     * - x coordinate determines vertical line membership
     * - y coordinate tracks level for potential level-order requirements
     * 
     * Example usage:
     * Root: new Pair(node, 0, 0)      // Start at origin
     * Left: new Pair(node, x-1, y+1)  // Move left and down
     * Right: new Pair(node, x+1, y+1) // Move right and down
     */
    static class Pair {
        Node node;    // Tree node
        int x;        // Horizontal distance from root
        int y;        // Level in tree

        Pair(Node node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Main method to test both approaches
     */
    public static void main(String[] args) {
        // Test Case 1: Complete binary tree
        /**
         *           1
         *          / \
         *         2   3
         *        / \ / \
         *       4  5 6  7
         */
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);
        root1.right.left = new Node(6);
        root1.right.right = new Node(7);

        System.out.println("Test Case 1 - Complete binary tree");
        System.out.println("Expected: [[4], [2], [1,5,6], [3], [7]]");
        System.out.println("DFS Output: " + verticalOrder(root1));
        System.out.println("BFS Output: " + verticalOrderBFS(root1));

        // Test Case 2: Skewed tree
        /**
         *       1
         *        \
         *         2
         *          \
         *           3
         */
        Node root2 = new Node(1);
        root2.right = new Node(2);
        root2.right.right = new Node(3);

        System.out.println("\nTest Case 2 - Right skewed tree");
        System.out.println("Expected: [[1], [2], [3]]");
        System.out.println("DFS Output: " + verticalOrder(root2));
        System.out.println("BFS Output: " + verticalOrderBFS(root2));
    }

    /**
     * Approach: DFS with TreeMap
     * 
     * Intuition:
     * - Use horizontal distance (column number) to identify vertical lines
     * - Root starts at column 0
     * - Left child: column - 1
     * - Right child: column + 1
     * - TreeMap maintains sorted order of columns
     * 
     * Explanation:
     * 1. Use TreeMap to store nodes by column number:
     *    - Key: column number (x-coordinate)
     *    - Value: list of nodes in that column
     * 
     * 2. Process nodes using DFS:
     *    - Add node to its column's list
     *    - Process left subtree with (column - 1)
     *    - Process right subtree with (column + 1)
     * 
     * Time Complexity: O(n*log(w)) - n nodes, w is width of tree
     * Space Complexity: O(n) - to store all nodes
     * 
     * @param root Root node of binary tree
     * @return ArrayList of ArrayLists containing vertical traversal
     */
    public static ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();    // Result list
        if (root == null) return out;                            // Handle empty tree
        
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>(); // Track vertical lines
        verticalTraversal(root, map, 0);                  // Start DFS
        
        out.addAll(map.values());                               // Convert map to result
        return out;
    }

    /**
     * Helper method to perform DFS traversal
     * 
     * @param node Current node being processed
     * @param map TreeMap storing nodes by column
     * @param col Current column number
     */
    private static void verticalTraversal(Node node, 
            TreeMap<Integer,ArrayList<Integer>> map, int col) {
        if(node == null) return;                                // Base case
        
        map.putIfAbsent(col, new ArrayList<>());               // Create column if needed
        map.get(col).add(node.data);                           // Add node to column
        
        verticalTraversal(node.left, map, col - 1);            // Process left subtree
        verticalTraversal(node.right, map, col + 1);           // Process right subtree
    }

    /**
     * Helper method for DFS traversal
     * 
     * Intuition:
     * - Think of tree as grid with (x,y) coordinates
     * - x represents horizontal distance from root
     * - y represents vertical level (depth)
     * - Each vertical line is collection of nodes with same x-coordinate
     * 
     * Example with coordinates:
     *           1(0,0)
     *          /     \
     *     (-1,1)     (1,1)
     *       2          3
     *      / \          \
     *  (-2,2) (0,2)    (2,2)
     *    4       5        6
     * 
     * Explanation:
     * 1. Current node is added to its vertical line (x-coordinate)
     * 2. For left child:
     *    - x decreases by 1 (move left in grid)
     *    - y increases by 1 (move down one level)
     * 3. For right child:
     *    - x increases by 1 (move right in grid)
     *    - y increases by 1 (move down one level)
     * 
     * Time Complexity: O(log n) - for TreeMap operations
     * Space Complexity: O(1) - excluding recursion stack
     * 
     * @param node Current node being processed
     * @param map TreeMap storing nodes by vertical line
     * @param x Horizontal distance from root
     * @param y Vertical level in tree
     */
    public static void verticalTraversalDFS(Node node, 
            TreeMap<Integer, ArrayList<Integer>> map, int x, int y) {
        if (node == null) return;                               // Base case
        
        map.putIfAbsent(x, new ArrayList<>());                 // Create list if needed
        map.get(x).add(node.data);                             // Add node to its vertical line
        
        verticalTraversalDFS(node.left, map, x - 1, y + 1);    // Process left subtree
        verticalTraversalDFS(node.right, map, x + 1, y + 1);   // Process right subtree
    }

    /**
     * Approach 2: BFS with TreeMap
     * 
     * Intuition:
     * - Use BFS to maintain level order property
     * - Nodes at same vertical line processed in correct order
     * - More space efficient for wide trees
     * 
     * Time Complexity: O(n*log(w)) - n nodes, w is width of tree
     * Space Complexity: O(w) - w is maximum width of level
     */
    static ArrayList<ArrayList<Integer>> verticalOrderBFS(Node root) {
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        if (root == null) return out;

        Queue<Pair> queue = new LinkedList<>();                  // Queue for BFS
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>(); // Track vertical lines

        queue.add(new Pair(root, 0, 0));                        // Start with root

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();                           // Get next node
            Node node = curr.node;
            int x = curr.x;

            map.putIfAbsent(x, new ArrayList<>());             // Create list if needed
            map.get(x).add(node.data);                         // Add node to vertical line

            // Add children with updated coordinates
            if (node.left != null)
                queue.add(new Pair(node.left, x - 1, curr.y + 1));
            if (node.right != null)
                queue.add(new Pair(node.right, x + 1, curr.y + 1));
        }

        out.addAll(map.values());                              // Convert map to result
        return out;
    }
}
