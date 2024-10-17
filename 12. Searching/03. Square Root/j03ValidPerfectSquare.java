import java.util.Scanner;

public class j03ValidPerfectSquare {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.print(isPerfectSquare(n));
        in.close();
    }

    public static boolean isPerfectSquare(int num) {
        long s = 1;
        long e = num;
        while (s <= e) {
            long mid = s + (e - s) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return false;
    }
}
