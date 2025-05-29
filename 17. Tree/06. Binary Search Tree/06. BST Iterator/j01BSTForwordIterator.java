/**
 * LeetCode 173. Binary Search Tree Iterator
 * 
 * Problem Statement:
 *     Implement the BSTIterator class that represents an iterator over the in-order
 *     traversal of a binary search tree (BST). The iterator should support next()
 *     and hasNext() operations.
 * 
 * Input:
 *     - TreeNode root: Root of the BST (1 <= Number of nodes <= 10^5)
 *     - 0 <= Node.val <= 10^6
 * 
 * Output:
 *     - next(): Returns the next smallest number in the BST
 *     - hasNext(): Returns true if there exists a next smallest number
 * 
 * Example:
 *     Input: root = [7,3,15,null,null,9,20]
 *     Output: [3,7,9,15,20]
 * 
 *     Explanation:
 *     BSTIterator iterator = new BSTIterator(root);
 *     iterator.next();    // return 3
 *     iterator.next();    // return 7
 *     iterator.hasNext(); // return true
 *     iterator.next();    // return 9
 *     iterator.hasNext(); // return true
 *     iterator.next();    // return 15
 *     iterator.hasNext(); // return true
 *     iterator.next();    // return 20
 *     iterator.hasNext(); // return false
 */

import java.util.Stack;

public class j01BSTForwordIterator {

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
     * Approach 1: Using Iterative Inorder Traversal with Stack
     * 
     * Intuition:
     * - Inorder traversal of BST gives elements in ascending order
     * - Stack helps in maintaining the state of traversal
     * - We can simulate the inorder traversal iteratively
     * 
     * Explanation:
     * - Initialize: Push all left nodes to stack
     * - next(): Pop top node, process it, and push all left nodes of its right child
     * - hasNext(): Check if stack is empty
     * 
     * Time Complexity: 
     * - Constructor: O(h) where h is height of tree
     * - next(): O(h) in worst case, O(1) amortized
     * - hasNext(): O(1)
     * 
     * Space Complexity: O(h) where h is height of tree
     */
    static class BSTIteratorUsingIterativeInorderTraversal {
        Stack<TreeNode> stack;

        public BSTIteratorUsingIterativeInorderTraversal(TreeNode root) {
            this.stack = new Stack<>();
            pushAllLeft(root);
        }

        private void pushAllLeft(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public int next() {
            TreeNode node = stack.pop();
            pushAllLeft(node.right);
            return node.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    /**
     * Approach 2: Using Morris Traversal
     * 
     * Intuition:
     * - Morris traversal allows inorder traversal without extra space
     * - We modify the tree structure temporarily to create threaded links
     * - These links help us traverse back to parent nodes
     * 
     * Explanation:
     * - Initialize: Create threaded links for left subtree
     * - next(): Process current node and move to next using threaded links
     * - hasNext(): Check if current node is null
     * 
     * Time Complexity: 
     * - Constructor: O(h) where h is height of tree
     * - next(): O(1) amortized
     * - hasNext(): O(1)
     * 
     * Space Complexity: O(1) as we don't use extra space
     */
    static class BSTIteratorUsingMorrisTraversal {
        TreeNode root;
        TreeNode curr;

        public BSTIteratorUsingMorrisTraversal(TreeNode root) {
            this.root = root;
            this.curr = root;

            while (curr.left != null) {
                TreeNode floor = floor(curr);
                floor.right = curr;
                curr = curr.left;
            }
        }

        public TreeNode floor(TreeNode root) {
            root = root.left;
            while (root.right != null && root.right != curr) {
                root = root.right;
            }
            return root;
        }

        public int next() {
            while (curr != null) {
                if (curr.left == null) {
                    int val = curr.val;
                    curr = curr.right;
                    return val;
                } else {
                    TreeNode floor = floor(curr);
                    if (floor.right == curr) {
                        floor.right = null;
                        int val = curr.val;
                        curr = curr.right;
                        return val;
                    } else {
                        floor.right = curr;
                        curr = curr.left;
                    }
                }
            }
            return -1;
        }

        public boolean hasNext() {
            return curr != null;
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

        BSTIteratorUsingIterativeInorderTraversal iterator1 = 
            new BSTIteratorUsingIterativeInorderTraversal(root1);
        System.out.println("Iterative Approach:");
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }

        // Test Case 2: Empty Tree
        System.out.println("\n\nEmpty Tree Test:");
        BSTIteratorUsingIterativeInorderTraversal iterator2 = 
            new BSTIteratorUsingIterativeInorderTraversal(null);
        System.out.println("Has next: " + iterator2.hasNext());

        // Test Case 3: Single Node
        System.out.println("\nSingle Node Test:");
        TreeNode root3 = new TreeNode(1);
        BSTIteratorUsingIterativeInorderTraversal iterator3 = 
            new BSTIteratorUsingIterativeInorderTraversal(root3);
        System.out.println("Next: " + iterator3.next());
        System.out.println("Has next: " + iterator3.hasNext());
    }
}
