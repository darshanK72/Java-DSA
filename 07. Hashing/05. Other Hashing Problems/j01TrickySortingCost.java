import java.util.HashMap;
import java.util.Scanner;

public class j01TrickySortingCost {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(sortingCost(n, arr));
        in.close();
    }

    public static int sortingCost(int N, int arr[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxL = -1;
        for (int i = 0; i < N; i++) {
            if (map.containsKey(arr[i] - 1)) {
                map.put(arr[i], map.get(arr[i] - 1) + 1);
            } else {
                map.put(arr[i], 1);
            }
            maxL = Math.max(maxL, map.get(arr[i]));
        }
        return N;
    }
}
