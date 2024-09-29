import java.util.Scanner;

public class j05MaxPointsFromCards {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(maxScore(nums, k));
        in.close();
    }

    public static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int s = n - k;
        int totalSum = 0;
        int minSum = 0;
        int currSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += cardPoints[i];
            if (i < s) {
                currSum = totalSum;
            }
        }
        minSum = currSum;
        for (int i = s; i < n; i++) {
            currSum += cardPoints[i] - cardPoints[i - s];
            minSum = Math.min(minSum, currSum);
        }
        return totalSum - minSum;
    }
}
