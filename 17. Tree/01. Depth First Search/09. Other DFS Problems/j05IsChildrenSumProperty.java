/**
 * Problem Statement:
 *     Children Sum Property (GeeksForGeeks)
 * 
 *     Given a binary tree, check if it follows children sum property.
 *     For every node, data value must be equal to sum of data values in left 
 *     and right children. Consider data value as 0 for NULL children.
 * 
 * Input:
 *     - Root node of binary tree
 * 
 * Output:
 *     - 1 if property is satisfied
 *     - 0 if property is not satisfied
 * 
 * Example:
 *     Input: 
 *           10
 *          /  \
 *         4    6
 *        / \
 *       1   3
 *     
 *     Output: 1
 *     Explanation: 
 *     - 10 = 4 + 6
 *     - 4 = 1 + 3
 *     - All leaf nodes satisfy property by default
 */

public class j05IsChildrenSumProperty {

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
        // Test Case 1: Valid tree
        Node root1 = new Node(10);
        root1.left = new Node(4);
        root1.right = new Node(6);
        root1.left.left = new Node(1);
        root1.left.right = new Node(3);
        
        System.out.println("Test Case 1: " + isSumProperty(root1));  // Expected: 1

        // Test Case 2: Invalid tree
        Node root2 = new Node(10);
        root2.left = new Node(8);
        root2.right = new Node(2);
        root2.left.left = new Node(5);
        root2.left.right = new Node(2);
        
        System.out.println("Test Case 2: " + isSumProperty(root2));  // Expected: 0
    }

    /**
     * DFS with Sum Verification Approach
     * 
     * Intuition:
     * 1. For each node, verify:
     *    - Sum of children equals node value
     *    - Both subtrees satisfy property
     * 2. Base cases:
     *    - Null node: satisfies property
     *    - Leaf node: satisfies property (no children)
     * 3. Return 0 as soon as violation found
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * 
     * @param root Root node of binary tree
     * @return 1 if property satisfied, 0 otherwise
     */
    public static int isSumProperty(Node root) {
        // Base cases
        if (root == null)
            return 1;
        if (root.left == null && root.right == null)
            return 1;

        // Calculate sum of children
        int childSum = 0;
        if (root.left != null)
            childSum += root.left.data;
        if (root.right != null)
            childSum += root.right.data;

        // Check current node's property
        if (childSum != root.data)
            return 0;

        // Verify property in subtrees
        int left = isSumProperty(root.left);
        int right = isSumProperty(root.right);

        // Return result
        return (left == 0 || right == 0) ? 0 : 1;
    }
}
