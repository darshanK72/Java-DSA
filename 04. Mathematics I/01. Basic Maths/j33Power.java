import java.util.Scanner;

public class j33Power {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        double x = in.nextDouble();
        int p = in.nextInt();

        System.out.println(powerNaive(x, p));
        System.out.println(powerForNegatives(x, p));
        System.out.println(powerBinaryExponentiation(x, p));

        in.close();
    }

    // O(n) -> will not work for negative numbers & +ve numbers within range
    public static double powerNaive(double x, int p) {
        double res = 1.0;
        for (int i = 1; i <= p; i++) {
            res *= x;
        }
        return res;
    }

    // O(n) -> this will work for for positive and negative powers, and large powers
    public static double powerForNegatives(double x, int p) {
        if (x == 1)
            return 1;
        if (x == -1) {
            if (p % 2 == 0)
                return 1;
            return -1;
        }
        double res = 1.0;
        long b = p;
        if (p < 0) {
            b = b * -1;
            x = 1.0 / x;
        }

        for (long i = 1; i <= b; i++) {
            res *= x;
        }
        return res;

    }

    // O(log(n)) -> will work form positive and -ve numbers
    public static double powerBinaryExponentiation(double x, int p) {
        if (x == 1)
            return 1;
        if (x == -1) {
            if (p % 2 == 0)
                return 1;
            return -1;
        }
        double result = 1.0;
        long b = p;
        if (b < 0) {
            b = b * -1;
            x = 1.0 / x;
        }
        while (b > 0) {
            if (b % 2 == 1)
                result *= x;
            x = x * x;
            b /= 2.0;
        }
        return result;
    }

    public static long modMul(long a, long b, long m) {
        return (a % m * b % m) % m;
    }

    public static long fastExponention(long a, long b, long m) {
        long result = 1;
        while (b > 0) {
            if ((b % 2) == 1) {
                result = modMul(result, a, m);
            }
            a = modMul(a, a, m);
            b /= 2;
        }
        return result;
    }

    // O(n)
    public static int power(int n, int p) {
        if (p == 0)
            return 1;
        return n * power(n, p - 1);
    }

    // O(n)
    public static int powerEfficient(int n, int p) {
        if (p == 0)
            return 1;
        if ((p & 1) == 0)
            return powerEfficient(n, p / 2) * powerEfficient(n, p / 2);
        else
            return n * powerEfficient(n, p / 2) * powerEfficient(n, p / 2);
    }

    // O(log(n))
    public static int powerMoreEfficient(int n, int p) {
        if (p == 0)
            return 1;

        int power = powerMoreEfficient(n, p / 2);
        if ((p & 1) == 0)
            return power * power;
        else
            return n * power * power;
    }

}
