/**
 * Problem Statement:
 * 
 *     You have `numBottles` full water bottles, and you can exchange `numExchange` empty bottles for one full bottle of water. 
 *     You want to know the total number of full bottles of water you can drink after using all the exchanges.
 * 
 * Input:
 *     - Two integers `numBottles` (1 <= numBottles <= 100), representing the initial number of full water bottles.
 *     - An integer `numExchange` (2 <= numExchange <= 100), representing how many empty bottles are needed to exchange for one full bottle.
 * 
 * Output:
 *     - An integer representing the total number of full bottles of water you can drink.
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

public class j07WaterBottlesI {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int numBottles = in.nextInt(); // Initial number of full bottles
        int numExchange = in.nextInt(); // Number of empty bottles required for 1 full bottle
        System.out.println(numWaterBottles(numBottles, numExchange)); // Output the total number of bottles drank
        in.close();
    }

    /**
     * Approach: We keep drinking full bottles and exchanging empty ones as long as possible.
     * 
     * Intuition:
     * - You start with `numBottles` full bottles.
     * - Each time you drink a bottle, it becomes an empty bottle.
     * - You can exchange `numExchange` empty bottles for 1 more full bottle.
     * - We continue exchanging as long as we have enough empty bottles for the exchange.
     * 
     * Time Complexity:
     * - O(log n), where `n` is the number of full bottles, because the number of empty bottles reduces by at least a factor of `numExchange` in each iteration.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of space for calculations.
     * 
     * @param numBottles The initial number of full bottles.
     * @param numExchange The number of empty bottles needed to exchange for 1 full bottle.
     * @return The total number of bottles you can drink.
     */
    public static int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles; // Initialize the answer with the initial number of full bottles
        while (numBottles / numExchange > 0) {
            // Calculate the number of new full bottles from empty bottles
            ans += numBottles / numExchange;
            // Update the number of remaining full bottles (from exchange) + any remaining
            // empty bottles
            numBottles = numBottles / numExchange + numBottles % numExchange;
        }
        return ans; // Return the total number of bottles drunk
    }

    /**
     * Alternative Approach: Optimized Simulation with Modular Arithmetic
     * 
     * Intuition:
     * - The previous approach works by simulating the process of exchanging bottles. We can also do the same using modular arithmetic to optimize the flow.
     * - Instead of recalculating the new full bottles every time, we use the modulus to track the remaining empty bottles efficiently.
     * 
     * Time Complexity:
     * - O(log n), where `n` is the initial number of full bottles.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used aside from a few variables.
     * 
     * @param numBottles The initial number of full bottles.
     * @param numExchange The number of empty bottles needed to exchange for 1 full bottle.
     * @return The total number of bottles you can drink.
     */
    public static int optimizedNumWaterBottles(int numBottles, int numExchange) {
        int totalBottles = numBottles; // Start with the total number of full bottles
        while (numBottles >= numExchange) {
            int newBottles = numBottles / numExchange;
            totalBottles += newBottles; // Add the number of new full bottles to the total
            numBottles = newBottles + (numBottles % numExchange); // Remaining full bottles after exchange
        }
        return totalBottles; // Return the total number of bottles drunk
    }
}
