/**
 * GeeksForGeeks - Activity Selection
 * 
 * Problem Statement:
 *     Given N activities with their start and finish times. Select the maximum 
 *     number of activities that can be performed by a single person, assuming 
 *     that a person can only work on a single activity at a time.
 * 
 * Input:
 *     - start[] (int[]): Array of start times of activities
 *     - finish[] (int[]): Array of finish times of activities
 * 
 * Output:
 *     - Maximum number of activities that can be performed
 * 
 * Example:
 *     Input: start[] = {1, 3, 0, 5, 8, 5}, finish[] = {2, 4, 6, 7, 9, 9}
 *     Output: 4
 * 
 *     Explanation:
 *     - Activity 1: [1,2]
 *     - Activity 2: [3,4]
 *     - Activity 3: [5,7]
 *     - Activity 4: [8,9]
 *     - Total activities = 4
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class j05ActivitySelection {
    
    /**
     * Approach 1: Sorting with Custom Comparator
     * 
     * Intuition:
     * - To maximize the number of activities, we should always pick the activity
     *   that ends earliest among the remaining activities
     * - This ensures we have maximum time left for other activities
     * - Sort activities by finish time while maintaining original indices
     * 
     * Explanation:
     * 1. Create list of indices and sort based on finish times
     * 2. Keep track of last finish time
     * 3. For each activity:
     *    - If current activity starts after last finish time
     *      * Select this activity
     *      * Update last finish time
     *      * Increment count
     * 
     * Time Complexity: O(N log N) where N is number of activities
     *                  - Sorting: O(N log N)
     *                  - Single pass: O(N)
     * Space Complexity: O(N) for storing indices
     * 
     * @param start     Array of start times
     * @param finish    Array of finish times
     * @return         Maximum number of activities that can be performed
     */
    public static int activitySelectionSorting(int[] start, int[] finish) {
        // Create and sort indices based on finish times
        ArrayList<Integer> index = new ArrayList<>();
        for(int i = 0; i < start.length; i++) index.add(i);
        Collections.sort(index,(a,b) -> finish[a] - finish[b]);
        
        int ans = 0;          // Count of selected activities
        int lastFinish = -1;  // Finish time of last selected activity
        
        // Process activities in order of finish time
        for(int i = 0; i < start.length; i++){
            int idx = index.get(i);
            if(start[idx] > lastFinish){
                lastFinish = finish[idx];
                ans++;
            }
        }
        return ans;
    }

    /**
     * Approach 2: Priority Queue
     * 
     * Intuition:
     * - Similar to Approach 1, but using a min heap to always get the activity
     *   with earliest finish time
     * - More memory efficient as we don't need to store indices
     * - Better for streaming data where activities come one by one
     * 
     * Explanation:
     * 1. Create min heap based on finish times
     * 2. Add all activities to heap
     * 3. While heap is not empty:
     *    - Get activity with earliest finish time
     *    - If it starts after last finish time
     *      * Select this activity
     *      * Update last finish time
     *      * Increment count
     * 
     * Time Complexity: O(N log N) where N is number of activities
     *                  - Building heap: O(N)
     *                  - Each heap operation: O(log N)
     * Space Complexity: O(N) for heap
     * 
     * @param start     Array of start times
     * @param finish    Array of finish times
     * @return         Maximum number of activities that can be performed
     */
    public static int activitySelectionPriorityQueue(int[] start, int[] finish) {
        // Create min heap based on finish times
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        
        // Add all activities to heap
        for (int i = 0; i < start.length; i++) {
            pq.add(new int[] { start[i], finish[i] });
        }
        
        int ans = 0;          // Count of selected activities
        int lastFinish = -1;  // Finish time of last selected activity
        
        // Process activities in order of finish time
        while (!pq.isEmpty()) {
            int[] activity = pq.remove();
            if (activity[0] > lastFinish) {
                lastFinish = activity[1];
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] start1 = {1, 3, 0, 5, 8, 5};
        int[] finish1 = {2, 4, 6, 7, 9, 9};
        System.out.println("Input: start=" + java.util.Arrays.toString(start1) + 
                         ", finish=" + java.util.Arrays.toString(finish1));
        System.out.println("Output (Sorting): " + activitySelectionSorting(start1, finish1));
        System.out.println("Output (PQ): " + activitySelectionPriorityQueue(start1, finish1));

        // Test Case 2: All activities overlap
        System.out.println("\nAll Activities Overlap:");
        int[] start2 = {1, 1, 1, 1};
        int[] finish2 = {2, 2, 2, 2};
        System.out.println("Input: start=" + java.util.Arrays.toString(start2) + 
                         ", finish=" + java.util.Arrays.toString(finish2));
        System.out.println("Output (Sorting): " + activitySelectionSorting(start2, finish2));
        System.out.println("Output (PQ): " + activitySelectionPriorityQueue(start2, finish2));

        // Test Case 3: No activities
        System.out.println("\nNo Activities:");
        int[] start3 = {};
        int[] finish3 = {};
        System.out.println("Input: start=" + java.util.Arrays.toString(start3) + 
                         ", finish=" + java.util.Arrays.toString(finish3));
        System.out.println("Output (Sorting): " + activitySelectionSorting(start3, finish3));
        System.out.println("Output (PQ): " + activitySelectionPriorityQueue(start3, finish3));

        // Test Case 4: Activities in reverse order
        System.out.println("\nActivities in Reverse Order:");
        int[] start4 = {9, 8, 7, 6};
        int[] finish4 = {10, 9, 8, 7};
        System.out.println("Input: start=" + java.util.Arrays.toString(start4) + 
                         ", finish=" + java.util.Arrays.toString(finish4));
        System.out.println("Output (Sorting): " + activitySelectionSorting(start4, finish4));
        System.out.println("Output (PQ): " + activitySelectionPriorityQueue(start4, finish4));
    }
}
