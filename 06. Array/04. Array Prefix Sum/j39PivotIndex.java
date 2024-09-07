import java.util.*;

public class j39PivotIndex{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(pivotIndex(arr));
        in.close();

    }

    public static int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n];
        int[] sufSum = new int[n];
        preSum[0] = nums[0];
        sufSum[n - 1] = nums[n - 1];
        for(int i = 1; i < n; i++){
            preSum[i] = nums[i] + preSum[i-1];
            sufSum[n - i - 1] = nums[n - i - 1] + sufSum[n - i];
        }

        for(int i = 0; i < n; i++){
            if(preSum[i] == sufSum[i]){
                return i;
            }
        }
        return -1;
    }
}