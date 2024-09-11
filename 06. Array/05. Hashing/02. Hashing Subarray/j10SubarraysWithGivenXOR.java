import java.util.HashMap;
import java.util.Scanner;

public class j10SubarraysWithGivenXOR {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(subarrrayWithGivenXOR(arr,k));
        in.close();
    }

    public static int subarrrayWithGivenXOR(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int xor = 0;
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            count += map.getOrDefault(xor ^ k, 0);
            map.put(xor, map.getOrDefault(xor, 0) + 1);
        }
        return count;
    }
}
