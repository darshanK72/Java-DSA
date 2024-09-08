import java.util.Scanner;

// Complexity : O(log10(N))

public class j02PrintDigits {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        printDigits(n);
        in.close();
    }

    public static void printDigits(int n) {
        int l = (int) (Math.floor(Math.log10(n)) + 1);
        int div = (int) Math.pow(10, l);

        while (div > 0) {
            int d = n / div;
            System.out.println(d);
            n = n % div;
            div /= 10;
        }
    }
}
