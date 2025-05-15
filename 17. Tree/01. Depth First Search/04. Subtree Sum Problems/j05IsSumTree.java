/**
 * Problem Statement:
 *     Sum Tree (GeeksForGeeks)
 * 
 *     Given a Binary Tree, check if it is a Sum Tree. A sum tree is a tree where
 *     the value of each non-leaf node is equal to the sum of its left and right
 *     subtree values.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 *     - Node values can be any integer
 * 
 * Output:
 *     - Boolean indicating if tree is a sum tree
 * 
 * Example:
 *     Input: 
 *           26
 *          /  \
 *         10   3
 *        /  \   \
 *       4    6   3
 *     
 *     Output: true
 *     Explanation: 
 *     - All leaf nodes (4, 6, 3): Valid by definition
 *     - Node 10: 4 + 6 = 10 ✓
 *     - Node 3: 0 + 3 = 3 ✓
 *     - Root 26: 10 + 16 = 26 ✓
 */

public class j05IsSumTree {

    /**
     * Node class represents a node in binary tree
     */
    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) {
        j05IsSumTree solution = new j05IsSumTree();

        // Test Case 1: Valid sum tree
        Node root1 = new Node(26);
        root1.left = new Node(10);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(6);
        root1.right.right = new Node(3);
        System.out.println("Test Case 1 (Valid Sum Tree): " + 
            solution.isSumTree(root1));  // Expected: true

        // Test Case 2: Invalid sum tree
        Node root2 = new Node(10);
        root2.left = new Node(8);
        root2.right = new Node(2);
        root2.left.left = new Node(3);
        root2.left.right = new Node(5);
        System.out.println("Test Case 2 (Invalid Sum Tree): " + 
            solution.isSumTree(root2));  // Expected: false

        // Test Case 3: Single node
        Node root3 = new Node(5);
        System.out.println("Test Case 3 (Single Node): " + 
            solution.isSumTree(root3));  // Expected: true

        // Test Case 4: Empty tree
        System.out.println("Test Case 4 (Empty Tree): " + 
            solution.isSumTree(null));   // Expected: true
    }

    /**
     * Approach: Post-order DFS with Sum Verification
     * 
     * Intuition:
     * - Use post-order traversal to calculate subtree sums bottom-up
     * - For each non-leaf node, verify if value equals sum of children
     * - Use boolean array to track validity across recursive calls
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - Constant time operations at each node
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    boolean isSumTree(Node root) {
        if (root == null)
            return true;                    // Empty tree is valid
        boolean[] isInvalid = new boolean[1];  // Track validity
        subtreeSum(root, isInvalid);
        return !isInvalid[0];              // Return true if no invalid nodes found
    }

    /**
     * Helper method to calculate subtree sums and verify sum property
     * Returns: sum of current subtree
     * Updates: ans[0] to true if sum property is violated
     */
    public int subtreeSum(Node root, boolean[] ans) {
        if (root == null)
            return 0;                       // Empty subtree sum is 0
        if (root.right == null && root.left == null)
            return root.data;               // Leaf node returns its value
            
        int left = subtreeSum(root.left, ans);   // Get left sum
        int right = subtreeSum(root.right, ans);  // Get right sum
        
        if (left + right != root.data) {         // Verify sum property
            ans[0] = true;                       // Mark as invalid
            return -1;                           // Return sentinel value
        }
        
        return left + right + root.data;         // Return total sum
    }
}
