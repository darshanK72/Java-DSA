import java.util.Scanner;
import java.util.ArrayList;

// Complexity : O(Sqrt(N))

public class j6Factorization {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        System.out.printf("Factors of %d are", n);
        factors(n);

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
}
