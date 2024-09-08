import java.util.Scanner;

public class j19CommonDivisors {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();
        System.out.println(commDiv(a, b));
        in.close();
    }

    public static long commDiv(long a, long b) {
        long c = 0;
        for (long i = 1; i <= Math.min(a, b); i++) {
            if (a % i == 0 && b % i == 0) {
                c++;
            }
        }
        return c;
    }
}
