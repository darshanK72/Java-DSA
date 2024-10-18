import java.util.Scanner;

public class j01PeakIndexInMountainArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(peakIndexInMountainArray(arr));
        System.out.println(peakIndexInMountainArrayEfficient(arr));
        in.close();
    }

    public static int peakIndexInMountainArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1])
                return i;
        }
        return -1;
    }

    public static int peakIndexInMountainArrayEfficient(int[] arr) {
        int s = 0;
        int e = arr.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] < arr[mid + 1]) {
                s = mid + 1;
            } else if (arr[mid] < arr[mid - 1]) {
                e = mid - 1;
            } else {
                return mid;
            }
        }
        return e;
    }
}