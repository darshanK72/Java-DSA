import java.util.Arrays;

public class j08MinMovesToEqualaArrayElementsII {
    public static int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int median = 0;
        if(n % 2 == 1){
            median = nums[n/2];
        }else{
            median = (nums[n/2] + nums[n/2 - 1])/2;
        }
        int ans = 0;
        for(int i = 0; i < n; i++){
            ans += Math.abs(nums[i] - median);
        }
        return ans;
    }
}
