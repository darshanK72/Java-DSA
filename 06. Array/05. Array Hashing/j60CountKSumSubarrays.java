import java.util.HashMap;
import java.util.Scanner;

public class j60CountKSumSubarrays {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(countSubarraysSumK(arr,k));
        System.out.println(countSubarraysSumKHashMap(arr, k));
        in.close();
    }

    // O(n*2)
    public static int countSubarraysSumK(int[] nums, int k) {
        int c = 0;
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            for(int j = i; j < nums.length; j++){
                sum += nums[j];
                if(sum == k) c++;
            }
        }
        return c;
    }

    // O(n)
    public static int countSubarraysSumKHashMap(int[] arr, int k) {
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
