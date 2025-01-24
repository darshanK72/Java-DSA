/**
 * Problem Statement:
 * 
 *      Given a sorted array of integers `citations`, where each element represents the number of citations a paper has received, 
 *      calculate the H-Index. The H-Index is defined as the maximum value `h` such that the researcher has published `h` papers 
 *      that have each been cited at least `h` times.
 * 
 * Input:
 *     - An array `citations` of integers, sorted in non-decreasing order.
 * 
 * Output:
 *     - An integer representing the H-Index.
 * 
 * Example:
 *     Input:
 *         citations = {0, 1, 3, 5, 6}
 *     Output:
 *         H-Index = 3
 * 
 * Constraints:
 *     - The array is sorted in ascending order.
 *     - Array length can vary from 1 to 10^5.
 */

public class j05HIndexII {
    public static void main(String[] args) {
        // Example Input: Citations array
        int[] citations = { 0, 1, 3, 5, 6 };

        // Calculate the H-Index
        int hIndex = hIndex(citations);

        // Output the H-Index
        System.out.println("The H-Index is: " + hIndex);
    }

    /**
     * Calculate the H-Index using Binary Search
     * 
     * Intuition:
     * - The array is sorted in ascending order, allowing us to leverage binary search to find the H-Index efficiently.
     * - For a given index `h` in the array, the condition to check is:
     *       citations[h] >= citations.length - h
     *   If true, it implies that there are `citations.length - h` papers with at least `citations.length - h` citations.
     * - Adjust the binary search bounds (`s` and `e`) based on whether the current index satisfies the H-Index condition.
     * 
     * Steps:
     * 1. Initialize two pointers `s` (start) and `e` (end) for the binary search.
     * 2. Perform binary search:
     *    - Calculate the mid-point `h`.
     *    - If `citations[h] == citations.length - h`, return `citations[h]` as the H-Index.
     *    - If `citations[h] < citations.length - h`, move `s` to `h + 1`.
     *    - Otherwise, move `e` to `h - 1`.
     * 3. If the loop ends, the H-Index is `citations.length - s`.
     * 
     * Time Complexity:
     * - O(log n): Binary search on the array of length `n`.
     * 
     * Space Complexity:
     * - O(1): Constant space usage.
     * 
     * @param citations The sorted array of citations.
     * @return The H-Index value.
     */
    public static int hIndex(int[] citations) {
        int s = 0; // Start of the search range
        int e = citations.length - 1; // End of the search range

        // Binary search to find the H-Index
        while (s <= e) {
            int h = s + (e - s) / 2; // Calculate the mid-point

            // Check if the current index satisfies the H-Index condition
            if (citations[h] == citations.length - h) {
                return citations[h]; // Return the H-Index
            } else if (citations[h] < citations.length - h) {
                s = h + 1; // Move to the right half
            } else {
                e = h - 1; // Move to the left half
            }
        }

        // If the loop ends, the H-Index is determined by the remaining papers
        return citations.length - s;
    }
}
