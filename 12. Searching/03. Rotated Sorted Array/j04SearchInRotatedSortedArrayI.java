import java.util.Scanner;

public class j04SearchInRotatedSortedArrayI {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(search1(arr, target));
        System.out.println(search2(arr, target));
        in.close();
    }

    public static int search1(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] >= nums[0]) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        if (s == nums.length) {
            return binarySearch(nums, 0, nums.length - 1, target);
        } else if (target >= nums[0]) {
            return binarySearch(nums, 0, e, target);
        } else {
            return binarySearch(nums, s, nums.length - 1, target);
        }
    }

    public static int search2(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] > nums[e]) {
                s = mid + 1;
            } else if (nums[mid] < nums[e]) {
                e = mid;
            } else {
                e--;
            }
        }
        if (target > nums[nums.length - 1]) {
            return binarySearch(nums, 0, e, target);
        } else {
            return binarySearch(nums, s, nums.length - 1, target);
        }
    }

    public static int search3(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] == target) {
                return mid;
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
        return -1;
    }

    public static int binarySearch(int[] nums, int s, int e, int target) {
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return -1;
    }
}
