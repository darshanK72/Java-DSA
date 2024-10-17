import java.util.Scanner;

// Complexity : O(log N)
public class j01SquareRootIntegral {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.printf("Square Root of %d is %f", n, sqrt(n));
        System.out.printf("Square Root of %d is %f", n, sqrtEfficient(n));
        in.close();
    }

    public static int sqrt(int x) {
        long ans = 0;
        for (long i = 1; i <= x; i++) {
            if (i * i <= x) {
                ans = i;
            } else {
                break;
            }
        }
        return (int) ans;
    }

    public static int sqrtEfficient(int x) {
        long s = 1;
        long e = x;
        long ans = 0;
        while (s <= e) {
            long mid = s + (e - s) / 2;
            if (mid * mid <= x) {
                ans = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return (int) ans;
    }
}
