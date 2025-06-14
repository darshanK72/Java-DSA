/**
 * LeetCode 1029. Two City Scheduling
 * 
 * Problem Statement:
 *     A company is planning to interview 2n people. Given the array costs where
 *     costs[i] = [aCosti, bCosti], the cost of flying the ith person to city A
 *     is aCosti, and the cost of flying the ith person to city B is bCosti.
 *     Return the minimum cost to fly every person to a city such that exactly n
 *     people arrive in each city.
 * 
 * Input:
 *     - costs (2D array): Array of costs where costs[i] = [aCosti, bCosti]
 *     - 1 <= costs.length <= 100
 *     - costs.length is even
 *     - 1 <= aCosti, bCosti <= 1000
 * 
 * Output:
 *     - Minimum total cost to fly exactly n people to each city
 * 
 * Example:
 *     Input: costs = [[10,20],[30,200],[400,50],[30,20]]
 *     Output: 110
 * 
 *     Explanation:
 *     The first person goes to city A for a cost of 10.
 *     The second person goes to city A for a cost of 30.
 *     The third person goes to city B for a cost of 50.
 *     The fourth person goes to city B for a cost of 20.
 *     The total minimum cost is 10 + 30 + 50 + 20 = 110.
 */

import java.util.Arrays;

public class j01TwoCityScheduling {
    /**
     * Approach: Greedy with Custom Sorting
     * 
     * Intuition:
     * - For each person, we need to decide whether to send them to city A or B
     * - The key insight is to sort people based on the cost difference between
     *   cities A and B
     * - If (costA - costB) is negative, it's cheaper to send to city A
     * - If (costA - costB) is positive, it's cheaper to send to city B
     * - We send first n people with lowest (costA - costB) to city A
     * - We send remaining n people to city B
     * 
     * Explanation:
     * 1. Sort the costs array based on the difference between costs[0] and costs[1]
     * 2. For first n people (smallest differences), send to city A
     * 3. For remaining n people, send to city B
     * 4. Sum up all costs to get minimum total cost
     * 
     * Time Complexity: O(n log n) where n is the length of costs array
     *                  Due to sorting operation
     * Space Complexity: O(1) as we only use constant extra space
     * 
     * @param costs    2D array where costs[i] = [aCosti, bCosti]
     * @return        Minimum total cost to fly exactly n people to each city
     */
    public int twoCitySchedCost(int[][] costs) {
        // Sort costs based on the difference between city A and B costs
        Arrays.sort(costs, (a, b) -> {
            return (a[0] - a[1]) - (b[0] - b[1]);
        });

        int n = costs.length / 2;
        int cost = 0;
        
        // Send first n people to city A and remaining to city B
        for (int i = 0; i < n; i++) {
            cost += costs[i][0];           // Cost for city A
            cost += costs[2 * n - i - 1][1]; // Cost for city B
        }

        return cost;
    }

    public static void main(String[] args) {
        j01TwoCityScheduling solution = new j01TwoCityScheduling();

        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[][] costs1 = {{10,20}, {30,200}, {400,50}, {30,20}};
        System.out.println("Input: " + Arrays.deepToString(costs1));
        System.out.println("Expected: 110, Output: " + solution.twoCitySchedCost(costs1));

        // Test Case 2: Equal costs
        System.out.println("\nEqual Costs Test Case:");
        int[][] costs2 = {{10,10}, {20,20}, {30,30}, {40,40}};
        System.out.println("Input: " + Arrays.deepToString(costs2));
        System.out.println("Expected: 60, Output: " + solution.twoCitySchedCost(costs2));

        // Test Case 3: Minimum possible costs
        System.out.println("\nMinimum Costs Test Case:");
        int[][] costs3 = {{1,1}, {1,1}, {1,1}, {1,1}};
        System.out.println("Input: " + Arrays.deepToString(costs3));
        System.out.println("Expected: 4, Output: " + solution.twoCitySchedCost(costs3));
    }
}