/**
 * GeeksForGeeks. Minimum Swaps for Bracket Balancing
 * 
 * Problem Statement:
 *     You are given a string S of 2N characters consisting of N '[' brackets and N ']'
 *     brackets. A string is considered balanced if it can be represented in the form
 *     S2[S1] where S1 and S2 are balanced strings. We can make any number of swaps
 *     between any two brackets. Find the minimum number of swaps required to make the
 *     string balanced.
 * 
 * Input:
 *     - String s (1 <= |s| <= 10^5)
 *     - String contains only '[' and ']' characters
 *     - Number of '[' equals number of ']'
 * 
 * Output:
 *     - Return the minimum number of swaps required to make the string balanced
 * 
 * Example:
 *     Input: s = "[]][]["
 *     Output: 2
 * 
 *     Explanation:
 *     First swap: Position 3 and 4
 *     "[]][][" -> "[]][]["
 *     Second swap: Position 5 and 6
 *     "[]][][" -> "[][][]"
 *     Now the string is balanced.
 */

public class j09MinumSwapsForBracketBalancing {

    /**
     * Approach: Greedy with Counter
     * 
     * Intuition:
     * - We need to track the number of unbalanced brackets
     * - When we find a closing bracket that creates imbalance, we need to swap it
     * - The number of swaps needed is equal to the number of unbalanced brackets
     * - We can keep track of open and close brackets to determine imbalance
     * 
     * Explanation:
     * - Step 1: Initialize counters for open brackets, close brackets, and unbalanced count
     * - Step 2: For each character in the string:
     *   * If it's an opening bracket:
     *     - Increment open count
     *     - If there are unbalanced brackets, add to swaps and decrease unbalanced count
     *   * If it's a closing bracket:
     *     - Increment close count
     *     - Calculate new unbalanced count (close - open)
     * - Step 3: Return total number of swaps
     * 
     * Time Complexity: O(n) where n is the length of the string
     *                  - Single pass through the string
     * Space Complexity: O(1) as we only use a constant amount of extra space
     * 
     * @param s    String containing only '[' and ']' characters
     * @return     Minimum number of swaps required to balance the string
     */
    public static int minimumNumberOfSwaps(String s) {
        int open = 0;
        int close = 0;
        int unbalanced = 0;
        int swaps = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '['){
                open++;
                if(unbalanced > 0){
                    swaps += unbalanced;
                    unbalanced--;
                }
            }else{
                close++;
                unbalanced = (close - open);
            }
        }
        return swaps;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        String s1 = "[]][][";
        System.out.println("Input: s = \"[]][][\"");
        System.out.println("Expected: 2, Output: " + minimumNumberOfSwaps(s1));

        // Test Case 2: Already balanced
        System.out.println("\nAlready Balanced Case:");
        String s2 = "[][]";
        System.out.println("Input: s = \"[][]\"");
        System.out.println("Expected: 0, Output: " + minimumNumberOfSwaps(s2));

        // Test Case 3: All closing brackets first
        System.out.println("\nAll Closing First Case:");
        String s3 = "]]][[[";
        System.out.println("Input: s = \"]]][[[\"");
        System.out.println("Expected: 2, Output: " + minimumNumberOfSwaps(s3));

        // Test Case 4: Alternating brackets
        System.out.println("\nAlternating Brackets Case:");
        String s4 = "][][";
        System.out.println("Input: s = \"][][\"");
        System.out.println("Expected: 1, Output: " + minimumNumberOfSwaps(s4));
    }
}
