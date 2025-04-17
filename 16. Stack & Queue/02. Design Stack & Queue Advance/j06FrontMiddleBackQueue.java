/**
 * Problem Statement:
 *     Design a queue that supports push and pop operations from front, middle and back.
 *     https://leetcode.com/problems/design-front-middle-back-queue/
 * 
 * Input Operations:
 *     - pushFront(val): Add element at front of queue
 *     - pushMiddle(val): Add element at middle of queue
 *     - pushBack(val): Add element at back of queue
 *     - popFront(): Remove and return front element
 *     - popMiddle(): Remove and return middle element
 *     - popBack(): Remove and return back element
 * 
 * Output:
 *     - For pop operations: Return element if exists, -1 if queue empty
 * 
 * Example:
 *     Input: 
 *     pushFront(1), pushBack(2), pushMiddle(3), pushMiddle(4)
 *     popFront(), popMiddle(), popMiddle(), popBack()
 *     
 *     Output:
 *     1, 3, 4, 2
 * 
 *     Explanation:
 *     [1] -> [1,2] -> [1,3,2] -> [1,4,3,2]
 *     Pop front returns 1: [4,3,2]
 *     Pop middle returns 3: [4,2]
 *     Pop middle returns 4: [2]
 *     Pop back returns 2: []
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class j06FrontMiddleBackQueue {
    /**
     * Approach 1: Singly Linked List Implementation
     * 
     * Intuition:
     * - Use singly linked list with head pointer
     * - Track size to calculate middle position
     * - Middle is (size-1)/2 for odd size, size/2 for even
     * - Traverse to required position for middle operations
     * 
     * Time Complexity:
     * - Front operations: O(1)
     * - Middle operations: O(n)
     * - Back operations: O(n)
     * 
     * Space Complexity:
     * - O(n) where n is number of elements
     */
    static class FrontMiddleBackQueueUsingList {
        private class Node {
            int val;
            Node next;

            Node(int val) {
                this.val = val;
                this.next = null;
            }
        }

        private Node head;
        private int size;

        public FrontMiddleBackQueueUsingList() {
            head = null;
            size = 0;
        }

        /**
         * Add element at front of queue
         * 
         * Intuition:
         * 1. Create new node with given value
         * 2. Make new node point to current head
         * 3. Update head to new node
         * 4. Increment size counter
         * 
         * Time Complexity: O(1) - direct head manipulation
         * Space Complexity: O(1) - only creates one new node
         * 
         * @param val value to add
         */
        public void pushFront(int val) {
            Node newNode = new Node(val);
            newNode.next = head;
            head = newNode;
            size++;
        }

        /**
         * Add element at middle of queue
         * 
         * Intuition:
         * 1. Handle empty queue case with pushFront
         * 2. Calculate middle position: size/2
         * 3. Traverse to node before middle position
         * 4. Insert new node between current and next
         * 5. Update size counter
         * 
         * Edge Cases:
         * - Empty queue: Insert at front
         * - Single element: Insert at front or back based on middle calculation
         * 
         * Time Complexity: O(n) - needs traversal to middle
         * Space Complexity: O(1) - only creates one new node
         * 
         * @param val value to add
         */
        public void pushMiddle(int val) {
            if (size == 0) {
                pushFront(val);
                return;
            }

            Node newNode = new Node(val);
            int middlePos = size / 2;
            Node current = head;

            if (middlePos == 0) {
                pushFront(val);
                return;
            }

            for (int i = 0; i < middlePos - 1; i++) {
                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
            size++;
        }

        /**
         * Add element at back of queue
         * 
         * Intuition:
         * 1. Handle empty queue case with pushFront
         * 2. Traverse to last node
         * 3. Add new node at end
         * 4. Update size counter
         * 
         * Edge Cases:
         * - Empty queue: Insert at front
         * 
         * Time Complexity: O(n) - needs full traversal
         * Space Complexity: O(1) - only creates one new node
         * 
         * @param val value to add
         */
        public void pushBack(int val) {
            if (head == null) {
                pushFront(val);
                return;
            }

            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(val);
            size++;
        }

        /**
         * Remove and return front element
         * 
         * Intuition:
         * 1. Check if queue is empty
         * 2. Store head's value
         * 3. Move head to next node
         * 4. Decrement size counter
         * 
         * Edge Cases:
         * - Empty queue: Return -1
         * 
         * Time Complexity: O(1) - direct head manipulation
         * Space Complexity: O(1) - no extra space needed
         * 
         * @return front element if exists, -1 if empty
         */
        public int popFront() {
            if (head == null)
                return -1;

            int val = head.val;
            head = head.next;
            size--;
            return val;
        }

        /**
         * Remove and return middle element
         * 
         * Intuition:
         * 1. Handle empty queue case
         * 2. Calculate middle position: (size-1)/2 for proper middle
         * 3. Traverse to node before middle
         * 4. Remove middle node and maintain links
         * 5. Decrement size counter
         * 
         * Edge Cases:
         * - Empty queue: Return -1
         * - Single element: Use popFront
         * 
         * Time Complexity: O(n) - needs traversal to middle
         * Space Complexity: O(1) - no extra space needed
         * 
         * @return middle element if exists, -1 if empty
         */
        public int popMiddle() {
            if (head == null)
                return -1;

            int middlePos = (size - 1) / 2;
            Node current = head;

            if (middlePos == 0) {
                return popFront();
            }

            for (int i = 0; i < middlePos - 1; i++) {
                current = current.next;
            }

            int val = current.next.val;
            current.next = current.next.next;
            size--;
            return val;
        }

        /**
         * Remove and return back element
         * 
         * Intuition:
         * 1. Handle empty queue case
         * 2. Handle single element case with popFront
         * 3. Traverse to second-last node
         * 4. Remove last node and update links
         * 5. Decrement size counter
         * 
         * Edge Cases:
         * - Empty queue: Return -1
         * - Single element: Use popFront
         * 
         * Time Complexity: O(n) - needs full traversal
         * Space Complexity: O(1) - no extra space needed
         * 
         * @return back element if exists, -1 if empty
         */
        public int popBack() {
            if (head == null)
                return -1;
            if (head.next == null)
                return popFront();

            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }

            int val = current.next.val;
            current.next = null;
            size--;
            return val;
        }
    }

    /**
     * Approach 2: Two Deque Implementation
     * 
     * Intuition:
     * - Use two deques (left and right) to split queue in middle
     * - Maintain balance: size difference ≤ 1
     * - For odd size: right deque has one extra element
     * - Balance after each operation to maintain invariant
     * 
     * Time Complexity:
     * - All operations: O(1) amortized
     * 
     * Space Complexity:
     * - O(n) where n is number of elements
     */
    static class FrontMiddleBackQueueUsingDeque {

        Deque<Integer> left;
        Deque<Integer> right;

        public FrontMiddleBackQueueUsingDeque() {
            this.left = new ArrayDeque<>();
            this.right = new ArrayDeque<>();
        }

        public int getSize() {
            return this.left.size() + this.right.size();
        }

        /**
         * Balance elements between left and right deques
         * 
         * Intuition:
         * 1. Check if total size is even or odd
         * 2. For even size:
         *    - Ensure left.size == right.size
         * 3. For odd size:
         *    - Ensure right.size == left.size + 1
         * 4. Transfer elements between deques until balanced
         * 
         * Examples:
         * Even total size 4: [1,2] | [3,4]
         * Odd total size 5: [1,2] | [3,4,5]
         * 
         * Time Complexity: O(k) where k is size difference
         * Space Complexity: O(1) - no extra space needed
         */
        private void balance() {
            if ((left.size() + right.size()) % 2 == 0) {
                while (left.size() > right.size()) {
                    int val = left.removeLast();
                    right.addFirst(val);
                }

                while (right.size() > left.size()) {
                    int val = right.removeFirst();
                    left.addLast(val);
                }
            } else {
                while (left.size() + 1 > right.size()) {
                    int val = left.removeLast();
                    right.addFirst(val);
                }
                while (right.size() > left.size() + 1) {
                    int val = right.removeFirst();
                    left.addLast(val);
                }
            }
        }

        /**
         * Add element at front of queue
         * 
         * Intuition:
         * 1. Add element to front of left deque
         * 2. Rebalance deques to maintain invariant
         * 
         * Example:
         * Before: [1,2] | [3,4]
         * Add 0: [0,1,2] | [3,4]
         * After balance: [0,1] | [2,3,4]
         * 
         * Time Complexity: O(1) amortized - balance operation is amortized
         * Space Complexity: O(1) - no extra space needed
         * 
         * @param val value to add
         */
        public void pushFront(int val) {
            left.addFirst(val);
            balance();
        }

        /**
         * Add element at middle of queue
         * 
         * Intuition:
         * 1. Check if total size is even or odd
         * 2. For even size: add to right.front (becomes new middle)
         * 3. For odd size: add to left.back (becomes new middle)
         * 4. Rebalance deques
         * 
         * Example:
         * Even size: [1,2] | [3,4] → add 5 → [1,2] | [5,3,4]
         * Odd size: [1] | [2,3] → add 4 → [1,4] | [2,3]
         * 
         * Time Complexity: O(1) amortized
         * Space Complexity: O(1)
         * 
         * @param val value to add
         */
        public void pushMiddle(int val) {
            if (getSize() % 2 == 0) {
                right.addFirst(val);
            } else {
                left.addLast(val);
            }
            balance();
        }

        /**
         * Add element at back of queue
         * 
         * Intuition:
         * 1. Add element to end of right deque
         * 2. Rebalance deques to maintain invariant
         * 
         * Example:
         * Before: [1,2] | [3,4]
         * Add 5: [1,2] | [3,4,5]
         * After balance: [1,2] | [3,4,5]
         * 
         * Time Complexity: O(1) amortized
         * Space Complexity: O(1)
         * 
         * @param val value to add
         */
        public void pushBack(int val) {
            right.addLast(val);
            balance();
        }

        /**
         * Remove and return front element
         * 
         * Intuition:
         * 1. Handle empty queue case
         * 2. If left is empty, remove from right
         * 3. Otherwise remove from left
         * 4. Rebalance deques
         * 
         * Example:
         * Before: [1,2] | [3,4,5]
         * Pop: [2] | [3,4,5]
         * After balance: [2,3] | [4,5]
         * 
         * Time Complexity: O(1) amortized
         * Space Complexity: O(1)
         * 
         * @return front element if exists, -1 if empty
         */
        public int popFront() {
            if (getSize() == 0)
                return -1;
            if (left.size() == 0) {
                int val = right.removeFirst();
                balance();
                return val;
            } else {
                int val = left.removeFirst();
                balance();
                return val;
            }
        }

        /**
         * Remove and return middle element
         * 
         * Intuition:
         * 1. Handle empty queue case
         * 2. For even size: remove from left's end
         * 3. For odd size: remove from right's front
         * 4. Rebalance deques
         * 
         * Example:
         * Even size: [1,2,3] | [4,5,6] → remove 3
         * Odd size: [1,2] | [3,4,5] → remove 3
         * 
         * Time Complexity: O(1) amortized
         * Space Complexity: O(1)
         * 
         * @return middle element if exists, -1 if empty
         */
        public int popMiddle() {
            if (getSize() == 0)
                return -1;
            int val;
            if (getSize() % 2 == 0) {
                val = left.removeLast();
            } else {
                val = right.removeFirst();
            }
            balance();
            return val;
        }

        /**
         * Remove and return back element
         * 
         * Intuition:
         * 1. Handle empty queue case
         * 2. If right is empty, remove from left's end
         * 3. Otherwise remove from right's end
         * 4. Rebalance deques
         * 
         * Example:
         * Before: [1,2] | [3,4,5]
         * Pop: [1,2] | [3,4]
         * After balance: [1,2] | [3,4]
         * 
         * Time Complexity: O(1) amortized
         * Space Complexity: O(1)
         * 
         * @return back element if exists, -1 if empty
         */
        public int popBack() {
            if (getSize() == 0)
                return -1;
            if (this.right.size() == 0) {
                int val = left.removeLast();
                balance();
                return val;
            } else {
                int val = right.removeLast();
                balance();
                return val;
            }
        }
    }
}
