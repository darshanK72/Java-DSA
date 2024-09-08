import java.util.Scanner;

public class j15ProductOfFactors {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(factorProduct(n));
        in.close();
    }

    public static int factorProduct(int N) {
        long c = 1;
        for (int i = 1; i * i <= N; i++) {
            if (N % i == 0) {
                if (N / i != i) {
                    c = (c * i) % 1000000007;
                    c = (c * N / i) % 1000000007;
                } else {
                    c = (c * i) % 1000000007;
                }
            }
        }
        return (int) c;
    }
}