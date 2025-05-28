/**
 * LeetCode 1008. Construct Binary Search Tree from Preorder Traversal
 * 
 * Problem Statement:
 *     Given an array of integers preorder, which represents the preorder traversal 
 *     of a BST, construct the tree and return its root.
 * 
 * Input:
 *     - preorder: int[] representing preorder traversal (1 <= length <= 100)
 *     - All values are unique
 * 
 * Output:
 *     - Root node of the constructed BST
 * 
 * Example:
 *     Input: preorder = [8,5,1,7,10,12]
 *     Output: Tree Structure:
 *            8
 *           / \
 *          5   10
 *         / \    \
 *        1   7    12
 */
public class j02ConstructBSTFromPreorder {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int item) {
            val = item;
            left = right = null;
        }
    }

    /**
     * Approach 1: Linear Search Based Construction
     * 
     * Intuition:
     * - In preorder traversal, first element is always the root
     * - Elements smaller than root form left subtree
     * - Elements larger than root form right subtree
     * 
     * Time Complexity: O(n^2) - Linear search for each node
     * Space Complexity: O(h) - Recursion stack height
     * 
     * @param preorder Array containing preorder traversal
     * @return Root of constructed BST
     */
    public static TreeNode bstFromPreorder(int[] preorder) {
        return convertPreorderToBST(preorder, 0, preorder.length - 1);
    }

    /**
     * Helper Method: Find First Larger Element
     * 
     * Intuition:
     * - Searches for first element larger than key to separate left and right subtrees
     * - All elements before this index belong to left subtree
     * - All elements after this index belong to right subtree
     * 
     * Time Complexity: O(n) - Linear search through array
     * Space Complexity: O(1) - No extra space used
     * 
     * @param preorder Array to search in
     * @param s Start index
     * @param e End index
     * @param key Value to compare against
     * @return Index of first larger element or -1 if not found
     */
    public static int findBiggerValueIndex(int[] preorder, int s, int e, int key) {
        for (int i = s; i <= e; i++) {
            if (preorder[i] > key)
                return i;
        }
        return -1;
    }

    /**
     * Helper Method: Convert Preorder Array to BST
     * 
     * Intuition:
     * - First element becomes root of current subtree
     * - Find first larger element to divide array into left and right parts
     * - Recursively build left and right subtrees
     * 
     * Explanation:
     * 1. If start > end, return null (base case)
     * 2. Create root node from first element
     * 3. Find index of first element larger than root
     * 4. If no larger element found, all remaining elements go to left subtree
     * 5. Otherwise, split elements between left and right subtrees
     * 
     * Time Complexity: O(n^2) - Due to findBiggerValueIndex linear search
     * Space Complexity: O(h) - Recursion stack
     * 
     * @param preorder Preorder array
     * @param s Start index of current subarray
     * @param e End index of current subarray
     * @return Root of constructed subtree
     */
    public static TreeNode convertPreorderToBST(int[] preorder, int s, int e) {
        if (s > e)
            return null;
        // Create root from first element
        TreeNode root = new TreeNode(preorder[s]);
        // Find first larger element
        int idx = findBiggerValueIndex(preorder, s, e, preorder[s]);
        if (idx == -1) {
            // No larger element found, all elements go to left subtree
            root.left = convertPreorderToBST(preorder, s + 1, e);
        } else {
            // Divide elements into left and right subtrees
            root.left = convertPreorderToBST(preorder, s + 1, idx - 1);
            root.right = convertPreorderToBST(preorder, idx, e);
        }
        return root;
    }

    /**
     * Approach 2: Efficient Bound-Based Construction
     * 
     * Intuition:
     * - Use upper bounds to determine valid node values
     * - Maintain single array index for traversal
     * - Each node value must be less than its upper bound
     * 
     * Time Complexity: O(n) - Single pass through array
     * Space Complexity: O(h) - Recursion stack
     * 
     * @param preorder Array containing preorder traversal
     * @return Root of constructed BST
     */
    public static TreeNode bstFromPreorderEfficient(int[] preorder) {
        return constructBSTFromPreorder(preorder, Integer.MAX_VALUE, new int[] { 0 });
    }

    /**
     * Helper Method: Construct BST using Upper Bounds
     * 
     * Intuition:
     * - Each node has an upper bound constraint
     * - Left child must be less than current node's value
     * - Right child must be less than parent's upper bound
     * 
     * Explanation:
     * 1. If current value exceeds bound or array end reached, return null
     * 2. Create node from current value and increment index
     * 3. Left subtree bound is current node's value
     * 4. Right subtree bound remains same as current bound
     * 
     * Time Complexity: O(n) - Single traversal
     * Space Complexity: O(h) - Recursion stack
     * 
     * @param preorder Array containing preorder traversal
     * @param bound Upper bound for current node value
     * @param index Current position in array (passed as array for reference)
     * @return Root of constructed subtree
     */
    public static TreeNode constructBSTFromPreorder(int[] preorder, int bound, int[] index) {
        // Return null if end of array or value exceeds bound
        if (index[0] >= preorder.length || preorder[index[0]] > bound)
            return null;
        // Create current node and increment index
        TreeNode root = new TreeNode(preorder[index[0]++]);
        // Construct left subtree with current value as bound
        root.left = constructBSTFromPreorder(preorder, root.val, index);
        // Construct right subtree with parent's bound
        root.right = constructBSTFromPreorder(preorder, bound, index);
        return root;
    }
}
