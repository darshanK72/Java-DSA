import java.util.HashMap;
import java.util.Scanner;

public class j16MinOpsToReduceXToZero {
     public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(minOperations(arr,k));
        in.close();
    }

    public static int minOperations(int[] nums, int x) {
        int target = 0;
        for(int n : nums) target += n;
        int k = target - x;
        if(k == 0) return nums.length;
        if(k < 0) return -1;
        HashMap<Integer,Integer> map = new HashMap<>();
        int s = 0;
        int maxL = -1;
        map.put(0,-1);
        for(int i = 0; i < nums.length; i++){
            s += nums[i];
            if(map.containsKey(s - k)){
                maxL = Math.max(maxL,i - map.get(s - k));
            }
            if(!map.containsKey(s)){
                map.put(s,i);
            }
        }
        
        return maxL == -1 ? maxL : nums.length - maxL;
    }
}
