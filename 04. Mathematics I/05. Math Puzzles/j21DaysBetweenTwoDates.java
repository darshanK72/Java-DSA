import java.util.Scanner;

public class j21DaysBetweenTwoDates {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String date1 = in.next();
        String date2 = in.next();
        System.out.println(daysBetweenDates(date1, date2));
        in.close();
    }

    public static int daysBetweenDates(String date1, String date2) {
        int[] yearDays = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        String[] date1Year = date1.split("-");
        String[] date2Year = date2.split("-");
        int year1 = Integer.parseInt(date1Year[0]);
        int month1 = Integer.parseInt(date1Year[1]);
        int days1 = Integer.parseInt(date1Year[2]);

        int year2 = Integer.parseInt(date2Year[0]);
        int month2 = Integer.parseInt(date2Year[1]);
        int days2 = Integer.parseInt(date2Year[2]);

        int daysTillDate1 = year1 * 365 + days1;
        for (int i = 0; i < month1 - 1; i++) {
            daysTillDate1 += yearDays[i];
        }

        daysTillDate1 += countLeapYears(year1, month1);

        int daysTillDate2 = year2 * 365 + days2;
        for (int i = 0; i < month2 - 1; i++) {
            daysTillDate2 += yearDays[i];
        }

        daysTillDate2 += countLeapYears(year2, month2);

        return Math.abs(daysTillDate2 - daysTillDate1);

    }

    public static int countLeapYears(int year, int month) {
        if (month <= 2) {
            year--;
        }

        return year / 4 - year / 100 + year / 400;
    }
}
