/**
 * Problem Statement:
 *     LeetCode 394. Decode String
 * 
 *     Given an encoded string, return its decoded string. The encoding rule is:
 *     k[encoded_string], where the encoded_string inside the square brackets 
 *     is being repeated exactly k times.
 * 
 * Input:
 *     - String s containing digits, letters, and square brackets
 *     - 1 <= s.length <= 30
 *     - s consists of lowercase English letters, digits, and square brackets '[]'
 *     - s is guaranteed to be a valid input
 *     - All the integers in s are in the range [1, 300]
 * 
 * Output:
 *     - Decoded string after expanding all k[encoded_string] expressions
 * 
 * Example:
 *     Input: s = "3[a]2[bc]"
 *     Output: "aaabcbc"
 * 
 *     Input: s = "3[a2[c]]"
 *     Output: "accaccacc"
 * 
 *     Input: s = "2[abc]3[cd]ef"
 *     Output: "abcabccdcdcdef"
 */

import java.util.Stack;

public class j13DecodeString {

    public static void main(String[] args) {
        // Test cases
        String[] testStrings = {
                "3[a]2[bc]",         // Basic case: simple repetition
                "3[a2[c]]",          // Nested case: nested brackets
                "2[abc]3[cd]ef"      // Multiple segments with suffix
        };

        // Test each case with both implementations
        for (String str : testStrings) {
            System.out.println("Input: " + str);
            System.out.println("Stack Approach: " + decodeStringMyImpl(str));
            System.out.println("Optimized Stack Approach: " + decodeStringOptimized(str));
            System.out.println();
        }
    }

    /**
     * Approach 1: Using Two Stacks with String Manipulation
     * 
     * Intuition:
     * - We need to handle nested brackets and repetitions, which suggests using a stack
     *   data structure to maintain the order of operations.
     * - Two stacks are used: one for numbers (repetition counts) and another for 
     *   strings (characters and intermediate results).
     * - When we encounter '[', we save the current state.
     * - When we encounter ']', we process the repetition by popping the count and
     *   applying it to the accumulated string.
     * 
     * Time Complexity:
     * - O(n * k) where n is the length of input string and k is the maximum value
     *   of number k in the string.
     * 
     * Space Complexity:
     * - O(m) where m is the maximum nesting depth of brackets.
     */
    public static String decodeStringMyImpl(String s) {
        // Initialize stacks for numbers and strings
        Stack<Integer> numbers = new Stack<>();
        Stack<String> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // Case 1: Handle letters
            if(c >= 'a' && c <= 'z') {
                StringBuilder w = new StringBuilder();
                // Collect consecutive letters
                while(i < s.length() && (c >= 'a' && c <= 'z')) {
                    w.append(c);
                    i++;
                    if(i < s.length()) c = s.charAt(i);
                }
                i--; // Adjust index after collecting letters
                stack.push(w.toString());
            }
            // Case 2: Handle numbers
            else if(c >= '0' && c <= '9') {
                int n = 0;
                // Build complete number
                while(i < s.length() && (c >= '0' && c <= '9')) {
                    n = n * 10 + (c - '0');
                    i++;
                    if(i < s.length()) c = s.charAt(i);
                }
                i--; // Adjust index after collecting number
                numbers.push(n);
            }
            // Case 3: Handle opening bracket
            else if(c == '[') {
                stack.push("" + c);
            }
            // Case 4: Handle closing bracket
            else if(c == ']') {
                String sw = "";
                // Collect everything until matching '['
                while(!stack.peek().equals("[")) {
                   sw = stack.pop() + sw;
                }
                stack.pop(); // Remove '['
                
                // Repeat the string according to number
                StringBuilder news = new StringBuilder();
                int t = numbers.pop();
                for(int j = 0; j < t; j++) {
                    news.append(sw);
                }
                stack.push(news.toString());
            }
        }

        // Combine remaining strings in stack
        String ans = "";
        while(!stack.isEmpty()) {
            ans = stack.pop() + ans;
        }
        return ans;
    }

    /**
     * Approach 2: Optimized Stack Implementation with StringBuilder
     * 
     * Intuition:
     * - Similar to Approach 1, but uses StringBuilder for better string manipulation
     *   performance.
     * - Instead of concatenating strings repeatedly, we build them efficiently using
     *   StringBuilder.
     * - This approach reduces the memory overhead of creating multiple string objects.
     * 
     * Time Complexity:
     * - O(n) where n is the length of the input string. StringBuilder operations
     *   are more efficient than String concatenation.
     * 
     * Space Complexity:
     * - O(m) where m is the maximum nesting depth of brackets.
     */
    public static String decodeStringOptimized(String s) {
        Stack<Integer> numbers = new Stack<>();
        Stack<StringBuilder> words = new Stack<>();
        StringBuilder currWord = new StringBuilder();
        int currNum = 0;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // Case 1: Handle letters - append to current word
            if(c >= 'a' && c <= 'z') {
                currWord.append(c);
            }
            // Case 2: Handle numbers - build complete number
            else if(c >= '0' && c <= '9') {
               currNum = currNum * 10 + (c - '0');
            }
            // Case 3: Handle opening bracket - save current state
            else if(c == '[') {
                numbers.push(currNum);
                words.push(currWord);
                // Reset current state
                currNum = 0;
                currWord = new StringBuilder();
            }
            // Case 4: Handle closing bracket - process repetition
            else if(c == ']') {
                int count = numbers.pop();
                StringBuilder temp = words.pop();
                // Repeat current word 'count' times
                while(count-- > 0) {
                    temp.append(currWord);
                }
                currWord = temp;
            }
        }

        return currWord.toString();
    }
}
