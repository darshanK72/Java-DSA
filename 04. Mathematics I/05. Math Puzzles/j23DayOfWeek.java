import java.util.Scanner;

public class j23DayOfWeek {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int day = in.nextInt();
        int month = in.nextInt();
        int year = in.nextInt();
        System.out.println(dayOfTheWeek(day, month,year));
        in.close();
    }

    public static String dayOfTheWeek(int day, int month, int year) {
        int[] monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        int countDays = year * 365 + day - 1;
        if (month <= 2)
            year--;
        int leapYears = year / 4 - year / 100 + year / 400;
        countDays += leapYears;
        for (int i = 0; i < month - 1; i++) {
            countDays += monthDays[i];
        }
        return days[countDays % 7];
    }
}
