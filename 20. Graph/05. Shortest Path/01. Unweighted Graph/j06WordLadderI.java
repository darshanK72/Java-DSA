/**
 * LeetCode 127. Word Ladder
 *
 * Problem Statement:
 *     Given two words, beginWord and endWord, and a wordList, return the length
 *     of the shortest transformation sequence from beginWord to endWord, such
 *     that:
 *         1. Only one letter can be changed at a time.
 *         2. Each transformed word must exist in the wordList.
 *     If no such sequence, return 0.
 *
 * Input:
 *     - beginWord (String): Starting word
 *     - endWord (String): Target word
 *     - wordList (List<String>): List of allowed words
 *
 * Output:
 *     - int: Length of shortest transformation sequence, or 0 if impossible
 *
 * Example:
 *     Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 *     Output: 5
 *
 *     Explanation:
 *     "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 *     Each transformation changes only one letter and all words are in wordList.
 */

import java.util.*;

public class j06WordLadderI {
    /**
     * Helper class to store word and its current transformation distance
     */
    static class Pair {
        String word;
        int dist;
        Pair(String word, int dist) {
            this.word = word;
            this.dist = dist;
        }
    }

    /**
     * Approach: Breadth-First Search (BFS)
     *
     * Intuition:
     * - Each word is a node; an edge exists if two words differ by one letter.
     * - Use BFS to find the shortest path from beginWord to endWord.
     * - Use a visited set to avoid revisiting words.
     *
     * Explanation:
     * - Step 1: Add beginWord to queue and visited set.
     * - Step 2: For each word, try all possible one-letter transformations.
     * - Step 3: If transformation is in wordList and not visited, add to queue.
     * - Step 4: If endWord is reached, return current distance.
     * - Step 5: If queue is empty and endWord not reached, return 0.
     *
     * Time Complexity: O(N * L^2) where N = wordList size, L = word length
     * Space Complexity: O(N * L) for visited set and queue
     *
     * @param beginWord Starting word
     * @param endWord   Target word
     * @param wordList  List of allowed words
     * @return Length of shortest transformation sequence, or 0 if impossible
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0; // If endWord is not in wordList, no transformation is possible
        Queue<Pair> queue = new LinkedList<>(); // Queue for BFS, stores word and distance
        HashSet<String> visited = new HashSet<>(); // Set to track visited words
        queue.add(new Pair(beginWord, 1)); // Start BFS from beginWord with distance 1
        visited.add(beginWord); // Mark beginWord as visited
        while (!queue.isEmpty()) {
            Pair curr = queue.poll(); // Get current word and its distance
            if (curr.word.equals(endWord))
                return curr.dist; // If current word is endWord, return distance
            // Try all possible next words in wordList
            for (String word : wordList) {
                // Only consider unvisited words that differ by one letter
                if (!visited.contains(word) && hasEdge(curr.word, word)) {
                    visited.add(word); // Mark word as visited
                    queue.add(new Pair(word, curr.dist + 1)); // Enqueue new word with incremented distance
                }
            }
        }
        return 0; // If endWord is not reached, return 0
    }

    /**
     * Helper Method: Check if two words differ by exactly one letter
     *
     * Intuition:
     * - Only one letter can be changed at a time.
     *
     * Explanation:
     * - Count the number of differing characters between two words.
     * - Return true if exactly one letter differs.
     *
     * Time Complexity: O(L) where L = word length
     * Space Complexity: O(1)
     *
     * @param w1 First word
     * @param w2 Second word
     * @return True if words differ by exactly one letter
     */
    public static boolean hasEdge(String w1, String w2) {
        int diff = 0; // Counter for differing characters
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i))
                diff++; // Increment if characters differ
            if (diff > 1) return false; // Early exit if more than one letter differs
        }
        return (diff == 1); // Return true if exactly one letter differs
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        List<String> wordList1 = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println("Basic Test Case:");
        // Should return 5 as the shortest transformation sequence length
        System.out.println("Expected: 5, Output: " + ladderLength("hit", "cog", wordList1));

        // Test Case 2: No possible transformation
        List<String> wordList2 = Arrays.asList("hot","dot","dog","lot","log");
        System.out.println("\nNo Possible Transformation:");
        // Should return 0 since 'cog' is not in wordList
        System.out.println("Expected: 0, Output: " + ladderLength("hit", "cog", wordList2));

        // Test Case 3: beginWord equals endWord
        List<String> wordList3 = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println("\nBeginWord Equals EndWord:");
        // Should return 1 as the only valid sequence is the word itself
        System.out.println("Expected: 1, Output: " + ladderLength("hit", "hit", wordList3));

        // Test Case 4: Edge case (single letter words)
        List<String> wordList4 = Arrays.asList("a","b","c");
        System.out.println("\nSingle Letter Words:");
        // Should return 2 as the shortest transformation from 'a' to 'c' is [a, c]
        System.out.println("Expected: 2, Output: " + ladderLength("a", "c", wordList4));
    }
}
