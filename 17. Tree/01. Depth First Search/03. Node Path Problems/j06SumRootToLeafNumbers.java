/**
 * Problem Statement:
 *     LeetCode 129. Sum Root to Leaf Numbers
 * 
 *     Given a binary tree containing digits from 0-9 only, each root-to-leaf path 
 *     could represent a number. Find the total sum of all root-to-leaf numbers.
 *     A leaf is a node with no children.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Each node contains a single digit (0-9)
 *     - Tree can have any number of nodes
 * 
 * Output:
 *     - Sum of all numbers formed by root-to-leaf paths
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *     Output: 25
 *     Explanation: 
 *     - Path 1->2 forms number 12
 *     - Path 1->3 forms number 13
 *     - Total sum = 12 + 13 = 25
 */

public class j06SumRootToLeafNumbers {

    /**
     * TreeNode class represents a node in binary tree
     * Contains value and references to left and right children
     */
    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree from example
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println("Test Case 1: " + sumNumbers(root1));  // Expected: 25

        // Test Case 2: Single path
        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(9);
        root2.left.right = new TreeNode(5);
        System.out.println("Test Case 2: " + sumNumbers(root2));  // Expected: 495

        // Test Case 3: Single node
        TreeNode root3 = new TreeNode(5);
        System.out.println("Test Case 3: " + sumNumbers(root3));  // Expected: 5

        // Test Case 4: Empty tree
        System.out.println("Test Case 4: " + sumNumbers(null));   // Expected: 0
    }

    /**
     * Approach: DFS with Path Number Building
     * 
     * Intuition:
     * - Use DFS to traverse from root to leaf
     * - At each node, current number = previous_number * 10 + current_digit
     * - Add complete number to sum when leaf is reached
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - Number operations at each node are O(1)
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * - Using array to store sum to avoid reference issues
     */
    public static int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;                                // Handle empty tree
        int[] sum = new int[1];                     // Store total sum
        sumTreeNumbers(root, 0, sum);               // Calculate sum
        return sum[0];
    }

    /**
     * Helper method to calculate sum using DFS
     * curr: current number formed along the path
     * ans: array to store final sum
     */
    private static void sumTreeNumbers(TreeNode root, int curr, int[] ans) {
        if (root == null)
            return;                                 // Base case
            
        if (root.left == null && root.right == null) {
            ans[0] += (curr * 10 + root.val);      // Add number at leaf
            return;
        }

        curr = curr * 10 + root.val;               // Build current number
        sumTreeNumbers(root.left, curr, ans);      // Process left subtree
        sumTreeNumbers(root.right, curr, ans);     // Process right subtree
    }
}
