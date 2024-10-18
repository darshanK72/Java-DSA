import java.util.Scanner;

public class j03FindPeakElement {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(findPeakElement(arr));
        in.close();
    }

    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return 0;
        if (nums[0] > nums[1])
            return 0;
        if (nums[n - 1] > nums[n - 2])
            return n - 1;

        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] < nums[mid + 1]) {
                s = mid + 1;
            } else if (nums[mid] < nums[mid - 1]) {
                e = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
