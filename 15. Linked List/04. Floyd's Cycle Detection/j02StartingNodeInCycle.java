/**
 * Problem Statement:
 * 
 *      Given a linked list, return the starting node of the cycle (if present).
 *      If no cycle exists, return null.
 * 
 * Constraints:
 *      - The linked list may be empty or contain multiple nodes.
 *      - The cycle can start at any position.
 * 
 * Input:
 *      - A reference to the head of a linked list.
 * 
 * Output:
 *      - The node where the cycle begins, or null if no cycle is found.
 * 
 * Example:
 * 
 *     Input:  3 -> 2 -> 0 -> -4
 *                 ^         |
 *                 |_________|
 *     Output: Node(2)
 */

import java.util.HashSet;

public class j02StartingNodeInCycle {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach 1: HashSet Method
     * 
     * Intuition:
     * - Uses a HashSet to track visited nodes.
     * - The first repeated node is the start of the cycle.
     * 
     * Explanation:
     * 1. Traverse the linked list.
     * 2. Store each node reference in a HashSet.
     * 3. If a node is encountered again, return that node (cycle detected).
     * 4. If traversal reaches `null`, return `null` (no cycle).
     * 
     * Time Complexity: O(N) - Each node is visited once.
     * Space Complexity: O(N) - HashSet stores up to N nodes.
     * 
     * @param head The head of the linked list.
     * @return The starting node of the cycle, or `null` if no cycle exists.
     */
    public Node detectCycle(Node head) {
        HashSet<Node> set = new HashSet<>();
        Node temp = head;
        while (temp != null) {
            if (set.contains(temp))
                return temp; // Cycle detected, return the starting node.
            set.add(temp);
            temp = temp.next;
        }
        return null; // No cycle detected.
    }

    /**
     * Approach 2: Floyd’s Cycle Detection Algorithm (Tortoise & Hare)
     * 
     * Intuition:
     * - Uses two pointers: slow (moves 1 step) and fast (moves 2 steps).
     * - If they meet, a cycle exists.
     * - To find the cycle's start, move one pointer to `head` and advance both one step at a time.
     * - The first meeting point is the start of the cycle.
     * 
     * Explanation:
     * 1. Use slow and fast pointers to detect if a cycle exists.
     * 2. If no cycle is detected, return `null`.
     * 3. If a cycle exists:
     *    - Reset one pointer to `head`.
     *    - Move both pointers one step at a time.
     *    - When they meet, return the meeting node (cycle start).
     * 
     * Time Complexity: O(N) - Each node is visited at most twice.
     * Space Complexity: O(1) - Uses only two pointers.
     * 
     * @param head The head of the linked list.
     * @return The starting node of the cycle, or `null` if no cycle exists.
     */
    public Node detectCycleEfficient(Node head) {
        Node slow = head;
        Node fast = head;

        // Detect if a cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break; // Cycle detected.
            }
        }

        // No cycle found
        if (fast == null || fast.next == null) {
            return null;
        }

        // Reset one pointer to head
        Node temp = head;
        while (temp != slow) {
            temp = temp.next;
            slow = slow.next;
        }
        return temp; // Starting node of the cycle.
    }
}
