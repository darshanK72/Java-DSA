/**
 * LeetCode 126. Word Ladder II
 *
 * Problem Statement:
 *     Given two words, beginWord and endWord, and a wordList, return all shortest
 *     transformation sequences from beginWord to endWord, such that:
 *         1. Only one letter can be changed at a time.
 *         2. Each transformed word must exist in the wordList.
 *     Return the answer as a list of lists. If no such sequence, return an empty list.
 *
 * Input:
 *     - beginWord (String): Starting word
 *     - endWord (String): Target word
 *     - wordList (List<String>): List of allowed words
 *
 * Output:
 *     - List<List<String>>: All shortest transformation sequences
 *
 * Example:
 *     Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 *     Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 *
 *     Explanation:
 *     Both sequences are valid and shortest (length 5).
 */

import java.util.*;

public class j07WordLadderII {
    /**
     * Approach: Breadth-First Search (BFS) for all shortest paths
     *
     * Intuition:
     * - Each word is a node; an edge exists if two words differ by one letter.
     * - Use BFS to find all shortest paths from beginWord to endWord.
     * - Track paths as lists in the queue; add to output if endWord is reached.
     * - Use a visited set to avoid revisiting words at the same level.
     *
     * Explanation:
     * - Step 1: Add beginWord as the first path in the queue.
     * - Step 2: For each path, try all possible one-letter transformations.
     * - Step 3: If transformation is in wordList and not visited, add new path to queue.
     * - Step 4: If endWord is reached, add path to output and set foundEnd flag.
     * - Step 5: After each level, add all words used at this level to visited set.
     * - Step 6: Stop BFS after finding the first level with endWord (all shortest paths).
     *
     * Time Complexity: O(N^2 * L) where N = wordList size, L = word length
     * Space Complexity: O(N^2 * L) for queue and output storage
     *
     * @param beginWord Starting word
     * @param endWord   Target word
     * @param wordList  List of allowed words
     * @return All shortest transformation sequences
     */
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> out = new ArrayList<>(); // Output list for all shortest paths
        if (!wordList.contains(endWord)) {
            // If endWord is not in wordList, no transformation is possible
            return out;
        }
        Queue<ArrayList<String>> queue = new LinkedList<>(); // Queue for BFS, stores paths
        HashSet<String> visited = new HashSet<>(); // Set to track visited words at current/previous levels
        ArrayList<String> tempList = new ArrayList<>(); // Temporary list to start BFS
        tempList.add(beginWord); // Start path with beginWord
        queue.add(tempList); // Enqueue initial path
        visited.add(beginWord); // Mark beginWord as visited
        boolean foundEnd = false; // Flag to indicate if endWord is found at current level
        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of paths to process at current BFS level
            ArrayList<String> temp = new ArrayList<>(); // To collect words visited at this level
            for (int i = 0; i < size; i++) {
                ArrayList<String> currList = queue.poll(); // Get current path
                String lastWord = currList.get(currList.size() - 1); // Last word in current path
                temp.add(lastWord); // Track for marking as visited after this level
                if (lastWord.equals(endWord)) {
                    // If last word is endWord, add current path to output
                    out.add(currList);
                    foundEnd = true; // Set flag to break after this level
                    continue;
                }
                // Try all possible next words in wordList
                for (String word : wordList) {
                    // Only consider unvisited words that differ by one letter
                    if (!visited.contains(word) && hasEdge(lastWord, word)) {
                        ArrayList<String> newList = new ArrayList<>(currList); // Copy current path
                        newList.add(word); // Add new word to path
                        queue.add(newList); // Enqueue new path for next level
                    }
                }
            }
            // Mark all words visited at this level to avoid revisiting in next level
            for (String w : temp) {
                visited.add(w);
            }
            if (foundEnd)
                break; // Stop BFS after finding all shortest paths
        }
        return out;
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
        // Should return two shortest transformation sequences
        System.out.println("Expected: [[hit, hot, dot, dog, cog], [hit, hot, lot, log, cog]], Output: " + findLadders("hit", "cog", wordList1));

        // Test Case 2: No possible transformation
        List<String> wordList2 = Arrays.asList("hot","dot","dog","lot","log");
        System.out.println("\nNo Possible Transformation:");
        // Should return empty list since 'cog' is not in wordList
        System.out.println("Expected: [], Output: " + findLadders("hit", "cog", wordList2));

        // Test Case 3: beginWord equals endWord
        List<String> wordList3 = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println("\nBeginWord Equals EndWord:");
        // Should return [[hit]] as the only valid sequence
        System.out.println("Expected: [[hit]], Output: " + findLadders("hit", "hit", wordList3));

        // Test Case 4: Edge case (single letter words)
        List<String> wordList4 = Arrays.asList("a","b","c");
        System.out.println("\nSingle Letter Words:");
        // Should return [[a, c]] as the shortest transformation
        System.out.println("Expected: [[a, c]], Output: " + findLadders("a", "c", wordList4));
    }
}