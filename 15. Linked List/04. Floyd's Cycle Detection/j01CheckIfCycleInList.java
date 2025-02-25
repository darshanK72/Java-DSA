/**
 * Problem Statement:
 * 
 *      Given a linked list, determine if it contains a cycle.
 * 
 * Constraints:
 *      - The linked list may be empty or contain multiple nodes.
 *      - The cycle may be at any position in the list.
 * 
 * Input:
 *      - A reference to the head of a linked list.
 * 
 * Output:
 *      - Boolean value indicating whether a cycle exists.
 * 
 * Example:
 * 
 *     Input:  3 -> 2 -> 0 -> -4
 *                 ^         |
 *                 |_________|
 *     Output: true
 */

import java.util.HashSet;

public class j01CheckIfCycleInList {

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
     * - If a node is encountered more than once, the list contains a cycle.
     * 
     * Explanation:
     * 1. Traverse the linked list.
     * 2. Store each node reference in a HashSet.
     * 3. If a node is already in the HashSet, return `true` (cycle detected).
     * 4. If the traversal reaches `null`, return `false` (no cycle).
     * 
     * Time Complexity: O(N) - Each node is visited once.
     * Space Complexity: O(N) - HashSet stores up to N nodes.
     * 
     * @param head The head of the linked list.
     * @return `true` if the list contains a cycle, `false` otherwise.
     */
    public boolean hasCycle(Node head) {
        HashSet<Node> set = new HashSet<>();
        Node temp = head;
        while (temp != null) {
            if (set.contains(temp))
                return true;
            set.add(temp);
            temp = temp.next;
        }
        return false;
    }

    /**
     * Approach 2: Floyd's Cycle Detection Algorithm (Tortoise & Hare)
     * 
     * Intuition:
     * - Uses two pointers: slow (moves 1 step) and fast (moves 2 steps).
     * - If they meet, a cycle exists.
     * - If `fast` reaches `null`, there is no cycle.
     * 
     * Explanation:
     * 1. Initialize two pointers: `slow` and `fast`, both at `head`.
     * 2. Move `slow` one step and `fast` two steps.
     * 3. If `slow` meets `fast`, return `true` (cycle detected).
     * 4. If `fast` or `fast.next` is `null`, return `false` (no cycle).
     * 
     * Time Complexity: O(N) - Each node is visited at most twice.
     * Space Complexity: O(1) - Uses only two pointers.
     * 
     * @param head The head of the linked list.
     * @return `true` if the list is circular, `false` otherwise.
     */
    public boolean isCircular(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }
}
