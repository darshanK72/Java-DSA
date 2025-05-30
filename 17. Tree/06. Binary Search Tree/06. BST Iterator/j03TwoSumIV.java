/**
 * LeetCode 653. Two Sum IV - Input is a BST
 * 
 * Problem Statement:
 *     Given the root of a Binary Search Tree and a target number k, return true if
 *     there exist two elements in the BST such that their sum is equal to the given
 *     target.
 * 
 * Input:
 *     - TreeNode root: Root of the BST (1 <= Number of nodes <= 10^4)
 *     - int k: Target sum (-10^5 <= k <= 10^5)
 *     - -10^4 <= Node.val <= 10^4
 * 
 * Output:
 *     - boolean: True if there exist two elements in the BST such that their sum
 *       equals k, false otherwise
 * 
 * Example:
 *     Input: root = [5,3,6,2,4,null,7], k = 9
 *     Output: true
 * 
 *     Explanation:
 *     The BST contains elements [2,3,4,5,6,7]
 *     We can find 2 + 7 = 9, so return true
 */

import java.util.Stack;

public class j03TwoSumIV {

    /**
     * TreeNode class represents a node in the Binary Search Tree
     * Each node contains:
     * - val: The value stored in the node
     * - left: Reference to the left child
     * - right: Reference to the right child
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Forward Iterator for BST
     * 
     * Intuition:
     * - Implements inorder traversal (left-root-right)
     * - Returns elements in ascending order
     * - Uses stack to maintain traversal state
     * 
     * Time Complexity: 
     * - Constructor: O(h) where h is height of tree
     * - next(): O(h) in worst case, O(1) amortized
     * - hasNext(): O(1)
     * 
     * Space Complexity: O(h) where h is height of tree
     */
    static class ForwordIterator {
        private Stack<TreeNode> stack;

        public ForwordIterator(TreeNode root) {
            stack = new Stack<>();
            pushAllLeft(root);  // Initialize stack with leftmost path
        }

        /**
         * Helper method to push all left nodes to stack
         * 
         * Intuition:
         * - In inorder traversal, we need to process left subtree first
         * - Push all left nodes to maintain the correct order
         * 
         * Time Complexity: O(h) where h is height of tree
         * Space Complexity: O(1) as we use existing stack
         * 
         * @param node Current node to process
         */
        private void pushAllLeft(TreeNode node) {
            while (node != null) {
                stack.push(node);  // Push current node to stack
                node = node.left;  // Move to left child
            }
        }

        /**
         * Returns the next value in inorder traversal
         * 
         * Intuition:
         * - Pop the top node from stack (current smallest)
         * - Push all left nodes of right child to maintain order
         * 
         * Time Complexity: O(h) in worst case, O(1) amortized
         * Space Complexity: O(1) as we use existing stack
         * 
         * @return Next value in inorder traversal
         */
        public int next() {
            TreeNode node = stack.pop();  // Get the next node to process
            pushAllLeft(node.right);      // Push left path of right subtree
            return node.val;              // Return the value
        }

        /**
         * Checks if there are more nodes to process
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return True if stack is not empty, false otherwise
         */
        public boolean hasNext() {
            return !stack.isEmpty();  // Check if stack has more nodes
        }
    }

    /**
     * Backward Iterator for BST
     * 
     * Intuition:
     * - Implements reverse inorder traversal (right-root-left)
     * - Returns elements in descending order
     * - Uses stack to maintain traversal state
     * 
     * Time Complexity: 
     * - Constructor: O(h) where h is height of tree
     * - prev(): O(h) in worst case, O(1) amortized
     * - hasPrev(): O(1)
     * 
     * Space Complexity: O(h) where h is height of tree
     */
    static class BackwordIterator {
        private Stack<TreeNode> stack;

        public BackwordIterator(TreeNode root) {
            stack = new Stack<>();
            pushAllRight(root);  // Initialize stack with rightmost path
        }

        /**
         * Helper method to push all right nodes to stack
         * 
         * Intuition:
         * - In reverse inorder traversal, we need to process right subtree first
         * - Push all right nodes to maintain the correct order
         * 
         * Time Complexity: O(h) where h is height of tree
         * Space Complexity: O(1) as we use existing stack
         * 
         * @param node Current node to process
         */
        private void pushAllRight(TreeNode node) {
            while (node != null) {
                stack.push(node);  // Push current node to stack
                node = node.right; // Move to right child
            }
        }

        /**
         * Returns the previous value in reverse inorder traversal
         * 
         * Intuition:
         * - Pop the top node from stack (current largest)
         * - Push all right nodes of left child to maintain order
         * 
         * Time Complexity: O(h) in worst case, O(1) amortized
         * Space Complexity: O(1) as we use existing stack
         * 
         * @return Previous value in reverse inorder traversal
         */
        public int prev() {
            TreeNode node = stack.pop();  // Get the next node to process
            pushAllRight(node.left);      // Push right path of left subtree
            return node.val;              // Return the value
        }

        /**
         * Checks if there are more nodes to process
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return True if stack is not empty, false otherwise
         */
        public boolean hasPrev() {
            return !stack.isEmpty();  // Check if stack has more nodes
        }
    }

    /**
     * Approach: Two Pointers with BST Iterators
     * 
     * Intuition:
     * - Use two iterators: one forward (ascending) and one backward (descending)
     * - Similar to two-pointer technique in sorted arrays
     * - Leverage BST property for efficient traversal
     * 
     * Explanation:
     * - Initialize forward and backward iterators
     * - Get smallest (left) and largest (right) elements
     * - If sum < target: move left pointer forward
     * - If sum > target: move right pointer backward
     * - If sum == target: return true
     * - Continue until pointers meet or no more elements
     * 
     * Time Complexity: O(n) where n is number of nodes
     * - Each node is visited at most once by each iterator
     * 
     * Space Complexity: O(h) where h is height of tree
     * - Space used by two iterators
     * 
     * @param root Root of the BST
     * @param k    Target sum to find
     * @return     True if two elements sum to k, false otherwise
     */
    public static boolean findTarget(TreeNode root, int k) {
        if (root == null)  // Handle empty tree case
            return false;

        // Initialize iterators
        ForwordIterator forward = new ForwordIterator(root);
        BackwordIterator backward = new BackwordIterator(root);

        // Get initial values
        int left = forward.next();   // Smallest element
        int right = backward.prev(); // Largest element

        // Two pointer approach
        while (left < right) {
            int sum = left + right;
            if (sum == k)  // Found target sum
                return true;
            if (sum > k) {  // Sum too large, move right pointer
                if (backward.hasPrev()) {
                    right = backward.prev();
                } else {
                    break;  // No more elements to check
                }
            } else {  // Sum too small, move left pointer
                if (forward.hasNext()) {
                    left = forward.next();
                } else {
                    break;  // No more elements to check
                }
            }
        }
        return false;  // No pair found
    }

    public static void main(String[] args) {
        // Test Case 1: Basic BST with target sum
        System.out.println("\nBasic Test Case:");
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(7);
        
        System.out.println("Target = 9: " + findTarget(root1, 9));   // Expected: true
        System.out.println("Target = 28: " + findTarget(root1, 28)); // Expected: false

        // Test Case 2: Empty Tree
        System.out.println("\nEmpty Tree Test:");
        System.out.println("Target = 0: " + findTarget(null, 0));  // Expected: false

        // Test Case 3: Single Node
        System.out.println("\nSingle Node Test:");
        TreeNode root3 = new TreeNode(1);
        System.out.println("Target = 2: " + findTarget(root3, 2));  // Expected: false
    }
}
