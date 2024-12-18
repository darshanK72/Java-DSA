/**
 * Problem Statement:
 * 
 *     A celebrity is a person who is known by everyone but does not know anyone. 
 *     You are given a 2D matrix `M` where `M[i][j]` is 1 if person `i` knows person `j`, and 0 otherwise.
 *     Find the celebrity in the matrix. If there is no celebrity, return -1.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the number of people.
 *     - An `n x n` matrix `M` where `M[i][j]` is 1 if person `i` knows person `j` and 0 otherwise.
 * 
 * Output:
 *     - Return the index of the celebrity if one exists, otherwise return -1.
 * 
 * Example:
 *     Input:
 *     4
 *     0 1 0 0
 *     0 0 0 1
 *     0 1 0 0
 *     0 1 0 0
 *     
 *     Output:
 *     1
 * 
 *     Explanation:
 *     Person 1 is a celebrity because everyone knows them, and they know no one.
 *     Matrix representation: 
 *     - Person 1 is known by all other people (matrix row of 1s except for themselves).
 *     - Person 1 knows no one (matrix column of 0s except for themselves).
 */

import java.util.Scanner;

public class j38CelebrityProblem {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number of people
        int[][] M = new int[n][n]; // Input: the matrix M representing knowledge of people
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                M[i][j] = in.nextInt(); // Input: the matrix values (0 or 1)
            }
        }

        // Call the solution
        System.out.println(celebrity(M, n)); // Output the celebrity's index (or -1 if no celebrity)

        in.close();
    }

    /**
     * Approach 1: Brute Force Approach (O(n^2))
     * 
     * Intuition:
     * - For each person, we check if they are known by everyone and if they don't know anyone.
     * - This involves checking every row and column for each person.
     * - If a person does not know anyone (row of all 0s except themselves) and is known by everyone (column of all 1s except themselves),
     *   they are the celebrity.
     * 
     * Time Complexity:
     * - O(n^2) as we are checking every person against every other person in a nested loop.
     * 
     * Space Complexity:
     * - O(n) for the auxiliary space required to store the trust/no-trust mappings for each person.
     * 
     * @param M The input matrix representing knowledge between people.
     * @param n The number of people.
     * @return The index of the celebrity if found, otherwise -1.
     */
    public static int celebrity(int M[][], int n) {
        int[] mapTrust = new int[n];
        int[] mapNoTrust = new int[n];

        // Build the map of trust and no-trust for each person
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    mapTrust[i]++; // Increment trust count for person i
                    mapNoTrust[j]++; // Increment no-trust count for person j
                }
            }
        }

        // Check each person to find the celebrity
        for (int i = 0; i < n; i++) {
            // A celebrity is someone who is trusted by everyone (mapNoTrust[i] == n-1)
            // and trusts no one (mapTrust[i] == 0).
            if (mapTrust[i] == 0 && mapNoTrust[i] == n - 1)
                return i; // Return the index of the celebrity
        }
        return -1; // No celebrity found
    }
}
