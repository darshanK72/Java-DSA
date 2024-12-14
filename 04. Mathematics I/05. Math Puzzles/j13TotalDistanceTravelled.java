/**
 * Problem Statement:
 * 
 *     You are given two integers: `mainTank` and `additionalTank`. The `mainTank` is the main fuel tank of a vehicle, and the `additionalTank` is an extra fuel reserve. The vehicle consumes 5 units of fuel for every 50 kilometers it travels. If the `mainTank` has less than 5 units of fuel, the vehicle can only travel the distance corresponding to the remaining fuel in the `mainTank`.
 *     
 *     In addition, the vehicle can use 1 unit of fuel from the `additionalTank` to add 1 unit of fuel to the `mainTank` if the `mainTank` has fuel left and the `additionalTank` still has fuel.
 * 
 *     You are tasked with calculating the total distance the vehicle can travel with the given amounts of fuel in the `mainTank` and the `additionalTank`.
 * 
 * Input:
 *     - An integer `mainTank` (1 <= mainTank <= 10^4) representing the amount of fuel in the main tank.
 *     - An integer `additionalTank` (0 <= additionalTank <= 10^4) representing the amount of fuel in the additional tank.
 * 
 * Output:
 *     - An integer representing the total distance the vehicle can travel.
 * 
 * Example:
 *     Input:
 *     10 2
 *     Output:
 *     120
 * 
 *     Explanation:
 *     - The vehicle can travel 50 kilometers with 5 units of fuel from the main tank.
 *     - It can then use 1 unit of fuel from the additional tank to add 1 unit to the main tank, which allows it to travel another 10 kilometers.
 *     - The vehicle can travel a total of 120 kilometers with the given fuel.
 */

import java.util.Scanner;

public class j13TotalDistanceTravelled {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int mainTank = in.nextInt(); // Input: the amount of fuel in the main tank
        int additionalTank = in.nextInt(); // Input: the amount of fuel in the additional tank
        System.out.println(distanceTraveled(mainTank, additionalTank)); // Output the total distance
        in.close();
    }

    /**
     * Approach: Greedy Approach
     * 
     * Intuition:
     * - The idea is to prioritize using fuel from the main tank as long as possible.
     * - For every 5 units of fuel in the main tank, the vehicle can travel 50 kilometers.
     * - If the main tank has less than 5 units, the remaining fuel in the main tank will directly contribute to the distance traveled.
     * - If the main tank runs out of fuel but the additional tank has fuel left, we refill the main tank by consuming 1 unit from the additional tank for every additional kilometer.
     * 
     * Time Complexity:
     * - O(n), where n is the amount of fuel in the main tank, because the loop runs as long as the main tank has fuel.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of extra space.
     * 
     * @param mainTank The amount of fuel in the main tank.
     * @param additionalTank The amount of fuel in the additional tank.
     * @return The total distance the vehicle can travel.
     */
    public static int distanceTraveled(int mainTank, int additionalTank) {
        int ans = 0; // Initialize the result
        // While there's fuel left in the main tank
        while (mainTank > 0) {
            if (mainTank < 5) {
                ans += mainTank * 10; // Add distance based on remaining fuel
                break;
            } else {
                ans += 50; // Add distance for every 5 units of fuel
                mainTank -= 5; // Subtract fuel from the main tank
                if (additionalTank >= 1) {
                    mainTank++; // Refill 1 unit of fuel from the additional tank
                    additionalTank--; // Decrease the additional tank fuel
                }
            }
        }
        return ans; // Return the total distance traveled
    }
}
