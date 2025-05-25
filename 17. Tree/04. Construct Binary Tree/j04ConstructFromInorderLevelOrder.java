/**
 * GeeksForGeeks: Construct Binary Tree from Inorder and Level Order Traversal
 * 
 * Problem Statement:
 *     Given inorder and level order traversals of a Binary Tree, construct the 
 *     Binary Tree. It is assumed that all nodes have unique values.
 * 
 * Input:
 *     - int[] inorder (array containing inorder traversal, values unique)
 *     - int[] level (array containing level order traversal, values unique)
 * 
 * Output:
 *     - Node (root of constructed binary tree)
 * 
 * Example:
 *     Input: inorder[] = {4, 8, 10, 12, 14, 20, 22}
 *            level[]   = {20, 8, 22, 4, 12, 10, 14}
 *     Output: Root of constructed tree
 * 
 *     Explanation:
 *            20
 *           /  \
 *          8   22
 *         / \
 *        4  12
 *          /  \
 *         10  14
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class j04ConstructFromInorderLevelOrder {

    static class Node {
        int val;
        Node left;
        Node right;

        Node(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    /**
     * Constructs binary tree from inorder and level order traversals
     * 
     * Intuition:
     * - Use level order to determine root nodes at each step
     * - Use inorder to determine left and right subtree elements
     * - HashMap for quick inorder index lookup
     * 
     * Explanation:
     * - Create HashMap to store inorder indices
     * - Call recursive helper with initial bounds
     * - Helper method constructs tree using array bounds
     * 
     * Time Complexity: O(n²) where n is number of nodes
     * Space Complexity: O(n) for HashMap storage
     * 
     * @param inord    Array containing inorder traversal
     * @param level    Array containing level order traversal
     * @return        Root node of constructed binary tree
     */
    public static Node buildTree(int inord[], int level[]) {
        // Create map for inorder value to index lookup
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < inord.length; i++) {
            map.put(inord[i], i);
        }
        
        // Start recursive construction
        return buildTreeInLevel(inord, level, map, 0, level.length-1, 0);
    }
    
    /**
     * Helper method for recursive tree construction
     * 
     * Intuition:
     * - First appearing level order value in current range is root
     * - Use inorder to split elements into left and right subtrees
     * 
     * Explanation:
     * - Base case: left > right means empty subtree
     * - Create root from current level order value
     * - Find root position in inorder using map
     * - Find next root indices for left and right subtrees
     * - Recursively construct left and right children
     * 
     * Time Complexity: O(n) for current subtree construction
     * Space Complexity: O(h) for recursion stack
     * 
     * @param inorder   Inorder traversal array
     * @param level     Level order traversal array
     * @param map       HashMap for inorder value lookup
     * @param left      Left bound of current subtree
     * @param right     Right bound of current subtree
     * @param index     Current index in level order array
     * @return         Root of current subtree
     */
    private static Node buildTreeInLevel(int[] inorder, int[] level, 
            HashMap<Integer,Integer> map, int left, int right, int index) {
        // Base case: empty subtree
        if(left > right) return null;
        
        // Create current root node
        int val = level[index];
        Node root = new Node(val);
        
        // Find position in inorder array
        int mid = map.get(val);
        
        // Find next root indices for subtrees
        int leftIndex = findIndex(inorder, level, left, mid-1);
        int rightIndex = findIndex(inorder, level, mid+1, right);
        
        // Recursively construct subtrees
        root.left = buildTreeInLevel(inorder, level, map, left, mid-1, leftIndex);
        root.right = buildTreeInLevel(inorder, level, map, mid+1, right, rightIndex);
        
        return root;
    }
    
    /**
     * Helper method to find next root in level order
     * 
     * Intuition:
     * - First value in level order that appears in current range is next root
     * - Use HashSet for O(1) lookup of valid values
     * 
     * Explanation:
     * - Create set of values in current inorder range
     * - Scan level order array for first matching value
     * 
     * Time Complexity: O(n) for scanning level order array
     * Space Complexity: O(n) for HashSet storage
     * 
     * @param inorder   Inorder traversal array
     * @param level     Level order traversal array
     * @param left      Left bound of current range
     * @param right     Right bound of current range
     * @return         Index in level order array or -1 if not found
     */
    private static int findIndex(int[] inorder, int[] level, int left, int right) {
        // Create set of values in current range
        HashSet<Integer> set = new HashSet<>();
        for(int i = left; i <= right; i++) {
            set.add(inorder[i]);
        }
        
        // Find first matching value in level order
        for(int i = 0; i < level.length; i++) {
            if(set.contains(level[i])) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        // Test Case 1: Complete binary tree
        System.out.println("\nComplete Binary Tree Test:");
        int[] inorder1 = {4, 8, 10, 12, 14, 20, 22};
        int[] level1 = {20, 8, 22, 4, 12, 10, 14};
        Node result1 = buildTree(inorder1, level1);
        printTree(result1);

        // Test Case 2: Skewed tree
        System.out.println("\nSkewed Tree Test:");
        int[] inorder2 = {1, 2, 3, 4};
        int[] level2 = {1, 2, 3, 4};
        Node result2 = buildTree(inorder2, level2);
        printTree(result2);

        // Test Case 3: Single node
        System.out.println("\nSingle Node Test:");
        int[] inorder3 = {1};
        int[] level3 = {1};
        Node result3 = buildTree(inorder3, level3);
        printTree(result3);
    }

     // Helper method to print tree structure
    private static void printTree(Node root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        
        // Level order traversal to print tree
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                System.out.print(node.val + " ");
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            System.out.println();
        }
    }
}
