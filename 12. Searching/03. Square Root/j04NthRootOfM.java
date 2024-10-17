import java.util.Scanner;

public class j04NthRootOfM {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        System.out.print(nthRoot(n, m));
        in.close();
    }

    public static int nthRoot(int n, int m) {
        int s = 1, e = m;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            long midPow = power(mid, n, m);
            if (midPow == m) {
                return mid;
            } else if (midPow < m) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return -1;
    }

    private static long power(int base, int exp, int max) {
        long result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
            if (result > max)
                break;
        }
        return result;
    }
}
