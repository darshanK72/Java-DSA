/*-
 * Problem Statement:
 *     Design a stack that supports push, pop, peek, isEmpty, size, clear, and print operations using 
 *     an array.
 * 
 * Input Operations:
 *     - push(x): Push element x onto stack
 *     - pop(): Remove and return the top element
 *     - peek(): Return the top element
 *     - isEmpty(): Return whether the stack is empty
 *     - size(): Return the size of stack
 *     - clear(): Remove all elements from stack
 *     - print(): Print all elements in stack
 * 
 * Output:
 *     - For pop() and peek(): Return element if exists, else -1
 *     - For isEmpty(): Return true if stack empty, else false
 *     - For size(): Return current number of elements
 * 
 * Example:
 *     Input: 
 *     push(1), push(2), push(3)
 *     pop(), pop(), pop(), pop()
 *     push(4), push(5)
 *     peek(), size()
 *     print()
 *     clear()
 *     isEmpty()
 *     
 *     Output:
 *     3, 2, 1, -1
 *     5, 2
 *     5 4
 *     true
 * 
 *     Explanation:
 *     First we push 1,2,3 onto stack
 *     Then pop three times to get 3,2,1 and fourth pop returns -1 as stack is empty
 *     Push 4,5 onto stack
 *     Peek returns top element 5, size returns 2
 *     Print displays elements 5 4
 *     Clear empties the stack
 *     isEmpty returns true as stack is now empty
 */

public class j01StackUsingArray {

    public static void main(String args[]) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop()); // 3
        System.out.println(stack.pop()); // 2
        System.out.println(stack.pop()); // 1
        System.out.println(stack.pop()); // -1 (stack is empty)
        stack.push(4);
        stack.push(5);
        System.out.println(stack.peek()); // 5 (top element)
        System.out.println(stack.size()); // 2 (size of stack)
        stack.print(); // 5 4 (print stack elements)
        stack.clear(); // clear stack
        System.out.println(stack.isEmpty()); // true (stack is empty)
    }

    /*-
     * Approach: Array Implementation
     * 
     * Intuition:
     * - Use an array to store elements with a top pointer tracking the topmost element
     * - Initialize top as -1 to indicate empty stack
     * - For push: increment top and add element
     * - For pop: return and decrement top
     * - For peek: return element at top
     * - Size is top+1 since top starts from -1
     * - Clear resets top to -1
     * 
     * Time Complexity:
     * - Push: O(1)
     * - Pop: O(1) 
     * - Peek: O(1)
     * - isEmpty: O(1)
     * - Size: O(1)
     * - Clear: O(1)
     * - Print: O(n) where n is size of stack
     * 
     * Space Complexity:
     * - O(n) where n is maximum size of stack (1000 in this case)
     */
    static class MyStack {
        private int[] arr;
        private int top;

        /**
         * Initialize an empty stack with fixed size array.
         * Time Complexity: O(1)
         * Space Complexity: O(n) where n is the fixed size (1000)
         */
        public MyStack() {
            arr = new int[1000];
            top = -1;
        }

        /**
         * Push an element onto the top of stack.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param x element to be pushed
         * @throws RuntimeException if stack is full
         */
        public void push(int x) {
            if (top == 1000)
                throw new RuntimeException("Stack Overflow");
            arr[++top] = x;
        }

        /**
         * Remove and return the top element from stack.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return top element if exists, -1 if stack is empty
         */
        public int pop() {
            if (top == -1)
                return -1;
            return arr[top--];
        }

        /**
         * Return the top element without removing it.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return top element if exists, -1 if stack is empty
         */
        public int peek() {
            if (top == -1)
                return -1;
            return arr[top];
        }

        /**
         * Check if stack is empty.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if stack is empty, false otherwise
         */
        public boolean isEmpty() {
            return top == -1;
        }

        /**
         * Return current number of elements in stack.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return size of stack
         */
        public int size() {
            return top + 1;
        }

        /**
         * Remove all elements from stack.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public void clear() {
            top = -1;
        }

        /**
         * Print all elements in stack from top to bottom.
         * Time Complexity: O(n) where n is current size of stack
         * Space Complexity: O(1)
         */
        public void print() {
            for (int i = top; i >= 0; i--) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}