import java.util.Scanner;

public class j08SmallestRangeI {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(smallestRangeI(arr, k));
        in.close();
    }

    public static int smallestRangeI(int[] nums, int k) {
        int max = 0;
        int min = 100000;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max)
                max = nums[i];
            if (nums[i] < min)
                min = nums[i];
        }

        int diff = max - min;
        if (2 * k > diff)
            return 0;
        else
            return diff - 2 * k;
    }
}
