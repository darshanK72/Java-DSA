import java.util.Scanner;

public class j07CountAllPositionsInSortedArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(countAll(arr, n, target));
        in.close();
    }

    public static int countAll(int[] nums, int n, int target) {
        int first = -1;
        int last = -1;
        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] < target) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        first = s;

        s = 0;
        e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] > target) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        last = e;

        if (first > last)
            return 0;
        return last - first + 1;
    }
}
