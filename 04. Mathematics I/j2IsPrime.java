import java.util.Scanner;

public class j2IsPrime {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        System.out.println("Number " + n + " is " + (isPrime(n) ? "Prime" : "Composite"));

        in.close();
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
