
/**
 * Problem Statement:
 * 
 *    The Tower of Hanoi is a puzzle consisting of three rods and a number of disks of different sizes 
 *    that can slide onto any rod. The puzzle begins with the disks stacked in increasing order of size 
 *    on one rod. The objective of the puzzle is to move the entire stack of disks from the source rod 
 *    to the target rod, following these rules:
 * 
 *    1. Only one disk can be moved at a time.
 *    2. Each move consists of taking the upper disk from one of the stacks and placing it on top 
 *       of another stack or on an empty rod.
 *    3. No disk may be placed on top of a smaller disk.
 * 
 *    You are required to calculate the number of moves required to solve the Tower of Hanoi puzzle 
 *    for `n` disks, and also display the sequence of moves for transferring the disks from the source rod 
 *    to the target rod.
 * 
 * Input:
 *    - A single integer n (n >= 1), representing the number of disks.
 * 
 * Output:
 *    - The sequence of moves required to solve the puzzle, displaying which disk moves from 
 *    which rod to which rod.
 *    - The total number of moves required to solve the puzzle.
 * 
 * Example:
 *    Input: 3
 * Output:
 *    move disk 1 from rod A to rod B
 *    move disk 2 from rod A to rod C
 *    move disk 1 from rod B to rod C
 *    move disk 3 from rod A to rod B
 *    move disk 1 from rod C to rod A
 *    move disk 2 from rod C to rod B
 *    move disk 1 from rod A to rod B
 *    7
 * 
 * Constraints:
 *    - The input `n` is a positive integer (1 <= n <= 20).
 */

import java.util.Scanner;

public class j06TowerOfHanoi {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // Call the recursive function to print the sequence of moves and get the move
        // count
        long moves = towerOfHanoi(n, 'A', 'B', 'C'); // n disks, from rod 'A' to rod 'B', using rod 'C' as auxiliary
        System.out.println(moves); // Total number of moves

        in.close();
    }

    /**
     * Recursive method to solve the Tower of Hanoi puzzle, printing the sequence of
     * moves
     * and returning the total number of moves required.
     * 
     * Time Complexity: O(2^n), as the number of moves grows exponentially with n.
     * Space Complexity: O(n), due to the recursion stack.
     *
     * @param N    The number of disks.
     * @param from The source rod.
     * @param to   The target rod.
     * @param aux  The auxiliary rod.
     * @return The total number of moves.
     */
    public static long towerOfHanoi(int N, char from, char to, char aux) {
        if (N == 0) {
            return 0; // Base case: No moves if no disks
        }

        // Move N-'A' disks from source to auxiliary rod
        long count = towerOfHanoi(N - 1, from, aux, to);

        // Print the move for the current disk
        System.out.println("move disk " + N + " from rod " + from + " to rod " + to);
        count++;

        // Move N-'A' disks from auxiliary rod to target rod
        count += towerOfHanoi(N - 1, aux, to, from);

        return count;
    }

    /**
     * towerOfHanoi(3, A, B, C)
     * |
     * |-- towerOfHanoi(2, A, C, B)
     * | |
     * | |-- towerOfHanoi(1, A, B, C)
     * | | |
     * | | |-- towerOfHanoi(0, A, C, B) (Base Case)
     * | |
     * | |-- move disk 1 from A to B
     * | |-- towerOfHanoi(0, C, B, A) (Base Case)
     * |
     * |-- move disk 2 from A to C
     * |
     * |-- towerOfHanoi(2, B, C, A)
     * |
     * |-- move disk 3 from A to B
     * |
     * |-- towerOfHanoi(1, C, B, A)
     * |
     * |-- move disk 1 from C to A
     * |
     * |-- towerOfHanoi(0, B, A, C) (Base Case)
     * |
     * |-- move disk 2 from B to C
     * |
     * |-- towerOfHanoi(0, A, C, B) (Base Case)
     * |
     * |-- move disk 1 from A to C
     */
}
