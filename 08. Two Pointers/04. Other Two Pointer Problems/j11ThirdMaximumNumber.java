import java.util.Arrays;
import java.util.Scanner;

public class j11ThirdMaximumNumber {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(thirdMax(arr));
        in.close();
    }

    public static int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int i = nums.length - 1;
        int j = 0;
        while (j < 2) {
            j++;
            while (i > 0 && nums[i - 1] == nums[i])
                i--;
            i--;
        }
        if (i < 0)
            return nums[nums.length - 1];
        return nums[i];
    }
}
