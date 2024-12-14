/*-
 * Problem Statement:
 *
 *     You are given a date consisting of the day, month, and year. The task is to return the day of the week for that specific date.
 *     The week starts with Sunday and ends with Saturday.
 *
 * Input:
 *     - Three integers representing the day, month, and year.
 * 
 * Output:
 *     - A string representing the day of the week for the given date.
 * 
 * Example:
 *     Input: 5 12 2024
 *     Output: "Thursday"
 * 
 *     Explanation: The given date is December 5th, 2024, and it falls on a Thursday.
 */

import java.util.Scanner;

public class j23DayOfWeek {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int day = in.nextInt();
        int month = in.nextInt();
        int year = in.nextInt();
        System.out.println(dayOfTheWeek(day, month, year));
        System.out.println(dayOfTheWeekZeller(day, month, year));
        in.close();
    }

    /*-
     * Approach: Days Calculation Tll the Date
     * 
     * Intuition:
     * - The approach calculates the number of days from the start of a base date (e.g., January 1, 0001) to the given date.
     * - This total number of days is then used to determine the day of the week by taking the modulus with 7 (because a week has 7 days).
     * - The calculation accounts for leap years, and the result is used to index into an array of day names.
     * 
     * Time Complexity:
     * - O(1): The operations (splitting the date and calculating the total days) are constant-time operations.
     * 
     * Space Complexity:
     * - O(1): Only a small fixed number of variables and arrays are used.
     * 
     * @param day The day of the month.
     * @param month The month of the year.
     * @param year The year.
     * @return The name of the day of the week for the given date.
     */
    public static String dayOfTheWeek(int day, int month, int year) {
        // Array representing the number of days in each month (non-leap year)
        int[] monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        // Days of the week, where index 0 represents Sunday
        String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

        // Calculate total number of days from base date (considering the year, month,
        // and day)
        int countDays = year * 365 + day - 1;

        // If the month is January or February, we adjust the year for leap year
        // calculation
        if (month <= 2)
            year--;

        // Calculate the number of leap years before the current year
        int leapYears = year / 4 - year / 100 + year / 400;
        countDays += leapYears;

        // Add the days of the months before the given month
        for (int i = 0; i < month - 1; i++) {
            countDays += monthDays[i];
        }

        // Return the day of the week by finding the modulus with 7
        return days[countDays % 7];
    }

    /*-
     * Approach: Zeller's Algorithm for Day of the Week Calculation.
     * 
     * Intuition:
     * Zeller's algorithm uses a formula to compute the day of the week based on the input date.
     * By using the formula, we can derive the day of the week (0 to 6), which corresponds to
     * a specific day: 0 = Saturday, 1 = Sunday, 2 = Monday, etc.
     * 
     * Time Complexity:
     * O(1): The algorithm performs a constant number of operations.
     * 
     * Space Complexity:
     * O(1): The algorithm uses a constant amount of space.
     * 
     * @param day The day of the month.
     * @param month The month of the year.
     * @param year The year.
     * @return The name of the day of the week for the given date.
     */
    public static String dayOfTheWeekZeller(int day, int month, int year) {
        // Adjust month and year for January and February (treated as 13, 14 of the
        // previous year)
        if (month <= 2) {
            month += 12;
            year -= 1;
        }

        int K = year % 100; // Year of the century
        int J = year / 100; // Zero-based century

        // Zeller's formula to compute day of the week
        int h = (day + (13 * (month + 1)) / 5 + K + K / 4 + J / 4 + 5 * J) % 7;

        // Array of days of the week corresponding to the result of Zeller's formula
        String[] daysOfWeek = { "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };

        // Return the day of the week as a string
        return daysOfWeek[h];
    }
}
