/*-
 * Problem Statement:
 *     Design a stack that supports push, pop, peek, isEmpty, size, clear, and print operations using 
 *     a Linked List.
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
 *     pop(), peek()
 *     size(), isEmpty()
 *     print()
 *     clear()
 *     isEmpty()
 *     
 *     Output:
 *     3, 2
 *     2, false
 *     2 1
 *     true
 * 
 *     Explanation:
 *     Push 1,2,3 onto stack
 *     Pop returns 3, peek shows 2
 *     Size is 2, stack is not empty
 *     Print shows elements 2 1
 *     Clear empties the stack
 *     isEmpty returns true
 */

public class j02StackUsingLinkedList {

    /*-
     * Approach: Linked List Implementation
     * 
     * Intuition:
     * - Use a linked list where each node contains data and reference to next node
     * - Maintain a top pointer to track the head of list
     * - For push: create new node and make it the new top
     * - For pop: return top's data and move top to next node
     * - Keep track of size for O(1) size operations
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
     * - O(n) where n is number of elements in stack
     */
    static class MyStack {
        /**
         * Node class for linked list implementation
         */
        class StackNode {
            int data;
            StackNode next;

            StackNode(int a) {
                data = a;
                next = null;
            }
        }

        StackNode top;
        public int size = 0;

        /**
         * Push an element onto the top of stack.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param a element to be pushed
         */
        void push(int a) {
            StackNode node = new StackNode(a);
            node.next = top;
            top = node;
            this.size++;
        }

        /**
         * Remove and return the top element from stack.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return top element if exists, -1 if stack is empty
         */
        int pop() {
            if (top == null)
                return -1;
            int value = top.data;
            top = top.next;
            this.size--;
            return value;
        }

        /**
         * Return the top element without removing it.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return top element if exists, -1 if stack is empty
         */
        public int peek() {
            if (top == null)
                return -1;
            return top.data;
        }

        /**
         * Check if stack is empty.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if stack is empty, false otherwise
         */
        public boolean isEmpty() {
            return top == null;
        }

        /**
         * Return current number of elements in stack.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return size of stack
         */
        public int size() {
            return this.size;
        }

        /**
         * Remove all elements from stack.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public void clear() {
            top = null;
            this.size = 0;
        }

        /**
         * Print all elements in stack from top to bottom.
         * Time Complexity: O(n) where n is size of stack
         * Space Complexity: O(1)
         */
        public void print() {
            StackNode current = top;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }
    }
}
