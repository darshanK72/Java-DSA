import java.util.Scanner;

public class j08IsPowerOfFour {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(isPowerOfFour1(n));
        System.out.println(isPowerOfFour2(n));
        System.out.println(isPowerOfFour3(n));
        in.close();
    }

    public static boolean isPowerOfFour1(int n) {
        // 0b01010101010101010101010101010101
        int mask = 0x55555555;
        return n > 0 && (n & (n - 1)) == 0 && (mask & n) > 0;
    }

    public static boolean isPowerOfFour2(int n) {
        // 0b10101010101010101010101010101010
        int mask = 0xAAAAAAAA;
        return n != 0 && (n & (n - 1)) == 0 && (n & mask) == 0;
    }

    public static boolean isPowerOfFour3(int n) {
        return (n > 0) && ((n & (n - 1)) == 0) && (n % 3 == 1);
    }
}