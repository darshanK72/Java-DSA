import java.util.ArrayList;
import java.util.Scanner;

// Complexity : O(sqrt(N))

public class j02PrimeFactors {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(getAllPrimeFactorsNaive(n));
        System.out.println(getAllAPrimeFactorsEfficient(n));
        System.out.println(getUniquePrimeFactors(n));
        in.close();
    }

    public static ArrayList<Integer> getAllPrimeFactorsNaive(int number) {
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            if (isPrime(i)) {
                int x = i;
                while (number % x == 0) {
                    out.add(i);
                    x *= i;
                }
            }
        }
        return out;
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> getAllAPrimeFactorsEfficient(int number) {
        ArrayList<Integer> out = new ArrayList<>();
        if (number <= 1)
            return out;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                while (number % i == 0) {
                    out.add(i);
                    number /= i;
                }
            }
        }
        if (number > 1)
            out.add(number);

        return out;
    }

    public static ArrayList<Integer> getUniquePrimeFactors(int n) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        if (n <= 1)
            return out;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                out.add(i);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n > 1)
            out.add(n);
        return out;
    }

}
