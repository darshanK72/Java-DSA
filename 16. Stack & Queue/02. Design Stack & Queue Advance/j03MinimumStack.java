/*-
 * Problem Statement:
 *     Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *     https://leetcode.com/problems/min-stack/
 * 
 * Input Operations:
 *     - push(val): Push element val onto stack
 *     - pop(): Remove the element on top of the stack
 *     - top(): Get the top element
 *     - getMin(): Retrieve the minimum element in the stack
 * 
 * Output:
 *     - For top/getMin: Return element if exists
 *     - For push/pop: void operations
 * 
 * Example:
 *     Input: 
 *     MinStack minStack = new MinStack();
 *     minStack.push(-2);
 *     minStack.push(0);
 *     minStack.push(-3);
 *     minStack.getMin();
 *     minStack.pop();
 *     minStack.top();
 *     minStack.getMin();
 *     
 *     Output:
 *     -3
 *     0
 *     -2
 * 
 *     Explanation:
 *     Push -2,0,-3 to stack
 *     getMin() returns -3
 *     pop() removes -3
 *     top() returns 0
 *     getMin() returns -2
 */

import java.util.Stack;

public class j03MinimumStack {
    /*-
     * Approach 1: Two Stacks
     * 
     * Intuition:
     * - Use main stack for normal stack operations
     * - Use auxiliary minStack to track minimum at each level
     * - minStack maintains current minimum for each corresponding element in main stack
     * - Initialize minStack with MAX_VALUE to handle empty stack case
     * 
     * Time Complexity:
     * - All operations: O(1)
     * 
     * Space Complexity:
     * - O(n) where n is number of elements
     */
    public static class MinStackTwoStacks {
        Stack<Integer> stack;    // Main stack for elements
        Stack<Integer> minStack; // Auxiliary stack for minimums

        /**
         * Initialize empty min stack with two stacks
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public MinStackTwoStacks() {
            this.stack = new Stack<>();
            this.minStack = new Stack<>();
            this.minStack.push(Integer.MAX_VALUE);
        }

        /**
         * Push element onto stack and update minimum
         * 
         * Intuition:
         * - Push element to main stack
         * - For minStack, push either new value if smaller than current min,
         *   or push current min again to maintain parallel structure
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param val value to push
         */
        public void push(int val) {
            this.stack.push(val);
            if (val < this.minStack.peek()) {
                this.minStack.push(val);
            } else {
                this.minStack.push(this.minStack.peek());
            }
        }

        /**
         * Remove top element and update minimum
         * 
         * Intuition:
         * - Pop from both stacks to maintain parallel structure
         * - minStack automatically maintains minimum for remaining elements
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public void pop() {
            this.stack.pop();
            this.minStack.pop();
        }

        /**
         * Get top element without removing
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return top element of stack
         */
        public int top() {
            return this.stack.peek();
        }

        /**
         * Get minimum element in stack
         * 
         * Intuition:
         * - minStack's top always holds current minimum
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return minimum element in stack
         */
        public int getMin() {
            return this.minStack.peek();
        }
    }

    /*-
     * Approach 2: Single Stack with Encryption
     * 
     * Intuition:
     * - Use single stack with mathematical encoding to track minimum
     * - When pushing element smaller than current min:
     *   - Store encoded value: 2 * val - min
     *   - Update min to new value
     * - When popping encoded element:
     *   - Calculate previous min: 2 * current_min - encoded_value
     * - Use long to handle integer overflow cases
     * 
     * Time Complexity:
     * - All operations: O(1)
     * 
     * Space Complexity:
     * - O(n) where n is number of elements
     * - Uses less space than two-stack approach
     */
    public static class MinStackEncryption {
        Stack<Long> stack; // Stack storing encoded values
        long min;         // Current minimum value

        /**
         * Initialize empty min stack with encoding approach
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public MinStackEncryption() {
            this.stack = new Stack<>();
            this.min = Integer.MAX_VALUE;
        }

        /**
         * Push element onto stack with encoding
         * 
         * Intuition:
         * - If val > min: push actual value
         * - If val ≤ min: push encoded value (2*val - min)
         * - Encoding ensures:
         *   1. Encoded value < min (flag for encoded elements)
         *   2. Can recover previous min during pop
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param val value to push
         */
        public void push(int val) {
            if (val > this.min) {
                this.stack.push((long) val);
            } else {
                this.stack.push(2l * val - this.min);
                this.min = val;
            }
        }

        /**
         * Remove top element and update minimum
         * 
         * Intuition:
         * - If top > min: simple pop (was actual value)
         * - If top < min: decode to get previous min
         *   Previous min = 2*current_min - encoded_value
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public void pop() {
            long top = this.stack.pop();
            if (top < this.min) {
                this.min = 2l * this.min - top;
            }
        }

        /**
         * Get top element without removing
         * 
         * Intuition:
         * - If top > min: return actual value
         * - If top < min: return current min (encoded value)
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return top element of stack
         */
        public int top() {
            long top = this.stack.peek();
            if (top > this.min)
                return (int) top;
            return (int) this.min;
        }

        /**
         * Get minimum element in stack
         * 
         * Intuition:
         * - min variable always holds current minimum
         * - No decoding needed
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return minimum element in stack
         */
        public int getMin() {
            return (int) this.min;
        }
    }
}
