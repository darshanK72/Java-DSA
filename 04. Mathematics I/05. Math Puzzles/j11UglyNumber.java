import java.util.Scanner;

public class j11UglyNumber {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(isUgly(n));
        in.close();
    }

    public static boolean isUgly(int n) {
        if (n == 0)
            return false;
        while (n % 2 == 0) {
            n /= 2;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        while (n % 5 == 0) {
            n /= 5;
        }

        if (n == 1)
            return true;
        return false;
    }
}
