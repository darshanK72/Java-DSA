/**
 * Problem Statement:
 *     Given a queue of integers, rearrange it such that elements at odd positions
 *     are interleaved with elements at even positions.
 * 
 * Input:
 *     Queue of integers with even number of elements
 * 
 * Output:
 *     Queue with interleaved elements
 * 
 * Example:
 *     Input Queue:  [1,2,3,4,5,6,7,8]
 *     Output Queue: [1,5,2,6,3,7,4,8]
 *     First half:   [1,2,3,4]
 *     Second half:  [5,6,7,8]
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class j04InterlevedQueue {

    public static void main(String[] args) {
        // Test both approaches
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (int i = 1; i <= 8; i++) {
            q1.add(i);
            q2.add(i);
        }

        System.out.println("Original Queue: " + q1);
        interLeaveQueue(q1);
        System.out.println("Interleaved Queue (Two Queue Approach): " + q1);

        System.out.println("\nOriginal Queue: " + q2);
        interLeaveQueueUsingStack(q2);
        System.out.println("Interleaved Queue (Stack Approach): " + q2);
    }

    /**
     * Approach 1: Using Two Queues
     * 
     * Intuition:
     * 1. Split original queue into two halves
     * 2. Alternatively take elements from both queues
     * 
     * Example Walkthrough:
     * Original: [1,2,3,4,5,6,7,8]
     * Split:
     * queue1: [1,2,3,4]
     * queue2: [5,6,7,8]
     * Interleave: [1,5,2,6,3,7,4,8]
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param q queue to be interleaved
     */
    public static void interLeaveQueue(Queue<Integer> q) {
        int size = q.size();
        Queue<Integer> firstHalf = new LinkedList<>();
        Queue<Integer> secondHalf = new LinkedList<>();

        // Split into two halves
        for (int i = 0; i < size/2; i++) {
            firstHalf.add(q.remove());
        }
        for (int i = size/2; i < size; i++) {
            secondHalf.add(q.remove());
        }

        // Interleave elements
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                q.add(firstHalf.remove());
            } else {
                q.add(secondHalf.remove());
            }
        }
    }

    /**
     * Approach 2: Using Stack
     * 
     * Intuition:
     * 1. Push second half to stack (reverses order)
     * 2. Re-enqueue first half
     * 3. Alternatively add from queue and stack
     * 
     * Example Walkthrough:
     * Original: [1,2,3,4,5,6,7,8]
     * First half in queue: [1,2,3,4]
     * Second half in stack: [8,7,6,5]
     * Interleave and reverse stack elements: [1,5,2,6,3,7,4,8]
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n/2)
     * 
     * @param q queue to be interleaved
     */
    public static void interLeaveQueueUsingStack(Queue<Integer> q) {
        Stack<Integer> stack = new Stack<>();
        int size = q.size();
        
        // Move first half to temporary storage
        for (int i = 0; i < size/2; i++) {
            q.add(q.remove());
        }
        
        // Push second half to stack
        for (int i = 0; i < size/2; i++) {
            stack.push(q.remove());
        }
        
        // Interleave elements
        while (!stack.isEmpty()) {
            q.add(q.remove());  // from first half
            q.add(stack.pop()); // from second half
        }
    }
}
