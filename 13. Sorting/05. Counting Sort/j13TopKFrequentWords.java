/**
 * Problem Statement:
 * 
 *     Given an array of strings `words` and an integer `k`, return the top `k` most frequent words.
 *     You may return the answer in any order. The words in the output should be sorted by their frequency, 
 *     with the most frequent word appearing first. If two words have the same frequency, sort them lexicographically.
 * 
 * Input:
 *     - An array of strings `words` (1 <= words.length <= 1000), where each string satisfies (1 <= words[i].length <= 100).
 *     - An integer `k` (1 <= k <= words.length), representing the number of top frequent words to return.
 * 
 * Output:
 *     - A list of strings containing the `k` most frequent words from the input array.
 * 
 * Example:
 *     Input:
 *     words = ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 *     Output:
 *     ["i", "love"]
 * 
 *     Explanation:
 *     The word "i" appears 2 times, and the word "love" also appears 2 times. 
 *     As both have the same frequency, they are sorted lexicographically, with "i" before "love".
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class j13TopKFrequentWords {

    public static void main(String args[]) {
        // Test Case 1: Words with common frequency
        String[] words1 = { "i", "love", "leetcode", "i", "love", "coding" };
        int k1 = 2;
        j13TopKFrequentWords obj1 = new j13TopKFrequentWords();
        List<String> result1 = obj1.topKFrequent(words1, k1);
        System.out.println("Top " + k1 + " frequent words: " + result1);

        // Test Case 2: All words unique
        String[] words2 = { "apple", "banana", "cherry", "date" };
        int k2 = 3;
        j13TopKFrequentWords obj2 = new j13TopKFrequentWords();
        List<String> result2 = obj2.topKFrequent(words2, k2);
        System.out.println("Top " + k2 + " frequent words: " + result2);

        // Test Case 3: Edge case with repeated words
        String[] words3 = { "hello", "world", "hello", "world", "world", "hello" };
        int k3 = 1;
        j13TopKFrequentWords obj3 = new j13TopKFrequentWords();
        List<String> result3 = obj3.topKFrequent(words3, k3);
        System.out.println("Top " + k3 + " frequent word: " + result3);
    }

    /**
     * Approach: Bucket Sort and HashMap for Frequency Count
     * 
     * Intuition:
     * - First, we count the frequency of each word using a HashMap.
     * - Then, we group words into buckets based on their frequency. The bucket index represents the frequency.
     * - After grouping words into buckets, we can retrieve the top `k` most frequent words by scanning the buckets.
     * - We need to sort the words within each bucket lexicographically in case of tie in frequency.
     * - Finally, we traverse through the buckets starting from the highest frequency to collect the top `k` words.
     * 
     * Time Complexity:
     * - O(n log n), where `n` is the number of words. The complexity comes from sorting the words within each frequency bucket.
     * 
     * Space Complexity:
     * - O(n), where `n` is the number of words. We use a HashMap to store the frequency of each word, and an array to hold the buckets.
     * 
     * @param words The input array of words.
     * @param k The number of top frequent words to return.
     * @return A list containing the top k most frequent words.
     */
    public List<String> topKFrequent(String[] words, int k) {
        // Create buckets for words based on their frequencies
        @SuppressWarnings("unchecked")
        ArrayList<String>[] buckets = new ArrayList[11]; // There are at most 10 different frequencies
        for (int i = 0; i < 11; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Map to store the frequency of each word
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        // Group words by their frequency into buckets
        for (String word : map.keySet()) {
            int freq = map.get(word);
            buckets[freq].add(word);
        }

        // List to store the top k frequent words
        ArrayList<String> out = new ArrayList<>();

        // Start from the highest frequency bucket and gather the top k words
        for (int i = 10; i > 0; i--) {
            // Sort words in the same frequency bucket lexicographically
            Collections.sort(buckets[i]);
            for (String word : buckets[i]) {
                out.add(word);
                k--;
                if (k == 0) {
                    break; // Stop once we have added k words
                }
            }
            if (k == 0) {
                break; // Stop once we have added k words
            }
        }

        return out;
    }
}
