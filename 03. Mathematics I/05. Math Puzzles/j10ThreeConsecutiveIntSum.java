import java.util.Scanner;

public class j10ThreeConsecutiveIntSum {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        System.out.println(sumOfThree(n));
        in.close();
    }

    public static long[] sumOfThree(long num) {
        if (num % 3 != 0)
            return new long[] {};
        long x = num / 3;
        return new long[] { x - 1, x, x + 1 };
    }
}
