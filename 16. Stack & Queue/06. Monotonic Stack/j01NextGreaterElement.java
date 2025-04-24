/*-
 * Problem Statement:
 *     Next Greater Element
 * 
 *     Given an array arr[] of size N having elements, the task is to find the next
 *     greater element for each element of the array in order of their appearance
 *     in the array. The Next greater Element for an element x is the first greater
 *     element on the right side of x in the array. Elements for which no greater
 *     element exist, consider next greater element as -1.
 * 
 * Input:
 *     - An array arr[] of size N (1 ≤ N ≤ 10^6)
 *     - Array elements are in the range of 1 to 10^18
 * 
 * Output:
 *     - An ArrayList containing the next greater element for each element
 * 
 * Example:
 *     Input: arr[] = [4, 5, 2, 10, 8]
 *     Output: [5, 10, 10, -1, -1]
 * 
 *     Explanation:
 *     - For 4, next greater element is 5
 *     - For 5, next greater element is 10
 *     - For 2, next greater element is 10
 *     - For 10, no greater element exists on right, so -1
 *     - For 8, no greater element exists on right, so -1
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class j01NextGreaterElement {

    public static void main(String[] args) {
        // Test case
        int[] arr = { 4, 5, 2, 10, 8 };
        ArrayList<Integer> result = nextLargerElementLeftToRight(arr);
        System.out.println(result); // Output: [5, 10, 10, -1, -1]
    }

    /*-
     * Approach 1: Left to Right Traversal using Monotonic Stack
     * 
     * Intuition:
     * - We use a stack to keep track of elements whose next greater element is yet
     *   to be found.
     * - The stack maintains elements in decreasing order (monotonic stack).
     * - When we find a greater element, we pop all smaller elements from stack and
     *   update their next greater element.
     * - This approach works because once we find a greater element, it's the next
     *   greater element for all previous smaller elements.
     * 
     * Time Complexity:
     * - O(n) where n is the size of the array. Each element is pushed and popped
     *   at most once.
     * 
     * Space Complexity:
     * - O(n) for the stack in worst case (when array is in decreasing order).
     */
    public static ArrayList<Integer> nextLargerElementLeftToRight(int[] arr) {
        ArrayList<Integer> out = new ArrayList<>();
        int[] outArr = new int[arr.length];
        Arrays.fill(outArr, -1);
        Stack<Integer> stack = new Stack<>();

        // Process all elements of array
        for (int i = 0; i < arr.length; i++) {
            // Pop elements smaller than current and update their next greater
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                outArr[stack.pop()] = arr[i];
            }
            stack.push(i);

        }

        // Convert array to ArrayList for return
        for (int i : outArr) {
            out.add(i);
        }
        return out;
    }

    /*-
     * Approach 2: Right to Left Traversal using Monotonic Stack
     * 
     * Intuition:
     * - Instead of moving left to right, we traverse the array from right to left.
     * - The stack maintains elements in decreasing order from right side.
     * - For each element, we remove all smaller elements from stack as they cannot
     *   be the next greater element.
     * - The top element in stack after removals (if exists) is the next greater
     *   element for current element.
     * - This approach can be more intuitive as we directly process elements in the
     *   order we need to check them.
     * 
     * Time Complexity:
     * - O(n) where n is the size of the array. Each element is pushed and popped
     *   at most once.
     * 
     * Space Complexity:
     * - O(n) for the stack in worst case (when array is in increasing order).
     */
    public static ArrayList<Integer> nextLargerElementRightToLeft(int[] arr) {
        ArrayList<Integer> out = new ArrayList<>();
        int[] outArr = new int[arr.length];
        Arrays.fill(outArr, -1);
        Stack<Integer> stack = new Stack<>();
    
        for (int i = arr.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            if(!stack.isEmpty()){
                outArr[i] = arr[stack.peek()];
            }
            stack.push(i);
        }

        for (int i : outArr) {
            out.add(i);
        }
        return out;
    }
}
