/**
 * LeetCode 1038. Binary Search Tree to Greater Sum Tree
 * 
 * Problem Statement:
 *     Given the root of a Binary Search Tree (BST), convert it to a Greater Sum Tree
 *     such that every key of the original BST is changed to the original key plus
 *     the sum of all keys greater than the original key in BST.
 * 
 * Input:
 *     - TreeNode root (root node of BST)
 *     - Node values are integers
 * 
 * Output:
 *     - TreeNode (root of modified Greater Sum Tree)
 * 
 * Example:
 *     Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 *     Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * 
 *     Original BST:          Greater Sum Tree:
 *          4                      30
 *         / \                    /  \
 *        1   6                  36   21
 *       / \  / \               / \   / \
 *      0  2 5   7            36 35 26  15
 *         \       \              \       \
 *          3       8             33       8
 */

public class j05ConvertToGreaterSumTree {

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
     * Converts BST to Greater Sum Tree
     * 
     * Intuition:
     * - Use reverse inorder traversal (right -> root -> left)
     * - Maintain running sum of all greater values
     * - Update each node with sum including its value
     * 
     * Explanation:
     * - Process right subtree first (greater values)
     * - Add current value to running sum
     * - Update current node with running sum
     * - Process left subtree
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree
     * 
     * @param root    Root node of BST
     * @return       Root of modified Greater Sum Tree
     */
    public static TreeNode bstToGst(TreeNode root) {
        int[] sum = new int[1];  // Array to store running sum
        convertToGreaterSum(root, sum);
        return root;
    }

    /**
     * Helper method for reverse inorder traversal
     * 
     * Intuition:
     * - Process nodes in decreasing order
     * - Maintain running sum of processed values
     * 
     * @param root    Current node being processed
     * @param sum     Array storing running sum
     */
    private static void convertToGreaterSum(TreeNode root, int[] sum) {
        if (root == null) 
            return;
            
        // Process right subtree (greater values)
        convertToGreaterSum(root.right, sum);
        
        // Update running sum and current node
        sum[0] += root.val;
        root.val = sum[0];
        
        // Process left subtree (smaller values)
        convertToGreaterSum(root.left, sum);
    }

    /**
     * Helper method to print tree structure
     */
    private static void printTree(TreeNode root, String indent, String prefix) {
        if (root == null) {
            System.out.println(indent + prefix + "null");
            return;
        }
        System.out.println(indent + prefix + root.val);
        printTree(root.left, indent + "    ", "L── ");
        printTree(root.right, indent + "    ", "R── ");
    }

    public static void main(String[] args) {
        // Test Case 1: Basic BST
        System.out.println("\nBasic BST Test:");
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(2);
        root1.left.right.right = new TreeNode(3);
        root1.right.left = new TreeNode(5);
        root1.right.right = new TreeNode(7);
        root1.right.right.right = new TreeNode(8);
        
        System.out.println("Original BST:");
        printTree(root1, "", "Root: ");
        
        TreeNode result1 = bstToGst(root1);
        
        System.out.println("\nGreater Sum Tree:");
        printTree(result1, "", "Root: ");

        // Test Case 2: Single node
        System.out.println("\nSingle Node Test:");
        TreeNode root2 = new TreeNode(1);
        TreeNode result2 = bstToGst(root2);
        printTree(result2, "", "Root: ");

        // Test Case 3: Linear BST
        System.out.println("\nLinear BST Test:");
        TreeNode root3 = new TreeNode(1);
        root3.right = new TreeNode(2);
        root3.right.right = new TreeNode(3);
        TreeNode result3 = bstToGst(root3);
        printTree(result3, "", "Root: ");
    }
}
