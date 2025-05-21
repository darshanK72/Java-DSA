/*-
 * Problem Statement:
 *     LeetCode 117. Populating Next Right Pointers in Each Node II
 * 
 *     Given a binary tree, populate each next pointer to point to its next right node.
 *     If there is no next right node, the next pointer should be set to NULL.
 *     Unlike Problem 116, the tree can be any binary tree (not necessarily perfect).
 * 
 * Input:
 *     - Root node of binary tree
 * 
 * Output:
 *     - Modified tree with next pointers set
 * 
 * Example:
 *     Input: 
 *           1
 *          /  \
 *         2    3
 *        / \    \
 *       4   5    7
 *     
 *     Output: 
 *           1 -> null
 *          /  \
 *         2 -> 3 -> null
 *        / \    \
 *       4-> 5 -> 7 -> null
 */

import java.util.LinkedList;
import java.util.Queue;

public class j09PopulatingNextPointersII {

    /*-
     * Node class representing each node in the binary tree
     * - val: value of the node
     * - left: left child
     * - right: right child
     * - next: pointer to the next right node
     */
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Irregular binary tree
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);
        root1.right.right = new Node(7);

        System.out.println("Test Case 1:");
        System.out.println("Constant Space Approach:");
        printLevelOrder(connectWithoutExtraSpace(root1));

        System.out.println("\nLevel Order Approach:");
        printLevelOrder(connectLevelOrder(root1));
    }

    /*-
     * Helper method to print level order traversal
     * to verify next pointers
     */
    private static void printLevelOrder(Node root) {
        Node curr = root;
        while (curr != null) {
            Node temp = curr;
            while (temp != null) {
                System.out.print(temp.val + " -> ");
                temp = temp.next;
            }
            System.out.println("null");

            // Find leftmost node of next level
            curr = curr.left;
            while (curr == null && temp != null) {
                curr = temp.right;
                temp = temp.next;
            }
        }
    }

    /*-
     * Approach 1: Constant Space Solution
     * 
     * Quick Intuition:
     * Think of the tree as a linked list at each level. We maintain a tail pointer
     * to connect nodes at the next level while traversing the current level using
     * next pointers.
     * 
     * Detailed Explanation:
     * 1. We process the tree level by level:
     * - Current level: Use 'next' pointers for horizontal traversal
     * - Next level: Build connections using dummy head and tail
     * 
     * 2. For each level:
     * - Create dummy head (-1) to simplify list building
     * - Use tail pointer to track last connected node
     * - Process current level nodes left to right
     * - For each node:
     * * Connect its left child to tail if exists
     * * Connect its right child to tail if exists
     * * Move horizontally using next pointer
     * 
     * 3. After level is done:
     * - Move to next level (head.next)
     * - Repeat until no more levels
     * 
     * Time Complexity : O(n) - visit each node once
     * Space Complexity: O(1) - only use constant extra space
     * 
     * @param root Root node of binary tree
     * @return Modified tree with next pointers set
     */
    public static Node connectWithoutExtraSpace(Node root) {
        if (root == null)
            return root;
        Node curr = root;
        while (curr != null) {
            Node head = new Node(-1);
            Node tail = head;
            while (curr != null) {
                if (curr.left != null) {
                    tail.next = curr.left;
                    tail = tail.next;
                }
                if (curr.right != null) {
                    tail.next = curr.right;
                    tail = tail.next;
                }
                curr = curr.next;
            }
            curr = head.next;
        }
        return root;
    }

    /*-
     * Approach 2: Level Order Traversal
     * 
     * Quick Intuition:
     * Use BFS to process nodes level by level, keeping track of the previous node
     * in current level to establish next pointers.
     * 
     * Detailed Explanation:
     * 1. Queue-based level traversal:
     * - Initialize queue with root
     * - Process nodes level by level
     * 
     * 2. For each level:
     * - Get level size to know boundary
     * - Process each node in level:
     * * Connect to next node if not last in level
     * * Add children to queue for next level
     * 
     * 3. Key points:
     * - queue.peek() gives next node in same level
     * - Last node in level remains pointing to null
     * - Children added in order for next level
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(w) - w is max width, 2^h for perfect tree
     * 
     * @param root Root node of binary tree
     * @return Modified tree with next pointers set
     */
    public static Node connectLevelOrder(Node root) {
        if (root == null)
            return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int s = queue.size();
            for (int i = 0; i < s; i++) {
                Node node = queue.poll();
                if (i < s - 1)
                    node.next = queue.peek();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }
}
