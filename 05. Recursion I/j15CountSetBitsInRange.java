import java.util.Scanner;
import java.util.Arrays;

public class j15CountSetBitsInRange {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(countSetBitsInRangeEfficientRecursive(n));

        in.close();
    }

    // DP Approach
    public static int[] countSetBitsInRagneEfficient(int n){
        int[] out = new int[n+1];
        for(int i = 0; i <= n; i++){
            out[i] = out[(i >> 1)] + (i & 1);
        }
        return out;
    }

    //  Recursion & Bit Manipulation
    public static int countSetBitsInRangeEfficientRecursive(int n){
        if (n == 0) return 0;
        
        int x = 0;
        while ((1 << x) <= n) {
            x++;
        }
        x = x - 1;

        int btill2x = x * (1 << (x - 1));
        int msb2xton = n - (1 << x) + 1;
        int rest = n - (1 << x);
        int ans = btill2x + msb2xton + countSetBitsInRangeEfficientRecursive(rest);

        return ans;
    }
}
