import java.util.Scanner;

public class j09LargestPrimeFactor {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(largestPrimeFactor(n));
        in.close();
    }

    public static int largestPrimeFactor(int n) {
        int max = -1;
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                max = Math.max(max, i);
                n /= i;
            }
        }
        if (n > 1)
            max = Math.max(max, n);
        return max;
    }
}
