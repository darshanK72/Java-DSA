/**
 * Problem Statement:
 *     LeetCode 950. Reveal Cards In Increasing Order
 * 
 *     You are given an integer array deck. The deck was initially shuffled in 
 *     random order and will be revealed card by card. You need to rearrange the
 *     deck such that when revealed, each card is in increasing order.
 *     
 *     The revealing process is as follows:
 *     1. Reveal the top card
 *     2. Move the next card to the bottom of the deck
 *     3. Repeat until all cards are revealed
 * 
 * Input:
 *     - Array deck where 1 <= deck.length <= 1000
 *     - 1 <= deck[i] <= 10^6
 *     - All deck[i] are unique
 * 
 * Output:
 *     - Array representing deck arranged to reveal in increasing order
 * 
 * Example:
 *     Input: deck = [17,13,11,2,3,5,7]
 *     Output: [2,13,3,11,5,17,7]
 *     
 *     Revealing process:
 *     - Reveal: 2
 *     - Move 13 to bottom, Reveal: 3
 *     - Move 11 to bottom, Reveal: 5
 *     - Move 17 to bottom, Reveal: 7
 *     - Move bottom cards: 13, 11, 17
 *     Final revealed order: 2,3,5,7,11,13,17
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class j09CardRotationI {

    public static void main(String args[]) {
        // Test cases with different scenarios
        int[][] testCases = {
                { 1, 2, 3, 4, 5 }, // Basic case
                { 5, 4, 3, 2, 1 }, // Reverse order
                { 1 },             // Single card
                {},                // Empty deck
                { 3, 1, 2 }       // Random order
        };

        // Test each case
        for (int[] test : testCases) {
            System.out.println("Deck: " + Arrays.toString(test));
            System.out.println("Revealed Deck: " + Arrays.toString(deckRevealedIncreasing(test)));
            System.out.println();
        }
    }

    /**
     * Approach: Using Queue for Simulation
     * 
     * Intuition:
     * - Sort the deck to get final revealed order
     * - Use queue to simulate reverse revealing process
     * - Queue maintains indices to place sorted cards
     * - Process maintains revealing order constraints
     * 
     * Time Complexity: O(nlogn)
     * - Sorting array: O(nlogn)
     * - Queue operations: O(n)
     * 
     * Space Complexity: O(n)
     * - Queue stores n indices
     * - Output array of size n
     */
    public static int[] deckRevealedIncreasing(int[] deck) {
        // Handle empty deck
        if (deck.length == 0) return new int[0];
        
        // Initialize queue with indices
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < deck.length; i++) {
            queue.add(i);
        }

        // Sort deck in ascending order
        Arrays.sort(deck);
        int[] out = new int[deck.length];

        // Simulate reverse revealing process
        for (int i = 0; i < deck.length; i++) {
            out[queue.poll()] = deck[i];     // Place card at current position
            if (!queue.isEmpty()) {
                queue.add(queue.poll());      // Move next position to end
            }
        }
        return out;
    }
}
