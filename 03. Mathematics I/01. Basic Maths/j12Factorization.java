import java.util.Scanner;
import java.util.ArrayList;

// Complexity : O(Sqrt(N))

public class j12Factorization {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        System.out.printf("Factors of %d are", n);
        factorsEfficient(n);

        in.close();
    }

    public static void factors(int n) {

        ArrayList<Integer> factors = new ArrayList<Integer>();

        int left = 1;
        int right = 1;
        factors.add(1);
        factors.add(n);

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (n / i == i) {
                    factors.add(left, i);
                    left++;
                } else {
                    factors.add(left, i);
                    left++;
                    right++;
                    factors.add(factors.size() - (right - 1), n / i);
                }
            }
        }

        System.out.print(factors.toString());
    }

    public static void factorsEfficient(int n) {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        int i;
        for (i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
            }
        }
        for (; i > 0; i--) {
            if (n % i == 0) {
                factors.add(n / i);
            }
        }
        System.out.print(factors.toString());
    }
}
