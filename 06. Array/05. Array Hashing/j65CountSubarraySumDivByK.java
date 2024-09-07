import java.util.HashMap;
import java.util.Scanner;

public class j65CountSubarraySumDivByK {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(subarraysDivByK(arr, k));
        System.out.println(subarraysDivByKHashMap(arr, k));
        in.close();
    }

    // O(n ^ 2)
    public static int subarraysDivByK(int[] nums, int k) {
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            int s  = 0;
            for(int j = i; j < nums.length; j++){
                s += nums[j];
                if(s % k == 0) count++;
            }
        }
        return count;
    }

    // O(n)
    public static int subarraysDivByKHashMap(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int s = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            s += nums[i];
            int rem = (s % k + k) % k;
            count += map.getOrDefault(rem, 0);
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }
        return count;
    }
}
