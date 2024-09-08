import java.util.Scanner;

public class j17ThreeDivisors {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(hasOnly3Factors(n));
        in.close();
    }

    public static boolean hasOnly3Factors(int n) {
        int c = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0)
                c++;
            if (c > 3)
                return false;
        }
        return c == 3 ? true : false;
    }
}
