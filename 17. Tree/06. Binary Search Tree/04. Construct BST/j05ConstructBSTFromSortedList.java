/**
 * LeetCode 109. Convert Sorted List to Binary Search Tree
 * 
 * Problem Statement:
 *     Given a singly linked list where elements are sorted in ascending order,
 *     convert it to a height balanced Binary Search Tree (BST).
 * 
 * Input:
 *     - head: Head of sorted linked list
 *     - Elements are sorted in ascending order
 *     - List length in range [0, 2 * 10^4]
 *     - Node values in range [-10^5, 10^5]
 * 
 * Output:
 *     - Root node of the height-balanced BST
 * 
 * Example:
 *     Input: [-10,-3,0,5,9]
 *     Output: Tree Structure:
 *            0
 *           / \
 *         -3   9
 *         /   /
 *       -10  5
 */
public class j05ConstructBSTFromSortedList {

    /**
     * Definition for singly-linked list node
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * Definition for binary tree node
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }

    /**
     * Approach: Fast-Slow Pointer Based Conversion
     * 
     * Intuition:
     * - Middle element of sorted list becomes root of BST
     * - Elements before middle form left subtree
     * - Elements after middle form right subtree
     * - Use fast-slow pointer to find middle efficiently
     * 
     * Time Complexity: O(nlogn) - Finding middle for each subtree
     * Space Complexity: O(logn) - Recursion stack height
     * 
     * @param head Head of the sorted linked list
     * @return Root of constructed height-balanced BST
     */
    public static TreeNode sortedListToBST(ListNode head) {
        return convertSortedLinkedListToBST(head, null);
    }

    /**
     * Helper Method: Convert List Range to BST
     * 
     * Intuition:
     * - Use start and end pointers to define current sublist
     * - Find middle node to create balanced subtrees
     * - Recursively construct left and right subtrees
     * 
     * Explanation:
     * 1. If start equals end, return null (empty sublist)
     * 2. Find middle node using fast-slow pointer technique
     * 3. Create root from middle node value
     * 4. Recursively build left subtree from start to mid
     * 5. Recursively build right subtree from mid.next to end
     * 
     * Time Complexity: O(n) for current level
     * Space Complexity: O(logn) recursion stack
     * 
     * @param start Start node of current sublist
     * @param end End node of current sublist (exclusive)
     * @return Root of constructed subtree
     */
    public static TreeNode convertSortedLinkedListToBST(ListNode start, ListNode end) {
        // Base case: empty sublist
        if (start == end)
            return null;
            
        // Find middle node
        ListNode mid = midNode(start, end);
        
        // Create root from middle value
        TreeNode root = new TreeNode(mid.val);
        
        // Recursively construct subtrees
        root.left = convertSortedLinkedListToBST(start, mid);
        root.right = convertSortedLinkedListToBST(mid.next, end);
        
        return root;
    }

    /**
     * Helper Method: Find Middle Node
     * 
     * Intuition:
     * - Use fast and slow pointers to find middle
     * - Fast pointer moves twice as fast as slow
     * - When fast reaches end, slow is at middle
     * 
     * Time Complexity: O(n) - Single pass through list
     * Space Complexity: O(1) - Only two pointers used
     * 
     * @param start Start node of list
     * @param end End node of list (exclusive)
     * @return Middle node of the list
     */
    public static ListNode midNode(ListNode start, ListNode end) {
        ListNode slow = start;
        ListNode fast = start;
        
        // Move fast pointer twice as fast until end
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
}
