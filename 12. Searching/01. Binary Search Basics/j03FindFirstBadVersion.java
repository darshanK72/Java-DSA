/*-
 * Problem Statement:
 * 
 *     You are given a version control system where some versions are known to be good and others are bad. 
 *     You need to find the first bad version.
 * 
 *     - You are given an integer `n`, representing the total number of versions.
 *     - You are also given an integer `bad`, representing the first bad version number.
 *     - Your task is to implement a method that returns the first bad version.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 2 * 10^4), representing the total number of versions.
 *     - An integer `bad` (1 <= bad <= n), representing the first bad version.
 * 
 * Output:
 *     - The integer representing the first bad version.
 * 
 * Example:
 *     Input:
 *     5 4
 *     Output:
 *     4
 *     
 *     Explanation:
 *     The first bad version is version `4`, so the output is `4`.
 */

import java.util.Scanner;

public class j03FindFirstBadVersion {
    private int bad;

    public j03FindFirstBadVersion(int bad) {
        this.bad = bad; // Store the first bad version
    }

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: total number of versions
        int bad = in.nextInt(); // Input: the first bad version number
        j03FindFirstBadVersion badVersion = new j03FindFirstBadVersion(bad); // Create object with first bad version
        System.out.println(badVersion.firstBadVersion(n)); // Output: first bad version
        in.close();
    }

    /*-
     * Approach 1: Binary Search for First Bad Version
     * 
     * Intuition:
     * - We can use binary search to efficiently find the first bad version. 
     * - The idea is to start with the range from version `1` to `n`.
     * - At each step, we check the middle version (`mid`):
     *     - If it's not a bad version, it means the first bad version must be after it, so we move the start of the range.
     *     - If it's a bad version, it means the first bad version is either `mid` or somewhere before it, so we move the end of the range.
     * - The process continues until the range converges to the first bad version.
     * 
     * Time Complexity:
     * - O(log n), because we are performing binary search.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used.
     * 
     * @param n The total number of versions.
     * @return The first bad version number.
     */
    public int firstBadVersion(int n) {
        int s = 1; // Start index
        int e = n; // End index
        while (s <= e) {
            int mid = s + ((e - s) / 2); // Calculate mid index
            if (!isBadVersion(mid)) {
                s = mid + 1; // Move the start index to mid + 1 if mid is not a bad version
            } else {
                e = mid - 1; // Move the end index to mid - 1 if mid is a bad version
            }
        }
        return s; // The first bad version is found at index s
    }

    /*-
     * Helper Function: isBadVersion
     * 
     * This function simulates the check to see if a version is bad. In the context of the problem,
     * this method would be provided by the system and here we simulate it.
     * 
     * Time Complexity:
     * - O(1), as it's a simple comparison.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used.
     * 
     * @param n The version number to check.
     * @return True if the version is bad, otherwise false.
     */
    public boolean isBadVersion(int n) {
        if (n < this.bad) {
            return false; // Return false if the version is not bad
        }
        return true; // Return true if the version is bad
    }
}
