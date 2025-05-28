/**
 * LeetCode 669. Trim a Binary Search Tree
 * 
 * Problem Statement:
 *     Given the root of a binary search tree and the lowest and highest boundaries
 *     as low and high, trim the tree so that all its elements lies in [low, high].
 *     Trimming the tree should not change the relative structure of the elements
 *     that will remain in the tree (i.e., any node's descendant should remain a
 *     descendant).
 * 
 * Input:
 *     - root: Root node of BST
 *     - low: Lower bound of range (inclusive)
 *     - high: Upper bound of range (inclusive)
 * 
 * Output:
 *     - Root node of trimmed BST
 * 
 * Example:
 *     Input: 
 *              3
 *            /   \
 *           0     4
 *            \
 *             2
 *            /
 *           1
 *         low = 1, high = 3
 *     
 *     Output:
 *              3
 *            /
 *           2
 *          /
 *         1
 *     
 *     Explanation:
 *     - Nodes 0 and 4 are outside range [1,3]
 *     - After trimming, the tree maintains BST properties with nodes in range
 */

public class j04TrimNodesOutOfRange {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int item) {
            val = item;
            left = right = null;
        }
    }

    /**
     * Approach: Recursive BST Trimming
     * 
     * Intuition:
     * - If node value < low, entire left subtree is out of range
     * - If node value > high, entire right subtree is out of range
     * - If node value is in range, recursively trim both subtrees
     * 
     * Explanation:
     * - Step 1: If current node is null, return null
     * - Step 2: If node value < low, trim right subtree only
     * - Step 3: If node value > high, trim left subtree only
     * - Step 4: If node value in range, recursively trim both subtrees
     * 
     * Time Complexity: O(N) where N is number of nodes
     * Space Complexity: O(H) for recursion stack where H is height
     * 
     * @param root    Root node of BST
     * @param low     Lower bound of range (inclusive)
     * @param high    Upper bound of range (inclusive)
     * @return        Root node of trimmed BST
     */
    public static TreeNode trimBST(TreeNode root, int low, int high) {
        // Base case: empty tree
        if (root == null)
            return null;
            
        // If current value < low, entire left subtree is out of range
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } 
        // If current value > high, entire right subtree is out of range
        else if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        
        // Current node is in range, recursively trim both subtrees
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(0);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);
        root1.left.right.left = new TreeNode(1);
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [1,3] on tree with nodes 0,1,2,3,4");
        TreeNode result1 = trimBST(root1, 1, 3);
        printTree(result1);

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: null tree, Expected: null");
        System.out.println("Output: " + trimBST(null, 1, 2));

        // Test Case 3: Boundary cases
        TreeNode singleNode = new TreeNode(5);
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: Single node [4,6]");
        TreeNode result3 = trimBST(singleNode, 4, 6);
        printTree(result3);

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: Range excluding all nodes [100,200]");
        TreeNode result4 = trimBST(root1, 100, 200);
        printTree(result4);
    }

    /**
     * Helper method to print tree in a visual format
     * 
     * @param root    Root node of the tree to print
     */
    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        // Simple inorder traversal to show tree structure
        System.out.print("Tree nodes in order: ");
        printInorder(root);
        System.out.println();
    }

    private static void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
}
