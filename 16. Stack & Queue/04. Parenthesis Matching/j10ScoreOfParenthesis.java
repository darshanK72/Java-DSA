/**
 * Problem Statement:
 *     Given a balanced parentheses string s, compute the score of the string based on the following rule:
 *     - "()" has score 1
 *     - AB has score A + B, where A and B are balanced parentheses strings
 *     - (A) has score 2 * A, where A is a balanced parentheses string
 *     https://leetcode.com/problems/score-of-parentheses/
 * 
 * Input:
 *     String containing only parentheses '(' and ')'
 * 
 * Output:
 *     Integer score based on the scoring rules
 * 
 * Example 1:
 *     Input: "()"
 *     Output: 1
 * 
 * Example 2:
 *     Input: "(()(()))"
 *     Output: 6
 *     Explanation: (()(()))
 *                = 2 * (1 + 2 * 1)
 *                = 2 * (1 + 2)
 *                = 2 * 3
 *                = 6
 */

import java.util.Stack;

public class j10ScoreOfParenthesis {

    /**
     * Main method to demonstrate parentheses scoring
     * Shows results for test cases
     */
    public static void main(String[] args) {
        String[] tests = {
            "()",           // 1
            "(())",         // 2
            "(()(()))",     // 6
            "(()())",       // 4
            "((()))"        // 4
        };

        for (String test : tests) {
            System.out.printf("Input: %s -> Score: %d%n", 
                            test, scoreOfParentheses(test));
        }
    }

    /**
     * Calculate score of balanced parentheses string
     * 
     * Intuition:
     * 1. Use stack to track scores at each nesting level
     * 2. For opening bracket:
     *    - Save current score on stack
     *    - Reset score for new level
     * 3. For closing bracket:
     *    - Double current level's score (or use 1 if empty)
     *    - Add to previous level's score
     * 
     * Algorithm Steps:
     * 1. For '(':
     *    - Push current score to stack
     *    - Reset score to 0
     * 2. For ')':
     *    - Calculate: prev_score + max(2 * current_score, 1)
     *    - Pop stack to move up one level
     * 
     * Example Walkthrough:
     * "(()(()))"
     * ( → stack:[0], score=0
     * ( → stack:[0,0], score=0
     * ) → stack:[0], score=1
     * ( → stack:[1,0], score=0
     * ( → stack:[1,0,0], score=0
     * ) → stack:[1,0], score=1
     * ) → stack:[1], score=2
     * ) → stack:[], score=6
     * 
     * Time Complexity: O(n) - single pass through string
     * Space Complexity: O(n) - stack can store n/2 scores
     * 
     * @param s input balanced parentheses string
     * @return computed score based on rules
     */
    public static int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int score = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(score);  // Save current score
                score = 0;         // Reset for new level
            } else {
                // Calculate: previous_score + max(2 * current_score, 1)
                score = stack.peek() + Math.max(2 * score, 1);
                stack.pop();       // Move up one level
            }
        }
        
        return score;
    }

    /**
     * Alternative Approach: Using Depth Counter
     * 
     * Intuition:
     * 1. Track depth of nesting using counter
     * 2. Add score only at base pairs "()"
     * 3. Score at depth d is 2^d
     * 
     * Time: O(n)
     * Space: O(1)
     */
    public static int scoreOfParenthesesEfficient(String s) {
        int depth = 0;
        int score = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                if (s.charAt(i-1) == '(') {
                    score += 1 << depth;  // 2^depth
                }
            }
        }
        
        return score;
    }
}
