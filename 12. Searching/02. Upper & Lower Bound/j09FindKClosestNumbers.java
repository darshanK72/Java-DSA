import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j09FindKClosestNumbers {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        int target = in.nextInt();
        System.out.println(findClosestElements(arr, k, target));
        in.close();
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        ArrayList<Integer> out = new ArrayList<>();
        int s = 0;
        int e = arr.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] >= x) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        while (k > 0) {
            int ceilDist = s == arr.length ? Integer.MAX_VALUE : arr[s] - x;
            int floorDist = e == -1 ? Integer.MAX_VALUE : x - arr[e];
            if (floorDist <= ceilDist) {
                e--;
            } else {
                s++;
            }
            k--;
        }
        for (int i = e + 1; i < s; i++) {
            out.add(arr[i]);
        }
        return out;
    }
}
