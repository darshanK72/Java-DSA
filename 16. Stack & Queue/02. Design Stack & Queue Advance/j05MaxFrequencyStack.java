/**
 * Problem Statement:
 *     Design a stack-like data structure that supports push and pop operations, and provides 
 *     a popMax operation to pop the element with maximum frequency.
 *     https://leetcode.com/problems/maximum-frequency-stack/
 * 
 * Input Operations:
 *     - push(val): Push element val onto the stack
 *     - pop(): Remove and return the most frequent element
 *              If there is a tie, remove the most recent one
 * 
 * Output:
 *     - For pop: Return the most frequent element
 *     - Return -1 if stack is empty
 * 
 * Example:
 *     Input: 
 *     FreqStack stack = new FreqStack();
 *     stack.push(5); stack.push(7); stack.push(5); stack.push(7); stack.push(4); stack.push(5);
 *     stack.pop();
 *     stack.pop();
 *     stack.pop();
 *     
 *     Output:
 *     5 (frequency=3)
 *     7 (frequency=2)
 *     5 (frequency=2)
 * 
 *     Explanation:
 *     After pushes: [5,7,5,7,4,5]
 *     First pop returns 5 as it appears 3 times
 *     Second pop returns 7 as it appears 2 times and is most recent
 *     Third pop returns 5 as it appears 2 times
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class j05MaxFrequencyStack {

    public static void main(String args[]){
        // Example usage of FreqStackStackMap
        FreqStackStackMap stack = new FreqStackStackMap();
        stack.push(5);
        stack.push(7);
        stack.push(5);
        stack.push(7);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pop()); // 5
        System.out.println(stack.pop()); // 7
        System.out.println(stack.pop()); // 5

        // Example usage of FreqStackListMap
        FreqStackListMap listStack = new FreqStackListMap();
        listStack.push(5);
        listStack.push(7);
        listStack.push(5);
        listStack.push(7);
        listStack.push(4);
        listStack.push(5);
        System.out.println(listStack.pop()); // 5
        System.out.println(listStack.pop()); // 7
        System.out.println(listStack.pop()); // 5
    }

    /**
     * Approach 1: Using Stack and HashMap
     * 
     * Intuition:
     * - Use freqMap to track frequency of each element
     * - Use groupMap to store elements by their frequency
     * - Each frequency maintains its own stack for timestamp ordering
     * - maxFreq tracks current maximum frequency
     * 
     * Time Complexity: O(1) for all operations
     * Space Complexity: O(n) where n is number of elements
     */
    public static class FreqStackStackMap {
        private Map<Integer, Integer> freqMap;    // Element -> Frequency
        private Map<Integer, Stack<Integer>> groupMap;  // Frequency -> Stack of elements
        private int maxFreq;

        /**
         * Initialize empty frequency stack
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public FreqStackStackMap() {
            this.freqMap = new HashMap<>();
            this.groupMap = new HashMap<>();
            this.maxFreq = 0;
        }

        /**
         * Push element onto stack and update frequencies
         * 
         * Intuition:
         * - Increment element's frequency
         * - Add to corresponding frequency group
         * - Update maxFreq if needed
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param val value to push
         */
        public void push(int val) {
            int freq = this.freqMap.getOrDefault(val, 0) + 1;
            this.freqMap.put(val, freq);
            this.maxFreq = Math.max(this.maxFreq, freq);
            Stack<Integer> stack = groupMap.getOrDefault(freq, new Stack<>());
            stack.push(val);
        }

        /**
         * Remove and return most frequent element
         * 
         * Intuition:
         * - Pop from stack of maxFreq group
         * - Decrease element's frequency
         * - Update maxFreq if group becomes empty
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return most frequent element
         */
        public int pop() {
            if (maxFreq == 0)
                return -1;
            Stack<Integer> stack = this.groupMap.get(maxFreq);
            int val = stack.pop();
            this.freqMap.put(val, this.freqMap.get(val) - 1);
            if (stack.isEmpty()) {
                maxFreq--;
            }
            return val;
        }
    }

    /**
     * Approach 2: Using Linked List and HashMap
     * 
     * Intuition:
     * - Use freqMap to track frequency of each element
     * - Use map to maintain linked list for each frequency
     * - New elements at same frequency are added at head
     * - maxFreq tracks current maximum frequency
     * 
     * Time Complexity: O(1) for all operations
     * Space Complexity: O(n) where n is number of elements
     */
    static class FreqStackListMap {
        static class Node {
            int data;
            Node next;
            
            public Node(int data) {
                this.data = data;
                this.next = null;
            }
        }

        HashMap<Integer, Node> map;        // Frequency -> List of elements
        HashMap<Integer, Integer> freqMap; // Element -> Frequency
        int maxFreq = 0;

        /**
         * Initialize empty frequency stack
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public FreqStackListMap() {
            this.map = new HashMap<>();
            this.freqMap = new HashMap<>();
        }

        /**
         * Push element onto stack and update frequencies
         * 
         * Intuition:
         * - Create new node for element
         * - Update/increase element's frequency
         * - If new maxFreq, create new list
         * - Else add to head of existing frequency list
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param val value to push
         */
        public void push(int val) {
            int f = freqMap.getOrDefault(val, 0) + 1;
            Node node = new Node(val);
            freqMap.put(val, f);
            if (f > maxFreq) {
                maxFreq = f;
                map.put(maxFreq, node);
            } else {
                Node head = map.get(f);
                node.next = head;
                map.put(f, node);
            }
        }

        /**
         * Remove and return most frequent element
         * 
         * Intuition:
         * - Get head of maxFreq list
         * - Update frequency and list references
         * - Decrease maxFreq if list becomes empty
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return most frequent element
         */
        public int pop() {
            if (maxFreq == 0) {
                return -1;
            }
            Node head = this.map.get(maxFreq);
            int out = head.data;
            this.freqMap.put(out, this.freqMap.get(out) - 1);
            head = head.next;
            this.map.put(maxFreq, head);
            if (head == null) {
                maxFreq--;
            }
            return out;
        }
    }
}
