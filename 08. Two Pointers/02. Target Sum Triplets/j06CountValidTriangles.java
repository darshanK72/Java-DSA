import java.util.Arrays;
import java.util.Scanner;

public class j06CountValidTriangles {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(triangleNumber(arr));
        in.close();
    }

    // for triangle, sum of two sides always greater than 3rd side.
    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int i = n - 1;
        int count = 0;
        while (i >= 0) {
            int s = 0;
            int e = i - 1;
            while (s < e) {
                int sum = nums[s] + nums[e];
                if (sum > nums[i]) {
                    count += e - s;
                    e--;
                } else {
                    s++;
                }
            }
            i--;
        }
        return count;
    }
}
