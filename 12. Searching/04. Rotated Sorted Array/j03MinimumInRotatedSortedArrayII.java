import java.util.Scanner;

public class j03MinimumInRotatedSortedArrayII {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(findMin2(arr));
        in.close();
    }

    public static int findMin2(int[] nums) {
        int s = 0;
        int e = nums.length - 1;

        while (s < e) {
            int mid = s + (e - s) / 2;

            if (nums[mid] > nums[e]) {
                s = mid + 1; // The minimum is in the right half
            } else if (nums[mid] < nums[e]) {
                e = mid; // The minimum is in the left half, including mid
            } else {
                // If nums[mid] == nums[e], we cannot determine which side the minimum is on
                // So we reduce the search space by moving the right pointer
                e--;
            }
        }

        return nums[s]; // The minimum will be at index s after the loop ends
    }
}
