import java.util.Scanner;

public class j05GetFloorAndCeil {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();
        int[] out = getFloorAndCeil(arr, n, target);
        System.out.println("Floor : " + out[0]);
        System.out.println("Ceil : " + out[1]);
        in.close();
    }

    public static int[] getFloorAndCeil(int[] a, int n, int x) {
        int s = 0;
        int e = n - 1;
        int floor = -1;
        int ceil = -1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (a[mid] == x) {
                return new int[] { x, x };
            }
            if (a[mid] < x) {
                floor = a[mid];
                s = mid + 1;
            } else {
                ceil = a[mid];
                e = mid - 1;
            }
        }

        return new int[] { floor, ceil };
    }
}
