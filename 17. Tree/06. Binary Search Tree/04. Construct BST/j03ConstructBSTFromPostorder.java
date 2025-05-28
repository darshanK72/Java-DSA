/**
 * GeeksForGeeks - Construct BST from Postorder
 * 
 * Problem Statement:
 *     Given postorder traversal of a Binary Search Tree, construct the BST.
 *     All nodes in BST have unique values.
 * 
 * Input:
 *     - post[]: Array containing postorder traversal (1 <= length <= 100)
 *     - n: Length of the array
 *     - All values are unique and in range [-10^8, 10^8]
 * 
 * Output:
 *     - Root node of the constructed BST
 * 
 * Example:
 *     Input: post[] = {1,7,5,50,40,10}
 *     Output: Tree Structure:
 *            10
 *           /  \
 *          5    40
 *         / \    \
 *        1   7    50
 */
public class j03ConstructBSTFromPostorder {

    /**
     * Node class for Binary Search Tree
     */
    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /**
     * Approach: Linear Search Based Construction
     * 
     * Intuition:
     * - In postorder traversal, last element is always the root
     * - Elements smaller than root form left subtree
     * - Elements larger than root form right subtree
     * 
     * Time Complexity: O(n^2) - Linear search for each node
     * Space Complexity: O(h) - Recursion stack height
     * 
     * @param post Array containing postorder traversal
     * @param n Length of the array
     * @return Root of constructed BST
     */
    public static Node constructTree(int post[], int n) {
        return convertPostorderToBST(post, n - 1, 0);
    }

    /**
     * Helper Method: Find Last Smaller Element
     * 
     * Intuition:
     * - Searches for last element smaller than key to separate left and right subtrees
     * - All elements before this index belong to left subtree
     * - All elements after this index belong to right subtree
     * 
     * Time Complexity: O(n) - Linear search through array
     * Space Complexity: O(1) - No extra space used
     * 
     * @param postorder Array to search in
     * @param e End index
     * @param s Start index
     * @param key Value to compare against
     * @return Index of last smaller element or -1 if not found
     */
    public static int findSmallerValueIndex(int[] postorder, int e, int s, int key) {
        // Search from end to start for first smaller element
        for (int i = e; i >= s; i--) {
            if (postorder[i] < key)
                return i;
        }
        return -1;
    }

    /**
     * Helper Method: Convert Postorder Array to BST
     * 
     * Intuition:
     * - Last element becomes root of current subtree
     * - Find last smaller element to divide array into left and right parts
     * - Recursively build left and right subtrees
     * 
     * Explanation:
     * 1. If end < start, return null (base case)
     * 2. Create root node from last element
     * 3. Find index of last element smaller than root
     * 4. If no smaller element found, all remaining elements go to right subtree
     * 5. Otherwise, split elements between left and right subtrees
     * 
     * Time Complexity: O(n^2) - Due to findSmallerValueIndex linear search
     * Space Complexity: O(h) - Recursion stack
     * 
     * @param postorder Postorder array
     * @param e End index of current subarray
     * @param s Start index of current subarray
     * @return Root of constructed subtree
     */
    public static Node convertPostorderToBST(int[] postorder, int e, int s) {
        // Base case: invalid range
        if (e < s)
            return null;
            
        // Create root from last element in postorder
        Node root = new Node(postorder[e]);
        
        // Find last smaller element to divide into subtrees
        int idx = findSmallerValueIndex(postorder, e, s, postorder[e]);
        
        if (idx == -1) {
            // No smaller element found, all elements go to right subtree
            root.right = convertPostorderToBST(postorder, e - 1, s);
        } else {
            // Divide elements into left and right subtrees
            // Elements after idx go to right subtree
            root.right = convertPostorderToBST(postorder, e - 1, idx + 1);
            // Elements before idx go to left subtree
            root.left = convertPostorderToBST(postorder, idx, s);
        }
        return root;
    }
}
