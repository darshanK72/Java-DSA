/**
 * Problem Statement:
 * 
 *      Given a linked list, detect and remove the cycle if it exists.
 *      If there is no cycle, the linked list remains unchanged.
 * 
 * Constraints:
 *      - The linked list may contain multiple nodes.
 *      - The cycle can start at any position.
 * 
 * Input:
 *      - A reference to the head of a linked list.
 * 
 * Output:
 *      - The linked list without a cycle.
 * 
 * Example:
 * 
 *     Input:  1 -> 2 -> 3 -> 4 -> 5 -> 3 (cycle)
 *     Output: 1 -> 2 -> 3 -> 4 -> 5 -> null
 */

import java.util.HashSet;

public class j04RemoveCycleFromList {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach 1: HashSet Method (Detect & Remove Cycle)
     * 
     * Intuition:
     * - Uses a HashSet to store visited nodes.
     * - If a node is visited again, a cycle is detected.
     * - Remove the cycle by setting the previous node's `next` pointer to `null`.
     * 
     * Explanation:
     * 1. Traverse the linked list while storing each node in a HashSet.
     * 2. If a node's `next` pointer is already in the HashSet, a cycle is detected.
     * 3. Break the cycle by setting `temp.next = null`.
     * 
     * Time Complexity: O(N) - Each node is visited once.
     * Space Complexity: O(N) - HashSet stores up to N nodes.
     * 
     * @param head The head of the linked list.
     */
    public static void removeLoop(Node head) {
        HashSet<Node> set = new HashSet<>();
        Node temp = head;
        while (temp != null && temp.next != null) {
            if (set.contains(temp.next)) {
                temp.next = null; // Break the cycle
                return;
            }
            set.add(temp);
            temp = temp.next;
        }
    }

    /**
     * Approach 2: Floyd’s Cycle Detection Algorithm (Tortoise & Hare)
     * 
     * Intuition:
     * - Uses slow and fast pointers to detect a cycle.
     * - Once a cycle is detected, find the starting node of the cycle.
     * - Traverse to the last node in the cycle and remove it by setting `next = null`.
     * 
     * Explanation:
     * 1. Detect if a cycle exists using slow & fast pointers.
     * 2. If a cycle exists, find the starting node of the cycle.
     * 3. Traverse the cycle until reaching the last node and remove the cycle.
     * 
     * Time Complexity: O(N) - Each node is visited at most twice.
     * Space Complexity: O(1) - Uses only two pointers.
     * 
     * @param head The head of the linked list.
     */
    public static void removeLoopEfficient(Node head) {
        Node slow = head;
        Node fast = head;

        // Detect if a cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null)
            return; // No cycle detected

        // Find the start of the cycle
        Node temp = head;
        while (temp != slow) {
            temp = temp.next;
            slow = slow.next;
        }

        // Traverse to the last node in the cycle and remove it
        Node cycleNode = slow;
        while (cycleNode.next != slow) {
            cycleNode = cycleNode.next;
        }
        cycleNode.next = null; // Break the cycle
    }
}
