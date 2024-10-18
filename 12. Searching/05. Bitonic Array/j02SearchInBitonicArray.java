import java.util.ArrayList;
import java.util.Scanner;

public class j02SearchInBitonicArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt());
        }
        int target = in.nextInt();
        System.out.println(search(arr, target));
        in.close();
    }

    public static int search(ArrayList<Integer> A, int B) {
        int s = 0;
        int e = A.size();
        int pivot = -1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (A.get(mid) < A.get(mid + 1)) {
                s = mid + 1;
            } else if (A.get(mid) < A.get(mid - 1)) {
                e = mid - 1;
            } else {
                pivot = mid;
                break;
            }
        }
        int index = binarySearch(A, B, 0, pivot);
        if (index == -1) {
            return binarySearch(A, B, pivot + 1, A.size() - 1);
        } else {
            return index;
        }

    }

    public static int binarySearch(ArrayList<Integer> A, int B, int s, int e) {
        boolean isAscending = A.get(s) < A.get(e) ? true : false;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (A.get(mid) == B) {
                return mid;
            }
            if (isAscending) {
                if (A.get(mid) > B) {
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
            } else {
                if (A.get(mid) > B) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }
        }
        return -1;
    }
}
