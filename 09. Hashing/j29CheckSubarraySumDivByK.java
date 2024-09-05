import java.util.HashMap;
import java.util.Scanner;

public class j29CheckSubarraySumDivByK {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(checkSubarraySumDivByK(arr, k));
        System.out.println(checkSubarraySumDivByKHashMap(arr, k));
        in.close();
    }

    public static boolean checkSubarraySumDivByK(int[] nums, int k) {
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            for(int j = i; j < nums.length; j++){
                sum += nums[i];
                if(sum % k == 0 && (j - i) > 1) return true;
            }
        }
        return false;
    }

    public static boolean checkSubarraySumDivByKHashMap(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int rem = (sum % k + k) % k;
            if (map.containsKey(rem)) {
                if (i - map.get(rem) >= 2)
                    return true; // checking for atleast size 2
            } else {
                map.put(rem, i);
            }
        }
        return false;
    }
}
