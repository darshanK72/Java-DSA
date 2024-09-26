import java.util.HashSet;
import java.util.Scanner;

public class j13HappyNumber {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(isHappy(n));
        in.close();
    }

    public static boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n > 1) {
            if (set.contains(n))
                return false;
            set.add(n);
            int t = n;
            int x = 0;
            while (t > 0) {
                int d = t % 10;
                x += d * d;
                t /= 10;
            }
            n = x;
        }
        return true;
    }
}
