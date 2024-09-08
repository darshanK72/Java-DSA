import java.util.Scanner;

public class j08PrimeArrangements {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(numPrimeArrangements(n));
        in.close();
    }

    public static int numPrimeArrangements(int n) {
        boolean[] seive = new boolean[n + 1];
        seive[0] = true;
        seive[1] = true;
        for (int i = 2; i * i <= n; i++) {
            if (!seive[i]) {
                for (int j = i * i; j <= n; j += i) {
                    seive[j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 0; i <= n; i++) {
            if (!seive[i])
                count++;
        }
        return (int) (fact(count) * fact(n - count) % 1000000007);
    }

    public static long fact(int n) {
        long f = 1;
        for (int i = 2; i <= n; i++) {
            f = (f * i) % 1000000007;
        }
        return f;
    }
}
