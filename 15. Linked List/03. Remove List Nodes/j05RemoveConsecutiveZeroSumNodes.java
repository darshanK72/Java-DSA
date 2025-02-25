/**
 * Problem Statement:
 * 
 * Given a linked list, remove all consecutive sequences of nodes that sum to zero.
 * 
 * Constraints:
 * - The linked list may contain both positive and negative integers.
 * - If removing a sequence results in new consecutive nodes summing to zero, they should also be removed.
 * - The resulting linked list should maintain relative order for remaining nodes.
 * 
 * Input:
 * - A reference to the head of a linked list.
 * 
 * Output:
 * - The head of the modified linked list with zero-sum consecutive nodes removed.
 * 
 * Example:
 * 
 *     Input:
 *     1 -> 2 -> -3 -> 3 -> 1
 * 
 *     Output:
 *     3 -> 1
 */

import java.util.HashMap;

public class j05RemoveConsecutiveZeroSumNodes {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach: Prefix Sum with HashMap
     * 
     * Intuition:
     * - By maintaining a running sum (prefix sum), we can determine if a subarray sums to zero.
     * - If a prefix sum value reoccurs, it indicates that all elements between these occurrences sum to zero.
     * - A HashMap is used to store the first occurrence of each prefix sum.
     * - If a prefix sum repeats, the sequence between the previous occurrence and the current node is removed.
     * 
     * Explanation:
     * 1. Use a dummy node (`dummy`) pointing to `head` to handle edge cases.
     * 2. Maintain a prefix sum (`sum`) while iterating through the list.
     * 3. Store the first occurrence of each sum in a HashMap (`map`).
     * 4. If the same sum reappears, remove all nodes between the first and last occurrence.
     * 5. Update node references accordingly.
     * 
     * Edge Cases Considered:
     * - The entire list sums to zero (return an empty list).
     * - Single node cases.
     * - No consecutive sequences sum to zero (return the original list).
     * 
     * Time Complexity:
     * - O(N) → Each node is processed at most twice.
     * 
     * Space Complexity:
     * - O(N) → Extra space for HashMap.
     * 
     * @param head The head of the linked list.
     * @return The head of the modified linked list.
     */
    public Node removeZeroSumSublists(Node head) {
        Node dummy = new Node(Integer.MIN_VALUE); // Dummy node to handle edge cases
        dummy.next = head;
        Node curr = head;
        HashMap<Integer, Node> map = new HashMap<>();
        int sum = 0;
        map.put(sum, dummy); // Store sum=0 at dummy node

        while (curr != null) {
            sum += curr.data; // Compute prefix sum

            // If sum was seen before, remove nodes between previous occurrence and current
            if (map.containsKey(sum)) {
                Node node = map.get(sum);
                Node temp = node;
                int tempSum = sum;

                // Remove all intermediate sums
                while (temp != curr) {
                    temp = temp.next;
                    tempSum += temp.data;
                    if (temp != curr) {
                        map.remove(tempSum);
                    }
                }
                node.next = curr.next; // Bypass the removed nodes
            } else {
                map.put(sum, curr); // Store prefix sum with its corresponding node
            }

            curr = curr.next; // Move to the next node
        }
        return dummy.next; // Return modified list
    }
}
