/**
 * GeeksForGeeks - Job Sequencing Problem
 * 
 * Problem Statement:
 *     Given a set of N jobs where each job has a deadline and profit associated
 *     with it. Each job takes 1 unit of time to complete and only one job can be
 *     scheduled at a time. Find the maximum profit and number of jobs that can be
 *     done before their deadlines.
 * 
 * Input:
 *     - deadline[]: Array of integers representing deadlines for each job
 *     - profit[]: Array of integers representing profit for each job
 *     - Constraints: 1 ≤ N ≤ 10^5, 1 ≤ deadline[i] ≤ 100, 1 ≤ profit[i] ≤ 500
 * 
 * Output:
 *     - ArrayList containing [number of jobs done, total profit]
 * 
 * Example:
 *     Input: 
 *         deadline[] = {4, 1, 1, 1}
 *         profit[] = {20, 10, 40, 30}
 *     Output: [2, 60]
 * 
 *     Explanation:
 *     Job 1: deadline = 4, profit = 20
 *     Job 2: deadline = 1, profit = 10
 *     Job 3: deadline = 1, profit = 40
 *     Job 4: deadline = 1, profit = 30
 *     
 *     We can do jobs 3 and 1 for maximum profit of 60
 */

import java.util.*;

public class j01JobSequencing {
    /**
     * Approach: Greedy Algorithm using Max-Heap
     * 
     * Intuition:
     * - Sort jobs by profit in descending order
     * - For each job, try to schedule it at the latest possible time before its deadline
     * - This ensures we maximize profit while meeting deadlines
     * 
     * Explanation:
     * 1. Create a max-heap of jobs sorted by profit
     * 2. Find maximum deadline to create time slots
     * 3. For each job in profit order:
     *    - Try to schedule it at the latest possible time before deadline
     *    - If slot is available, schedule job and add profit
     * 4. Return total jobs done and total profit
     * 
     * Time Complexity: O(N log N + N*D) where:
     * - N is number of jobs
     * - D is maximum deadline
     * - Building heap: O(N log N)
     * - Scheduling jobs: O(N*D) in worst case
     * 
     * Space Complexity: O(N + D) where:
     * - N for priority queue
     * - D for time slots array
     * 
     * @param deadline    Array of job deadlines
     * @param profit      Array of job profits
     * @return           ArrayList containing [jobs done, total profit]
     */
    public static ArrayList<Integer> jobSequencingMaxHeap(int[] deadline, int[] profit) {
        // Create max-heap of jobs sorted by profit
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        // Find maximum deadline and add jobs to heap
        int maxDeadline = -1;
        for (int i = 0; i < deadline.length; i++) {
            pq.add(new int[] {
                deadline[i],
                profit[i]
            });
            maxDeadline = Math.max(maxDeadline, deadline[i]);
        }

        // Initialize time slots array
        int[] timeSlots = new int[maxDeadline];

        // Schedule jobs and calculate profit
        int jobs = 0;
        int totalProfit = 0;

        while (!pq.isEmpty()) {
            int[] pair = pq.remove();

            // Try to schedule job at latest possible time
            for (int i = pair[0] - 1; i >= 0; i--) {
                if (timeSlots[i] == 0) {
                    timeSlots[i] = pair[0];
                    jobs++;
                    totalProfit += pair[1];
                    break;
                }
            }
        }

        return new ArrayList<>(Arrays.asList(jobs, totalProfit));
    }

    /**
     * Approach: Greedy with Sorting
     * 
     * Intuition:
     * - The key insight is to prioritize jobs with higher profits
     * - For each job, we try to schedule it at the latest possible time before its deadline
     * - This ensures we don't block earlier time slots that could be used for other jobs
     * 
     * Explanation:
     * 1. Create a 2D array of jobs combining deadline and profit
     * 2. Sort jobs by profit in descending order to prioritize higher paying jobs
     * 3. Find the maximum deadline to determine time slot array size
     * 4. For each job (in profit order):
     *    - Try to schedule it at the latest possible time before its deadline
     *    - If a slot is available, mark it and add profit
     *    - If no slot is available, skip the job
     * 
     * Time Complexity: O(n log n + n*m) where:
     *                  - n is the number of jobs
     *                  - m is the maximum deadline
     *                  - Sorting takes O(n log n)
     *                  - Scheduling each job takes O(m) in worst case
     * 
     * Space Complexity: O(n + m) where:
     *                   - n for storing jobs array
     *                   - m for time slots array
     * 
     * @param deadline  Array of deadlines for each job
     * @param profit    Array of profits for each job
     * @return         ArrayList containing [number of jobs done, total profit]
     */
    public static ArrayList<Integer> jobSequencingSorting(int[] deadline, int[] profit) {
        // Create array of jobs with deadline and profit
        int[][] jobs = new int[deadline.length][2];
        for (int i = 0; i < deadline.length; i++) {
            jobs[i][0] = deadline[i];
            jobs[i][1] = profit[i];
        }
        
        // Sort jobs by profit in descending order
        Arrays.sort(jobs, (a, b) -> b[1] - a[1]);
        
        // Find maximum deadline to determine time slot array size
        int maxDeadline = Arrays.stream(deadline).max().getAsInt();
        
        // Initialize time slots array (0 means slot is available)
        int[] timeSlots = new int[maxDeadline];
        
        int jobsDone = 0;    // Count of successfully scheduled jobs
        int totalProfit = 0; // Total profit from scheduled jobs
        
        // Try to schedule each job in profit order
        for (int[] job : jobs) {
            // Try to schedule at latest possible time before deadline
            for (int i = job[0] - 1; i >= 0; i--) {
                if (timeSlots[i] == 0) {  // If slot is available
                    timeSlots[i] = 1;     // Mark slot as occupied
                    jobsDone++;           // Increment jobs count
                    totalProfit += job[1]; // Add job's profit
                    break;                // Move to next job
                }
            }
        }
        
        return new ArrayList<>(Arrays.asList(jobsDone, totalProfit));
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] deadline1 = {4, 1, 1, 1};
        int[] profit1 = {20, 10, 40, 30};
        System.out.println("Input: deadline = " + Arrays.toString(deadline1) + 
                         ", profit = " + Arrays.toString(profit1));
        System.out.println("Output: " + jobSequencingMaxHeap(deadline1, profit1));

        // Test Case 2: All jobs can be done
        System.out.println("\nAll Jobs Possible Test:");
        int[] deadline2 = {1, 2, 3, 4};
        int[] profit2 = {10, 20, 30, 40};
        System.out.println("Input: deadline = " + Arrays.toString(deadline2) + 
                         ", profit = " + Arrays.toString(profit2));
        System.out.println("Output: " + jobSequencingMaxHeap(deadline2, profit2));

        // Test Case 3: Single job
        System.out.println("\nSingle Job Test:");
        int[] deadline3 = {1};
        int[] profit3 = {100};
        System.out.println("Input: deadline = " + Arrays.toString(deadline3) + 
                         ", profit = " + Arrays.toString(profit3));
        System.out.println("Output: " + jobSequencingSorting(deadline3, profit3));

        // Test Case 4: Equal deadlines
        System.out.println("\nEqual Deadlines Test:");
        int[] deadline4 = {2, 2, 2, 2};
        int[] profit4 = {50, 40, 30, 20};
        System.out.println("Input: deadline = " + Arrays.toString(deadline4) + 
                         ", profit = " + Arrays.toString(profit4));
        System.out.println("Output: " + jobSequencingSorting(deadline4, profit4));
    }
}
