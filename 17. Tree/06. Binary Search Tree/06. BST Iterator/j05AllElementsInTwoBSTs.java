/**
 * LeetCode 1305. All Elements in Two Binary Search Trees
 * 
 * Problem Statement:
 *     Given two binary search trees root1 and root2, return a list containing all
 *     the integers from both trees sorted in ascending order.
 * 
 * Input:
 *     - TreeNode root1: Root of first BST (0 <= Number of nodes <= 5000)
 *     - TreeNode root2: Root of second BST (0 <= Number of nodes <= 5000)
 *     - -10^5 <= Node.val <= 10^5
 * 
 * Output:
 *     - List<Integer>: All elements from both trees in ascending order
 * 
 * Example:
 *     Input: root1 = [2,1,4], root2 = [1,0,3]
 *     Output: [0,1,1,2,3,4]
 * 
 *     Explanation:
 *     Elements from first tree: [1,2,4]
 *     Elements from second tree: [0,1,3]
 *     Merged result: [0,1,1,2,3,4]
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class j05AllElementsInTwoBSTs {

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
     * Approach 1: Recursive Inorder Traversal with Merge
     * 
     * Intuition:
     * - Get inorder traversal of both trees (gives sorted lists)
     * - Merge the two sorted lists
     * 
     * Time Complexity: O(n + m) where n and m are number of nodes in both trees
     * Space Complexity: O(n + m) for storing the lists
     * 
     * @param root1 Root of first BST
     * @param root2 Root of second BST
     * @return List of all elements in ascending order
     */
    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = getInorder(root1);  // Get inorder traversal of first tree
        List<Integer> list2 = getInorder(root2);  // Get inorder traversal of second tree
        return mergeTwoSortedList(list1, list2);  // Merge the sorted lists
    }

    /**
     * Helper method to get inorder traversal of a BST
     * 
     * Intuition:
     * - Recursive inorder traversal gives elements in ascending order
     * - Process left subtree, then root, then right subtree
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(n) for recursion stack and output list
     * 
     * @param root Root of the BST
     * @return List of elements in inorder traversal
     */
    private static List<Integer> getInorder(TreeNode root) {
        ArrayList<Integer> out = new ArrayList<>();
        if (root == null)  // Handle empty tree case
            return out;
        out.addAll(getInorder(root.left));   // Process left subtree
        out.add(root.val);                   // Process current node
        out.addAll(getInorder(root.right));  // Process right subtree
        return out;
    }

    /**
     * Helper method to merge two sorted lists
     * 
     * Intuition:
     * - Use two pointers to merge sorted lists
     * - Compare elements and add smaller one to result
     * - Handle remaining elements in either list
     * 
     * Time Complexity: O(n + m) where n and m are lengths of input lists
     * Space Complexity: O(n + m) for output list
     * 
     * @param list1 First sorted list
     * @param list2 Second sorted list
     * @return Merged sorted list
     */
    private static List<Integer> mergeTwoSortedList(List<Integer> list1, List<Integer> list2) {
        int i = 0, j = 0;  // Pointers for both lists
        List<Integer> out = new ArrayList<>();

        // Merge while both lists have elements
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) < list2.get(j)) {
                out.add(list1.get(i++));  // Add smaller element from list1
            } else if (list1.get(i) > list2.get(j)) {
                out.add(list2.get(j++));  // Add smaller element from list2
            } else {
                out.add(list1.get(i++));  // Add equal elements from both lists
                out.add(list2.get(j++));
            }
        }

        // Add remaining elements from list1
        while (i < list1.size())
            out.add(list1.get(i++));

        // Add remaining elements from list2
        while (j < list2.size())
            out.add(list2.get(j++));

        return out;
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
     * - peek(): O(1)
     * 
     * Space Complexity: O(h) where h is height of tree
     */
    static class ForwardIterator {
        private Stack<TreeNode> stack;

        public ForwardIterator(TreeNode root) {
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

        /**
         * Returns the next value without removing it from stack
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return Next value in inorder traversal
         */
        public int peek() {
            return stack.peek().val;  // Return value without popping
        }
    }

    /**
     * Approach 2: Using Iterators
     * 
     * Intuition:
     * - Use forward iterators for both trees
     * - Compare next elements and add smaller one to result
     * - Handle remaining elements in either tree
     * 
     * Time Complexity: O(n + m) where n and m are number of nodes
     * Space Complexity: O(h1 + h2) where h1 and h2 are heights of trees
     * 
     * @param root1 Root of first BST
     * @param root2 Root of second BST
     * @return List of all elements in ascending order
     */
    public List<Integer> getAllElementsUsingIterator(TreeNode root1, TreeNode root2) {
        // Initialize iterators for both trees
        ForwardIterator forward1 = new ForwardIterator(root1);
        ForwardIterator forward2 = new ForwardIterator(root2);
        ArrayList<Integer> out = new ArrayList<>();

        // Merge elements while both iterators have values
        while (forward1.hasNext() && forward2.hasNext()) {
            if (forward1.peek() < forward2.peek()) {
                out.add(forward1.next());  // Add smaller element from first tree
            } else {
                out.add(forward2.next());  // Add smaller element from second tree
            }
        }

        // Add remaining elements from first tree
        while (forward1.hasNext()) {
            out.add(forward1.next());
        }

        // Add remaining elements from second tree
        while (forward2.hasNext()) {
            out.add(forward2.next());
        }

        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic trees
        System.out.println("\nBasic Test Case:");
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right = new TreeNode(3);

        List<Integer> result = getAllElements(root1, root2);
        System.out.println("Merged elements: " + result);  // Expected: [0,1,1,2,3,4]

        // Test Case 2: Empty trees
        System.out.println("\nEmpty trees test:");
        List<Integer> result2 = getAllElements(null, null);
        System.out.println("Result: " + result2);  // Expected: []

        // Test Case 3: One empty tree
        System.out.println("\nOne empty tree test:");
        List<Integer> result3 = getAllElements(root1, null);
        System.out.println("Result: " + result3);  // Expected: [1,2,4]

        // Test Case 4: Large trees
        System.out.println("\nLarge trees test:");
        TreeNode root4 = new TreeNode(5);
        root4.left = new TreeNode(3);
        root4.right = new TreeNode(7);
        root4.left.left = new TreeNode(2);
        root4.left.right = new TreeNode(4);
        root4.right.left = new TreeNode(6);
        root4.right.right = new TreeNode(8);

        TreeNode root5 = new TreeNode(1);
        root5.right = new TreeNode(9);

        List<Integer> result4 = getAllElements(root4, root5);
        System.out.println("Result: " + result4);  // Expected: [1,2,3,4,5,6,7,8,9]
    }
}
