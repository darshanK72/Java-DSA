/**
 * LeetCode 1834 - Single-Threaded CPU
 * 
 * Problem Statement:
 *     You are given n tasks labeled from 0 to n-1 represented by a 2D integer array
 *     tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the ith
 *     task will be available to process at enqueueTimei and will take processingTimei
 *     to finish processing. You have a single-threaded CPU that can process at most
 *     one task at a time and will act in the following way:
 *     1. If the CPU is idle and there are no available tasks, the CPU remains idle
 *     2. If the CPU is idle and there are available tasks, it picks the task with
 *        the smallest processing time. If multiple tasks have same processing time,
 *        it picks the one with smallest index
 *     3. Once a task is started, the CPU will process the entire task without
 *        stopping
 *     4. The CPU can finish a task then start a new one instantly
 * 
 * Input:
 *     - tasks[][]: 2D array where tasks[i] = [enqueueTimei, processingTimei]
 *     - Constraints: 1 ≤ n ≤ 10^5, 1 ≤ enqueueTimei, processingTimei ≤ 10^9
 * 
 * Output:
 *     - Array containing the order in which tasks are processed
 * 
 * Example:
 *     Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
 *     Output: [0,2,3,1]
 * 
 *     Explanation:
 *     Time 1: Task 0 is available, process it
 *     Time 3: Task 0 finishes, Task 2 is available, process it
 *     Time 5: Task 2 finishes, Task 3 is available, process it
 *     Time 6: Task 3 finishes, Task 1 is available, process it
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class j02SingleThradedCUP {
    /**
     * Approach: Greedy Algorithm using Min-Heap
     * 
     * Intuition:
     * - Sort tasks by enqueue time to process them in chronological order
     * - Use min-heap to always pick the task with smallest processing time
     * - When CPU is idle, jump to next available task's enqueue time
     * 
     * Explanation:
     * 1. Create indexed tasks array to maintain original indices
     * 2. Sort tasks by enqueue time and processing time
     * 3. Use min-heap to store available tasks sorted by:
     *    - Processing time (primary)
     *    - Task index (secondary)
     * 4. Process tasks:
     *    - Add all available tasks to heap
     *    - If heap is empty, jump to next task's enqueue time
     *    - Process task with smallest processing time
     * 
     * Time Complexity: O(N log N) where N is number of tasks
     * - Sorting tasks: O(N log N)
     * - Heap operations: O(N log N)
     * 
     * Space Complexity: O(N)
     * - Indexed tasks array: O(N)
     * - Priority queue: O(N)
     * - Output array: O(N)
     * 
     * @param tasks    2D array of tasks [enqueueTime, processingTime]
     * @return        Array of task indices in processing order
     */
    public static int[] getOrder(int[][] tasks) {
        // Create indexed tasks array to maintain original indices
        int[][] indexedTasks = new int[tasks.length][3];
        for(int i = 0; i < tasks.length; i++){
            indexedTasks[i] = new int[]{
                tasks[i][0],  // enqueue time
                tasks[i][1],  // processing time
                i            // original index
            };
        }

        // Sort tasks by enqueue time and processing time
        Arrays.sort(indexedTasks,(a,b) -> {
            if(a[0] != b[0]) return a[0] - b[0];  // sort by enqueue time
            return a[1] - b[1];                   // then by processing time
        });

        // Create min-heap for available tasks
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if(a[1] != b[1]) return a[1] - b[1];  // sort by processing time
            return a[2] - b[2];                   // then by task index
        });

        // Initialize output array and tracking variables
        int[] out = new int[tasks.length];
        int outIndex = 0;
        int taskIndex = 0;
        long timeline = 0;

        // Process all tasks
        while(outIndex < tasks.length) {
            // Add all available tasks to heap
            while(taskIndex < tasks.length && indexedTasks[taskIndex][0] <= timeline) {
                pq.add(indexedTasks[taskIndex++]);
            }
            
            // If no tasks available, jump to next task's enqueue time
            if(pq.isEmpty()) {
                timeline = indexedTasks[taskIndex][0];
                continue;
            }
            
            // Process task with smallest processing time
            int[] task = pq.poll();
            out[outIndex++] = task[2];
            timeline += task[1];
        }

        return out;
    }

    public static void main(String[] args) {
         // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[][] tasks1 = {{1,2}, {2,4}, {3,2}, {4,1}};
        System.out.println("Input: " + Arrays.deepToString(tasks1));
        System.out.println("Output: " + Arrays.toString(getOrder(tasks1)));

        // Test Case 2: All tasks available at start
        System.out.println("\nAll Tasks Available Test:");
        int[][] tasks2 = {{1,1}, {1,2}, {1,3}, {1,4}};
        System.out.println("Input: " + Arrays.deepToString(tasks2));
        System.out.println("Output: " + Arrays.toString(getOrder(tasks2)));

        // Test Case 3: Single task
        System.out.println("\nSingle Task Test:");
        int[][] tasks3 = {{1,1}};
        System.out.println("Input: " + Arrays.deepToString(tasks3));
        System.out.println("Output: " + Arrays.toString(getOrder(tasks3)));

        // Test Case 4: Tasks with same processing time
        System.out.println("\nSame Processing Time Test:");
        int[][] tasks4 = {{1,2}, {2,2}, {3,2}, {4,2}};
        System.out.println("Input: " + Arrays.deepToString(tasks4));
        System.out.println("Output: " + Arrays.toString(getOrder(tasks4)));
    }
}
