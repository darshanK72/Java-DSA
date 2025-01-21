import java.util.ArrayList;
import java.util.Scanner;

/*-
 * Problem Statement:
 * 
 *     Given `n` boards and `k` painters, where each painter takes `t` units of
 *     time to paint 1 unit of board, find the minimum time required to paint
 *     all the boards.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An integer `A`, representing the number of painters.
 *     - An integer `B`, representing the time taken to paint 1 unit of board.
 *     - An array `C` of size `n` where each element represents the length of
 *       the board.
 * 
 * Output:
 *     - An integer representing the minimum time required to paint all the
 *       boards.
 * 
 * Example:
 *     Input:
 *     n = 5
 *     A = 2
 *     B = 5
 *     C = [1, 10, 10, 10, 10]
 *     Output:
 *     50
 * 
 *     Explanation:
 *     The minimum time required to paint all the boards is 50 units.
 */

public class j05PaintersPartitionProblem {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int A = in.nextInt(); // Input: number of painters
        int B = in.nextInt(); // Input: time taken to paint 1 unit of board
        ArrayList<Integer> C = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            C.add(in.nextInt()); // Input: elements of the array
        }

        // Call your solution
        System.out.printf("Minimum Time for painting boards: %d\n", paint(A, B, C));

        in.close();
    }

    /**
     * Approach: Binary Search on Answer
     * 
     * Intuition:
     * - The problem can be solved using binary search on the answer. The minimum
     * time required must be at least the maximum length of a single board, and
     * at most the sum of all board lengths multiplied by the time per unit.
     * - We perform binary search within this range to find the minimum time that
     * allows us to paint all boards with the given number of painters.
     * 
     * Time Complexity:
     * - O(n log(sum(C) - max(C))). This is because we perform binary search on
     * the range [max(C), sum(C)], and for each mid value, we iterate through
     * the array to check if the time is feasible.
     * 
     * Space Complexity:
     * - O(1). We only use a few extra variables for the binary search and the
     * feasibility check.
     * 
     * @param A The number of painters.
     * @param B The time taken to paint 1 unit of board.
     * @param C The input array of board lengths.
     * @return The minimum time required to paint all the boards.
     */
    public static int paint(int A, int B, ArrayList<Integer> C) {
        int minBlocks = 0;
        int maxBlocks = 0;
        for (int i : C) {
            minBlocks = Math.max(minBlocks, i);
            maxBlocks += i;
        }

        while (minBlocks <= maxBlocks) {
            int mid = minBlocks + (maxBlocks - minBlocks) / 2;
            if (isPossible(C, A, mid)) {
                maxBlocks = mid - 1;
            } else {
                minBlocks = mid + 1;
            }
        }
        return minBlocks * B;
    }

    /*-
     * Helper method to check if it is possible to paint all boards within the
     * given time with the given number of painters.
     * 
     * @param C The input array of board lengths.
     * @param A The number of painters.
     * @param time The time limit to check feasibility.
     * @return True if it is possible to paint all boards within the given time
     *         with the given number of painters, otherwise false.
     */
    public static boolean isPossible(ArrayList<Integer> C, int A, int time) {
        int paintersRequired = 1;
        int currentTime = 0;
        for (int i = 0; i < C.size(); i++) {
            if (currentTime + C.get(i) <= time) {
                currentTime += C.get(i);
            } else {
                paintersRequired++;
                currentTime = C.get(i);
            }
        }

        return (paintersRequired <= A);
    }
}