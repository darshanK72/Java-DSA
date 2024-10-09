import java.util.Scanner;

public class j01BinarySearch {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(binarySearch(arr, k));
        System.out.println(binarySearchAnyOrder(arr, k));
        in.close();
    }

    public static int binarySearch(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int mid = (s + e) / 2; // s + (e - s)/2
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return -1;
    }

    public static int binarySearchAnyOrder(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        boolean isAscending = nums[s] < nums[e] ? true : false;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (isAscending) {
                if (nums[mid] > target) {
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
            } else {
                if (nums[mid] > target) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }

        }
        return -1;
    }
}
