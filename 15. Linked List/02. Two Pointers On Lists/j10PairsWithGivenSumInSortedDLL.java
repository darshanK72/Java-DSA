/**
 * Problem Statement:
 * 
 *     Given a sorted doubly linked list and an integer `target`, find all pairs of nodes
 *     whose sum equals the given `target`.
 * 
 * Input:
 *     - A sorted doubly linked list where each node contains an integer value.
 *     - An integer `target` representing the sum to be achieved by the pairs.
 * 
 * Output:
 *     - A list of all pairs of integers (as lists) whose sum equals the `target`.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 4, 5, 6, 8, 9]
 *         target = 7
 *     Output:
 *         [[1, 6], [2, 5]]
 * 
 *     Explanation:
 *         The pairs (1, 6) and (2, 5) add up to 7. These pairs are returned in the output.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class j10PairsWithGivenSumInSortedDLL {

    /**
     * Node Class:
     * 
     * Represents a node in a doubly linked list. Each node contains:
     * - `data`: The integer value stored in the node.
     * - `next`: A reference to the next node in the list.
     * - `prev`: A reference to the previous node in the list.
     */
    static class Node {
        public int data;
        public Node next;
        public Node prev;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * Approach 1: Hashing
     * 
     * Intuition:
     * - Use a HashSet to store the values of nodes as we traverse the list.
     * - For each node, check if the complement (target - current node's value) exists in the HashSet.
     * - If it exists, we have found a pair that sums to the target.
     * 
     * Explanation:
     * 1. Traverse the list while maintaining a HashSet of visited node values.
     * 2. For each node, check if `target - current node's value` exists in the HashSet.
     * 3. If it exists, add the pair to the output list.
     * 4. Add the current node's value to the HashSet.
     * 5. Reverse the output list to maintain the order of pairs as per the problem's requirements.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list.
     * 
     * Space Complexity:
     * - O(n), due to the HashSet used for storing visited values.
     * 
     * @param target The target sum.
     * @param head The head of the doubly linked list.
     * @return A list of pairs whose sum equals the target.
     */
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSumHashing(int target, Node head) {
        HashSet<Integer> set = new HashSet<>();
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        Node curr = head;

        while (curr != null) {
            if (set.contains(target - curr.data)) {
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(target - curr.data);
                pair.add(curr.data);
                out.add(pair);
            }
            set.add(curr.data);
            curr = curr.next;
        }

        Collections.reverse(out); // Reverse the output list to maintain order
        return out;
    }

    /**
     * Approach 2: Two Pointers
     * 
     * Intuition:
     * - Use two pointers, one starting at the beginning (`start`) and the other at the end (`end`) of the list.
     * - If the sum of the values at the two pointers equals the target, add the pair to the output list.
     * - If the sum is less than the target, move the `start` pointer forward to increase the sum.
     * - If the sum is greater than the target, move the `end` pointer backward to decrease the sum.
     * 
     * Explanation:
     * 1. Initialize two pointers: `start` at the head and `end` at the tail of the list.
     * 2. Traverse the list while `start.data < end.data`:
     *    - If the sum of `start.data` and `end.data` equals the target, add the pair to the output list.
     *    - If the sum is less than the target, move the `start` pointer forward.
     *    - If the sum is greater than the target, move the `end` pointer backward.
     * 3. Return the output list containing all pairs.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from the output list.
     * 
     * @param target The target sum.
     * @param head The head of the doubly linked list.
     * @return A list of pairs whose sum equals the target.
     */
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSumTwoPointers(int target, Node head) {
        Node start = head;
        Node end = head;

        // Move the `end` pointer to the last node
        while (end.next != null) {
            end = end.next;
        }

        ArrayList<ArrayList<Integer>> out = new ArrayList<>();

        while (start.data < end.data) {
            if (start.data + end.data == target) {
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(start.data);
                pair.add(end.data);
                out.add(pair);
                start = start.next; // Move `start` forward
                end = end.prev; // Move `end` backward
            } else if (start.data + end.data < target) {
                start = start.next; // Increase the sum
            } else {
                end = end.prev; // Decrease the sum
            }
        }

        return out;
    }
}