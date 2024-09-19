import java.util.Arrays;
import java.util.Scanner;

public class j10FindPairsWithAbsKDifference {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int x = in.nextInt();
        System.out.println(findPair(n, x, arr));
        System.out.println(findPairEfficient(n, x, arr));
        in.close();
    }

    public static int findPair(int n, int x,int[] arr) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(arr[i] - arr[j]) == x) {
                    return 1;
                }
            }
        }
        return -1;
    }

    public static int findPairEfficient(int n, int x, int[] arr) {
        Arrays.sort(arr);
        int left = 0, right = 1;
        while (right < n) {
            int diff = Math.abs(arr[right] - arr[left]);
            if (diff == x && left != right) {
                return 1;
            }
            if (diff < x) {
                right++;
            } else {
                left++;
            }
            if (left == right) {
                right++;
            }
        }
        return -1;
    }
}
