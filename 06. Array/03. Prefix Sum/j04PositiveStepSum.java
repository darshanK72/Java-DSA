import java.util.Scanner;

public class j04PositiveStepSum {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(minStartValue(arr));
        in.close();
    }

    public static int minStartValue(int[] nums) {
        int ans = 1;
        int tempSum = 1;
        for (int i = 0; i < nums.length; i++) {
            tempSum += nums[i];
            if (tempSum <= 0) {
                ans += (1 - tempSum);
                tempSum = 1;
            }
        }
        return ans;
    }
}
