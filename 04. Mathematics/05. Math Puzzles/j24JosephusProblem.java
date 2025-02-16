import java.util.Scanner;

/**
 * Problem Statement:
 *     In the Josephus problem, n people stand in a circle and every k-th person is eliminated until only one person remains. 
 *     The task is to determine the position of the last remaining person in the circle.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), the number of people in the circle.
 *     - An integer `k` (1 <= k <= n), the step size for elimination.
 * 
 * Output:
 *     - The position (1-based index) of the last remaining person.
 * 
 * Example:
 *     Input:
 *     5 2
 *     Output:
 *     3
 * 
 *     Explanation:
 *     For `n = 5` people and a step size of `k = 2`, the order of elimination is:
 *     1st eliminated: Person 2, 2nd eliminated: Person 4, 3rd eliminated: Person 1, 4th eliminated: Person 5.
 *     Person 3 is the last remaining.
 */

public class j24JosephusProblem {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: number of people in the circle
        int k = in.nextInt(); // Input: step size for elimination
        System.out.println(josephusProb(n, k)); // Call and print the Josephus problem solution
        in.close();
    }

    /**
     * Approach 1: Recursive Solution
     * 
     * Intuition:
     * - This approach solves the problem recursively. The Josephus problem can be solved by reducing the size of the circle in each step.
     * - The recursive formula is:
     *   J(n, k) = (J(n-1, k) + k) % n.
     * 
     * Time Complexity:
     * - O(n), since we solve the problem recursively for each reduction of the circle size.
     * 
     * Space Complexity:
     * - O(n), due to the recursion stack depth.
     * 
     * @param n The number of people.
     * @param k The step size for elimination.
     * @return The position of the last remaining person.
     */
    public static int joesephKill(int n, int k) {
        if (n == 1)
            return 0; // The last remaining person is at index 0 (0-based index)
        return (joesephKill(n - 1, k) + k) % n;
    }

    /**
     * Approach 2: Optimized Iterative Solution
     * 
     * Intuition:
     * - This approach uses an efficient mathematical formula to find the position of the last remaining person.
     * - It iteratively calculates the position starting from a 1-person circle.
     * 
     * Time Complexity:
     * - O(n), because we iterate through all `n` people to find the solution.
     * 
     * Space Complexity:
     * - O(1), since no extra space is used other than a few variables.
     * 
     * @param n The number of people.
     * @param k The step size for elimination.
     * @return The position of the last remaining person.
     */
    public static int josephusProb(int n, int k) {
        int p = 0; // Start with a 1-person circle
        for (int i = 2; i <= n; i++) {
            p = (p + k) % i; // Calculate the survivor for circle size i
        }
        return p + 1; // Convert from 0-based index to 1-based index
    }
}
