/**
 * Problem Statement:
 *     Foldable Binary Tree (GeeksForGeeks)
 * 
 *     Given a binary tree, check if it is foldable. A tree is foldable if the left
 *     and right subtrees are mirror images structurally (ignoring node values).
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 *     - Node values are not considered for folding
 * 
 * Output:
 *     - Boolean indicating if tree is foldable
 * 
 * Example:
 *     Input: 
 *           10
 *          /  \
 *         7    15
 *          \  /
 *          9 11
 *     
 *     Output: true
 *     Explanation: Left and right subtrees are structural mirrors
 *     (node values don't matter for foldability)
 */

public class j03IsFoldableTree {

    /**
     * Node class represents a node in binary tree
     * Contains data and references to left and right children
     */
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int x) {
            data = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Foldable tree
        Node root1 = new Node(10);
        root1.left = new Node(7);
        root1.right = new Node(15);
        root1.left.right = new Node(9);
        root1.right.left = new Node(11);
        System.out.println("Test Case 1 (Foldable Tree): " + 
            IsFoldable(root1));  // Expected: true

        // Test Case 2: Non-foldable tree
        Node root2 = new Node(10);
        root2.left = new Node(7);
        root2.right = new Node(15);
        root2.left.left = new Node(5);
        System.out.println("Test Case 2 (Non-foldable Tree): " + 
            IsFoldable(root2));  // Expected: false

        // Test Case 3: Single node
        Node root3 = new Node(5);
        System.out.println("Test Case 3 (Single Node): " + 
            IsFoldable(root3));  // Expected: true

        // Test Case 4: Empty tree
        System.out.println("Test Case 4 (Empty Tree): " + 
            IsFoldable(null));   // Expected: true
    }

    /**
     * Approach: Structure-only Mirror Comparison
     * 
     * Intuition:
     * - A tree is foldable if its subtrees are structural mirrors
     * - Compare only the structure, ignoring node values
     * - Use recursive comparison of mirror positions
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static boolean IsFoldable(Node node) {
        if (node == null)
            return true;                            // Empty tree is foldable
        return isMirrorStructure(node.left, node.right);
    }

    /**
     * Helper method to check if trees are structural mirrors
     * Only compares structure, ignores node values
     */
    public static boolean isMirrorStructure(Node p, Node q) {
        if (p == null && q == null)
            return true;                           // Both empty - mirror structure
        if (p == null || q == null)
            return false;                          // One empty - different structure
            
        return isMirrorStructure(p.left, q.right)  // Check mirror positions
            && isMirrorStructure(p.right, q.left);
    }
}
