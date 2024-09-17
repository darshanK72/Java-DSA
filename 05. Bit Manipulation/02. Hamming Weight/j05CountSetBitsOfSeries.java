import java.util.Arrays;
import java.util.Scanner;

public class j05CountSetBitsOfSeries {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(Arrays.toString(countBits(n)));
        System.out.println(Arrays.toString(countBitsEfficient(n)));
        in.close();
    }

    public static int[] countBits(int n) {
        int[] out = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            out[i] = hammingWeight(i);
        }
        return out;
    }

    public static int hammingWeight(int n) {
        int count = 0;
        while (n > 0) {
            n -= (n & -n);
            count++;
        }
        return count;
    }

    public static int[] countBitsEfficient(int n) {
        int[] out = new int[n+1];
        for(int i = 0; i <= n; i++){
            out[i] = out[(i >> 1)] + (i & 1);
        }
        return out;
    }

}