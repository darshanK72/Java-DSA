import java.util.Scanner;

public class j04CountPrimes {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(countPrimes(n));
        in.close();
    }

    public static int countPrimes(int n) {
        boolean[] seive = new boolean[n + 1];
        for (int i = 2; i * i <= n; i++) {
            if (!seive[i]) {
                for (int j = 2 * i; j <= n; j += i) {
                    seive[j] = true;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!seive[i])
                count++;
        }
        return count;
    }
}