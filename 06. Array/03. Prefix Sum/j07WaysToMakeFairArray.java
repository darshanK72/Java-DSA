import java.util.Scanner;

public class j07WaysToMakeFairArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(waysToMakeFair(arr));
        in.close();
    }

    public static int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int[] evenSum = new int[n];
        int[] oddSum = new int[n];
        evenSum[0] = nums[0];
        oddSum[0] = 0;
        for (int i = 1; i < n; i++) {
            if (i % 2 == 0) {
                evenSum[i] = nums[i] + evenSum[i - 1];
                oddSum[i] = oddSum[i - 1];
            } else {
                oddSum[i] = nums[i] + oddSum[i - 1];
                evenSum[i] = evenSum[i - 1];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                if (oddSum[n - 1] == evenSum[n - 1] - nums[i])
                    ans++;
            } else {
                int currentEvenSum = evenSum[i - 1] + oddSum[n - 1] - oddSum[i];
                int currentOddSum = oddSum[i - 1] + evenSum[n - 1] - evenSum[i];
                if (currentEvenSum == currentOddSum)
                    ans++;
            }
        }
        return ans;
    }
}