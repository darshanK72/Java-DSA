import java.util.Scanner;
import java.lang.Math;

public class j19DivideWithoutDivision {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();

        System.out.println(divide(n, d));
        in.close();
    }

    public static int divide(int dividend, int divisor) {
        
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        if(divisor == 1)
            return dividend;
        if(divisor == -1){
            return -dividend;
        }

        boolean sign = (dividend < 0) ^ (divisor < 0);

        long n = Math.abs((long)dividend);
        long d = Math.abs((long)divisor);

        long sum = 0;
        while (n >= d) {
            int i = 0;
            while (n >= (d << (i + 1))) {
                i++;
            }
            sum += (1 << i);
            n = n - d * (1 << i);
        }
        if (sign) {
            if (sum > Integer.MAX_VALUE)
                return Integer.MIN_VALUE;
            return -1 * (int)sum;
        } else {
            if (sum > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            return (int)sum;
        }
    }
}
