import java.util.HashMap;
import java.util.Scanner;

public class j09LongestSubarraySumDivByK {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(longestSubarraysDivByK(arr, k));
        System.out.println(longestSubarraysDivByKHashMap(arr, k));
        in.close();
    }

    // O(n ^ 2)
    public static int longestSubarraysDivByK(int[] nums, int k) {
        int maxL = 0;
        for(int i = 0; i < nums.length; i++){
            int s  = 0;
            for(int j = i; j < nums.length; j++){
                s += nums[j];
                if(s % k == 0){
                    maxL = Math.max(maxL, j - i + 1);
                }
            }
        }
        return maxL;
    }

    // O(n)
    public static int longestSubarraysDivByKHashMap(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int s = 0;
        int maxL = 0;
        for (int i = 0; i < nums.length; i++) {
            s += nums[i];
            int rem = (s % k + k) % k;
            if(map.containsKey(rem)){
                maxL = Math.max(maxL,i - map.get(rem));
            }
            if(!map.containsKey(rem)){
                map.put(rem,i);
            }
        }
        return maxL;
    }
}
