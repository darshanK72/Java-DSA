/**
 * LeetCode 938. Range Sum of BST
 * 
 * Problem Statement:
 *     Given the root node of a binary search tree and two integers low and high,
 *     return the sum of values of all nodes with a value in the inclusive range [low, high].
 * 
 * Input:
 *     - root: Root node of BST
 *     - low: Lower bound of range (inclusive)
 *     - high: Upper bound of range (inclusive)
 * 
 * Output:
 *     - Integer sum of all node values in the given range
 * 
 * Example:
 *     Input: 
 *              10
 *            /    \
 *           5      15
 *          /  \     \
 *         3    7     18
 *         low = 7, high = 15
 *     
 *     Output: 32
 *     
 *     Explanation:
 *     Nodes 7 + 10 + 15 = 32 lie in range [7,15]
 */

public class j03RangeSumInBST {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int item) {
            val = item;
            left = right = null;
        }
    }

    /**
     * Approach: Optimized BST Traversal
     * 
     * Intuition:
     * - Use BST property to skip unnecessary subtrees
     * - Only traverse paths that could contain nodes in range
     * - Add node values that fall within range
     * 
     * Explanation:
     * - Step 1: If node < low, only explore right subtree
     * - Step 2: If node > high, only explore left subtree
     * - Step 3: If node in range, add its value and explore both subtrees
     * 
     * Time Complexity: O(H + K) where H is height and K is nodes in range
     * Space Complexity: O(H) for recursion stack
     * 
     * @param root    Root node of BST
     * @param low     Lower bound of range (inclusive)
     * @param high    Upper bound of range (inclusive)
     * @return        Sum of all node values in the given range
     */
    public static int rangeSumBST(TreeNode root, int low, int high) {
        // Base case: empty tree
        if(root == null) return 0;

        // If current value is less than low, only check right subtree
        if(root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        // If current value is more than high, only check left subtree
        else if(root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        // Node is in range, add its value and check both subtrees
        else {
            return rangeSumBST(root.left, low, high) + 
                   rangeSumBST(root.right, low, high) + 
                   root.val;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(15);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(7);
        root1.right.right = new TreeNode(18);
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [7,15], Expected: 32, Output: " + 
                          rangeSumBST(root1, 7, 15));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: null tree, Expected: 0, Output: " + 
                          rangeSumBST(null, 1, 10));
        
        // Test Case 3: Boundary cases
        TreeNode singleNode = new TreeNode(5);
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: Single node [5,5], Expected: 5, Output: " + 
                          rangeSumBST(singleNode, 5, 5));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: No nodes in range [100,200], Expected: 0, Output: " + 
                          rangeSumBST(root1, 100, 200));
    }
}
