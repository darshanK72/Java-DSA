/**
 * Problem Statement:
 *     Next Greater Node In Linked List (LeetCode 1019)
 * 
 *     Given a linked list, return an array of integers answer, where answer[i] is
 *     the next greater node after node i in the linked list. If there is no greater
 *     node, set the value to 0.
 * 
 * Input:
 *     - Head of a linked list
 *     - The number of nodes in the list is between 1 and 10^4
 *     - Each node's value is between 1 and 10^9
 * 
 * Output:
 *     - An array where answer[i] is the next greater value for node i
 * 
 * Example:
 *     Input: head = [2,7,4,3,5]
 *     Output: [7,0,5,5,0]
 *     
 *     Explanation:
 *     - For 2, the next greater element is 7
 *     - For 7, no greater element exists, so 0
 *     - For 4, the next greater element is 5
 *     - For 3, the next greater element is 5
 *     - For 5, no greater element exists, so 0
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class j04NextGreaterElementLinkedList {

    static class Node {
        int val;
        Node next;

        Node(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * Approach 1: Stack with HashMap (Memory-Efficient)
     * 
     * Intuition:
     * - Use a stack to keep track of nodes waiting for their next greater element
     * - Use HashMap to maintain node-to-index mapping for result array
     * - Process nodes sequentially, updating result array when finding greater elements
     * 
     * Time Complexity: O(n)
     * - Single pass through the linked list
     * - Each node is pushed and popped at most once
     * 
     * Space Complexity: O(n)
     * - Stack can store up to n nodes: O(n)
     * - HashMap stores n node-to-index mappings: O(n)
     * - Result array: O(n)
     */
    public static int[] nextLargerNodesUsingHashMap(Node head) {
        // Get length for result array
        int length = getLength(head);
        int[] out = new int[length];
        
        // Stack for nodes waiting for next greater element
        Stack<Node> stack = new Stack<>();
        // Map to store node-to-index mapping
        HashMap<Node, Integer> map = new HashMap<>();
        
        Node temp = head;
        int i = 0;
        
        while (temp != null) {
            // Process nodes that found their next greater element
            while (!stack.isEmpty() && stack.peek().val < temp.val) {
                out[map.get(stack.pop())] = temp.val;
            }
            // Store current node
            map.put(temp, i);
            stack.push(temp);
            i++;
            temp = temp.next;
        }
        return out;
    }

    /**
     * Approach 2: In-Place Stack (Space-Efficient)
     * 
     * Intuition:
     * - Use the linked list nodes themselves to store results
     * - Modify node values directly to store next greater elements
     * - Convert modified list to array at the end
     * 
     * Time Complexity: O(n)
     * - Two passes through the linked list
     * - Each node is pushed and popped at most once
     * 
     * Space Complexity: O(n)
     * - Stack space: O(n)
     * - No additional data structures needed
     * - Result array: O(n)
     */
    public static int[] nextLargerNodesUsingStack(Node head) {
        int length = getLength(head);
        Stack<Node> stack = new Stack<>();
        Node temp = head;
        while (temp != null) {
            while (!stack.isEmpty() && stack.peek().val < temp.val) {
                stack.pop().val = temp.val;
            }
            stack.push(temp);
            temp = temp.next;
        }
        while (!stack.isEmpty()) {
            stack.pop().val = 0;
        }
        temp = head;
        int[] out = new int[length];
        for (int i = 0; i < length; i++) {
            out[i] = temp.val;
            temp = temp.next;
        }

        return out;
    }

    /**
     * Approach 3: Array Conversion
     * 
     * Intuition:
     * - Convert linked list to array first
     * - Apply standard next greater element solution on array
     * - Simple but requires extra space for array
     * 
     * Time Complexity: O(n)
     * - Converting list to array: O(n)
     * - Processing array: O(n)
     * 
     * Space Complexity: O(n)
     * - Additional array to store list values: O(n)
     * - Stack space: O(n)
     * - Result array: O(n)
     */
    public static int[] nextLargerNodesUsingArray(Node head) {
        int length = getLength(head);
        int[] arr = new int[length];
        Node temp = head;
        int i = 0;
        while(temp != null){
            arr[i] = temp.val;
            i++;
            temp = temp.next;
        }
        return nextLargerElementLeftToRight(arr);
    }

    /**
     * Helper Method: nextLargerElementLeftToRight
     * 
     * Intuition:
     * - Process array from left to right using a monotonic stack
     * - Stack maintains indices of elements waiting for next greater element
     * - When finding a greater element, update all smaller elements in stack
     * 
     * Time Complexity: O(n)
     * - Single pass through the array
     * - Each element pushed and popped at most once
     * 
     * Space Complexity: O(n)
     * - Stack can store up to n elements in worst case
     * - Output array of size n
     */
    public static int[] nextLargerElementLeftToRight(int[] arr) {
        // Initialize output array with default value 0
        int[] outArr = new int[arr.length];
        Arrays.fill(outArr, 0);
        
        // Stack to store indices of elements waiting for next greater
        Stack<Integer> stack = new Stack<>();

        // Process each element in array
        for (int i = 0; i < arr.length; i++) {
            // Pop elements smaller than current and update their next greater
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                outArr[stack.pop()] = arr[i];
            }
            // Push current element's index
            stack.push(i);
        }

        return outArr;
    }

    /**
     * Helper Method: getLength
     * 
     * Purpose:
     * - Calculates the length of the linked list
     * - Used to initialize result arrays with correct size
     * 
     * Time Complexity: O(n)
     * - Single pass through the linked list
     * 
     * Space Complexity: O(1)
     * - Only uses a single counter variable
     */
    private static int getLength(Node head) {
        // Temporary pointer to traverse list
        Node temp = head;
        int l = 0;
        
        // Count nodes until end of list
        while (temp != null) {
            l++;
            temp = temp.next;
        }
        return l;
    }
}
