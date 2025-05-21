/**
 * Problem Statement:
 *     LeetCode 116. Populating Next Right Pointers in Each Node
 * 
 *     You are given a perfect binary tree where all leaves are on the same level.
 *     Populate each next pointer to point to its next right node. If there is no
 *     next right node, the next pointer should be set to NULL.
 * 
 * Input:
 *     - Root node of perfect binary tree
 * 
 * Output:
 *     - Modified tree with next pointers set
 * 
 * Example:
 *     Input: 
 *           1
 *          /  \
 *         2    3
 *        / \  / \
 *       4  5 6   7
 *     
 *     Output: 
 *           1 -> null
 *          /  \
 *         2 -> 3 -> null
 *        / \  / \
 *       4->5->6->7 -> null
 */

import java.util.LinkedList;
import java.util.Queue;

public class j08PopulatingNextPointersI {

    /*
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

        public Node() {}

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
        // Test Case 1: Perfect Binary Tree
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);
        root1.right.left = new Node(6);
        root1.right.right = new Node(7);

        System.out.println("Test Case 1:");
        System.out.println("Recursive Approach:");
        printLevelOrder(connectRecursive(root1));
        
        System.out.println("\nConstant Space Approach:");
        printLevelOrder(connectWithoutExtraSpace(root1));
        
        System.out.println("\nLevel Order Approach:");
        printLevelOrder(connectLevelOrder(root1));
    }

    /*-
     * Approach 1: Recursive Solution
     * 
     * Quick Intuition:
     * Perfect binary tree structure allows us to know exactly which nodes should be
     * connected - left child to right child, and right child to next node's left child.
     * 
     * Detailed Explanation:
     * 1. Process each node:
     *    - Left child connects to right child (same parent)
     *    - Right child connects to next node's left child (using parent's next)
     * 
     * 2. Connection patterns:
     *    - Within same parent: root.left.next = root.right
     *    - Across parents: root.right.next = root.next.left
     * 
     * 3. Recursion ensures:
     *    - Top-down processing
     *    - All levels get connected
     *    - Perfect tree property maintains connection pattern
     * 
     * Time Complexity : O(n) - visit each node once
     * Space Complexity : O(h) - recursion stack, h is height
     * 
     * @param root Root node of binary tree
     * @return Modified tree with next pointers set
     */
    public static Node connectRecursive(Node root) {
        if (root == null) return null;
        
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }
        
        connectRecursive(root.left);
        connectRecursive(root.right);
        return root;
    }

    /*-
     * Approach 2: Constant Space Solution
     * 
     * Quick Intuition:
     * Use already established next pointers of current level to build connections
     * for the next level, treating each level as a linked list.
     * 
     * Detailed Explanation:
     * 1. Process tree level by level:
     *    - Current level: Use next pointers for traversal
     *    - Next level: Build connections using dummy head
     * 
     * 2. For each level:
     *    - Create dummy head for next level connections
     *    - Use tail pointer for current connection point
     *    - Process each node in current level left to right
     *    - Connect its children to the growing linked list
     * 
     * 3. Perfect tree property ensures:
     *    - Every non-leaf node has both children
     *    - Complete connections at each level
     * 
     * Time Complexity : O(n) - visit each node once
     * Space Complexity: O(1) - only use constant extra space
     * 
     * @param root Root node of binary tree
     * @return Modified tree with next pointers set
     */
    public static Node connectWithoutExtraSpace(Node root) {
        if (root == null) return root;
        
        Node curr = root;
        while (curr != null) {
            Node head = new Node(-1);
            Node tail = head;
            
            // Connect nodes at next level
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
     * Approach 3: Level Order Traversal
     * 
     * Quick Intuition:
     * Use BFS to ensure we process nodes level by level, connecting each node
     * to the next node in queue except for the last node in each level.
     * 
     * Detailed Explanation:
     * 1. Queue-based level processing:
     *    - Initialize with root
     *    - Track level boundaries using size
     * 
     * 2. For each level:
     *    - Process nodes in order
     *    - Connect to next except last node
     *    - Add children in order for next level
     * 
     * 3. Perfect tree advantages:
     *    - Known level sizes (2^h at level h)
     *    - Complete connections at each level
     *    - Predictable child node existence
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(w) - w is max width, 2^h for perfect tree
     * 
     * @param root Root node of binary tree
     * @return Modified tree with next pointers set
     */
    public static Node connectLevelOrder(Node root) {
        if (root == null) return root;
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                
                // Connect to next node except for last node in level
                if (i < levelSize - 1) {
                    node.next = queue.peek();
                }
                
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return root;
    }

    /**
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
            curr = curr.left;
        }
    }
}