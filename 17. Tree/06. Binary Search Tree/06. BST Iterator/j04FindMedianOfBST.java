/**
 * LeetCode 530. Find Median of BST
 * 
 * Problem Statement:
 *     Given a Binary Search Tree (BST), find the median value of all nodes in the
 *     tree. If the number of nodes is even, return the average of the two middle
 *     values.
 * 
 * Input:
 *     - TreeNode root: Root of the BST (1 <= Number of nodes <= 10^4)
 *     - -10^5 <= Node.val <= 10^5
 * 
 * Output:
 *     - float: Median value of all nodes in the BST
 *     - For odd number of nodes: middle value
 *     - For even number of nodes: average of two middle values
 * 
 * Example:
 *     Input: root = [5,3,6,2,4,null,7]
 *     Output: 4.0
 * 
 *     Explanation:
 *     The BST contains elements [2,3,4,5,6,7]
 *     Since there are 6 elements, median is average of 3rd and 4th elements
 *     Median = (4 + 5) / 2 = 4.5
 */

import java.util.Stack;

public class j04FindMedianOfBST {
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
            pushAllLeft(root);
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
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * Returns the next node in inorder traversal
         * 
         * Intuition:
         * - Pop the top node from stack (current smallest)
         * - Push all left nodes of right child to maintain order
         * 
         * Time Complexity: O(h) in worst case, O(1) amortized
         * Space Complexity: O(1) as we use existing stack
         * 
         * @return Next node in inorder traversal
         */
        public TreeNode next() {
            TreeNode node = stack.pop();
            pushAllLeft(node.right);
            return node;
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
            return !stack.isEmpty();
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
            pushAllRight(root);
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
                stack.push(node);
                node = node.right;
            }
        }

        /**
         * Returns the previous node in reverse inorder traversal
         * 
         * Intuition:
         * - Pop the top node from stack (current largest)
         * - Push all right nodes of left child to maintain order
         * 
         * Time Complexity: O(h) in worst case, O(1) amortized
         * Space Complexity: O(1) as we use existing stack
         * 
         * @return Previous node in reverse inorder traversal
         */
        public TreeNode prev() {
            TreeNode node = stack.pop();
            pushAllRight(node.left);
            return node;
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
            return !stack.isEmpty();
        }
    }

    /**
     * Approach: Two Pointers with BST Iterators
     * 
     * Intuition:
     * - Use two iterators: one forward (ascending) and one backward (descending)
     * - Move pointers towards center until they meet or cross
     * - Handle both odd and even number of nodes cases
     * 
     * Explanation:
     * - Initialize forward and backward iterators
     * - Get smallest (left) and largest (right) elements
     * - Move pointers towards center until they meet or cross
     * - If pointers meet: return that value (odd number of nodes)
     * - If pointers cross: return average of their values (even number of nodes)
     * 
     * Time Complexity: O(n) where n is number of nodes
     * - Each node is visited at most once by each iterator
     * 
     * Space Complexity: O(h) where h is height of tree
     * - Space used by two iterators
     * 
     * @param root Root of the BST
     * @return Median value of the BST
     */
    public static float findMedian(TreeNode root) {
        if (root == null)
            return 0.0f;

        ForwordIterator forward = new ForwordIterator(root);
        BackwordIterator backward = new BackwordIterator(root);

        TreeNode left = forward.next();
        TreeNode right = backward.prev();
        while (left.val < right.val && left != right) {
            left = forward.next();
            right = backward.prev();
        }
        if (left == right)
            return left.val;
        else {
            return ((float) left.val + (float) right.val) / 2;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic BST with odd number of nodes
        System.out.println("\nBasic Test Case (Odd):");
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        
        System.out.println("Median: " + findMedian(root1));  // Expected: 4.0

        // Test Case 2: Basic BST with even number of nodes
        System.out.println("\nBasic Test Case (Even):");
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(7);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.right.left = new TreeNode(6);
        
        System.out.println("Median: " + findMedian(root2));  // Expected: 4.5

        // Test Case 3: Empty Tree
        System.out.println("\nEmpty Tree Test:");
        System.out.println("Median: " + findMedian(null));  // Expected: 0.0

        // Test Case 4: Single Node
        System.out.println("\nSingle Node Test:");
        TreeNode root4 = new TreeNode(1);
        System.out.println("Median: " + findMedian(root4));  // Expected: 1.0
    }
}
