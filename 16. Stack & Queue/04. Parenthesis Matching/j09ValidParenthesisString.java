/**
 * Problem Statement:
 *     Given a string containing only three types of characters: '(', ')' and '*',
 *     check whether the input string is valid. '*' can be treated as '(', ')' or empty.
 *     https://leetcode.com/problems/valid-parenthesis-string/
 * 
 * Input:
 *     String containing '(', ')', and '*' characters
 * 
 * Output:
 *     true if string can be valid, false otherwise
 * 
 * Example 1:
 *     Input: "(*)"
 *     Output: true
 *     Explanation: '*' can be empty, making "()"
 * 
 * Example 2:
 *     Input: "(**)"
 *     Output: true
 *     Explanation: '*' can be empty or ')', making "()" or "(())"
 */

 import java.util.Stack;

 public class j09ValidParenthesisString {
 
     /**
      * Main method to demonstrate parenthesis string validation
      */
     public static void main(String args[]) {
         String[] tests = {
             "(*)",    // true - '*' can be empty
             "(*())",  // true - '*' can be '('
             "(**)",   // true - '*' can be empty or ')'
             "(*)))",  // false - not enough opening brackets
             "((*))"   // true - '*' can be ')'
         };
 
         for (String test : tests) {
             System.out.printf("Input: %s -> Valid: %b%n", test, checkValidString(test));
         }
     }
 
     /**
      * Check if string can be valid with '*' wildcards
      * 
      * Intuition:
      * 1. Use two stacks to track positions:
      *    - opens: store indices of '('
      *    - stars: store indices of '*'
      * 2. For closing brackets:
      *    - First try to match with opening bracket
      *    - If no opening, try to use a star
      * 3. After processing:
      *    - Match remaining opens with stars
      *    - Stars must come after opens to be valid
      * 
      * Example Walkthrough:
      * "(*)" →
      * ( → opens:[0]
      * * → stars:[1]
      * ) → matched with '('
      * Final: All matched = valid
      * 
      * "(**)" →
      * ( → opens:[0]
      * * → stars:[1]
      * * → stars:[1,2]
      * ) → matched with '('
      * Final: All matched = valid
      * 
      * Time Complexity: O(n) - single pass through string
      * Space Complexity: O(n) - two stacks can store all chars
      * 
      * @param s input string containing '(', ')', and '*'
      * @return true if string can be valid, false otherwise
      */
     public static boolean checkValidString(String s) {
         Stack<Integer> opens = new Stack<>();    // Track '(' positions
         Stack<Integer> stars = new Stack<>();    // Track '*' positions
         
         // Process each character
         for (int i = 0; i < s.length(); i++) {
             char c = s.charAt(i);
             if (c == '(') {
                 opens.push(i);
             } else if (c == '*') {
                 stars.push(i);
             } else {  // closing bracket
                 // Try to match with opening bracket first
                 if (!opens.isEmpty()) {
                     opens.pop();
                 }
                 // If no opening bracket, try to use a star
                 else if (!stars.isEmpty()) {
                     stars.pop();
                 }
                 // No match possible
                 else {
                     return false;
                 }
             }
         }
 
         // Check if remaining opens can be matched with stars
         if (opens.size() > stars.size()) {
             return false;  // Not enough stars
         }
 
         // Stars must come after opens to be valid
         while (!opens.isEmpty()) {
             int openIdx = opens.pop();
             int starIdx = stars.pop();
             if (openIdx > starIdx) {
                 return false;  // Star comes before open, invalid
             }
         }
         
         return true;
     }
 }