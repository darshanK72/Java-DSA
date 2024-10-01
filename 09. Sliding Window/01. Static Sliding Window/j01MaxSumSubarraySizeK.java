import java.util.ArrayList;
import java.util.Scanner;

public class j01MaxSumSubarraySizeK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        ArrayList<Integer> Arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Arr.add(in.nextInt());
        }
        int k = in.nextInt();
        System.out.println(maximumSumSubarray(k, Arr, N));
        System.out.println(maximumSumSubarrayEfficient1(k, Arr, N));
        System.out.println(maximumSumSubarrayEfficient2(k, Arr, N));
        in.close();
    }

    public static long maximumSumSubarray(int k, ArrayList<Integer> Arr, int N) {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += Arr.get(j);
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public static long maximumSumSubarrayEfficient1(int k, ArrayList<Integer> Arr, int N) {
        long maxSum = 0;
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += Arr.get(i);
        }
        maxSum = sum;
        for (int i = k; i < N; i++) {
            sum -= Arr.get(i - k);
            sum += Arr.get(i);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public static long maximumSumSubarrayEfficient2(int k, ArrayList<Integer> Arr, int N) {
        long sum = 0;
        long maxSum = 0;
        int j = 0;
        for (int i = 0; i < N; i++) {
            sum += Arr.get(i);
            if (i - j + 1 == k) {
                maxSum = Math.max(maxSum, sum);
                sum -= Arr.get(j);
                j++;
            }
        }
        return maxSum;
    }
}