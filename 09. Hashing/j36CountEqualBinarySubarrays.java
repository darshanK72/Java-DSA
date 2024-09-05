import java.util.HashMap;
import java.util.Scanner;

public class j36CountEqualBinarySubarrays {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(countSubarrWithEqualZeroAndOne(arr, n));
        in.close();
    }

    public static int countSubarrWithEqualZeroAndOne(int arr[], int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i] == 1 ? arr[i] : -1;
            count += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
