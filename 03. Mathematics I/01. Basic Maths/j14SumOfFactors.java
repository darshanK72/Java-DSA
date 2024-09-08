import java.util.Scanner;

public class j14SumOfFactors {
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(factorSum(n));
        in.close();
    }

    public static long factorSum(int N) {
        long c = 0;
        for (int i = 1; i * i <= N; i++) {
            if (N % i == 0) {
                if (N / i != i) {
                    c += i;
                    c += N / i;
                } else {
                    c += i;
                }
            }
        }
        return c;
    }
}
