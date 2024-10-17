import java.util.Scanner;

public class j05SearchInRotatedSortedArrayII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(search(arr, target));
        in.close();
    }

    public static boolean search(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] == nums[s] && nums[mid] == nums[e]) {
                s++;
                e--;
                continue;
            }
            if (nums[s] <= nums[mid]) {
                if (nums[s] <= target && nums[mid] >= target) {
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
            } else {
                if (nums[mid] <= target && nums[e] >= target) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }
        }
        return false;
    }
}
