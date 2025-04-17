import java.util.Stack;

public class j04MaximumStack {

    public static void main(String args[]) {
        // Example usage of MaxStackTwoStacks
        MaxStackTwoStacks maxStack = new MaxStackTwoStacks();
        maxStack.push(2);
        maxStack.push(0);
        maxStack.push(3);
        System.out.println(maxStack.getMax()); // Returns 3
        maxStack.pop();
        System.out.println(maxStack.top()); // Returns 0
        System.out.println(maxStack.getMax()); // Returns 2

        MaxStackEncryption maxStackEnc = new MaxStackEncryption();
        maxStackEnc.push(2);
        maxStackEnc.push(0);
        maxStackEnc.push(3);
        System.out.println(maxStackEnc.getMax()); // Returns 3
        maxStackEnc.pop();
        System.out.println(maxStackEnc.top()); // Returns 0
        System.out.println(maxStackEnc.getMax()); // Returns 2
    }

    public static class MaxStackTwoStacks {
        public Stack<Integer> stack;
        public Stack<Integer> maxStack;

        public MaxStackTwoStacks() {
            this.stack = new Stack<>();
            this.maxStack = new Stack<>();
        }

        public void push(int value) {
            this.stack.push(value);
            if (maxStack.isEmpty() || value > maxStack.peek()) {
                this.maxStack.push(value);
            } else {
                this.maxStack.push(maxStack.peek());
            }
        }

        public int pop() {
            if (this.stack.isEmpty())
                return -1;
            this.maxStack.pop();
            return this.stack.pop();
        }

        public int top() {
            if (this.stack.isEmpty())
                return -1;
            return this.stack.peek();
        }

        public int getMax() {
            if (this.maxStack.isEmpty())
                return -1;
            return this.maxStack.peek();
        }
    }

    public static class MaxStackEncryption {
        private Stack<Long> stack; // Stack storing encoded values
        private long max; // Current maximum value

        /**
         * Initialize empty max stack with encoding approach
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public MaxStackEncryption() {
            this.stack = new Stack<>();
            this.max = Integer.MIN_VALUE;
        }

        /**
         * Push element onto stack with encoding
         * 
         * Intuition:
         * - If val <= max: push actual value
         * - If val > max: push encoded value (2*val - max)
         * - Encoding ensures:
         * 1. Encoded value > max (flag for encoded elements)
         * 2. Can recover previous max during pop
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public void push(int val) {
            if (val <= this.max) {
                this.stack.push((long) val);
            } else {
                this.stack.push(2L * val - this.max);
                this.max = val;
            }
        }

        /**
         * Remove top element and update maximum
         * 
         * Intuition:
         * - If top < max: simple pop (was actual value)
         * - If top > max: decode to get previous max
         * Previous max = 2*current_max - encoded_value
         * 
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int pop() {
            if (this.stack.isEmpty())
                return -1;
            long top = this.stack.pop();
            if (top > this.max) {
                int out = (int) this.max;
                this.max = 2l * this.max - top;
                return out;
            }
            return (int) top;
        }

        /**
         * Get top element without removing
         * 
         * Intuition:
         * - If top < max: return actual value
         * - If top > max: return current max (encoded value)
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int top() {
            if (stack.isEmpty())
                return -1;

            long top = this.stack.peek();
            if (top < this.max) {
                return (int) top;
            }
            return (int) this.max;
        }

        /**
         * Get maximum element in stack
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int getMax() {
            if (stack.isEmpty())
                return -1;
            return (int) this.max;
        }

        // Main method for testing

    }
}
