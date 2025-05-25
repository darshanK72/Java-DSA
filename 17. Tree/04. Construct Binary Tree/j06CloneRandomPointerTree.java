/**
 * GeeksForGeeks: Clone a Binary Tree with Random Pointers
 * 
 * Problem Statement:
 *     Given a binary tree where each node has an additional random pointer which 
 *     could point to any node in the tree or null. Clone the given binary tree.
 * 
 * Input:
 *     - Tree root (root node of binary tree with random pointers)
 *     - Each node has data and three pointers: left, right, and random
 *     - Random pointer can point to any node in tree or null
 * 
 * Output:
 *     - Tree (root of cloned tree with identical structure and pointers)
 * 
 * Example:
 *     Input: Tree with nodes where node 1's random points to 3
 *            node 2's random points to 1, node 3's random points to 5
 * 
 *     Original Tree:        Clone Should Be:
 *          1*→3                  1*→3
 *         / \                   / \
 *        2*→1 3*→5            2*→1 3*→5
 *           / \                   / \
 *          4   5                 4   5
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class j06CloneRandomPointerTree {

    static class Tree {
        int data;
        Tree left;
        Tree right;
        Tree random;

        Tree(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.random = null;
        }
    }

    /**
     * Clone a binary tree with random pointers
     * 
     * Intuition:
     * - Two-pass approach: first clone structure, then set random pointers
     * - Use HashMap to maintain mapping between original and cloned nodes
     * - This mapping helps in setting random pointers correctly
     * 
     * Explanation:
     * - First pass: Clone basic tree structure (left and right pointers)
     * - Second pass: Set random pointers using HashMap mapping
     * - HashMap ensures O(1) lookup for corresponding cloned nodes
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(n) for HashMap storage
     * 
     * @param root    Root of original tree
     * @return       Root of cloned tree
     */
    public static Tree cloneTree(Tree root) {
        // Create map to store original to clone node mapping
        HashMap<Tree, Tree> map = new HashMap<>();
        
        // First pass: Clone basic tree structure
        Tree out = cloneSimpleTree(root, map);
        
        // Second pass: Set random pointers
        cloneRandomPointers(root, out, map);
        
        return out;
    }

    /**
     * Helper method to clone basic tree structure
     * 
     * Intuition:
     * - Recursively clone nodes in preorder
     * - Store mapping in HashMap for later use
     * 
     * Explanation:
     * - Create new node with same data
     * - Store mapping in HashMap
     * - Recursively clone left and right subtrees
     * 
     * Time Complexity: O(n) for visiting each node
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root    Current node in original tree
     * @param map     HashMap storing original to clone mapping
     * @return       Cloned node
     */
    public static Tree cloneSimpleTree(Tree root, HashMap<Tree, Tree> map) {
        // Base case: null node
        if (root == null)
            return null;
            
        // Create new node with same data
        Tree node = new Tree(root.data);
        
        // Store mapping in HashMap
        map.put(root, node);
        
        // Recursively clone left and right subtrees
        node.left = cloneSimpleTree(root.left, map);
        node.right = cloneSimpleTree(root.right, map);
        
        return node;
    }

    /**
     * Helper method to set random pointers in cloned tree
     * 
     * Intuition:
     * - Use HashMap mapping to find corresponding cloned nodes
     * - Set random pointers in cloned tree
     * 
     * Explanation:
     * - For each node, find its random pointer's clone from map
     * - Set random pointer in cloned tree
     * - Recursively process left and right subtrees
     * 
     * Time Complexity: O(n) for visiting each node
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root    Current node in original tree
     * @param out     Corresponding node in cloned tree
     * @param map     HashMap storing original to clone mapping
     */
    public static void cloneRandomPointers(Tree root, Tree out, HashMap<Tree, Tree> map) {
        // Base case: null node
        if (root == null)
            return;
            
        // Set random pointer if exists
        if (root.random != null) {
            out.random = map.get(root.random);
        }
        
        // Recursively process left and right subtrees
        cloneRandomPointers(root.left, out.left, map);
        cloneRandomPointers(root.right, out.right, map);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree with random pointers
        System.out.println("\nBasic Tree Test:");
        Tree root1 = new Tree(1);
        root1.left = new Tree(2);
        root1.right = new Tree(3);
        root1.right.left = new Tree(4);
        root1.right.right = new Tree(5);
        root1.random = root1.right;           // 1 -> 3
        root1.left.random = root1;            // 2 -> 1
        root1.right.random = root1.right.right; // 3 -> 5
        Tree clone1 = cloneTree(root1);
        printTree(clone1);

        // Test Case 2: Single node with self random
        System.out.println("\nSingle Node Test:");
        Tree root2 = new Tree(1);
        root2.random = root2;  // Points to self
        Tree clone2 = cloneTree(root2);
        printTree(clone2);

        // Test Case 3: Linear tree (only right children)
        System.out.println("\nLinear Tree Test:");
        Tree root3 = new Tree(1);
        root3.right = new Tree(2);
        root3.right.right = new Tree(3);
        root3.random = root3.right.right;  // 1 -> 3
        Tree clone3 = cloneTree(root3);
        printTree(clone3);
    }

    // Helper method to print tree structure
    private static void printTree(Tree root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        
        // Level order traversal to print tree
        Queue<Tree> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Tree node = queue.poll();
                System.out.print(node.data + " ");
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            System.out.println();
        }
    }
}
