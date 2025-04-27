/**
 * Problem Statement:
 *     LeetCode 739. Daily Temperatures
 * 
 *     Given an array of integers temperatures representing daily temperatures, return
 *     an array answer such that answer[i] is the number of days you have to wait
 *     after the ith day to get a warmer temperature. If there is no future day for
 *     which this is possible, put 0 instead.
 * 
 * Input:
 *     - Array temperatures where 1 <= temperatures.length <= 10^5
 *     - 30 <= temperatures[i] <= 100
 * 
 * Output:
 *     - Array where answer[i] is number of days until warmer temperature
 * 
 * Example:
 *     Input: temperatures = [73,74,75,71,69,72,76,73]
 *     Output: [1,1,4,2,1,1,0,0]
 *     
 *     Explanation:
 *     - Day 1: 73 -> 1 day (74 is warmer)
 *     - Day 2: 74 -> 1 day (75 is warmer)
 *     - Day 3: 75 -> 4 days (76 is warmer)
 *     - Day 4: 71 -> 2 days (72 is warmer)
 *     - Day 5: 69 -> 1 day (72 is warmer)
 *     - Day 6: 72 -> 1 day (76 is warmer)
 *     - Day 7: 76 -> 0 days (no warmer temperature)
 *     - Day 8: 73 -> 0 days (no warmer temperature)
 */

import java.util.Stack;
import java.util.Arrays;

public class j07DailyTemperatures {

    public static void main(String args[]) {
        // Test cases
        int[][] testCases = {
            {73, 74, 75, 71, 69, 72, 76, 73},  // Given example
            {30, 40, 50, 60},                   // Strictly increasing
            {60, 50, 40, 30},                   // Strictly decreasing
            {30, 30, 30},                       // Same temperatures
            {35},                               // Single day
            {89, 62, 70, 58, 47, 47, 46, 76}   // Complex case
        };

        // Test both implementations
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            int[] temps = testCases[i].clone();
            System.out.println("Temperatures: " + Arrays.toString(temps));
            System.out.println("Brute Force: " + 
                Arrays.toString(dailyTemperatures(temps.clone())));
            System.out.println("Stack Based: " + 
                Arrays.toString(dailyTemperaturesEfficientI(temps.clone())));
            System.out.println();
        }
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - For each day, look ahead until we find a warmer temperature
     * - Keep track of whether we found a warmer day using a flag
     * - If no warmer day found, mark as 0
     * 
     * Time Complexity: O(n²)
     * - For each temperature, we may need to scan all future temperatures
     * - Nested loops result in quadratic time complexity
     * 
     * Space Complexity: O(1)
     * - Only uses a constant amount of extra space
     * - Modifies input array in-place
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        for (int i = 0; i < temperatures.length; i++) {
            boolean flag = true;
            // Look ahead for warmer temperature
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    temperatures[i] = (j - i);
                    flag = false;
                    break;
                }
            }
            // No warmer temperature found
            if (flag) temperatures[i] = 0;
        }
        return temperatures;
    }

    /**
     * Approach 2: Monotonic Stack
     * 
     * Intuition:
     * - Use stack to keep track of indices of temperatures waiting for warmer day
     * - When we find a warmer temperature, we can update all cooler temperatures
     *   in the stack at once
     * - Stack maintains temperatures in decreasing order
     * 
     * Time Complexity: O(n)
     * - Single pass through the array
     * - Each index is pushed and popped at most once
     * 
     * Space Complexity: O(n)
     * - Stack can store up to n indices in worst case (decreasing temperatures)
     */
    public static int[] dailyTemperaturesEfficientI(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        
        // Process each temperature
        for (int i = 0; i < temperatures.length; i++) {
            // Update days for all temperatures that found warmer day
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                temperatures[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }

        // Set remaining temperatures to 0 (no warmer day found)
        while (!stack.isEmpty()) {
            temperatures[stack.pop()] = 0;
        }
        return temperatures;
    }

    /**
     * Approach 3: Right-to-Left Monotonic Stack
     * 
     * Intuition:
     * - Process temperatures from right to left
     * - Stack stores indices of warmer temperatures
     * - For each temperature, pop colder or equal temperatures as they can't be
     *   the next warmer day
     * - After popping, stack top (if exists) is the next warmer day
     * - This approach avoids modifying input array
     * 
     * Time Complexity: O(n)
     * - Single right-to-left pass through array
     * - Each index is pushed and popped at most once
     * 
     * Space Complexity: O(n)
     * - Additional array for results: O(n)
     * - Stack can store up to n indices in worst case
     */
    static public int[] dailyTemperaturesEfficientII(int[] temperatures) {
        // Initialize result array with zeros
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[temperatures.length];
        Arrays.fill(out, 0);
        
        // Process temperatures from right to left
        for (int i = temperatures.length - 1; i >= 0; i--) {
            // Remove colder or equal temperatures from stack
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            // If warmer day exists, calculate waiting days
            if(!stack.isEmpty()) out[i] = stack.peek() - i;
            // Add current day to stack
            stack.push(i);
        }

        return out;
    }
}