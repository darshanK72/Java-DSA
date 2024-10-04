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
        System.out.println(maxScore1(nums, k));
        System.out.println(maxScore2(nums, k));
        in.close();
    }

    public static int maxScore1(int[] cardPoints, int k) {
        int sum = 0;
        int maxSum = 0;
        for(int i = 0; i < k; i++) sum += cardPoints[i];
        maxSum = sum;
        int i = k - 1;
        int j = cardPoints.length - 1;
        while(i >= 0){
            sum -= cardPoints[i];
            sum += cardPoints[j];
            i--;
            j--;
            maxSum = Math.max(maxSum,sum);
        }
        return maxSum;
    }
    public static int maxScore2(int[] cardPoints, int k) {
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
