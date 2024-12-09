import java.util.Scanner;

public class j05PowerOfTwo {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(isPowerofTwo(n));
        System.out.println(isPowerOfTwoBit(n));
        in.close();
    }

    public static boolean isPowerofTwo(long n) {
        if (n == 0)
            return false;
        if (n == 1)
            return true;
        while (n > 1) {
            if (n % 2 == 0)
                n /= 2;
            else
                return false;
        }
        return true;
    }

    public static boolean isPowerOfTwoBit(int n) {
        return n != 0 && (n & (n - 1)) == 0;
    }
}