/**
 * Problem Statement:
 *     Generate Binary Numbers Using Queue
 * 
 *     Given a number N, generate all binary numbers from 1 to N using a queue.
 *     The numbers should be generated in increasing order of value.
 * 
 * Input:
 *     - Integer N where 1 <= N <= 10^4
 * 
 * Output:
 *     - ArrayList containing binary representation of numbers from 1 to N
 * 
 * Example:
 *     Input: N = 5
 *     Output: ["1", "10", "11", "100", "101"]
 *     
 *     Explanation:
 *     - 1 in binary is "1"
 *     - 2 in binary is "10"
 *     - 3 in binary is "11"
 *     - 4 in binary is "100"
 *     - 5 in binary is "101"
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class j03GenerateBinaryStringUsingQueue {

    public static void main(String args[]) {
        // Test cases with different scenarios
        int[] testCases = {5, 1, 10, 2, 3};
        
        for (int test : testCases) {
            System.out.println("N = " + test);
            System.out.println("Binary numbers: " + generate(test));
            System.out.println();
        }
    }

    /**
     * Approach: Using Queue for Pattern Generation
     * 
     * Intuition:
     * - Start with "1" in queue
     * - For each number polled from queue:
     *   * Add it to result
     *   * Append "0" and "1" to create next numbers
     *   * Add new numbers back to queue
     * - Process until N numbers are generated
     * 
     * Time Complexity: O(N)
     * - Generate N binary numbers
     * - Each number processed once
     * 
     * Space Complexity: O(N)
     * - Queue stores at most N+2 strings
     * - Output ArrayList stores N strings
     */
    public static ArrayList<String> generate(int n) {
        ArrayList<String> out = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        
        queue.add("1");  // Start with 1
        
        while (n-- > 0) {
            String top = queue.poll();     // Get next number
            out.add(top);                  // Add to result
            
            // Generate next two binary numbers
            queue.add(top + "0");         // Append 0
            queue.add(top + "1");         // Append 1
        }
        
        return out;
    }
}
