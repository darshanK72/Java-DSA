/**
 * Problem Statement:
 * 
 *     In this problem, you are playing a game where you are trying to guess a number.
 *     - The system has chosen a number between 1 and `n`.
 *     - You have to guess the number.
 *     - After each guess, the system will return:
 *         - `-1` if the guess is too high.
 *         - `1` if the guess is too low.
 *         - `0` if the guess is correct.
 *     - Your task is to find the correct number using the minimum number of guesses.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 2 * 10^7), the range of possible numbers.
 *     - An integer `guessed` (1 <= guessed <= n), the correct number that you need to guess.
 * 
 * Output:
 *     - The integer representing the number you guessed correctly.
 * 
 * Example:
 *     Input:
 *     10 6
 *     Output:
 *     6
 *     
 *     Explanation:
 *     The correct number is `6`, so the output is `6`.
 */

import java.util.Scanner;

public class j04GuessNumberHigherLower {
    private int guessed;

    public j04GuessNumberHigherLower(int guessed) {
        this.guessed = guessed; // Store the correct guessed number
    }

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int guessed = in.nextInt(); // Input: the correct guessed number
        j04GuessNumberHigherLower guessingGame = new j04GuessNumberHigherLower(guessed); // Create object with correct
                                                                                         // number
        int n = in.nextInt(); // Input: the range of possible numbers (1 to n)
        System.out.println(guessingGame.guessNumber(n)); // Output: the guessed number
        in.close();
    }

    /**
     * Approach 1: Binary Search to Guess the Number
     * 
     * Intuition:
     * - The goal is to guess the number in the range from `1` to `n`. We can optimize the guessing process by using binary search.
     * - We start by guessing the middle number (`mid`) of the range and check the result:
     *     - If the guess is too low (`guess(mid) == 1`), we move the start of the range to `mid + 1`.
     *     - If the guess is too high (`guess(mid) == -1`), we move the end of the range to `mid - 1`.
     *     - If the guess is correct (`guess(mid) == 0`), we return the guessed number.
     * - By narrowing the search space in half after each guess, we minimize the number of guesses needed to find the correct number.
     * 
     * Time Complexity:
     * - O(log n), because each guess cuts the search space in half.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used other than a few variables.
     * 
     * @param n The range of numbers (1 to n).
     * @return The guessed number.
     */
    public int guessNumber(int n) {
        int s = 1; // Start of the range
        int e = n; // End of the range
        while (s <= e) {
            int mid = s + (e - s) / 2; // Calculate the middle number
            if (guess(mid) == -1) {
                e = mid - 1; // If guess is too high, move the end of the range
            } else if (guess(mid) == 1) {
                s = mid + 1; // If guess is too low, move the start of the range
            } else {
                return mid; // If guess is correct, return the guessed number
            }
        }
        return -1; // This line is never reached if the input is valid
    }

    /**
     * Helper Function: guess
     * 
     * This function simulates the feedback mechanism after each guess. 
     * It is provided by the system, and here we simulate the behavior.
     * 
     * Time Complexity:
     * - O(1), as it simply compares the guessed number to the actual number.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used.
     * 
     * @param n The number guessed.
     * @return 0 if the guess is correct, 1 if the guess is too low, and -1 if the guess is too high.
     */
    public int guess(int n) {
        if (guessed == n) {
            return 0; // Return 0 if the guessed number is correct
        } else if (guessed < n) {
            return 1; // Return 1 if the guessed number is too low
        } else {
            return -1; // Return -1 if the guessed number is too high
        }
    }
}
