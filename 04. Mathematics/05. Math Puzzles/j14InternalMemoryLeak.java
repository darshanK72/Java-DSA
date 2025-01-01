/**
 * Problem Statement:
 * 
 *     You are given two integers: `memory1` and `memory2`, representing two different memory segments in a system. At each step, the system consumes a certain amount of memory, starting with 1 unit. After each consumption, the amount of memory that is consumed increases by 1 for the next step.
 * 
 *     The system will keep consuming memory from the larger memory segment (either `memory1` or `memory2`). If both segments have the same amount of memory, it will consume from `memory1` first. The process continues until neither segment can provide the required memory for the next step.
 * 
 *     The task is to determine after how many steps the memory consumption stops, and what the remaining memory in each segment is.
 * 
 * Input:
 *     - An integer `memory1` (1 <= memory1 <= 10^4), the amount of memory in the first segment.
 *     - An integer `memory2` (1 <= memory2 <= 10^4), the amount of memory in the second segment.
 * 
 * Output:
 *     - An array of three integers: the total number of steps taken, the remaining memory in `memory1`, and the remaining memory in `memory2`.
 * 
 * Example:
 *     Input:
 *     8 11
 *     Output:
 *     [6, 0, 6]
 * 
 *     Explanation:
 *     - Step 1: memory1 = 8, memory2 = 11, consume 1 unit from memory1 → memory1 = 7, memory2 = 11
 *     - Step 2: memory1 = 7, memory2 = 11, consume 2 units from memory1 → memory1 = 5, memory2 = 11
 *     - Step 3: memory1 = 5, memory2 = 11, consume 3 units from memory1 → memory1 = 2, memory2 = 11
 *     - Step 4: memory1 = 2, memory2 = 11, consume 4 units from memory2 → memory1 = 2, memory2 = 7
 *     - Step 5: memory1 = 2, memory2 = 7, consume 5 units from memory2 → memory1 = 2, memory2 = 2
 *     - Step 6: memory1 = 2, memory2 = 2, consume 6 units from memory1 → memory1 = 0, memory2 = 2
 *     After 6 steps, memory1 is exhausted, and memory2 has 6 units left.
 */

import java.util.Scanner;

public class j14InternalMemoryLeak {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int memory1 = in.nextInt(); // Input: the initial amount of memory in the first segment
        int memory2 = in.nextInt(); // Input: the initial amount of memory in the second segment
        System.out.println(memLeak(memory1, memory2)); // Output the result of the memory leak
        in.close();
    }

    /**
     * Approach: Simulation of Memory Consumption
     * 
     * Intuition:
     * - Start by consuming 1 unit of memory in each step and increment the consumption in each subsequent step.
     * - In each step, consume memory from the segment that has the larger amount of memory.
     * - Continue until neither segment can provide enough memory for the next step.
     * 
     * Time Complexity:
     * - O(n), where n is the total number of steps until the memory consumption stops. This depends on the initial memory sizes and grows incrementally with each step.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of extra space for variables.
     * 
     * @param memory1 The initial amount of memory in the first segment.
     * @param memory2 The initial amount of memory in the second segment.
     * @return An array of three integers: the number of steps taken, the remaining memory in the first segment, and the remaining memory in the second segment.
     */
    public static int[] memLeak(int memory1, int memory2) {
        int crash = 1; // Initialize the memory consumption step
        // Continue until neither memory segment has enough memory for the next step
        while (memory1 > 0 || memory2 > 0) {
            if (memory1 < crash && memory2 < crash) {
                break; // Stop if neither memory segment can provide the required memory
            }
            // Consume memory from the larger memory segment
            if (memory1 >= memory2) {
                memory1 -= crash; // Consume from memory1
            } else {
                memory2 -= crash; // Consume from memory2
            }
            crash++; // Increment the memory consumption for the next step
        }
        return new int[] { crash, memory1, memory2 }; // Return the result
    }
}
