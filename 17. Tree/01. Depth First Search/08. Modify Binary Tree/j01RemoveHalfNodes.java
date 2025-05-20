/**
 * Problem Statement:
 *     Remove Half Nodes (GeeksForGeeks)
 * 
 *     Given a binary tree, remove all nodes that have only one child.
 *     A half node is a node which has exactly one child. Returns the root
 *     of modified tree.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 * 
 * Output:
 *     - Root of modified tree with no half nodes
 * 
 * Example:
 *     Input: 
 *           2
 *          / \
 *         7   5
 *          \   \
 *           6   9
 *          /     \
 *         1       4
 *     
 *     Output:
 *           2
 *          / \
 *         1   4
 *     
 *     Explanation:
 *     - Node 7 has only right child (half node) - removed
 *     - Node 6 has only left child (half node) - removed
 *     - Node 5 has only right child (half node) - removed
 *     - Node 9 has only right child (half node) - removed
 */

public class j01RemoveHalfNodes {

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
        // Test Case 1: Tree with multiple half nodes
        Node root1 = new Node(2);
        root1.left = new Node(7);
        root1.right = new Node(5);
        root1.left.right = new Node(6);
        root1.right.right = new Node(9);
        root1.left.right.left = new Node(1);
        root1.right.right.right = new Node(4);

        System.out.println("Test Case 1:");
        System.out.println("Before:");
        printInorder(root1);
        root1 = removeHalfNodes(root1);
        System.out.println("\nAfter:");
        printInorder(root1);

        // Test Case 2: Linear tree (all half nodes)
        Node root2 = new Node(1);
        root2.right = new Node(2);
        root2.right.right = new Node(3);

        System.out.println("\n\nTest Case 2:");
        System.out.println("Before:");
        printInorder(root2);
        root2 = removeHalfNodes(root2);
        System.out.println("\nAfter:");
        printInorder(root2);
    }

    /**
     * Helper method to print tree inorder
     */
    private static void printInorder(Node root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    /**
     * DFS to remove half nodes from binary tree
     * 
     * Intuition:
     * - Use post-order traversal (process children first)
     * - For each node, check if it's a half node:
     *   * If only left child exists -> Return left child
     *   * If only right child exists -> Return right child
     *   * If both or no children exist -> Keep node as is
     * 
     * How it works:
     * 1. Process left subtree recursively
     * 2. Process right subtree recursively
     * 3. For current node:
     *    - If leaf node (no children) -> Keep it
     *    - If half node -> Return its only child
     *    - If full node -> Keep it with processed children
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(h) - recursion stack, h is height
     * 
     * @param root Root node of binary tree
     * @return Root of modified tree
     */
    public static Node removeHalfNodes(Node root) {
        // Base case: empty tree
        if (root == null)
            return null;

        // Process children first (post-order)
        root.left = removeHalfNodes(root.left);
        root.right = removeHalfNodes(root.right);

        // Case 1: Leaf node - keep it
        if (root.left == null && root.right == null)
            return root;

        // Case 2: Only right child - return right
        if (root.left == null)
            return root.right;

        // Case 3: Only left child - return left
        if (root.right == null)
            return root.left;

        // Case 4: Both children - keep node
        return root;
    }
}
