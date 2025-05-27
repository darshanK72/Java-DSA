/**
 * LeetCode 700. Search in a Binary Search Tree
 * 
 * Problem Statement:
 *     Given the root node of a binary search tree (BST) and a value, find the node
 *     in the BST that has the given value. Return the subtree rooted with that node.
 *     If such node doesn't exist, return null.
 * 
 * Input:
 *     - TreeNode root (root node of BST)
 *     - int val (value to search)
 *     - Node values are unique
 * 
 * Output:
 *     - TreeNode (root of subtree containing val, or null if not found)
 * 
 * Example:
 *     Input: root = [4,2,7,1,3], val = 2
 *     Output: [2,1,3]
 * 
 *     Explanation:
 *          4
 *         / \
 *        2   7
 *       / \
 *      1   3
 *     
 *     Return subtree:
 *          2
 *         / \
 *        1   3
 */

public class j01SearchNodeInBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    /**
     * Searches for a value in BST
     * 
     * Intuition:
     * - Use BST property: left subtree < node < right subtree
     * - Compare value with current node to decide direction
     * - Recursively search in appropriate subtree
     * 
     * Explanation:
     * - Base case: null tree or found value
     * - If value < current node, search left
     * - If value > current node, search right
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root    Root node of BST
     * @param val     Value to search
     * @return       Root of subtree containing val, or null if not found
     */
    public static TreeNode searchBST(TreeNode root, int val) {
        // Base cases: empty tree or found value
        if(root == null) return null;
        if(root.val == val) return root;
        
        // Search in appropriate subtree
        if(val < root.val) 
            return searchBST(root.left, val);
        else 
            return searchBST(root.right, val);
    }

    /**
     * Iterative version of searchBST
     * Intuition:
     * - Use while loop to traverse tree
     * - Compare value with current node to decide direction
     * - Continue until value is found or tree ends
     * 
     * Explanation:
     * - Start from root and traverse downwards
     * - If value < current node, go left
     * - If value > current node, go right
     * - If value matches current node, return it
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(1) for iterative approach
     * 
     * @param root    Root node of BST
     * @param val     Value to search
     * @return       Root of subtree containing val, or null if not found
     */
    public static TreeNode searchBSTIterative(TreeNode root, int val) {
        while(root != null && root.val != val){
            root = (val < root.val) ? root.left : root.right;
        }
        return root;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic search
        System.out.println("\nBasic Search Test:");
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        TreeNode result1 = searchBST(root1, 2);
        System.out.println("Search for 2:" + result1 != null ? " Found" : " Not Found");

        // Test Case 2: Value not found
        System.out.println("\nValue Not Found Test:");
        TreeNode result2 = searchBST(root1, 5);
        System.out.println("Search for 5:" + result2 != null ? " Found" : " Not Found");
        // Test Case 3: Empty tree
        System.out.println("\nEmpty Tree Test:");
        TreeNode result3 = searchBST(null, 1);
        System.out.println("Search for 1:" + (result3 != null ? " Found" : " Not Found"));
    }
}
