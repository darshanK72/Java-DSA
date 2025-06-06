/*-
 * LeetCode 455 - Assign Cookies
 * 
 * Problem Statement:
 *     Assume you are an awesome parent and want to give your children some 
 *     cookies. But, you should give each child at most one cookie. Each child i 
 *     has a greed factor g[i], which is the minimum size of a cookie that the 
 *     child will be content with; and each cookie j has a size s[j]. If s[j] >= 
 *     g[i], we can assign the cookie j to the child i, and the child i will be 
 *     content. Your goal is to maximize the number of your content children and 
 *     output the maximum number.
 * 
 * Input:
 *     - g[] (int[]): Array of greed factors of children
 *     - s[] (int[]): Array of cookie sizes
 * 
 * Output:
 *     - Maximum number of content children
 * 
 * Example:
 *     Input: g = [1,2,3], s = [1,1]
 *     Output: 1
 * 
 *     Explanation:
 *     - Child 1 (g=1) can be satisfied with cookie 1 (s=1)
 *     - Child 2 (g=2) cannot be satisfied with remaining cookie (s=1)
 *     - Child 3 (g=3) cannot be satisfied
 *     - Maximum content children = 1
 */

import java.util.Arrays;

public class j02AssignCookies {
    
    /*-
     * Approach: Greedy
     * 
     * Intuition:
     * - To maximize the number of content children, we should match the smallest
     *   cookie that can satisfy each child's greed factor
     * - Sorting both arrays allows us to match cookies to children optimally
     * - If a cookie can't satisfy current child, it won't satisfy any child with
     *   higher greed factor
     * 
     * Explanation:
     * 1. Sort both arrays to process them in ascending order
     * 2. Use two pointers to track current child and cookie
     * 3. For each child:
     *    - Try to find the smallest cookie that can satisfy them
     *    - If found, increment count and move both pointers
     *    - If not found, move to next cookie
     * 
     * Time Complexity: O(N log N) where N is max(g.length, s.length)
     *                  - Sorting both arrays: O(N log N)
     *                  - Two pointer traversal: O(N)
     * Space Complexity: O(1) as we only use constant extra space
     * 
     * @param g    Array of greed factors of children
     * @param s    Array of cookie sizes
     * @return    Maximum number of content children
     */
    public static int findContentChildren(int[] g, int[] s) {
        // Sort both arrays to process them in ascending order
        Arrays.sort(g);
        Arrays.sort(s);
        
        int ans = 0;  // Count of content children
        int i = 0;    // Pointer for children array
        int j = 0;    // Pointer for cookies array
        
        // Process each child and cookie
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                // Current cookie can satisfy current child
                i++;
                ans++;
            }
            // Move to next cookie regardless of whether current child was satisfied
            j++;
        }
        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] g1 = {1, 2, 3};
        int[] s1 = {1, 1};
        System.out.println("Input: g=" + java.util.Arrays.toString(g1) + 
                         ", s=" + java.util.Arrays.toString(s1));
        System.out.println("Output: " + findContentChildren(g1, s1));

        // Test Case 2: All children can be satisfied
        System.out.println("\nAll Children Satisfied:");
        int[] g2 = {1, 2};
        int[] s2 = {1, 2, 3};
        System.out.println("Input: g=" + java.util.Arrays.toString(g2) + 
                         ", s=" + java.util.Arrays.toString(s2));
        System.out.println("Output: " + findContentChildren(g2, s2));

        // Test Case 3: Edge case - empty arrays
        System.out.println("\nEdge Case - Empty Arrays:");
        int[] g3 = {};
        int[] s3 = {};
        System.out.println("Input: g=" + java.util.Arrays.toString(g3) + 
                         ", s=" + java.util.Arrays.toString(s3));
        System.out.println("Output: " + findContentChildren(g3, s3));

        // Test Case 4: No cookies can satisfy any child
        System.out.println("\nNo Satisfiable Cookies:");
        int[] g4 = {2, 3, 4};
        int[] s4 = {1, 1, 1};
        System.out.println("Input: g=" + java.util.Arrays.toString(g4) + 
                         ", s=" + java.util.Arrays.toString(s4));
        System.out.println("Output: " + findContentChildren(g4, s4));
    }
}
