/**
 * LeetCode 134. Gas Station
 * 
 * Problem Statement:
 *     There are n gas stations along a circular route, where the amount of gas at the ith
 *     station is gas[i]. You have a car with an unlimited gas tank and it costs cost[i] of
 *     gas to travel from the ith station to its next (i + 1)th station. You begin the
 *     journey with an empty tank at one of the gas stations. Given two integer arrays gas
 *     and cost, return the starting gas station's index if you can travel around the
 *     circuit once in the clockwise direction, otherwise return -1.
 * 
 * Input:
 *     - gas[] (1 <= gas.length <= 10^5, 0 <= gas[i] <= 10^4)
 *     - cost[] (cost.length == gas.length, 0 <= cost[i] <= 10^4)
 * 
 * Output:
 *     - Return the starting gas station's index if you can travel around the circuit
 *     - Return -1 if it's not possible
 * 
 * Example:
 *     Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 *     Output: 3
 * 
 *     Explanation:
 *     Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 *     Travel to station 4. Your tank = 4 - 1 + 5 = 8
 *     Travel to station 0. Your tank = 8 - 2 + 1 = 7
 *     Travel to station 1. Your tank = 7 - 3 + 2 = 6
 *     Travel to station 2. Your tank = 6 - 4 + 3 = 5
 *     Travel to station 3. The cost is 5. Your gas is just enough to travel back to
 *     station 3. Therefore, return 3 as the starting index.
 */


public class j06GasStations {

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - Try each station as a starting point
     * - For each starting point, simulate the journey around the circuit
     * - If we can complete the circuit from a starting point, return that index
     * 
     * Explanation:
     * - Step 1: Iterate through each station as potential starting point
     * - Step 2: Skip stations where initial gas is less than cost
     * - Step 3: Simulate journey by adding gas and subtracting cost at each station
     * - Step 4: If we can complete the circuit, return the starting index
     * 
     * Time Complexity: O(n²) where n is the number of stations
     * Space Complexity: O(1) as we only use a constant amount of extra space
     * 
     * @param gas    Array representing amount of gas at each station
     * @param cost   Array representing cost to travel to next station
     * @return       Starting station index if possible, -1 otherwise
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for(int i = 0; i < n; i++){
            if(gas[i] < cost[i]) continue;
            int j = 0;
            int myGas = 0;
            while(j < n){
                myGas += gas[(i + j) % n];
                if(myGas < cost[(i + j) % n]){
                    break;
                }
                myGas -= cost[(i + j) % n];
                j++;
            }
            if(j == n) return i;
        }   

        return -1;
    }

    /**
     * Approach 2: Greedy
     * 
     * Intuition:
     * - If total gas is less than total cost, it's impossible to complete the circuit
     * - If we can't reach station j from station i, we can't reach j from any station
     *   between i and j
     * - Keep track of total gas and reset when it becomes negative
     * 
     * Explanation:
     * - Step 1: Check if total gas is sufficient for total cost
     * - Step 2: Keep track of running sum of (gas - cost)
     * - Step 3: If running sum becomes negative, reset and try next station
     * - Step 4: The last station where we reset is our answer
     * 
     * Time Complexity: O(n) where n is the number of stations
     * Space Complexity: O(1) as we only use a constant amount of extra space
     * 
     * @param gas    Array representing amount of gas at each station
     * @param cost   Array representing cost to travel to next station
     * @return       Starting station index if possible, -1 otherwise
     */
    public static int canCompleteCircuitEfficnent(int[] gas, int[] cost) {
        int myGas = 0;
        int myCost = 0;
        for(int i = 0; i < gas.length; i++){
            myGas += gas[i];
            myCost += cost[i];
        }

        if(myGas < myCost) return -1;

        int totalGas = 0;
        int ans = 0;
        for(int i = 0; i < gas.length; i++){
            totalGas += (gas[i] - cost[i]);
            if(totalGas < 0){
                totalGas = 0;
                ans = i + 1;
            } 
        }

        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println("Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]");
        System.out.println("Expected: 3, Output: " + canCompleteCircuitEfficnent(gas1, cost1));

        // Test Case 2: Impossible case
        System.out.println("\nImpossible Case:");
        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println("Input: gas = [2,3,4], cost = [3,4,3]");
        System.out.println("Expected: -1, Output: " + canCompleteCircuitEfficnent(gas2, cost2));

        // Test Case 3: Single station
        System.out.println("\nSingle Station Case:");
        int[] gas3 = {4};
        int[] cost3 = {5};
        System.out.println("Input: gas = [4], cost = [5]");
        System.out.println("Expected: -1, Output: " + canCompleteCircuitEfficnent(gas3, cost3));

        // Test Case 4: Equal gas and cost
        System.out.println("\nEqual Gas and Cost Case:");
        int[] gas4 = {1, 1, 1, 1};
        int[] cost4 = {1, 1, 1, 1};
        System.out.println("Input: gas = [1,1,1,1], cost = [1,1,1,1]");
        System.out.println("Expected: 0, Output: " + canCompleteCircuitEfficnent(gas4, cost4));
    }
}
