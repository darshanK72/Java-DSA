/**
 * LeetCode 1382. Balance a Binary Search Tree
 * 
 * Problem Statement:
 *     Given the root of a binary search tree, return a balanced binary search tree
 *     with the same node values. If there is more than one answer, return any of them.
 *     A binary search tree is balanced if the depth of the two subtrees of every
 *     node never differs by more than 1.
 * 
 * Input:
 *     - root: Root node of binary search tree
 *     - Number of nodes in range [1, 10^4]
 *     - Node values in range [1, 10^5]
 * 
 * Output:
 *     - Root node of balanced BST
 * 
 * Example:
 *     Input:    1               Output:     2
 *                \                        /   \
 *                 2                      1     3
 *                  \                      	\
 *                   3                          4
 *                    \
 *                     4
 */
import java.util.ArrayList;

public class j09BalanceBinarySearchTree {

    /**
     * Definition for binary tree node
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }   

    /**
     * Approach: Inorder + Binary Search Tree Construction
     * 
     * Intuition:
     * - Get sorted nodes using inorder traversal
     * - Build balanced BST using binary search approach
     * - Middle element becomes root at each step
     * 
     * Explanation:
     * - Step 1: Convert BST to sorted list
     *          - Use inorder traversal to get nodes in sorted order
     *          - Store nodes in ArrayList for random access
     * 
     * - Step 2: Build balanced BST
     *          - Use divide and conquer approach
     *          - Middle element becomes root
     *          - Recursively build left and right subtrees
     * 
     * Time Complexity: O(n) - One pass for inorder + one pass for construction
     * Space Complexity: O(n) - ArrayList storage + recursion stack
     * 
     * @param root Root of input BST
     * @return Root of balanced BST
     */
    public static TreeNode balanceBST(TreeNode root) {
        // Step 1: Store nodes in sorted order
        ArrayList<TreeNode> nodes = new ArrayList<>();
        convertToList(root, nodes);
        
        // Step 2: Create balanced BST using stored nodes
        return createBSTUsingInorder(nodes, 0, nodes.size() - 1);
    }

    /**
     * Helper Method: Inorder Traversal
     * 
     * Intuition:
     * - Process nodes in sorted order (left -> root -> right)
     * - Add to ArrayList maintaining BST property
     * 
     * Explanation:
     * - Step 1: Handle base case (null node)
     * - Step 2: Process left subtree (smaller values)
     * - Step 3: Add current node to list
     * - Step 4: Process right subtree (larger values)
     * 
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack height
     * 
     * @param root Current node being processed
     * @param nodes ArrayList to store nodes in sorted order
     */
    public static void convertToList(TreeNode root, ArrayList<TreeNode> nodes) {
        if (root == null) return;
        convertToList(root.left, nodes);
        nodes.add(root);
        convertToList(root.right, nodes);
    }

    /**
     * Helper Method: Balanced BST Construction
     * 
     * Intuition:
     * - Use binary search approach to maintain balance
     * - Middle element becomes root at each step
     * 
     * Explanation:
     * - Step 1: Handle base case (invalid range)
     * - Step 2: Find middle element as root
     * - Step 3: Recursively build left subtree from left half
     * - Step 4: Recursively build right subtree from right half
     * 
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(log n) - Recursion stack height
     * 
     * @param nodes ArrayList containing nodes in sorted order
     * @param s Start index of current range
     * @param e End index of current range
     * @return Root of balanced BST for current range
     */
    public static TreeNode createBSTUsingInorder(ArrayList<TreeNode> nodes, int s, int e) {
        if (s > e) return null;
        
        // Find middle element as root
        int mid = s + (e - s)/2;
        TreeNode root = nodes.get(mid);
        
        // Recursively construct left and right subtrees
        root.left = createBSTUsingInorder(nodes, s, mid-1);
        root.right = createBSTUsingInorder(nodes, mid+1, e);
        
        return root;
    }
}
