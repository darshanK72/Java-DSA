import java.util.Scanner;

public class j01SearchInNearlySortedArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(searchInNearlySorted(arr, target));
        in.close();
    }

    public static int searchInNearlySorted(int[] arr, int target) {
        int s = 0;
        int e = arr.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid - 1] == target) {
                return mid - 1;
            } else if (arr[mid + 1] == target) {
                return mid + 1;
            }

            if (arr[mid] > target) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return -1;
    }
}