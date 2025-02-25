/**
 * Problem Statement:
 * 
 *      Given a linked list, determine the number of nodes inside a cycle (if a cycle exists).
 *      If no cycle exists, return 0.
 * 
 * Constraints:
 *      - The linked list may be empty or contain multiple nodes.
 *      - The cycle can start at any position.
 * 
 * Input:
 *      - A reference to the head of a linked list.
 * 
 * Output:
 *      - The count of nodes in the cycle, or 0 if no cycle is found.
 * 
 * Example:
 * 
 *     Input:  1 -> 2 -> 3 -> 4 -> 5
 *                          ^    |
 *                          |____|
 *     Output: 3 (nodes in cycle: 3, 4, 5)
 */

import java.util.HashMap;

public class j03CountNodesInCycle {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach 1: HashMap Method
     * 
     * Intuition:
     * - Uses a HashMap to track the index of visited nodes.
     * - If a node is visited again, a cycle is detected.
     * - The difference in index gives the number of nodes in the cycle.
     * 
     * Explanation:
     * 1. Traverse the linked list.
     * 2. Store each node's reference and its index in a HashMap.
     * 3. If a node is encountered again, compute the cycle length.
     * 4. If traversal reaches `null`, return 0 (no cycle).
     * 
     * Time Complexity: O(N) - Each node is visited once.
     * Space Complexity: O(N) - HashMap stores up to N nodes.
     * 
     * @param head The head of the linked list.
     * @return The number of nodes in the cycle, or 0 if no cycle exists.
     */
    public int countNodesinLoop(Node head) {
        HashMap<Node, Integer> map = new HashMap<>();
        int index = 0;
        Node temp = head;
        while (temp != null) {
            if (map.containsKey(temp)) {
                return index - map.get(temp); // Cycle detected, return cycle length.
            }
            map.put(temp, index);
            temp = temp.next;
            index++;
        }
        return 0; // No cycle detected.
    }

    /**
     * Approach 2: Floyd’s Cycle Detection Algorithm (Tortoise & Hare)
     * 
     * Intuition:
     * - Uses slow and fast pointers to detect a cycle.
     * - If they meet, a cycle exists.
     * - To count the cycle's nodes, traverse from the meeting point until it loops back.
     * 
     * Explanation:
     * 1. Use slow and fast pointers to detect if a cycle exists.
     * 2. If no cycle is detected, return `0`.
     * 3. If a cycle exists:
     *    - Move one pointer inside the cycle.
     *    - Count the number of nodes until it loops back to the same node.
     * 
     * Time Complexity: O(N) - Each node is visited at most twice.
     * Space Complexity: O(1) - Uses only two pointers.
     * 
     * @param head The head of the linked list.
     * @return The number of nodes in the cycle, or 0 if no cycle exists.
     */
    public int countNodesinLoopEfficient(Node head) {
        Node slow = head;
        Node fast = head;

        // Detect if a cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return countCycleNodes(slow); // Count nodes in cycle.
            }
        }
        return 0; // No cycle detected.
    }

    /**
     * Helper method to count the number of nodes in a detected cycle.
     * 
     * @param node A node inside the cycle.
     * @return The number of nodes in the cycle.
     */
    private int countCycleNodes(Node node) {
        Node temp = node.next;
        int count = 1;
        while (temp != node) {
            temp = temp.next;
            count++;
        }
        return count;
    }
}
