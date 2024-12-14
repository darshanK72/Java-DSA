/*-
 * Problem Statement:
 * 
 *     A tournament is held with `n` teams. In each match, one team wins and the other team is eliminated. 
 *     The tournament continues until there is only one team remaining. How many matches are required to determine the winner?
 *     You need to calculate the number of matches that will take place in the tournament.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the number of teams in the tournament.
 * 
 * Output:
 *     - An integer representing the number of matches played during the tournament.
 * 
 * Example:
 *     Input:
 *     7
 *     Output:
 *     6
 * 
 *     Explanation:
 *     - For `7` teams, there will be 6 matches: 
 *     - 1st round: 3 matches, 3 winners remain.
 *     - 2nd round: 1 match, 1 winner remains.
 *     - Final match: 1 match, 1 winner remains.
 *     - Total matches = 3 + 1 + 2 = 6.
 */

import java.util.Scanner;

public class j06TurnamentMatches {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: number of teams in the tournament
        System.out.println(numberOfMatches(n)); // Output: number of matches played
        in.close();
    }

    /*-
     * Alternative Approach: Simulation of the Tournament Rounds
     * 
     * Intuition:
     * - We can simulate the tournament by pairing teams and eliminating one team per match. 
     * - This will continue until only one team remains.
     * - In each round, the number of teams is halved (or almost halved if `n` is odd), and we count the number of matches in each round.
     * 
     * Time Complexity:
     * - O(log n), as the number of teams is halved in each round.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used aside from a few variables.
     * 
     * @param n The number of teams in the tournament.
     * @return The number of matches played in the tournament.
     */
    public static int simulateTournament(int n) {
        int matches = 0;
        while (n > 1) {
            // In each round, half of the teams are eliminated
            matches += n / 2;
            n = (n + 1) / 2; // Update n to the number of remaining teams
        }
        return matches;
    }

    /*-
    * Approach: The number of matches required is always `n - 1`, because each match eliminates exactly one team. 
    *     To reduce from `n` teams to 1 winner, we need to eliminate `n - 1` teams. Hence, the number of matches played will be `n - 1`.
    * 
    * Intuition:
    * - In each match, one team is eliminated, and this continues until there is only one team remaining.
    * - If there are `n` teams, we need `n - 1` eliminations to determine a winner.
    * 
    * Time Complexity:
    * - O(1), since we are only performing a simple subtraction operation.
    * 
    * Space Complexity:
    * - O(1), as we only use a constant amount of space.
    * 
    * @param n The number of teams in the tournament.
    * @return The number of matches played in the tournament.
    */
    public static int numberOfMatches(int n) {
        return n - 1;
    }
}
