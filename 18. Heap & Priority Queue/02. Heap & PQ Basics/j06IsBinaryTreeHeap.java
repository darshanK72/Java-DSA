/**
 * Binary Tree Max Heap Validator
 * 
 * Problem Statement:
 *     Given a binary tree, determine if it represents a valid max heap. A valid
 *     max heap must satisfy two conditions:
 *     1. Complete Binary Tree: All levels are filled except possibly the last,
 *        which is filled from left to right
 *     2. Max Heap Property: Each parent node is greater than or equal to its
 *        children
 * 
 * Input:
 *     - tree (Node): Root node of the binary tree
 * 
 * Output:
 *     - boolean: True if tree represents a valid max heap, false otherwise
 * 
 * Example:
 *     Input: 
 *           10
 *          /  \
 *         5    3
 *        / \
 *       2   4
 *     Output: true
 * 
 *     Explanation:
 *     The tree is a valid max heap because:
 *     - It is a complete binary tree (all levels filled except last)
 *     - Each parent is greater than its children (10 > 5,3; 5 > 2,4)
 */

public class j06IsBinaryTreeHeap {
    /**
     * Node Structure: Represents a node in the binary tree
     * 
     * Fields:
     * - data: Value stored in the node
     * - left: Reference to left child
     * - right: Reference to right child
     */
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    /**
     * Max Heap Validator: Validates if binary tree is a max heap
     * 
     * Intuition:
     * - Count total nodes to verify complete binary tree property
     * - Use index-based approach to validate structure
     * - Check max heap property at each node
     * 
     * Explanation:
     * - Step 1: Count total nodes in tree
     * - Step 2: Validate complete binary tree structure
     * - Step 3: Verify max heap property
     * 
     * Edge Cases:
     * - Empty tree: Returns true
     * - Single node: Returns true
     * - Incomplete tree: Returns false
     * 
     * Connection to Solution:
     * - Entry point for heap validation
     * - Combines structure and property checks
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(log n) due to recursion
     * 
     * @param tree    Root node of the binary tree
     * @return        True if tree is a valid max heap
     */
    public static boolean isHeap(Node tree) {
        int count = countNode(tree);
        return isBinaryTreeHeap(tree, count, 1);
    }

    /**
     * Node Counter: Counts total nodes in binary tree
     * 
     * Intuition:
     * - Recursively count nodes in left and right subtrees
     * - Add 1 for current node
     * 
     * Explanation:
     * - Step 1: Base case - null node returns 0
     * - Step 2: Count nodes in left subtree
     * - Step 3: Count nodes in right subtree
     * - Step 4: Add 1 for current node
     * 
     * Edge Cases:
     * - Null tree: Returns 0
     * - Single node: Returns 1
     * 
     * Connection to Solution:
     * - Used to verify complete binary tree property
     * - Essential for index-based validation
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(log n) due to recursion
     * 
     * @param tree    Root node of the binary tree
     * @return        Total number of nodes
     */
    public static int countNode(Node tree) {
        if (tree == null)
            return 0;
        return countNode(tree.left) + countNode(tree.right) + 1;
    }

    /**
     * Binary Tree Heap Checker: Validates complete binary tree and max heap properties
     * 
     * Intuition:
     * - Use index-based approach to verify complete binary tree
     * - Check max heap property at each node
     * - Recursively validate subtrees
     * 
     * Explanation:
     * - Step 1: Base case - null node is valid
     * - Step 2: Check if index exceeds node count (incomplete tree)
     * - Step 3: Leaf nodes are valid
     * - Step 4: Check max heap property with children
     * - Step 5: Recursively validate subtrees
     * 
     * Edge Cases:
     * - Null node: Returns true
     * - Leaf node: Returns true
     * - Incomplete tree: Returns false
     * - Heap property violation: Returns false
     * 
     * Connection to Solution:
     * - Core validation logic
     * - Combines structure and property checks
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(log n) due to recursion
     * 
     * @param tree     Root node of the binary tree
     * @param count    Total number of nodes
     * @param index    Current node index (1-based)
     * @return         True if subtree is a valid max heap
     */
    public static boolean isBinaryTreeHeap(Node tree, int count, int index) {
        if (tree == null)
            return true;
        if (count < index)
            return false;
        if (tree.left == null && tree.right == null)
            return true;
        if (tree.left != null && tree.data < tree.left.data)
            return false;
        if (tree.right != null && tree.data < tree.right.data)
            return false;
        return isBinaryTreeHeap(tree.left, count, index * 2) && 
               isBinaryTreeHeap(tree.right, count, index * 2 + 1);
    }

    public static void main(String[] args) {

        // Test Case 1: Valid Max Heap
        System.out.println("\nTest Case 1: Valid Max Heap");
        Node root1 = new Node(10);
        root1.left = new Node(5);
        root1.right = new Node(3);
        root1.left.left = new Node(2);
        root1.left.right = new Node(4);
        System.out.println("Is Max Heap: " + isHeap(root1) + 
                         " (Expected: true)");

        // Test Case 2: Invalid Max Heap (Property Violation)
        System.out.println("\nTest Case 2: Invalid Max Heap (Property Violation)");
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        System.out.println("Is Max Heap: " + isHeap(root2) + 
                         " (Expected: false)");

        // Test Case 3: Invalid Max Heap (Incomplete Tree)
        System.out.println("\nTest Case 3: Invalid Max Heap (Incomplete Tree)");
        Node root3 = new Node(10);
        root3.left = new Node(5);
        root3.left.left = new Node(2);
        System.out.println("Is Max Heap: " + isHeap(root3) + 
                         " (Expected: false)");

        // Test Case 4: Single Node
        System.out.println("\nTest Case 4: Single Node");
        Node root4 = new Node(1);
        System.out.println("Is Max Heap: " + isHeap(root4) + 
                         " (Expected: true)");

        // Test Case 5: Empty Tree
        System.out.println("\nTest Case 5: Empty Tree");
        System.out.println("Is Max Heap: " + isHeap(null) + 
                         " (Expected: true)");
    }
}
