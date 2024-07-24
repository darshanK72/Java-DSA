import java.util.Scanner;

// Complexity : O(log10(N))

public class j13PrintDigits {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        int div = (int) Math.pow(10, (int) Math.floor(Math.log10(n)) + 1);

        while (div > 0) {
            int d = n / div;
            System.out.println(d);

            n = n % div;
            div /= 10;
        }

        in.close();
    }
}
