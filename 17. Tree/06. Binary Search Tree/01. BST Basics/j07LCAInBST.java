/**
 * LeetCode 235. Lowest Common Ancestor of a Binary Search Tree
 * 
 * Problem Statement:
 *     Given a binary search tree (BST), find the lowest common ancestor (LCA) node
 *     of two given nodes in the BST. The lowest common ancestor is defined between
 *     two nodes p and q as the lowest node in T that has both p and q as descendants.
 * 
 * Input:
 *     - TreeNode root (root node of BST)
 *     - TreeNode p (first node)
 *     - TreeNode q (second node)
 *     - Node values are unique
 * 
 * Output:
 *     - TreeNode (LCA node of p and q)
 * 
 * Example:
 *     Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 *     Output: 6
 * 
 *     Explanation:
 *          6
 *         / \
 *        2   8
 *       / \ / \
 *      0  4 7  9
 *        / \
 *       3   5
 */

public class j07LCAInBST {

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
     * Finds Lowest Common Ancestor in BST
     * 
     * Intuition:
     * - Use BST property to navigate towards LCA
     * - If both nodes are smaller than current, go left
     * - If both nodes are larger than current, go right
     * - If nodes are on different sides, current is LCA
     * 
     * Explanation:
     * - Base case: null tree returns null
     * - If both values < root, search left subtree
     * - If both values > root, search right subtree
     * - Otherwise, current node is LCA
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root    Root node of BST
     * @param p       First node to find LCA
     * @param q       Second node to find LCA
     * @return       Lowest Common Ancestor node
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: empty tree
        if(root == null) 
            return null;
            
        // If both nodes are in left subtree
        if(root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } 
        // If both nodes are in right subtree
        else if(root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // Nodes are on different sides or one is current node
        return root;
    }

    /**
     * Helper method to print BST structure
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
        // Test Case 1: Basic LCA test
        System.out.println("\nBasic LCA Test:");
        TreeNode root1 = new TreeNode(6);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(7);
        root1.right.right = new TreeNode(9);
        root1.left.right.left = new TreeNode(3);
        root1.left.right.right = new TreeNode(5);
        
        System.out.println("Tree structure:");
        printTree(root1, "", "Root: ");
        
        TreeNode result1 = lowestCommonAncestor(root1, root1.left, root1.right);
        System.out.println("LCA of 2 and 8: " + result1.val);  // Expected: 6

        // Test Case 2: LCA is one of the nodes
        System.out.println("\nLCA is Node Test:");
        TreeNode result2 = lowestCommonAncestor(root1, root1.left, root1.left.right);
        System.out.println("LCA of 2 and 4: " + result2.val);  // Expected: 2

        // Test Case 3: Nodes in same subtree
        System.out.println("\nSame Subtree Test:");
        TreeNode result3 = lowestCommonAncestor(root1, root1.left.right.left, root1.left.right.right);
        System.out.println("LCA of 3 and 5: " + result3.val);  // Expected: 4
    }
}
