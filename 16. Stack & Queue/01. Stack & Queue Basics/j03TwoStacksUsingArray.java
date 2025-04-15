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
        * Approach: Interleaved Array Implementation
        * 
        * Intuition:
        * - Use even indices (0,2,4...) for stack1 and odd indices (1,3,5...) for stack2
        * - top1 starts from -2 and increments by 2 for stack1
        * - top2 starts from -1 and increments by 2 for stack2
        * - This ensures maximum utilization of array space and no overlap between stacks
        * - Each stack can grow until array is full, providing equal space to both
        * 
        * Time Complexity:
        * - Push operations (push1, push2): O(1)
        * - Pop operations (pop1, pop2): O(1)
        * 
        * Space Complexity:
        * - O(n) where n is size of array (101 in this case)
        */
        int[] arr = new int[101];
        int top1 = -2;
        int top2 = -1;

        /**
         * Push element into first stack.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param x element to push
         */
        void push1(int x) {
            top1 += 2;
            arr[top1] = x;
        }

        /**
         * Push element into second stack.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param x element to push
         */
        void push2(int x) {
            top2 += 2;
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
            if (top1 == -2)
                return -1;
            int out = arr[top1];
            top1 -= 2;
            return out;
        }

        /**
         * Pop element from second stack.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return top element if exists, -1 if stack empty
         */
        int pop2() {
            if (top2 == -1)
                return -1;
            int out = arr[top2];
            top2 -= 2;
            return out;
        }
    }
}