/**
 * LeetCode 826. Most Profit Assigning Work
 * 
 * Problem Statement:
 *     We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit
 *     of the ith job. We have workers: worker[i] is the ability of the ith worker. A worker can
 *     only complete one job with difficulty at most worker[i]. Return the maximum profit we can
 *     make after assigning the workers to the jobs.
 * 
 * Input:
 *     - difficulty (int[]): Array of job difficulties
 *     - profit (int[]): Array of job profits
 *     - worker (int[]): Array of worker abilities
 * 
 * Output:
 *     - int: Maximum total profit possible
 * 
 * Example:
 *     Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 *     Output: 100
 * 
 *     Explanation:
 *     - Worker 4 can do jobs with difficulty <= 4: max profit = 20
 *     - Worker 5 can do jobs with difficulty <= 5: max profit = 20
 *     - Worker 6 can do jobs with difficulty <= 6: max profit = 30
 *     - Worker 7 can do jobs with difficulty <= 7: max profit = 30
 *     - Total profit = 20 + 20 + 30 + 30 = 100
 */

import java.util.Arrays;

public class j09MaxProfitAssigningWork {

    /**
     * Approach: Greedy with Two-Pointer Technique
     * 
     * Intuition:
     * - Sort jobs by difficulty and profit
     * - Sort workers by ability
     * - For each worker, find the maximum profit job they can do
     * - Keep track of maximum profit seen so far for current difficulty level
     * 
     * Explanation:
     * - Step 1: Combine difficulty and profit into job pairs
     * - Step 2: Sort jobs by difficulty (ascending) and profit (descending)
     * - Step 3: Sort workers by ability
     * - Step 4: For each worker, find maximum profit job they can do
     * - Step 5: Add maximum profit to total
     * 
     * Time Complexity: O(n log n + m log m) where n is number of jobs and m is number of workers
     *                  - O(n log n) for sorting jobs
     *                  - O(m log m) for sorting workers
     * Space Complexity: O(n) for storing job pairs
     * 
     * @param difficulty    Array of job difficulties
     * @param profit        Array of job profits
     * @param worker        Array of worker abilities
     * @return             Maximum total profit possible
     */
    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        // Combine difficulty and profit into job pairs
        int[][] jobs = new int[difficulty.length][2];
        for(int i = 0; i < difficulty.length; i++){
            jobs[i] = new int[]{difficulty[i], profit[i]};
        }

        // Sort jobs by difficulty (ascending) and profit (descending)
        Arrays.sort(jobs,(a,b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            else return b[1] - a[1];
        });

        // Sort workers by ability
        Arrays.sort(worker);

        // Calculate maximum profit for each worker
        int totalProfit = 0;
        int maxProfit = 0;
        int j = 0;
        for(int i = 0; i < worker.length; i++){
            // Find maximum profit job for current worker
            while(j < jobs.length && jobs[j][0] <= worker[i]){
                maxProfit = Math.max(maxProfit, jobs[j][1]);
                j++;
            }
            totalProfit += maxProfit;
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] difficulty1 = {2,4,6,8,10};
        int[] profit1 = {10,20,30,40,50};
        int[] worker1 = {4,5,6,7};
        System.out.println("Input: difficulty = " + Arrays.toString(difficulty1));
        System.out.println("       profit = " + Arrays.toString(profit1));
        System.out.println("       worker = " + Arrays.toString(worker1));
        System.out.println("Expected: 100, Output: " + 
            maxProfitAssignment(difficulty1, profit1, worker1));

        // Test Case 2: All workers can do all jobs
        System.out.println("\nSpecial Case - All Workers Can Do All Jobs:");
        int[] difficulty2 = {1,2,3};
        int[] profit2 = {10,20,30};
        int[] worker2 = {3,3,3};
        System.out.println("Input: difficulty = " + Arrays.toString(difficulty2));
        System.out.println("       profit = " + Arrays.toString(profit2));
        System.out.println("       worker = " + Arrays.toString(worker2));
        System.out.println("Expected: 90, Output: " + 
            maxProfitAssignment(difficulty2, profit2, worker2));

        // Test Case 3: No worker can do any job
        System.out.println("\nSpecial Case - No Worker Can Do Any Job:");
        int[] difficulty3 = {5,6,7};
        int[] profit3 = {10,20,30};
        int[] worker3 = {1,2,3};
        System.out.println("Input: difficulty = " + Arrays.toString(difficulty3));
        System.out.println("       profit = " + Arrays.toString(profit3));
        System.out.println("       worker = " + Arrays.toString(worker3));
        System.out.println("Expected: 0, Output: " + 
            maxProfitAssignment(difficulty3, profit3, worker3));

        // Test Case 4: Single worker and job
        System.out.println("\nEdge Case - Single Worker and Job:");
        int[] difficulty4 = {5};
        int[] profit4 = {10};
        int[] worker4 = {5};
        System.out.println("Input: difficulty = " + Arrays.toString(difficulty4));
        System.out.println("       profit = " + Arrays.toString(profit4));
        System.out.println("       worker = " + Arrays.toString(worker4));
        System.out.println("Expected: 10, Output: " + 
            maxProfitAssignment(difficulty4, profit4, worker4));

        // Test Case 5: Same difficulty different profits
        System.out.println("\nSpecial Case - Same Difficulty Different Profits:");
        int[] difficulty5 = {5,5,5};
        int[] profit5 = {10,20,30};
        int[] worker5 = {5,5,5};
        System.out.println("Input: difficulty = " + Arrays.toString(difficulty5));
        System.out.println("       profit = " + Arrays.toString(profit5));
        System.out.println("       worker = " + Arrays.toString(worker5));
        System.out.println("Expected: 90, Output: " + 
            maxProfitAssignment(difficulty5, profit5, worker5));
    }
}
