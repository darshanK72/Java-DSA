/**
 * Problem Statement:
 *     Design a stack that supports increment operation on all its elements below a given index.
 *     https://leetcode.com/problems/design-a-stack-with-increment-operation/
 * 
 * Input Operations:
 *     - push(x): Push element x onto stack
 *     - pop(): Remove and return the top element
 *     - increment(k, val): Increment bottom k elements by val
 * 
 * Output:
 *     - For pop: Return element if exists, -1 if stack empty
 * 
 * Example:
 *     Input: 
 *     CustomStack stack = new CustomStack(3);
 *     stack.push(1); stack.push(2);
 *     stack.pop();
 *     stack.push(2); stack.push(3);
 *     stack.increment(5, 100);
 *     stack.increment(2, 100);
 *     stack.pop();
 *     stack.pop();
 *     stack.pop();
 *     
 *     Output:
 *     2
 *     [103,202,201]
 *     103, 202, 201
 * 
 *     Explanation:
 *     First increment adds 100 to all elements
 *     Second increment adds 100 to bottom 2 elements
 *     Pop operations return modified values
 */

public class j07StackWithIncrement {
    /**
     * Approach 1: Simple Array Implementation
     * 
     * Intuition:
     * - Use array to store elements
     * - Track top pointer for push/pop
     * - Directly modify elements during increment
     * 
     * Time Complexity:
     * - Push/Pop: O(1)
     * - Increment: O(k) where k is number of elements to increment
     * 
     * Space Complexity:
     * - O(n) where n is maxSize
     */
    public static class CustomStack {
        int[] arr;  // Array to store elements
        int top;    // Points to next free position

        /**
         * Initialize empty stack with given size
         * Time Complexity: O(1)
         * Space Complexity: O(n)
         * 
         * @param maxSize maximum size of stack
         */
        public CustomStack(int maxSize) {
            this.arr = new int[maxSize];
            this.top = 0;
        }

        /**
         * Push element onto stack if not full
         * 
         * Intuition:
         * 1. Check if stack has space
         * 2. Add element at top position
         * 3. Increment top pointer
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param x element to push
         */
        public void push(int x) {
            if (top < this.arr.length) {
                this.arr[top] = x;
                top++;
            }
        }

        /**
         * Remove and return top element
         * 
         * Intuition:
         * 1. Check if stack is not empty
         * 2. Decrement top pointer
         * 3. Return element at top
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return top element if exists, -1 if empty
         */
        public int pop() {
            if (top > 0) {
                top--;
                return this.arr[top];
            }
            return -1;
        }

        /**
         * Increment bottom k elements by val
         * 
         * Intuition:
         * 1. Iterate through first k elements
         * 2. Add val to each element
         * 3. Handle case where k > stack size
         * 
         * Time Complexity: O(k)
         * Space Complexity: O(1)
         * 
         * @param k number of elements to increment
         * @param val value to add to elements
         */
        public void increment(int k, int val) {
            for (int i = 0; i < k && i < this.top; i++) {
                this.arr[i] += val;
            }
        }
    }

    /**
     * Approach 2: Lazy Propagation Implementation
     * 
     * Intuition:
     * - Use additional array to store increment values
     * - Delay actual increments until pop operation
     * - Propagate increments during pop
     * 
     * Time Complexity:
     * - Push: O(1)
     * - Pop: O(1)
     * - Increment: O(1)
     * 
     * Space Complexity:
     * - O(n) where n is maxSize
     */
    public static class IncrementStackLazyPropogation {
        int[] arr;  // Array to store elements
        int[] inc;  // Array to store increment values
        int top;    // Points to next free position

        /**
         * Initialize empty stack with lazy increment tracking
         * Time Complexity: O(1)
         * Space Complexity: O(n)
         * 
         * @param maxSize maximum size of stack
         */
        public IncrementStackLazyPropogation(int maxSize) {
            this.arr = new int[maxSize];
            this.inc = new int[maxSize];
            this.top = 0;
        }

        /**
         * Push element onto stack if not full
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param x element to push
         */
        public void push(int x) {
            if (top < this.arr.length) {
                this.arr[top] = x;
                top++;
            }
        }

        /**
         * Remove and return top element with propagated increments
         * 
         * Intuition:
         * 1. Check if stack is not empty
         * 2. Get current increment value
         * 3. Reset current increment
         * 4. Propagate increment to previous element
         * 5. Return element plus increment
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return modified top element if exists, -1 if empty
         */
        public int pop() {
            if (top > 0) {
                top--;
                int increment = this.inc[top];
                this.inc[top] = 0;
                if (top > 0)
                    this.inc[top - 1] += increment;
                return this.arr[top] + increment;
            }
            return -1;
        }

        /**
         * Increment bottom k elements by val using lazy approach
         * 
         * Intuition:
         * 1. Store increment at highest affected position
         * 2. Actual increment happens during pop
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param k number of elements to increment
         * @param val value to add to elements
         */
        public void increment(int k, int val) {
            if (top > 0) {
                int last = Math.min(k - 1, top - 1);
                this.inc[last] += val;
            }
        }
    }
}
