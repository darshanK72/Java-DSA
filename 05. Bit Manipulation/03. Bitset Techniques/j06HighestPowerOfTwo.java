import java.util.Scanner;

public class j06HighestPowerOfTwo {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(highestPowerOfTwo1(n));
        System.out.println(highestPowerOfTwo2(n));
        System.out.println(highestPowerOfTwo3(n));
        in.close();
    }

    static int highestPowerOfTwo1(int n) {
        int res = 0;
        for (int i = n; i >= 1; i--) {

            // If i is a power of 2
            if ((i & (i - 1)) == 0) {
                res = i;
                break;
            }
        }
        return res;
    }

    static int highestPowerOfTwo2(int n) {
        // Invalid input
        if (n < 1)
            return 0;

        int res = 1;

        // Try all powers
        // starting from 2^1
        for (int i = 0; i < 8 * Integer.BYTES; i++) {
            int curr = 1 << i;

            // If current power is
            // more than n, break
            if (curr > n)
                break;

            res = curr;
        }
        return res;
    }

    public static int highestPowerOfTwo3(int n) {
        int p = (int) (Math.log(n) /
                Math.log(2));
        return (int) Math.pow(2, p);
    }

}
