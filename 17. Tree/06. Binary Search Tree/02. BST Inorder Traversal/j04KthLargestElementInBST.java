/**
 * GeeksForGeeks: Kth Largest Element in BST
 * 
 * Problem Statement:
 *     Given a Binary Search Tree and a positive integer k, find the kth largest
 *     element in the Binary Search Tree.
 * 
 * Input:
 *     - Node root (root node of BST)
 *     - int k (position from largest, 1-indexed)
 *     - Node values are integers
 *     - 1 <= k <= number of nodes
 * 
 * Output:
 *     - int (kth largest value in BST)
 * 
 * Example:
 *     Input: root = [4,2,9,1,3], k = 2
 *     Output: 4
 * 
 *     Explanation:
 *          4
 *         / \
 *        2   9
 *       / \
 *      1   3
 *     
 *     2nd largest element is 4
 */

public class j04KthLargestElementInBST {
    
    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /**
     * Finds kth largest element in BST
     * 
     * Intuition:
     * - Convert kth largest to equivalent kth smallest problem
     * - Use size of tree to map largest to smallest position
     * - Use inorder traversal to find mapped position
     * 
     * Explanation:
     * - Calculate total nodes in tree
     * - Map kth largest to (size - k + 1)th smallest
     * - Use inorder traversal to find result
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree
     * 
     * @param root    Root node of BST
     * @param k       Position from largest (1-indexed)
     * @return       kth largest value in BST
     */
    public static int kthLargest(Node root, int k) {
        int[] result = new int[2];  // [kth value, nodes processed]
        int size = size(root);
        kthSmall(root, size - k + 1, result);
        return result[0];
    }

    /**
     * Helper method for inorder traversal
     * 
     * Intuition:
     * - Use inorder traversal to get values in ascending order
     * - Track count of nodes processed
     * - Stop when target position reached
     * 
     * @param root     Current node being processed
     * @param k        Target position
     * @param result   Array storing result and count
     */
    private static void kthSmall(Node root, int k, int[] result) {
        if (root == null)
            return;
            
        // Process left subtree
        kthSmall(root.left, k, result);
        
        // Process current node
        result[1]++;
        if (result[1] == k) {
            result[0] = root.data;
            return;
        }
        
        // Process right subtree if target not found
        kthSmall(root.right, k, result);
    }

    /**
     * Helper method to calculate tree size
     * 
     * @param root    Root node of tree/subtree
     * @return       Number of nodes in tree
     */
    private static int size(Node root) {
        if (root == null) 
            return 0;
        return size(root.left) + size(root.right) + 1;
    }

    /**
     * Helper method to print BST structure
     */
    private static void printTree(Node root, String indent, String prefix) {
        if (root == null) {
            System.out.println(indent + prefix + "null");
            return;
        }
        System.out.println(indent + prefix + root.data);
        printTree(root.left, indent + "    ", "L── ");
        printTree(root.right, indent + "    ", "R── ");
    }

    public static void main(String[] args) {
        // Test Case 1: Basic BST
        System.out.println("\nBasic BST Test:");
        Node root1 = new Node(4);
        root1.left = new Node(2);
        root1.right = new Node(9);
        root1.left.left = new Node(1);
        root1.left.right = new Node(3);
        System.out.println("Tree structure:");
        printTree(root1, "", "Root: ");
        System.out.println("2nd largest: " + kthLargest(root1, 2));  // Expected: 4

        // Test Case 2: Linear BST
        System.out.println("\nLinear BST Test:");
        Node root2 = new Node(5);
        root2.right = new Node(7);
        root2.right.right = new Node(9);
        System.out.println("2nd largest: " + kthLargest(root2, 2));  // Expected: 7

        // Test Case 3: Single node
        System.out.println("\nSingle Node Test:");
        Node root3 = new Node(1);
        System.out.println("1st largest: " + kthLargest(root3, 1));  // Expected: 1
    }
}
