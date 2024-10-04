import java.util.Scanner;

public class j04MaxSubarraySumLessThanK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        long k = in.nextLong();
        System.out.println(findMaxSubarraySum(arr, k));
        in.close();
    }

    public static long findMaxSubarraySum(int[] arr, long x) {
        int j = 0;
        long currentSum = 0;
        long maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            while (currentSum > x) {
                currentSum -= arr[j];
                j++;
            }
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
