import java.util.Scanner;

public class j16KthFactorOfN {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        System.out.println(kthFactor(n,k));
        in.close();
    }

    public static int kthFactor(int n, int k) {
        int factor = -1;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                k--;
            }
            if (k == 0 && n % i == 0) {
                factor = i;
                break;
            }
            if (k == 0) {
                break;
            }
        }
        return factor;
    }
}
