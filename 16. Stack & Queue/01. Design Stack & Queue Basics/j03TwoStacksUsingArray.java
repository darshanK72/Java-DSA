/*-
 * Problem Statement:
 *     Implement two stacks in a single array efficiently.
 * 
 * Input Operations:
 *     - push1(x): Push element x into first stack
 *     - push2(x): Push element x into second stack
 *     - pop1(): Pop element from first stack and return it
 *     - pop2(): Pop element from second stack and return it
 * 
 * Output:
 *     - For pop operations: Return popped element if exists, else -1
 * 
 * Example:
 *     Input: 
 *     push1(2), push1(3), push2(4)
 *     pop1(), pop2(), pop2()
 *     
 *     Output:
 *     3, 4, -1
 * 
 *     Explanation:
 *     Push 2,3 to stack1 and 4 to stack2
 *     Pop from stack1 returns 3
 *     Pop from stack2 returns 4
 *     Pop from empty stack2 returns -1
 */

public class j03TwoStacksUsingArray {

    public static void main(String args[]) {
        TwoStack stack = new TwoStack();
        stack.push1(2);
        stack.push1(3);
        stack.push2(4);
        System.out.println(stack.pop1()); // 3
        System.out.println(stack.pop2()); // 4
        System.out.println(stack.pop2()); // -1 (stack2 is empty)
        stack.push1(5);
    }

    public static class TwoStack {
        /*-
         * Approach: Two-End Array Implementation
         * 
         * Intuition:
         * - Use a single array to implement two stacks by growing them from opposite ends
         * - Stack1 grows from left end (starting at index 0)
         * - Stack2 grows from right end (starting at index n-1)
         * - top1 starts from -1 and grows towards right
         * - top2 starts from array end and grows towards left
         * - Stacks are full when they meet in middle (top1 == top2)
         * - This approach provides dynamic space allocation based on usage
         * 
         * Time Complexity:
         * - Push operations (push1, push2): O(1)
         * - Pop operations (pop1, pop2): O(1)
         * 
         * Space Complexity:
         * - O(n) where n is size of array (100 in this case)
         */
        int[] arr = new int[100];
        int top1 = -1;
        int top2 = 100;

        /**
         * Push element into first stack from left end.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param x element to push
         * @return void, silently fails if stack is full
         */
        void push1(int x) {
            top1++;
            if (top1 == top2)
                return;
            arr[top1] = x;
        }

        /**
         * Push element into second stack from right end.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param x element to push
         * @return void, silently fails if stack is full
         */
        void push2(int x) {
            top2--;
            if (top1 == top2)
                return;
            arr[top2] = x;
        }

        /**
         * Pop element from first stack.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return top element if exists, -1 if stack empty
         */
        int pop1() {
            if (top1 == -1)
                return -1;
            return arr[top1--];
        }

        /**
         * Pop element from second stack.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return top element if exists, -1 if stack empty
         */
        int pop2() {
            if (top2 == 100)
                return -1;
            return arr[top2++];
        }
    }
}