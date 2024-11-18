import java.util.Scanner;

public class j06DivisibleBy9 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String n = in.next();
        System.out.println(isDivisibleBy9(n));
        in.close();
    }

    // Compute sum of digits
    // Check if sum of digits is divisible by 9.
    public static boolean isDivisibleBy9(String str) {
        int n = str.length();
        int digitSum = 0;
        for (int i = 0; i < n; i++)
            digitSum += (str.charAt(i) - '0');

        return (digitSum % 9 == 0);
    }
}
