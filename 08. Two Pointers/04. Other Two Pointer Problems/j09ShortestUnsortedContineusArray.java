import java.util.Scanner;

public class j09ShortestUnsortedContineusArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(findUnsortedSubarray(arr));
        in.close();
    }

    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int end = -1;
        int maxTill = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] < maxTill) {
                end = i;
            } else {
                maxTill = nums[i];
            }
        }

        int start = 0;
        int minTill = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > minTill) {
                start = i;
            } else {
                minTill = nums[i];
            }
        }
        return end - start + 1;
    }
}
