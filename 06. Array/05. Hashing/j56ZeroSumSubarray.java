import java.util.HashMap;
import java.util.Scanner;

public class j56ZeroSumSubarray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(findSum(arr, n));
        System.out.println(findSumHashMap(arr, n));
        in.close();
    }

    // O(N^2)
    public static boolean findSum(int arr[], int n) {
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j = i; j < n; j++) {
                s += arr[j];
                if (s == 0)
                    return true;
            }
        }
        return false;
    }

    // O(N) 
    public static boolean findSumHashMap(int arr[], int n) {

        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = arr[0];
        for (int i = 1; i < n; i++) {
            if (map.containsKey(sum) || sum == 0)
                return true;
            map.put(sum, i);
            sum += arr[i];
        }
        return false;
    }
}