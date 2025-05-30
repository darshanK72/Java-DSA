/**
 * LeetCode 1586. Binary Search Tree Iterator II
 * 
 * Problem Statement:
 *     Implement the BSTIterator class that represents an iterator over the in-order
 *     traversal of a binary search tree (BST) in reverse order. The iterator should
 *     support prev() and hasPrev() operations.
 * 
 * Input:
 *     - TreeNode root: Root of the BST (1 <= Number of nodes <= 10^5)
 *     - 0 <= Node.val <= 10^6
 * 
 * Output:
 *     - prev(): Returns the previous largest number in the BST
 *     - hasPrev(): Returns true if there exists a previous largest number
 * 
 * Example:
 *     Input: root = [7,3,15,null,null,9,20]
 *     Output: [20,15,9,7,3]
 * 
 *     Explanation:
 *     BSTIterator iterator = new BSTIterator(root);
 *     iterator.prev();    // return 20
 *     iterator.prev();    // return 15
 *     iterator.hasPrev(); // return true
 *     iterator.prev();    // return 9
 *     iterator.hasPrev(); // return true
 *     iterator.prev();    // return 7
 *     iterator.hasPrev(); // return true
 *     iterator.prev();    // return 3
 *     iterator.hasPrev(); // return false
 */

import java.util.Stack;

public class j02BSTBackwordIterator {

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
     * Approach 1: Using Iterative Reverse Inorder Traversal with Stack
     * 
     * Intuition:
     * - Reverse inorder traversal of BST gives elements in descending order
     * - Stack helps in maintaining the state of traversal
     * - We can simulate the reverse inorder traversal iteratively
     * 
     * Explanation:
     * - Initialize: Push all right nodes to stack
     * - prev(): Pop top node, process it, and push all right nodes of its left child
     * - hasPrev(): Check if stack is empty
     * 
     * Time Complexity: 
     * - Constructor: O(h) where h is height of tree
     * - prev(): O(h) in worst case, O(1) amortized
     * - hasPrev(): O(1)
     * 
     * Space Complexity: O(h) where h is height of tree
     */
    static class BSTBackwardIteratorUsingIterativeTraversal {
        Stack<TreeNode> stack;

        public BSTBackwardIteratorUsingIterativeTraversal(TreeNode root) {
            this.stack = new Stack<>();
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
     * Approach 2: Using Morris Traversal (Reverse)
     * 
     * Intuition:
     * - Morris traversal allows reverse inorder traversal without extra space
     * - We modify the tree structure temporarily to create threaded links
     * - These links help us traverse back to parent nodes in reverse order
     * 
     * Explanation:
     * - Initialize: Create threaded links for right subtree
     * - prev(): Process current node and move to previous using threaded links
     * - hasPrev(): Check if current node is null
     * 
     * Time Complexity: 
     * - Constructor: O(h) where h is height of tree
     * - prev(): O(1) amortized
     * - hasPrev(): O(1)
     * 
     * Space Complexity: O(1) as we don't use extra space
     */
    static class BSTBackwardIteratorUsingMorrisTraversal {
        TreeNode root;
        TreeNode curr;

        public BSTBackwardIteratorUsingMorrisTraversal(TreeNode root) {
            this.root = root;
            this.curr = root;

            // Initialize by creating threaded links for rightmost path
            while (curr.right != null) {
                TreeNode ceil = ceil(curr);  // Find leftmost node in right subtree
                ceil.left = curr;            // Create threaded link
                curr = curr.right;           // Move to right child
            }
        }

        /**
         * Helper method to find leftmost node in right subtree
         * 
         * Time Complexity: O(h) where h is height of tree
         * Space Complexity: O(1)
         * 
         * @param root Current node
         * @return Leftmost node in right subtree
         */
        public TreeNode ceil(TreeNode root) {
            root = root.right;  // Move to right child
            // Find leftmost node that's not already threaded
            while (root.left != null && root.left != curr) {
                root = root.left;
            }
            return root;
        }

        /**
         * Returns the previous value in reverse inorder traversal
         * 
         * Intuition:
         * - Process current node based on threaded links
         * - Move to previous node using Morris traversal rules
         * 
         * Time Complexity: O(1) amortized
         * Space Complexity: O(1)
         * 
         * @return Previous value in reverse inorder traversal
         */
        public int prev() {
            while (curr != null) {
                if (curr.right == null) {
                    // Case 1: No right child, process current node
                    int val = curr.val;
                    curr = curr.left;  // Move to left child
                    return val;
                } else {
                    TreeNode ceil = ceil(curr);
                    if (ceil.left == curr) {
                        // Case 2: Threaded link exists, process current node
                        ceil.left = null;  // Remove threaded link
                        int val = curr.val;
                        curr = curr.left;   // Move to left child
                        return val;
                    } else {
                        // Case 3: Create threaded link and move right
                        ceil.left = curr;  // Create threaded link
                        curr = curr.right; // Move to right child
                    }
                }
            }
            return -1;  // No more nodes to process
        }

        /**
         * Checks if there are more nodes to process
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return True if current node is not null, false otherwise
         */
        public boolean hasPrev() {
            return curr != null;  // Check if current node exists
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic BST
        System.out.println("\nBasic Test Case:");
        TreeNode root1 = new TreeNode(7);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(15);
        root1.right.left = new TreeNode(9);
        root1.right.right = new TreeNode(20);

        BSTBackwardIteratorUsingIterativeTraversal iterator1 = 
            new BSTBackwardIteratorUsingIterativeTraversal(root1);
        System.out.println("Iterative Approach:");
        while (iterator1.hasPrev()) {
            System.out.print(iterator1.prev() + " ");
        }

        // Test Case 2: Empty Tree
        System.out.println("\n\nEmpty Tree Test:");
        BSTBackwardIteratorUsingIterativeTraversal iterator2 = 
            new BSTBackwardIteratorUsingIterativeTraversal(null);
        System.out.println("Has prev: " + iterator2.hasPrev());

        // Test Case 3: Single Node
        System.out.println("\nSingle Node Test:");
        TreeNode root3 = new TreeNode(1);
        BSTBackwardIteratorUsingIterativeTraversal iterator3 = 
            new BSTBackwardIteratorUsingIterativeTraversal(root3);
        System.out.println("Prev: " + iterator3.prev());
        System.out.println("Has prev: " + iterator3.hasPrev());
    }
}
