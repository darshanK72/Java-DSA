import java.util.Scanner;

// Complexity : O(N *log(log N))

public class j3SieveOfEratosthenes {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        boolean[] primes = new boolean[n + 1];

        printSieve(n, primes);

        in.close();
    }

    // if in array flag is false, then number is prime, and true then not prime

    public static void printSieve(int n, boolean[] primes) {
        for (int i = 2; i * i <= n; i++) {
            if (!primes[i]) {
                for (int j = 2 * i; j <= n; j += i) {
                    primes[j] = true;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (!primes[i]) {
                System.out.print(i + " ");
            }
        }
    }
}