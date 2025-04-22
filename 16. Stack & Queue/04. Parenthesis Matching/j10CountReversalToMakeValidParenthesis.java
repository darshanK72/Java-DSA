/**
 * Problem Statement:
 *     Given a string of curly brackets '{' and '}', find the minimum number of reversals
 *     required to make the string balanced. Return -1 if it cannot be balanced.
 * 
 * Input:
 *     String containing only curly brackets '{' and '}'
 * 
 * Output:
 *     Minimum number of reversals needed, or -1 if impossible
 * 
 * Example 1:
 *     Input: "}{" 
 *     Output: 2
 *     Explanation: Reverse both brackets: "{}"
 * 
 * Example 2:
 *     Input: "{{{{}}}}}}}"
 *     Output: 3
 *     Explanation: Convert last three '}' to '{': "{{{{}}}}{{{"
 */

public class j10CountReversalToMakeValidParenthesis {

    public static void main(String[] args) {
        // Test cases
        String[] tests = {
            "{{{{}}}}}}}}",  // 3
            "}{",            // 2
            "{{{",           // -1
            "{}{",           // 1
            "}}{{"           // 2
        };

        for (String test : tests) {
            System.out.printf("Input: %s -> Reversals needed: %d%n", 
                            test, countMinReversals(test));
        }
    }

    /**
     * Count minimum reversals needed to make string balanced
     * 
     * Intuition:
     * 1. Track unmatched brackets:
     *    - 'open': counts extra opening brackets
     *    - 'close': counts extra closing brackets at start
     * 
     * 2. Processing:
     *    - For '{': increment open count
     *    - For '}': 
     *      * If open > 0: match with existing opening
     *      * If open = 0: increment close count (unmatched)
     * 
     * 3. Final calculation:
     *    - Impossible if total unmatched (open + close) is odd
     *    - For odd number of open brackets: need extra reversal
     *    - Formula: ceil(open/2) + ceil(close/2)
     * 
     * Example: "}}{{" (close=2, open=2)
     * }} → close=2 (no open to match)
     * {{ → open=2
     * Need 2 reversals: "}{}{" → "{}{}"
     * 
     * Time Complexity: O(n) - single pass through string
     * Space Complexity: O(1) - only uses counters
     * 
     * @param s input string of curly brackets
     * @return minimum reversals needed or -1 if impossible
     */
    public static int countMinReversals(String s) {
        // Track unmatched brackets
        int open = 0;   // counts excess opening brackets
        int close = 0;  // counts unmatched closing brackets

        // Process each bracket
        for (char c : s.toCharArray()) {
            if (c == '{') {
                open++;  // new opening bracket
            } else {
                if (open > 0) {
                    open--;  // match with existing opening
                } else {
                    close++;  // unmatched closing bracket
                }
            }
        }

        // Calculate total unmatched brackets
        int totalUnmatched = open + close;

        // Check if possible to balance
        if (totalUnmatched % 2 == 1) {
            return -1;  // odd count cannot be balanced
        }

        // Calculate minimum reversals needed
        if (open % 2 == 1) {
            // Need extra reversal when odd number of open brackets
            return totalUnmatched / 2 + 1;
        }
        return totalUnmatched / 2;  // Even split of reversals
    }
}
