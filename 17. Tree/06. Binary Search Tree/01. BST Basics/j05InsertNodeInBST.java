/**
 * LeetCode 701. Insert into a Binary Search Tree
 * 
 * Problem Statement:
 *     Given the root node of a binary search tree (BST) and a value to be inserted
 *     into the tree, insert the value into the BST. Return the root node of the BST
 *     after the insertion. The input is guaranteed to be unique.
 * 
 * Input:
 *     - TreeNode root (root node of BST)
 *     - int val (value to insert)
 *     - Node values are unique
 *     - val is guaranteed to be different from values in BST
 * 
 * Output:
 *     - TreeNode (root of BST after insertion)
 * 
 * Example:
 *     Input: root = [4,2,7,1,3], val = 5
 *     Output: [4,2,7,1,3,5]
 * 
 *     Explanation:
 *        Before:     After:
 *          4           4
 *         / \         / \
 *        2   7       2   7
 *       / \         / \  /
 *      1   3       1  3 5
 */

public class j05InsertNodeInBST {

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
     * Inserts a new value into BST
     * 
     * Intuition:
     * - Compare value with current node
     * - If smaller, go left; if larger, go right
     * - Insert at null position when found
     * 
     * Explanation:
     * - Base case: empty tree, create new node
     * - If value < root, insert in left subtree
     * - If value > root, insert in right subtree
     * - Return modified root
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root    Root node of BST
     * @param val     Value to insert
     * @return       Root of modified BST
     */
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        // Base case: empty tree or reached insertion point
        if (root == null)
            return new TreeNode(val);
            
        // If value is less than current node, go left
        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        // If value is greater than current node, go right
        else
            root.right = insertIntoBST(root.right, val);
            
        return root;
    }


    /**
     * Iterative version of insertIntoBST
     * Intuition:
     * - Use a loop to find the correct position
     * - Compare value with current node
     * - If smaller, go left; if larger, go right
     * - Insert at null position when found
     * 
     * Explanation:
     * - Create new node with value
     *   - If tree is empty, return new node
     * - Use a while loop to traverse the tree
     *   - If value < current node, go left
     *   - If value > current node, go right
     *   - Insert new node at the first null position found
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(1) constant space
     * 
     * @param root    Root node of BST
     * @param val     Value to insert
     * @return       Root of modified BST
     */
    public static TreeNode insertIntoBSTIterative(TreeNode root, int val) {
        TreeNode node = new TreeNode(val); // Create new node to insert
         // If tree is empty, return new node
        if(root == null) return node;
        TreeNode curr = root;
        while(true){
            if(val < curr.val){ // If value is less, go left
                // If left child is null, insert here
                if(curr.left != null) curr = curr.left;
                else{
                    // Insert new node as left child
                    curr.left = node;
                    break;
                }
            }else{
                // If value is greater, go right

                if(curr.right != null) curr = curr.right;
                else{
                    // Insert new node as right child
                    curr.right = node;
                    break;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        // Test Case 1: Insert into empty tree
        System.out.println("\nEmpty Tree Test:");
        TreeNode result1 = insertIntoBST(null, 5);
        printTree(result1);
        // Output should be: 5 null null
        
        // Test Case 2: Basic BST insertion
        System.out.println("\nBasic BST Test:");
        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(7);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);
        TreeNode result2 = insertIntoBST(root2, 5);
        printTree(result2);
        // Output should be: 4 2 1 null null 3 null null 7 5 null null
        
        // Test Case 3: Insert smaller value
        System.out.println("\nSmaller Value Test:");
        TreeNode root3 = new TreeNode(5);
        root3.right = new TreeNode(7);
        TreeNode result3 = insertIntoBST(root3, 3);
        printTree(result3);
        // Output should be: 5 null 3 null null 7 null null

    }

    // Helper method to print the tree in pre-order
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }
}