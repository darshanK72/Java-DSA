import java.util.Scanner;

public class j02SquareRootDecimal {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        System.out.printf("Square Root of %d is %f", n, squareRoot(n, p));
        in.close();
    }

    public static double squareRoot(long n, int p) {
        long s = 1;
        long e = n;
        double root = 0.0;

        while (s <= e) {
            long m = s + (e - s) / 2;

            if (m * m == n) {
                root = m;
                break;
            }

            if (m * m > n) {
                e = m - 1;
            } else {
                root = m;
                s = m + 1;
            }
        }

        double precision = 0.1;

        for (int i = 1; i <= p; i++) {
            while ((root + precision) * (root + precision) <= n) {
                root += precision;
            }
            precision /= 10;
        }

        return root;
    }
}
