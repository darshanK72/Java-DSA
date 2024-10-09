import java.util.ArrayList;
import java.util.Scanner;

public class j06IndexFixedPoint {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt());
        }
        System.out.println(equalIndex(arr, n));
        in.close();
    }

    public static int equalIndex(ArrayList<Integer> arr, int n) {
        int s = 0;
        int e = n - 1;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (arr.get(mid) > mid) {
                e = mid - 1;
            } else if (arr.get(mid) < mid) {
                s = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
