import java.util.Scanner;

public class j13ValueOfPolynomial {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] poly = new long[n];
        for (int i = 0; i < n; i++) {
            poly[i] = in.nextLong();
        }
        int x = in.nextInt();

        System.out.println(valueOfPolynomial(n, poly, x));

        in.close();
    }

    public static int valueOfPolynomial(int n, long poly[], int x) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long term = (poly[i] * fastExp(x, (n - i - 1))) % 1000000007;
            ans = (ans + (int) (term)) % 1000000007;
        }
        return ans;
    }

    public static int fastExp(int x, int p) {
        int ans = 1;
        while (p > 0) {
            if ((p & 1) == 1)
                ans = (int) (ans * (long) x % 1000000007);
            x = (int) ((long) x * x % 1000000007);
            p >>= 1;
        }
        return ans;
    }
}
