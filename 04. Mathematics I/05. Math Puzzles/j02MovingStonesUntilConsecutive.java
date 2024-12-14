/**
 * Problem Statement:
 * 
 *     You are given three distinct integers `a`, `b`, and `c` representing positions of three stones on a number line. 
 *     The goal is to move the stones until they are positioned consecutively, i.e., the difference between the minimum and 
 *     maximum positions is 2 or less. The task is to find the minimum and maximum number of moves required to make the stones consecutive.
 *     In one move, you can move any stone one position to the left or right.
 * 
 * Input:
 *     - Three integers `a`, `b`, and `c` (1 <= a, b, c <= 100), representing the positions of the stones.
 * 
 * Output:
 *     - A list of two integers:
 *       - The first integer is the minimum number of moves required to make the stones consecutive.
 *       - The second integer is the maximum number of moves required to make the stones consecutive.
 * 
 * Example:
 *     Input:
 *     1 5 3
 *     Output:
 *     1 2
 * 
 *     Explanation:
 *     - The minimum number of moves is 1: Move stone at position 5 to position 4 or move stone at position 1 to position 2.
 *     - The maximum number of moves is 2: Move stone at position 1 to position 2 and move stone at position 5 to position 4.
 */

import java.util.Scanner;

public class j02MovingStonesUntilConsecutive {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int a = in.nextInt(); // Input: position of the first stone
        int b = in.nextInt(); // Input: position of the second stone
        int c = in.nextInt(); // Input: position of the third stone

        // Call the method and output result
        System.out.println(numMovesStones(a, b, c));

        in.close();
    }

    /**
     * Approach: Calculate the minimum and maximum number of moves to make the stones consecutive.
     * 
     * Intuition:
     * - First, find the minimum (`min`), middle (`mid`), and maximum (`max`) positions of the stones.
     * - The minimum number of moves is determined based on the distances between adjacent stones:
     *     - If the stones are already consecutive (i.e., `max - min == 2`), then no moves are required.
     *     - If there is a gap of 2 or more between any two stones, at least one move is required, and the number of moves will 
     *       be 2 in the worst case (e.g., when the gap between the stones is large).
     * - The maximum number of moves is simply the number of moves required to shift the stone at `max` to a position adjacent to `min`.
     * 
     * Time Complexity:
     * - O(1), as the solution only involves basic arithmetic operations and comparisons.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used aside from the input and output.
     * 
     * @param a The position of the first stone.
     * @param b The position of the second stone.
     * @param c The position of the third stone.
     * @return An array of two integers: the minimum and maximum number of moves required.
     */
    public static int[] numMovesStones(int a, int b, int c) {
        // Find the minimum, middle, and maximum positions of the stones
        int min = Math.min(a, Math.min(b, c));
        int max = Math.max(a, Math.max(b, c));
        int mid = a + b + c - min - max; // Middle value is the remaining one after removing min and max

        // Variables to store minimum and maximum moves
        int minMoves = 0;
        int maxMoves = 0;

        // Check if the stones are not already consecutive
        if (max - min > 2) {
            // If the gap between max and mid or between mid and min is less than 3, it
            // takes 1 move
            // Otherwise, it requires 2 moves
            minMoves = (max - mid < 3 || mid - min < 3) ? 1 : 2;
            maxMoves = max - min - 2; // Max moves is the number of stones to move to make them consecutive
        }

        return new int[] { minMoves, maxMoves };
    }
}
