
/**
 * Problem Statement:
 * 
 *     You are given an integer `candies` representing the total number of candies and an integer `num_people` 
 *     representing the number of people. The candies are distributed among the people following these rules:
 *     - Starting with the first person, each person receives a certain number of candies.
 *     - In the first round, the first person gets 1 candy, the second person gets 2 candies, and so on until 
 *       the last person gets `num_people` candies.
 *     - After reaching the last person, the distribution continues from the first person again, but now the first 
 *       person gets `num_people + 1` candies, the second person gets `num_people + 2` candies, and so on.
 *     - This process repeats, with the number of candies increasing by 1 for each person in the row each time we 
 *       pass through the row, until all candies are distributed.
 *     - The last person will receive all the remaining candies, which may not necessarily be one more than the 
 *       previous round's candies.
 * 
 * Input:
 *     - Two integers `candies` and `num_people` representing the total number of candies and the number of people respectively.
 *     - The integers `candies` and `num_people` satisfy (1 <= candies <= 10^9) and (1 <= num_people <= 100).
 * 
 * Output:
 *     - An array of integers representing the number of candies each person receives.
 * 
 * Example:
 *     Input:
 *     7 4
 *     Output:
 *     [1, 2, 3, 1]
 * 
 *     Explanation:
 *     - In the first round, person 1 gets 1 candy, person 2 gets 2 candies, person 3 gets 3 candies, and person 4 gets 1 candy.
 *     - The distribution ends after this because there are no candies left.
 * 
 *     Input:
 *     10 3
 *     Output:
 *     [5, 2, 3]
 * 
 *     Explanation:
 *     - In the first round, person 1 gets 1 candy, person 2 gets 2 candies, and person 3 gets 3 candies.
 *     - In the second round, person 1 gets 4 candies, person 2 gets 4 candies, and person 3 gets 2 candies.
 *     - The distribution ends after this because there are no candies left.
 */

import java.util.Scanner;

public class j20DistributeCandiesToPeople {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int candies = in.nextInt();
        int num_people = in.nextInt();
        System.out.println(distributeCandies(candies, num_people));
        in.close();
    }

    /**
     * Approach 1: Distribute Candies Using Simple Iteration
     * 
     * Intuition:
     * - We need to distribute the candies in a round-robin fashion, starting with 1 candy for the first person, 
     *   2 candies for the second person, and so on until the last person.
     * - After each round, the number of candies given increases. In the second round, the first person gets 
     *   `num_people + 1` candies, the second person gets `num_people + 2` candies, and so on.
     * - We continue this process until all candies are distributed, giving remaining candies to the last person.
     * 
     * Time Complexity:
     * - O(candies), as we are iterating through all candies to distribute them.
     * 
     * Space Complexity:
     * - O(num_people), as we are storing the candies distributed to each person in an array of size `num_people`.
     * 
     * @param candies The total number of candies to be distributed.
     * @param num_people The number of people to distribute candies to.
     * @return An array representing the number of candies each person receives.
     */
    public static int[] distributeCandies(int candies, int num_people) {
        int[] out = new int[num_people]; // Array to store candies distributed to each person
        int s = 1; // The initial number of candies to be given in the first round

        while (candies > 0) {
            // Distribute candies to each person in the current round
            for (int i = 0; i < num_people; i++) {
                // Give the current person 's' candies or whatever is left, whichever is smaller
                out[i] += (s < candies) ? s : candies;
                candies -= s; // Reduce the number of remaining candies
                if (candies <= 0)
                    break; // If no candies are left, break out of the loop
                s++; // Increase the number of candies to be distributed in the next round
            }
        }
        return out; // Return the final distribution of candies
    }
}