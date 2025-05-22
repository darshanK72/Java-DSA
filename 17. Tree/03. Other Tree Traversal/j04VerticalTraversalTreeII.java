/**
 * LeetCode 987. Vertical Order Traversal of a Binary Tree
 * 
 * Problem Statement:
 *     Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 *     For each node at position (row, col), its left and right children will be at positions 
 *     (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 *     The vertical order traversal of a binary tree is a list of top-to-bottom orderings for
 *     each column index starting from the leftmost column and ending on the rightmost column.
 *     For each column, nodes are ordered from top to bottom with tied nodes ordered by value.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - List of lists containing nodes in vertical order with proper sorting
 * 
 * Example:
 *     Input: 
 *           3
 *          / \
 *         9   20
 *            /  \
 *           15   7
 *     
 *     Output: [[9], [3,15], [20], [7]]
 *     
 *     Explanation:
 *     Column -1: [9]
 *     Column  0: [3,15] (15 below 3)
 *     Column  1: [20]
 *     Column  2: [7]
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class j04VerticalTraversalTreeII {

    /**
     * TreeNode class representing each node in the binary tree
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Main method to test the implementation
     */
    public static void main(String[] args) {

        // Test Case 1: Example tree
        /**
         *           3
         *          / \
         *         9   20
         *            /  \
         *           15   7
         */
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println("Test Case 1 - Example tree");
        System.out.println("Expected: [[9], [3,15], [20], [7]]");
        System.out.println("Output: " + verticalTraversal(root1));

        // Test Case 2: Tree with same column values
        /**
         *           1
         *          / \
         *         2   3
         *        /     \
         *       4       5
         */
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);
        System.out.println("\nTest Case 2 - Tree with same column values");
        System.out.println("Expected: [[4], [2], [1], [3], [5]]");
        System.out.println("Output: " + verticalTraversal(root2));
    }

    /**
     * Approach: DFS with Double TreeMap
     * 
     * Intuition:
     * - Use two TreeMaps to maintain both column and row ordering
     * - First TreeMap: maps column (x) to another TreeMap
     * - Second TreeMap: maps row (y) to list of nodes at that position
     * - Sort nodes at same position by value
     * 
     * Time Complexity: O(n*log(n)) - due to sorting at same positions
     * Space Complexity: O(n) - to store all nodes
     */
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> out = new ArrayList<>();             // Result list
        if(root == null) return out;                            // Handle empty tree
        
        // Map<Column, Map<Row, Values>>
        TreeMap<Integer,TreeMap<Integer,ArrayList<Integer>>> map = new TreeMap<>();
        verticalTraversal(root, map, 0, 0);                    // Start DFS
        
        // Process map to create result
        for(int col : map.keySet()) {                          // For each column
            ArrayList<Integer> colList = new ArrayList<>();
            TreeMap<Integer,ArrayList<Integer>> rowMap = map.get(col);
            
            for(int row : rowMap.keySet()) {                   // For each row
                ArrayList<Integer> values = rowMap.get(row);
                Collections.sort(values);                       // Sort same position values
                colList.addAll(values);
            }
            out.add(colList);
        }
        return out;
    }

    /**
     * Helper method to perform DFS traversal
     * @param node Current node being processed
     * @param map TreeMap storing vertical order
     * @param x Column number (horizontal distance)
     * @param y Row number (vertical level)
     */
    public static void verticalTraversal(TreeNode node, 
            TreeMap<Integer,TreeMap<Integer,ArrayList<Integer>>> map, 
            int x, int y) {
        if(node == null) return;                               // Base case
        
        // Initialize maps if needed
        map.putIfAbsent(x, new TreeMap<>());                  // Create column if needed
        map.get(x).putIfAbsent(y, new ArrayList<>());         // Create row if needed
        map.get(x).get(y).add(node.val);                      // Add value to position
        
        verticalTraversal(node.left, map, x-1, y+1);          // Process left child
        verticalTraversal(node.right, map, x+1, y+1);         // Process right child
    }
}
