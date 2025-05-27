/**
 * LeetCode 450. Delete Node in a BST
 * 
 * Problem Statement:
 *     Given a root node reference of a BST and a key, delete the node with the 
 *     given key in the BST. Return the root node reference (possibly updated) of
 *     the BST.
 * 
 * Input:
 *     - TreeNode root (root node of BST)
 *     - int key (value to delete)
 *     - Node values are unique
 * 
 * Output:
 *     - TreeNode (root of BST after deletion)
 * 
 * Example:
 *     Input: root = [5,3,6,2,4,null,7], key = 3
 *     Output: [5,4,6,2,null,null,7]
 * 
 *     Explanation:
 *     Before:         After:
 *          5             5
 *         / \           / \
 *        3   6         4   6
 *       / \   \       /     \
 *      2   4   7     2       7
 */

public class j05DeleteNodeInBST {

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
     * Deletes a node with given key from BST
     * 
     * Intuition:
     * - First locate the node to delete using BST property
     * - Handle three cases:
     *   1. Node has no children (leaf)
     *   2. Node has one child
     *   3. Node has two children
     * 
     * Explanation:
     * - Use recursive approach to find node
     * - For leaf node: simply remove it
     * - For one child: replace with child
     * - For two children: replace with inorder predecessor
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root    Root node of BST
     * @param key     Value to delete
     * @return       Root of modified BST
     */
    public static TreeNode deleteNode(TreeNode root, int key) {
        // Base case: empty tree
        if (root == null)
            return null;

        // Search for node to delete
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else { // Found node to delete
            // Case 1 & 2: No left child or leaf
            if (root.left == null)
                return root.right;
            // Case 2: No right child
            if (root.right == null)
                return root.left;
            // Case 3: Two children
            else {
                // Find inorder predecessor
                int replace = findInorderPredecessor(root);
                root.val = replace;
                // Delete predecessor
                root.left = deleteNode(root.left, replace);
            }
        }
        return root;
    }

    /**
     * Finds inorder predecessor (largest value in left subtree)
     * 
     * Intuition:
     * - Go left once, then right until null
     * - Rightmost node in left subtree is predecessor
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(1)
     * 
     * @param node    Current node
     * @return       Value of inorder predecessor
     */
    private static int findInorderPredecessor(TreeNode node) {
        node = node.left;
        while (node.right != null)
            node = node.right;
        return node.val;
    }

    /**
    * Iterative approach to delete node from BST
    * 
    * Intuition:
    * - Locate node to delete using iterative traversal
    * - Use separate method to handle node reconnection
    * - Maintain parent-child relationship while searching
    * 
    * Explanation:
    * - Special case: if root is target node
    * - Otherwise, traverse to find parent of target node
    * - Update appropriate child pointer after deletion
    * - Use connectNode helper for actual deletion logic
    * 
    * Time Complexity: O(h) where h is height of tree
    * Space Complexity: O(1) as no recursion or extra space used
    * 
    * @param root    Root node of BST
    * @param key     Value to delete
    * @return       Root of modified BST
    */
    public static TreeNode deleteNodeII(TreeNode root, int key) {
        // Handle empty tree case
        if (root == null)
            return root;

        // If root is target node
        if (root.val == key) {
            return connectNode(root);
        }

        // Search for parent of target node
        TreeNode curr = root;
        while (curr != null) {
            // Check left subtree
            if (curr.val > key) {
                if (curr.left != null && curr.left.val == key) {
                    curr.left = connectNode(curr.left);
                    break;
                } else {
                    curr = curr.left;
                }
            }
            // Check right subtree
            else {
                if (curr.right != null && curr.right.val == key) {
                    curr.right = connectNode(curr.right);
                    break;
                } else {
                    curr = curr.right;
                }
            }
        }
        return root;
    }

    /**
     * Helper method to handle node deletion and reconnection
     * 
     * Intuition:
     * - Handle three cases: no child, one child, two children
     * - For two children case, connect right subtree to rightmost node of left subtree
     * 
     * Explanation:
     * - If no left child: return right subtree
     * - If no right child: return left subtree
     * - If both children:
     *   1. Save right subtree
     *   2. Find rightmost node in left subtree
     *   3. Connect right subtree to rightmost node
     *   4. Return left subtree as new root
     * 
     * Time Complexity: O(h) for finding rightmost node
     * Space Complexity: O(1) constant space
     * 
     * @param curr    Node to be deleted
     * @return       New root of reconnected subtree
     */
    public static TreeNode connectNode(TreeNode curr) {
        // Case 1: No left child
        if (curr.left == null) {
            return curr.right;
        }
        // Case 2: No right child
        else if (curr.right == null) {
            return curr.left;
        }
        // Case 3: Both children exist
        else {
            TreeNode rightTree = curr.right;
            TreeNode lastRight = curr.left;
            // Find rightmost node in left subtree
            while (lastRight.right != null) {
                lastRight = lastRight.right;
            }
            // Connect right subtree
            lastRight.right = rightTree;
            return curr.left;
        }
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
        // Test Case 1: Delete leaf node
        System.out.println("\nDelete Leaf Node Test:");
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(7);
        System.out.println("Before deletion:");
        printTree(root1, "", "Root: ");
        TreeNode result1 = deleteNode(root1, 2);
        System.out.println("After deletion of 2:");
        printTree(result1, "", "Root: ");

        // Test Case 2: Delete node with one child
        System.out.println("\nDelete One Child Node Test:");
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.right.right = new TreeNode(7);
        System.out.println("Before deletion:");
        printTree(root2, "", "Root: ");
        TreeNode result2 = deleteNode(root2, 6);
        System.out.println("After deletion of 6:");
        printTree(result2, "", "Root: ");

        // Test Case 3: Delete node with two children
        System.out.println("\nDelete Two Children Node Test:");
        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(3);
        root3.right = new TreeNode(6);
        root3.left.left = new TreeNode(2);
        root3.left.right = new TreeNode(4);
        System.out.println("Before deletion:");
        printTree(root3, "", "Root: ");
        TreeNode result3 = deleteNode(root3, 3);
        System.out.println("After deletion of 3:");
        printTree(result3, "", "Root: ");
    }
}