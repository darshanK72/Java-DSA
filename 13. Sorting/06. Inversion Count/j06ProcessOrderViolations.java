/*-
 * Problem Statement:
 * 
 * You are given two arrays: `processingOrder` and `executionOrder`. The first array,
 * `processingOrder`, represents the order in which processes should be executed, 
 * and the second array, `executionOrder`, represents the actual order in which 
 * the processes were executed. 
 * 
 * Your task is to find how many "violations" occurred, where a violation is defined
 * as a process being executed before a process that should have been executed before it,
 * based on the `processingOrder`.
 * 
 * Input:
 *     - An integer array `processingOrder` of size n (1 <= n <= 10^4).
 *     - An integer array `executionOrder` of size n (1 <= n <= 10^4).
 * 
 * Output:
 *     - An integer representing the number of violations.
 * 
 * Example:
 *     Input:
 *         processingOrder = [3, 5, 4, 2, 1]
 *         executionOrder = [4, 3, 5, 1, 2]
 *     Output:
 *         2
 * 
 *     Explanation:
 *         In this case:
 *         - Process 4 is executed before process 3, violating the order.
 *         - Process 1 is executed before process 2, violating the order.
 */

import java.util.HashMap;
import java.util.Map;

public class j06ProcessOrderViolations {

    public static void main(String[] args) {
        // Test data
        int[] processingOrder = { 3, 5, 4, 2, 1 };
        int[] executionOrder = { 4, 3, 5, 1, 2 };

        // Call the function to count violations
        System.out.println("Number of Violations (Brute Force): " + countViolations(processingOrder, executionOrder));

        // Call the efficient solution to count violations
        System.out.println(
                "Number of Violations (Efficient): " + countViolationsEfficient(processingOrder, executionOrder));
    }

    /*-
     * Approach 1: Brute Force Solution
     * 
     * Intuition:
     * - In this approach, we traverse through the `processingOrder` array. For each
     *   process, we check if it appears after it’s supposed to be processed in the 
     *   `executionOrder`. If any process is found out of order, we count it as a violation.
     * - This approach involves a nested loop where we search the `executionOrder` from the
     *   last processed index (using variable `s`), so that we can check if the processes
     *   appear in the correct order.
     * 
     * Explanation:
     * - For each process in the `processingOrder` array, we search the `executionOrder`
     *   array starting from the last matched index. If the process is not found after the
     *   last matched position, it’s counted as a violation.
     * - The variable `s` is used to keep track of the last matched index in `executionOrder`
     *   to ensure we only look for subsequent positions.
     * 
     * Time Complexity:
     * - O(n^2), where n is the length of the input arrays. This is due to the nested loop 
     *   to find each element in the execution order.
     * 
     * Space Complexity:
     * - O(1), since we only use a constant amount of space for variables like `s` and `ans`.
     * 
     * @param processingOrder The intended processing order of the processes.
     * @param executionOrder The actual execution order of the processes.
     * @return The number of violations in the execution order.
     */
    public static int countViolations(int[] processingOrder, int[] executionOrder) {
        int ans = 0;
        int s = 0;

        for (int i = 0; i < processingOrder.length; i++) {
            boolean found = false;

            for (int j = s; j < executionOrder.length; j++) {
                if (processingOrder[i] == executionOrder[j]) {
                    s = j + 1; // Update 's' to continue from the next position
                    found = true;
                    break;
                }
            }

            // If the process in `processingOrder` wasn't found in order, count it as a
            // violation
            if (!found) {
                ans++;
            }
        }

        return ans;
    }

    /*-
     * Approach 2: Efficient Solution Using HashMap
     * 
     * Intuition:
     * - This approach leverages a HashMap to quickly access the intended positions
     *   of processes from the `processingOrder`. We then iterate over the `executionOrder`
     *   and check for violations by keeping track of the highest index we’ve seen so far.
     * - If a process is executed at an index lower than the highest index we’ve encountered,
     *   that means it's out of order, and we count that as a violation.
     * 
     * Explanation:
     * - First, we map each process in `processingOrder` to its intended position using a HashMap.
     * - Then, we traverse through `executionOrder`. For each process, we check if its intended 
     *   index is less than the highest index encountered so far (i.e., a violation occurs).
     *   If no violation occurs, we update the highest index to the current process’s intended index.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the input arrays. The HashMap lookup and iteration over 
     *   the `executionOrder` array each take O(n) time.
     * 
     * Space Complexity:
     * - O(n), for storing the process-to-index mapping in the HashMap.
     * 
     * @param processingOrder The intended processing order of the processes.
     * @param executionOrder The actual execution order of the processes.
     * @return The number of violations in the execution order.
     */
    public static int countViolationsEfficient(int[] processingOrder, int[] executionOrder) {
        // Step 1: Map each process to its index in the processingOrder
        Map<Integer, Integer> processIndexMap = new HashMap<>();
        for (int i = 0; i < processingOrder.length; i++) {
            processIndexMap.put(processingOrder[i], i);
        }

        int violations = 0;
        int maxIndexSeen = -1;

        // Step 2: Check for violations in executionOrder
        for (int process : executionOrder) {
            int currentIndex = processIndexMap.get(process); // Retrieve intended position

            // If the current index is less than maxIndexSeen, it's a violation
            if (currentIndex < maxIndexSeen) {
                violations++;
            } else {
                maxIndexSeen = currentIndex; // Update max index if in correct order
            }
        }

        return violations;
    }
}
