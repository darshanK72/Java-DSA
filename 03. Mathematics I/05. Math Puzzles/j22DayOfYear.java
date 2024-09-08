import java.util.Scanner;

public class j22DayOfYear {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String date = in.next();
        System.out.println(dayOfYear(date));
        in.close();
    }

    public static int dayOfYear(String date) {
        int[] daysInYear = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        String[] dates = date.split("-");
        // int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);
        int days = 0;
        for (int i = 1; i < month; i++) {
            days += daysInYear[i - 1];
        }
        if (month > 2) {
            if (month % 4 == 0) {
                if (month % 100 == 0 && month % 400 == 0) {
                    days++;
                } else if (month % 100 != 0) {
                    days++;
                }
            }
        }
        days += day;
        return days;
    }
}
