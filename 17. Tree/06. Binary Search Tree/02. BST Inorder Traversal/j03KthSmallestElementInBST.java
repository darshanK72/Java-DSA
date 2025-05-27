/**
 * LeetCode 230. Kth Smallest Element in a BST
 * 
 * Problem Statement:
 *     Given the root of a binary search tree, and an integer k, return the kth
 *     smallest value (1-indexed) of all the values of the nodes in the tree.
 * 
 * Input:
 *     - TreeNode root (root node of BST)
 *     - int k (position of element to find, 1-indexed)
 *     - Node values are integers
 *     - 1 <= k <= number of nodes
 * 
 * Output:
 *     - int (kth smallest value in BST)
 * 
 * Example:
 *     Input: root = [3,1,4,null,2], k = 1
 *     Output: 1
 * 
 *     Explanation:
 *          3
 *         / \
 *        1   4
 *         \
 *          2
 *     
 *     1st smallest element is 1
 */

public class j03KthSmallestElementInBST {
    
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
     * Finds kth smallest element in BST
     * 
     * Intuition:
     * - Use inorder traversal to process nodes in ascending order
     * - Track count of nodes processed
     * - Return value when kth node is reached
     * 
     * Explanation:
     * - Initialize result array with:
     *   - index 0: kth smallest value (result)
     *   - index 1: count of nodes processed
     * - Perform inorder traversal until kth node found
     * 
     * Time Complexity: O(H + k) where H is height of tree
     * Space Complexity: O(H) for recursion stack
     * 
     * @param root    Root node of BST
     * @param k       Position of element to find (1-indexed)
     * @return       kth smallest value in BST
     */
    public static int kthSmallest(TreeNode root, int k) {
        int[] result = new int[2];  // [kth value, nodes processed]
        kthSmall(root, k, result);
        return result[0];
    }

    /**
     * Helper method for inorder traversal
     * 
     * Intuition:
     * - Process left subtree first (smaller elements)
     * - Process current node and check if kth element
     * - Process right subtree if kth not found
     * 
     * Explanation:
     * - result[0] stores kth smallest value when found
     * - result[1] tracks count of nodes processed
     * - Stop traversal when kth element found
     * 
     * @param root     Current node being processed
     * @param k        Target position
     * @param result   Array storing result and count
     */
    private static void kthSmall(TreeNode root, int k, int[] result) {
        if (root == null)
            return;
            
        // Process left subtree
        kthSmall(root.left, k, result);
        
        // Process current node
        result[1]++;
        if (result[1] == k) {
            result[0] = root.val;
            return;
        }
        
        // Process right subtree if kth not found
        kthSmall(root.right, k, result);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic BST
        System.out.println("\nBasic BST Test:");
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);
        System.out.println("1st smallest: " + kthSmallest(root1, 1));  // Expected: 1
        System.out.println("2nd smallest: " + kthSmallest(root1, 2));  // Expected: 2

        // Test Case 2: Linear BST
        System.out.println("\nLinear BST Test:");
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.left.left = new TreeNode(1);
        System.out.println("2nd smallest: " + kthSmallest(root2, 2));  // Expected: 3

        // Test Case 3: Complete BST
        System.out.println("\nComplete BST Test:");
        TreeNode root3 = new TreeNode(4);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(6);
        root3.left.left = new TreeNode(1);
        root3.left.right = new TreeNode(3);
        System.out.println("3rd smallest: " + kthSmallest(root3, 3));  // Expected: 3
    }
}
