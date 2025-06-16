/**
 * Problem: Partition Labels
 * 
 * Problem Statement:
 * You are given a string s. We want to partition the string into as many parts as possible 
 * so that each letter appears in at most one part. Return a list of integers representing 
 * the size of these parts.
 * 
 * Example:
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 * 
 * Constraints:
 * - 1 <= s.length <= 500
 * - s consists of lowercase English letters.
 * 
 * Approach:
 * 1. First pass: Store the last occurrence of each character
 * 2. Second pass: For each character, extend the partition until we reach its last occurrence
 * 3. Keep track of the maximum last occurrence in current partition
 * 4. When we reach the end of a partition, add its length to result
 * 
 * Time Complexity: O(n) where n is the length of string
 * Space Complexity: O(1) as we only store 26 characters at most
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class j02PartitionLabels {

    /**
     * Method 1: Using nested loop to find partition boundaries
     * 
     * @param s Input string to partition
     * @return List of partition sizes
     */
    public static List<Integer> partitionLabels1(String s) {
        // Store last occurrence of each character
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
            }
        }
        
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int lastIndex = map.get(s.charAt(i));
            // Extend partition until we reach the last occurrence of current character
            for (int j = i + 1; j <= lastIndex - 1; j++) {
                if (map.get(s.charAt(j)) > lastIndex) {
                    lastIndex = map.get(s.charAt(j));
                }
            }
            out.add(lastIndex - i + 1);
            i = lastIndex;
        }
        return out;
    }

    /**
     * Method 2: Using single pass with start and end pointers
     * More efficient approach that avoids nested loops
     * 
     * @param s Input string to partition
     * @return List of partition sizes
     */
    public static List<Integer> partitionLabels2(String s) {
        // Store last occurrence of each character
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
            }
        }
        
        ArrayList<Integer> out = new ArrayList<>();
        int start = 0;
        int end = map.get(s.charAt(0));
        
        // Single pass to find partitions
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, map.get(s.charAt(i)));
            if (i == end) {
                out.add(end - start + 1);
                start = end + 1;
            }
        }
        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        String s1 = "ababcbacadefegdehijhklij";
        System.out.println("Test Case 1:");
        System.out.println("Input: " + s1);
        System.out.println("Output (Method 1): " + partitionLabels1(s1));
        System.out.println("Output (Method 2): " + partitionLabels2(s1));
        System.out.println();

        // Test Case 2: Single character
        String s2 = "a";
        System.out.println("Test Case 2:");
        System.out.println("Input: " + s2);
        System.out.println("Output (Method 1): " + partitionLabels1(s2));
        System.out.println("Output (Method 2): " + partitionLabels2(s2));
        System.out.println();

        // Test Case 3: All same characters
        String s3 = "aaaaa";
        System.out.println("Test Case 3:");
        System.out.println("Input: " + s3);
        System.out.println("Output (Method 1): " + partitionLabels1(s3));
        System.out.println("Output (Method 2): " + partitionLabels2(s3));
        System.out.println();

        // Test Case 4: No overlapping characters
        String s4 = "abcdefg";
        System.out.println("Test Case 4:");
        System.out.println("Input: " + s4);
        System.out.println("Output (Method 1): " + partitionLabels1(s4));
        System.out.println("Output (Method 2): " + partitionLabels2(s4));
    }
}
