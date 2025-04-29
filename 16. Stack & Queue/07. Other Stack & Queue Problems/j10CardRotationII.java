/**
 * Problem Statement:
 *     Card Rotation II
 * 
 *     Given a number N, construct an array A[] such that when we rotate elements
 *     by their respective positions and put them at their indices, we get numbers
 *     from 1 to N in ascending order.
 * 
 * Input:
 *     - Integer N where 1 <= N <= 10^4
 * 
 * Output:
 *     - ArrayList containing rotated arrangement of numbers
 * 
 * Example:
 *     Input: N = 4
 *     Output: [2, 1, 4, 3]
 *     
 *     Explanation:
 *     After rotation:
 *     - Index 0: rotate 2 once => 2 goes to index 1
 *     - Index 1: rotate 1 once => 1 goes to index 0
 *     - Index 2: rotate 4 thrice => 4 goes to index 3
 *     - Index 3: rotate 3 thrice => 3 goes to index 2
 *     Final array [1, 2, 3, 4] is in ascending order
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class j10CardRotationII {
    
    public static void main(String args[]) {
        // Test cases with different scenarios
        int[] testCases = { 5, 1, 10, 2, 3 };

        for (int test : testCases) {
            System.out.println("N = " + test);
            System.out.println("Rotated Array: " + rotation(test));
            System.out.println();
        }
    }

    /**
     * Approach: Using Queue for Rotation Simulation
     * 
     * Intuition:
     * - Use queue to simulate rotations efficiently
     * - For each number i from 1 to N:
     *   * Rotate queue i times
     *   * Place i at position determined by queue
     * - Convert final array to ArrayList
     * 
     * Time Complexity: O(n²)
     * - For each number (n), perform up to n rotations
     * - Queue operations are O(1)
     * 
     * Space Complexity: O(n)
     * - Queue stores n indices
     * - Output array and ArrayList of size n
     */
    public static ArrayList<Integer> rotation(int n) {
        // Initialize queue with indices
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }

        // Create array with numbers 1 to n
        int[] arr = new int[n];
        for (int i = 1; i < n + 1; i++) {
            arr[i - 1] = i;
        }

        // Process rotations
        int[] out = new int[n];
        int rotations = 1;  // Number of rotations for current element
        for (int i = 0; i < n; i++) {
            // Perform current number of rotations
            int j = rotations;
            while (j-- > 0 && !queue.isEmpty()) {
                queue.add(queue.poll());
            }
            // Place number at rotated position
            out[queue.poll()] = arr[i];
            rotations++;
        }

        // Convert to ArrayList
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(out[i]);
        }
        return result;
    }
}
