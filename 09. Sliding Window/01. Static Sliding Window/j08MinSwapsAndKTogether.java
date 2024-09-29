import java.util.Scanner;

public class j08MinSwapsAndKTogether {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(minSwap(nums, n, k));
        in.close();
    }

    public static int minSwap(int arr[], int n, int k) {
        int countMins = 0;
        int windowSize = 0;
        int maxInWindow = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] <= k)
                windowSize++;
        }

        for (int i = 0; i < windowSize; i++) {
            if (arr[i] <= k)
                countMins++;
        }

        maxInWindow = countMins;
        for (int i = windowSize; i < n; i++) {
            int last = arr[i - windowSize];
            if (last <= k)
                countMins--;
            int next = arr[i];
            if (next <= k)
                countMins++;
            maxInWindow = Math.max(maxInWindow, countMins);
        }

        return windowSize - maxInWindow;
    }

}
