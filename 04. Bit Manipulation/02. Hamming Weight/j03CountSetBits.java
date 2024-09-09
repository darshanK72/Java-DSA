import java.util.Scanner;

public class j03CountSetBits {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(countSetBitsNive(n));
        System.out.println(countSetBitsEfficient1(n));
        System.out.println(countSetBitsEfficient2(n));

        in.close();
    }

    // O(log(n))
    public static int countSetBitsNive(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    // O(log(n))
    public static int countSetBitsEfficient1(int n) {
        int count = 0;
        while (n > 0) {
            n -= (n & -n);
            count++;
        }
        return count;
    }

    // Kahingam Algorithms
    // O(log(n))
    public static int countSetBitsEfficient2(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
