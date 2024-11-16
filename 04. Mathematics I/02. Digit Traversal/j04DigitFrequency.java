import java.util.Scanner;

public class j04DigitFrequency {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        int d = in.nextInt();
        System.out.print("Frequency of Digit " + n + "is " + digitFrequency(n, d));
        in.close();
    }

    public static int digitFrequency(long n, int d) {
        int count = 0;
        while (n > 0) {
            int t = (int) (n % 10);
            if (d == t) {
                count++;
            }
            n = n / 10;
        }
        return count;
    }
}
