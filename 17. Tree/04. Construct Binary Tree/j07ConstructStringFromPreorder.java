/**
 * LeetCode 606. Construct String from Binary Tree
 * 
 * Problem Statement:
 *     Given the root of a binary tree, construct a string consisting of parenthesis
 *     and integers from a binary tree with the preorder traversal way, and return it.
 *     Omit all the empty parenthesis pairs that do not affect the one-to-one mapping
 *     relationship between the string and the original binary tree.
 * 
 * Input:
 *     - TreeNode root (binary tree root node)
 *     - Node values are in range [-1000, 1000]
 * 
 * Output:
 *     - String (preorder representation with parentheses)
 * 
 * Example:
 *     Input: root = [1,2,3,4]
 *     Output: "1(2(4))(3)"
 * 
 *     Explanation:
 *          1
 *         / \
 *        2   3
 *       /     
 *      4      
 *     
 *     Originally, it needs to be "1(2(4)())(3()())", but you need to omit all the
 *     unnecessary empty parenthesis pairs.
 */

public class j07ConstructStringFromPreorder {

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
     * Approach 1: Using StringBuilder
     * 
     * Intuition:
     * - Use StringBuilder for efficient string concatenation
     * - Follow preorder traversal pattern (root-left-right)
     * - Skip empty parentheses for null right child when left is also null
     * 
     * Explanation:
     * - Base case: null tree returns null
     * - Call helper method for actual conversion
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree (recursion stack)
     * 
     * @param root    Root node of binary tree
     * @return       String representation of tree
     */
    public static String tree2strUsingStringBuilder(TreeNode root) {
        // Handle null tree case
        if (root == null)
            return null;
        return convertsingStringBuilder(root);
    }

    /**
     * Helper method for StringBuilder approach
     * 
     * Intuition:
     * - Append current node value first (preorder)
     * - Add parentheses around left subtree (always needed if children exist)
     * - Add parentheses around right subtree (only if right child exists)
     * 
     * Explanation:
     * - Handle leaf nodes separately (no parentheses needed)
     * - Use StringBuilder for efficient string building
     * - Recursively process left and right subtrees
     * 
     * Time Complexity: O(n) for visiting each node once
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root    Current node being processed
     * @return       String representation of current subtree
     */
    public static String convertsingStringBuilder(TreeNode root) {
        // Base case: null node
        if (root == null)
            return "";
            
        // Leaf node case
        if (root.left == null && root.right == null) {
            return root.val + "";
        }

        // Build string using StringBuilder
        StringBuilder ans = new StringBuilder();
        ans.append(root.val);
        
        // Always add left child parentheses
        ans.append("(").append(convertsingStringBuilder(root.left)).append(")");
        
        // Add right child parentheses only if right child exists
        if (root.right != null)
            ans.append("(").append(convertsingStringBuilder(root.right)).append(")");

        return ans.toString();
    }

    /**
     * Approach 2: Using String Concatenation
     * 
     * Intuition:
     * - Direct string concatenation (less efficient but simpler)
     * - Same preorder traversal logic as StringBuilder approach
     * 
     * Explanation:
     * - Base case: null tree returns null
     * - Call helper method for actual conversion
     * 
     * Time Complexity: O(n²) due to string concatenation
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root    Root node of binary tree
     * @return       String representation of tree
     */
    public static String tree2strUsingStr(TreeNode root) {
        if (root == null)
            return null;
        return convertUsingStr(root);
    }

    /**
     * Helper method for String concatenation approach
     * 
     * Intuition:
     * - Build string directly using + operator
     * - Add parentheses according to same rules as StringBuilder
     * 
     * Explanation:
     * - Start with root value
     * - Add left subtree with parentheses if any child exists
     * - Add right subtree with parentheses only if it exists
     * 
     * Time Complexity: O(n²) due to string concatenation
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root    Current node being processed
     * @return       String representation of current subtree
     */
    public static String convertUsingStr(TreeNode root) {
        if (root == null)
            return "";
            
        String ans = root.val + "";
        
        // Add left subtree if any child exists
        if (root.left != null || root.right != null) {
            ans += "(";
            ans += convertUsingStr(root.left);
            ans += ")";
        }
        
        // Add right subtree only if it exists
        if (root.right != null) {
            ans += "(";
            ans += convertUsingStr(root.right);
            ans += ")";
        }
        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree
        System.out.println("\nBasic Tree Test:");
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        System.out.println("StringBuilder: " + tree2strUsingStringBuilder(root1));
        System.out.println("String: " + tree2strUsingStr(root1));

        // Test Case 2: Tree with missing left child
        System.out.println("\nMissing Left Child Test:");
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        System.out.println("StringBuilder: " + tree2strUsingStringBuilder(root2));
        System.out.println("String: " + tree2strUsingStr(root2));

        // Test Case 3: Single node
        System.out.println("\nSingle Node Test:");
        TreeNode root3 = new TreeNode(1);
        System.out.println("StringBuilder: " + tree2strUsingStringBuilder(root3));
        System.out.println("String: " + tree2strUsingStr(root3));
    }
}
