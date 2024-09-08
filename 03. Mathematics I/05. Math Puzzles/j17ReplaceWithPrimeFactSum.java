import java.util.Scanner;

public class j17ReplaceWithPrimeFactSum {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(smallestValue(num));
        in.close();
    }

    public static int smallestValue(int n) {
        int ans = n;
        while (true) {
            int temp = reduce(ans);
            if (temp == ans)
                return ans;
            else
                ans = temp;
        }
    }

    public static int reduce(int n) {
        int s = 0;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    s += i;
                    n /= i;
                }
            }
        }
        if (n > 1) {
            s += n;
        }
        return s;
    }
}
