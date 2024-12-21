/**
 * Problem Statement:
 * 
 *     Given two version numbers `version1` and `version2`, compare them.
 *     Version numbers are represented as strings in the form of "x1.x2.x3..." where xi are non-negative integers. 
 *     For example, "1.01", "1.001", and "1.0" are equivalent versions of the same number.
 * 
 *     Your task is to implement a function that compares the two version numbers:
 *     - If `version1 > version2` return 1.
 *     - If `version1 < version2` return -1.
 *     - Otherwise, return 0 if both versions are equal.
 * 
 * Input:
 *     - Two strings `version1` and `version2` (1 <= version1.length, version2.length <= 100).
 *     - Both `version1` and `version2` consist of numeric strings separated by dots.
 * 
 * Output:
 *     - An integer: 1, -1, or 0 based on the comparison between the two versions.
 * 
 * Example:
 *     Input:
 *     "1.2"
 *     "1.10"
 *     Output:
 *     -1
 * 
 *     Explanation:
 *     - In the second revision, "2" is less than "10", so version1 is smaller than version2.
 * 
 * Example:
 *     Input:
 *     "1.01"
 *     "1.001"
 *     Output:
 *     0
 * 
 *     Explanation:
 *     - Both versions represent the same number "1" when ignoring the leading zeros.
 * 
 * Example:
 *     Input:
 *     "1.0"
 *     "1.0.0.0"
 *     Output:
 *     0
 * 
 *     Explanation:
 *     - Missing revisions in version1 are treated as "0".
 */

import java.util.Scanner;

public class j12CompareVersions {
    public static void main(String args[]) {
        // Reading input for two version strings
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();

        // Outputting the result of the comparison
        System.out.println(compareVersion(s1, s2));
        in.close();
    }

    /**
     * Approach: Iterative Comparison of Each Version Component
     * 
     * Intuition:
     * - The core idea is to split both version strings by the dot ('.') and compare each corresponding version component (integer values).
     * - We iterate over the components of both version strings, and for each pair of components:
     *     - If a component of version1 is greater than the corresponding component in version2, return 1.
     *     - If a component of version2 is greater than the corresponding component in version1, return -1.
     *     - If the components are equal, continue to the next component.
     * - If one version string ends and the other still has components left, treat the missing components as zero and continue the comparison.
     * 
     * Time Complexity:
     * - O(n + m), where `n` and `m` are the lengths of version1 and version2, respectively. We traverse both strings fully, splitting and comparing each version component.
     * 
     * Space Complexity:
     * - O(1), since we only use a few variables to track the current positions and version components.
     * 
     * @param version1 The first version string.
     * @param version2 The second version string.
     * @return 1 if version1 > version2, -1 if version1 < version2, and 0 if they are equal.
     */
    public static int compareVersion(String version1, String version2) {
        int i = 0; // Pointer for version1
        int j = 0; // Pointer for version2

        // Continue comparing until both strings are completely traversed
        while (i < version1.length() || j < version2.length()) {
            int v1 = 0; // Store the current component of version1
            int v2 = 0; // Store the current component of version2

            // Extract the next component from version1
            while (i < version1.length() && version1.charAt(i) != '.') {
                v1 = v1 * 10 + version1.charAt(i) - '0'; // Build the integer component
                i++;
            }
            i++; // Skip the dot

            // Extract the next component from version2
            while (j < version2.length() && version2.charAt(j) != '.') {
                v2 = v2 * 10 + version2.charAt(j) - '0'; // Build the integer component
                j++;
            }
            j++; // Skip the dot

            // Compare the components
            if (v1 > v2)
                return 1; // version1 is greater
            if (v2 > v1)
                return -1; // version2 is greater
        }

        // If all components are equal, return 0
        return 0;
    }
}
