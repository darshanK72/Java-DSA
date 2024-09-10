import java.util.*;

public class j02SuffixSum {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(Arrays.toString(suffixSum(arr)));
        in.close();
    }

    public static int[] suffixSum(int[] arr) {
        int[] sufSum = new int[arr.length];
        sufSum[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            sufSum[i] = arr[i] + sufSum[i + 1];
        }
        return sufSum;
    }
}