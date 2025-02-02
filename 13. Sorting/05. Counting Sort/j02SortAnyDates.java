/**
 * Problem Statement:
 * 
 *     Given a list of dates, each represented as an array of three integers [day, month, year],
 *     sort the list of dates in ascending order. The sorting should be performed efficiently
 *     even for large lists by leveraging the Counting Sort algorithm, which is particularly
 *     useful when the range of input values (day, month, year) is limited.
 * 
 * Input:
 *     - A 2D integer array `dates` of size `n x 3`, where each subarray represents a date:
 *         * dates[i][0] is the day (1 <= day <= 31)
 *         * dates[i][1] is the month (1 <= month <= 12)
 *         * dates[i][2] is the year (2000 <= year <= 2100)
 * 
 * Output:
 *     - The sorted 2D array of dates in ascending order (sorted by year, then month, then day).
 * 
 * Example:
 *     Input:
 *         dates = [[15, 8, 2020], [1, 1, 2000], [31, 12, 2010], [1, 1, 2020]]
 *     Output:
 *         [[1, 1, 2000], [31, 12, 2010], [1, 1, 2020], [15, 8, 2020]]
 * 
 *     Explanation:
 *         The dates are first sorted by day and month using a stable Counting Sort,
 *         then finally by year. The final sorted order is achieved because the stable
 *         sorts preserve the relative ordering of equal elements.
 */

import java.util.Arrays;

public class j02SortAnyDates {

    public static void main(String[] args) {
        // Example usage:
        int[][] dates = {
                { 15, 8, 2020 },
                { 1, 1, 2000 },
                { 31, 12, 2010 },
                { 1, 1, 2020 }
        };

        // Approach 1: Counting Sort based approach (LSD stable sort)
        int[][] sortedDates = sortBigListDates(dates);
        System.out.println("Sorted Dates (Counting Sort):");
        for (int[] date : sortedDates) {
            System.out.printf("%02d-%02d-%d\n", date[0], date[1], date[2]);
        }

        // Approach 2: Alternative Approach using Java's built-in sort with custom
        // comparator
        int[][] datesCopy = {
                { 15, 8, 2020 },
                { 1, 1, 2000 },
                { 31, 12, 2010 },
                { 1, 1, 2020 }
        };
        sortBigListDatesBuiltInSort(datesCopy);

        System.out.println("\nSorted Dates (Built-in Sort):");
        for (int[] date : datesCopy) {
            System.out.printf("%02d-%02d-%d\n", date[0], date[1], date[2]);
        }
    }

    /**
     * Approach 1: Alternative Approach using Java's built-in sort with custom comparator
     */
    public static void sortBigListDatesBuiltInSort(int[][] dates) {
        // Sort the dates array using Arrays.sort with a custom comparator
        Arrays.sort(dates, (a, b) -> {
            // Compare years
            if (a[2] != b[2]) {
                return a[2] - b[2];
                // Compare months if years are the same
            } else if (a[1] != b[1]) {
                return a[1] - b[1];
                // Compare days if years and months are the same
            } else {
                return a[0] - b[0];
            }
        });
    }

    /**
     * Approach 2: Counting Sort based approach (LSD Stable Sort)
     * 
     * Intuition:
     * - We leverage Counting Sort, which is efficient for sorting data when the range of
     *   input values is small relative to the number of elements. Since the date components
     *   (day, month, year) have limited ranges, Counting Sort can be applied in multiple
     *   passes to achieve a stable sort.
     * - The idea is to sort the dates starting with the least significant component (day),
     *   followed by month, and finally the most significant (year). Each pass is a stable
     *   sort which preserves the order of the previously sorted keys.
     * - This guarantees that after the final pass, the dates are correctly sorted by year,
     *   then month, and then day.
     * 
     * Time Complexity:
     * - O(n + k) per counting sort pass, where n is the number of dates and k is the range
     *   of values for that particular date component. Since we perform three passes, the overall
     *   time complexity is O(3 * (n + k)) which simplifies to O(n) for practical ranges.
     * 
     * Space Complexity:
     * - O(n + k) for each pass due to the auxiliary count and output arrays.
     * 
     * @param dates A 2D array where each subarray represents a date [day, month, year].
     * @return The sorted array of dates.
     */
    public static int[][] sortBigListDates(int[][] dates) {
        // Sort by day (index 0): days range from 1 to 31.
        countingSort(dates, 0, 31, 1);
        // Sort by month (index 1): months range from 1 to 12.
        countingSort(dates, 1, 12, 1);
        // Sort by year (index 2): years range from 2000 to 2100 (range 101, but
        // parameter provided is 100).
        countingSort(dates, 2, 100, 2000);
        return dates;
    }

    /**
     * Helper Method: countingSort
     * 
     * Intuition:
     * - This helper method performs a stable Counting Sort on the 2D date array based on the
     *   specified index (day, month, or year). It counts the frequency of each value after
     *   adjusting with the `minValue`, computes the cumulative count to determine the correct
     *   positions, and finally builds the sorted output array.
     * 
     * Explanation:
     * - Count the occurrences of each value (adjusted by `minValue`).
     * - Transform the frequency array into a prefix sum (cumulative count) array.
     * - Traverse the original array in reverse order to maintain stability and place each element
     *   in the correct position in the output array.
     * - Copy the sorted output back into the original array.
     * 
     * Time Complexity:
     * - O(n + range), where n is the number of dates and range is the range of values for the
     *   current index.
     * 
     * Space Complexity:
     * - O(n + range) due to the additional count and output arrays.
     * 
     * @param dates   A 2D array where each subarray represents a date [day, month, year].
     * @param index   The index to sort by (0 for day, 1 for month, 2 for year).
     * @param range   The range of possible values for this index.
     * @param minValue The minimum possible value for this index.
     */
    public static void countingSort(int[][] dates, int index, int range, int minValue) {
        int n = dates.length;
        int[] count = new int[range + 1];
        int[][] output = new int[n][3];

        // Count occurrences of each value at the given index.
        for (int i = 0; i < n; i++) {
            count[dates[i][index] - minValue]++;
        }

        // Compute the cumulative frequency (prefix sum) to determine positions.
        for (int i = 1; i <= range; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array in a stable manner by iterating from right to left.
        for (int i = n - 1; i >= 0; i--) {
            int value = dates[i][index] - minValue;
            output[count[value] - 1] = dates[i];
            count[value]--;
        }

        // Copy the sorted output back into the original dates array.
        for (int i = 0; i < n; i++) {
            dates[i] = output[i];
        }
    }
}
