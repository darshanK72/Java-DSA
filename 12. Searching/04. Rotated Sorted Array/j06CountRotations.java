import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j06CountRotations {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt());
        }
        System.out.println(findKRotationUnique(arr));
        System.out.println(findKRotationDuplicates(arr));
        in.close();
    }

    public static int findKRotationUnique(List<Integer> arr) {
        int s = 0;
        int e = arr.size() - 1;
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (arr.get(mid) > arr.get(e)) {
                s = mid + 1;
            } else if (arr.get(mid) < arr.get(e)) {
                e = mid;
            }
        }
        return s;
    }

    public static int findKRotationDuplicates(List<Integer> arr) {
        int s = 0;
        int e = arr.size() - 1;
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (arr.get(mid) > arr.get(e)) {
                s = mid + 1;
            } else if (arr.get(mid) < arr.get(e)) {
                e = mid;
            } else {
                e--;
            }
        }
        return s;
    }
}
