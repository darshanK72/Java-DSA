/**
 * Problem Statement:
 * 
 *     Given two dates in the format "yyyy-mm-dd", calculate the number of days between the two dates. 
 *     Consider leap years and the number of days in each month. 
 *     The function should return the absolute difference between the two dates in terms of days.
 * 
 * Input:
 *     - A string `date1` representing the first date in "yyyy-mm-dd" format.
 *     - A string `date2` representing the second date in "yyyy-mm-dd" format.
 * 
 * Output:
 *     - An integer representing the number of days between `date1` and `date2`.
 * 
 * Example:
 *     Input:
 *     "2021-01-01", "2021-12-31"
 *     Output:
 *     364
 * 
 *     Explanation:
 *     The difference between 1st January 2021 and 31st December 2021 is 364 days.
 */

import java.util.Scanner;

public class j22DaysBetweenTwoDates {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String date1 = in.next(); // Input: first date
        String date2 = in.next(); // Input: second date
        System.out.println(daysBetweenDates(date1, date2)); // Output the number of days
        in.close();
    }

    /**
     * Approach: Calculate the number of days between the two given dates.
     * 
     * Intuition:
     * - Convert both dates into the total number of days from a reference point (e.g., 1st January 1 AD).
     * - Compute the difference in days between these two total day counts.
     * - Handle leap years and different month lengths.
     * 
     * Time Complexity:
     * - O(1), since the operations involved are constant in terms of computation.
     * 
     * Space Complexity:
     * - O(1), since we are using a fixed amount of extra space.
     * 
     * @param date1 The first date in "yyyy-mm-dd" format.
     * @param date2 The second date in "yyyy-mm-dd" format.
     * @return The absolute difference in days between the two dates.
     */
    public static int daysBetweenDates(String date1, String date2) {
        // Days for each month in a common year (non-leap year)
        int[] yearDays = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        // Split the date1 and date2 strings into year, month, and day components
        String[] date1Year = date1.split("-");
        String[] date2Year = date2.split("-");
        int year1 = Integer.parseInt(date1Year[0]);
        int month1 = Integer.parseInt(date1Year[1]);
        int days1 = Integer.parseInt(date1Year[2]);

        int year2 = Integer.parseInt(date2Year[0]);
        int month2 = Integer.parseInt(date2Year[1]);
        int days2 = Integer.parseInt(date2Year[2]);

        // Calculate the total number of days from the reference date (1st January 1)
        int daysTillDate1 = year1 * 365 + days1;
        for (int i = 0; i < month1 - 1; i++) {
            daysTillDate1 += yearDays[i]; // Add days for each month of year1
        }

        // Account for leap years for date1
        daysTillDate1 += countLeapYears(year1, month1);

        int daysTillDate2 = year2 * 365 + days2;
        for (int i = 0; i < month2 - 1; i++) {
            daysTillDate2 += yearDays[i]; // Add days for each month of year2
        }

        // Account for leap years for date2
        daysTillDate2 += countLeapYears(year2, month2);

        // Return the absolute difference in days between the two dates
        return Math.abs(daysTillDate2 - daysTillDate1);
    }

    /**
     * Helper Function: Count the number of leap years before the given year.
     * 
     * Intuition:
     * - A leap year occurs every 4 years, but years divisible by 100 are not leap years unless divisible by 400.
     * - This function counts leap years that have occurred before the specified year.
     * 
     * Time Complexity:
     * - O(1), constant time for calculating leap years.
     * 
     * Space Complexity:
     * - O(1), no extra space required.
     * 
     * @param year The year for which leap years need to be counted.
     * @param month The month of the given year to adjust the leap year calculation.
     * @return The number of leap years that have occurred up to the given month of the year.
     */
    public static int countLeapYears(int year, int month) {
        if (month <= 2) {
            year--; // If the month is January or February, consider the previous year for leap year
                    // counting.
        }

        // Leap year formula: A year is a leap year if it's divisible by 4, but not
        // divisible by 100, unless divisible by 400.
        return year / 4 - year / 100 + year / 400;
    }
}
