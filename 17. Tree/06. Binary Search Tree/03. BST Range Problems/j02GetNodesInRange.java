/**
 * GeeksForGeeks. Print BST nodes in given range
 * 
 * Problem Statement:
 *     Given a Binary Search Tree (BST) and a range [low, high], return a list of all
 *     nodes that lie in the given range (inclusive) in sorted order.
 * 
 * Input:
 *     - root: Root node of BST
 *     - low: Lower bound of range (inclusive)
 *     - high: Upper bound of range (inclusive)
 * 
 * Output:
 *     - ArrayList<Integer> containing nodes in range in sorted order
 * 
 * Example:
 *     Input: 
 *              10
 *            /    \
 *           5      50
 *          /  \   /  \
 *         1    8 40   100
 *         low = 5, high = 45
 *     
 *     Output: [5, 8, 10, 40]
 *     
 *     Explanation:
 *     Nodes with values 5, 8, 10, and 40 lie between 5 and 45 in sorted order
 */

import java.util.ArrayList;

public class j02GetNodesInRange {

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /**
     * Approach 1: Basic Inorder Traversal
     * 
     * Intuition:
     * - Use inorder traversal to get nodes in sorted order
     * - Add nodes to list only if they are within range
     * 
     * Explanation:
     * - Step 1: Create ArrayList to store result
     * - Step 2: Perform inorder traversal using helper method
     * - Step 3: Return list containing nodes in range
     * 
     * Time Complexity: O(N) where N is number of nodes
     * Space Complexity: O(H) for recursion + O(K) for output list
     * where H is height and K is nodes in range
     * 
     * @param root    Root node of BST
     * @param low     Lower bound of range (inclusive)
     * @param high    Upper bound of range (inclusive)
     * @return        ArrayList containing nodes in range in sorted order
     */
    public static ArrayList<Integer> printNearNodes(Node root, int low, int high) {
        ArrayList<Integer> out = new ArrayList<>();
        getNodesInRangeDFS(root, low, high, out);
        ArrayList<Integer> outEfficient = new ArrayList<>();
        getNodesInRangeEfficient(root, low, high, outEfficient);
        System.out.println("Using DFS: " + out);
        System.out.println("Using Efficient Search: " + outEfficient);
        return out;
    }

    /**
     * Helper Method: Inorder DFS Traversal
     * 
     * Intuition:
     * - Use inorder traversal to process nodes in sorted order
     * - Add nodes to list when they fall in range
     * 
     * Explanation:
     * - Step 1: Traverse left subtree
     * - Step 2: Process current node if in range
     * - Step 3: Traverse right subtree
     * 
     * Time Complexity: O(N) where N is number of nodes
     * Space Complexity: O(H) for recursion stack
     * 
     * @param root    Current node being processed
     * @param low     Lower bound of range
     * @param high    Upper bound of range
     * @param out     ArrayList to store result
     */
    private static void getNodesInRangeDFS(Node root, int low, int high, ArrayList<Integer> out) {
        if (root == null)
            return;
        // Process left subtree
        getNodesInRangeDFS(root.left, low, high, out);
        // Add current node if in range
        if (root.data >= low && root.data <= high) {
            out.add(root.data);
        }
        // Process right subtree
        getNodesInRangeDFS(root.right, low, high, out);
    }

    /**
     * Helper Method: Efficient Range Search
     * 
     * Intuition:
     * - Use BST properties to skip unnecessary subtrees
     * - Only traverse paths that could contain nodes in range
     * 
     * Explanation:
     * - Step 1: If node < low, only check right subtree
     * - Step 2: If node > high, only check left subtree
     * - Step 3: If node in range, process left, add node, process right
     * 
     * Time Complexity: O(H + K) where H is height and K is nodes in range
     * Space Complexity: O(H) for recursion stack
     * 
     * @param root    Current node being processed
     * @param low     Lower bound of range
     * @param high    Upper bound of range
     * @param out     ArrayList to store result
     */
    private static void getNodesInRangeEfficient(Node root, int low, int high, ArrayList<Integer> out) {
        if (root == null)
            return;
        // If current value is less than low, only check right subtree
        if (root.data < low) {
            getNodesInRangeEfficient(root.right, low, high, out);
        } 
        // If current value is more than high, only check left subtree
        else if (root.data > high) {
            getNodesInRangeEfficient(root.left, low, high, out);
        } 
        // Node is in range, process left, add node, process right
        else {
            getNodesInRangeEfficient(root.left, low, high, out);
            out.add(root.data);
            getNodesInRangeEfficient(root.right, low, high, out);
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        Node root1 = new Node(10);
        root1.left = new Node(5);
        root1.right = new Node(50);
        root1.left.left = new Node(1);
        root1.left.right = new Node(8);
        root1.right.left = new Node(40);
        root1.right.right = new Node(100);
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [5,45], Expected: [5,8,10,40], Output: " + 
                          printNearNodes(root1, 5, 45));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: null tree, Expected: [], Output: " + 
                          printNearNodes(null, 1, 10));
        
        // Test Case 3: Boundary cases
        Node singleNode = new Node(5);
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: Single node [5,5], Expected: [5], Output: " + 
                          printNearNodes(singleNode, 5, 5));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: No nodes in range [100,200], Expected: [], Output: " + 
                          printNearNodes(root1, 100, 200));
    }
}
