import java.util.Scanner;

public class j07CountSubarraysMaxLessThanK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(countSubarrayMaxLessThanK(arr, n, k));
        in.close();
    }

    public static long countSubarrayMaxLessThanK(int arr[], int n, int k) {
        long ans = 0;
        long current = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > k) {
                current = i + 1;
            }
            ans += current;
        }
        return ans;
    }
}
