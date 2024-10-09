import java.util.Scanner;

public class j05ArrangeCoins {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(arrangeCoins(n));
        in.close();
    }

    public static int arrangeCoins(int n) {
        long s = 1;
        long e = n;
        while (s <= e) {
            long mid = s + (e - s) / 2;
            long k = mid * (mid + 1) / 2;
            if (k == n) {
                return (int) mid;
            } else if (k < n) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return (int) e;
    }
}
