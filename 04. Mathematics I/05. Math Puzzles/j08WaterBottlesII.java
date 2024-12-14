/*-
 * Problem Statement:
 * 
 *     You have `numBottles` full water bottles, and you can exchange `numExchange` empty bottles for one full bottle of water.
 *     Every time you exchange empty bottles, you receive 1 full bottle in return.
 *     The goal is to calculate the maximum number of full bottles of water you can drink, considering that each exchange gives you one more bottle.
 * 
 * Input:
 *     - Two integers `numBottles` (1 <= numBottles <= 100), representing the initial number of full water bottles.
 *     - An integer `numExchange` (2 <= numExchange <= 100), representing how many empty bottles are required to exchange for one full bottle.
 * 
 * Output:
 *     - An integer representing the maximum number of full bottles of water you can drink.
 * 
 * Example:
 *     Input:
 *     9 3
 *     Output:
 *     13
 * 
 *     Explanation:
 *     - Initially, you have 9 full bottles of water.
 *     - You can exchange 3 empty bottles for 1 new full bottle, so you drink 3 more bottles.
 *     - Now, you have 9 - 3 (exchanged) + 3 (new) = 9 empty bottles.
 *     - You can again exchange 3 empty bottles for 1 full bottle, so you drink 1 more.
 *     - After drinking that bottle, you have 7 empty bottles, which lets you exchange 3 again for another full bottle.
 *     - This process continues until there are no more full bottles to drink.
 */

import java.util.Scanner;

public class j08WaterBottlesII {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int numBottles = in.nextInt(); // Initial number of full bottles
        int numExchange = in.nextInt(); // Number of empty bottles required for 1 full bottle
        System.out.println(maxBottlesDrunk(numBottles, numExchange)); // Output the total number of bottles drank
        System.out.println(optimizedMaxBottlesDrunk(numBottles, numExchange)); // Output the total number of bottles drank
        in.close();
    }

    /*-
     * Approach: Keep exchanging full bottles as long as we have enough empty bottles for the exchange.
     * 
     * Intuition:
     * - You start with `numBottles` full bottles.
     * - Every time you drink a full bottle, it turns into an empty bottle.
     * - You can exchange `numExchange` empty bottles for 1 full bottle, and each time you make an exchange, the number of empty bottles increases by 1 (as you get one new full bottle).
     * 
     * Time Complexity:
     * - O(log n), where `n` is the number of full bottles, because the number of empty bottles and full bottles changes in each iteration.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of space for calculations.
     * 
     * @param numBottles The initial number of full bottles.
     * @param numExchange The number of empty bottles needed to exchange for 1 full bottle.
     * @return The total number of bottles you can drink.
     */
    public static int maxBottlesDrunk(int numBottles, int numExchange) {
        int ans = 0; // Variable to hold the total number of bottles drunk
        // Keep exchanging full bottles as long as we have enough empty bottles
        while (numBottles - numExchange >= 0) {
            ans += numExchange; // Add the exchanged full bottles to the total
            numBottles = numBottles - numExchange + 1; // Update the remaining full bottles (after exchange)
            numExchange += 1; // Increment the number of bottles needed for the next exchange
        }
        ans += numBottles; // Add the remaining full bottles that can be drunk directly
        return ans; // Return the total number of bottles drunk
    }

    /*-
     * Alternative Approach: Optimized simulation with a more efficient exchange model
     * 
     * Intuition:
     * - Similar to the previous approach, we continue exchanging bottles until we can no longer do so.
     * - However, we can improve the flow by simplifying the calculation logic within the loop.
     * - The method focuses on the total number of bottles in each exchange cycle and ensures we keep track of the remaining full bottles.
     * 
     * Time Complexity:
     * - O(log n), where `n` is the number of full bottles.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used aside from a few variables.
     * 
     * @param numBottles The initial number of full bottles.
     * @param numExchange The number of empty bottles needed to exchange for 1 full bottle.
     * @return The total number of bottles you can drink.
     */
    public static int optimizedMaxBottlesDrunk(int numBottles, int numExchange) {
        int totalBottles = 0; // Total bottles drunk
        while (numBottles >= numExchange) {
            totalBottles += numExchange; // Add the exchanged full bottles to the total
            numBottles -= numExchange; // Calculate remaining full bottles
            numBottles++; // increment by one after exchanged
            numExchange++; // Increment the number of bottles required for the next exchange
        }
        totalBottles += numBottles;
        return totalBottles; // Return the total bottles drunk
    }
}
