import java.util.Scanner;
import java.util.ArrayList;

public class j24LargestCircularSubarraySum{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        System.out.println(getMaxCircularSubarraySumNive(arr));
        System.out.println(getMaxCircularSubarraySumBetter(arr));
        System.out.println(getMaxCircularSubarraySumEfficient(arr));
        in.close();
    }

    // O(n^3)
    public static int getMaxCircularSubarraySumNive(int[] arr){
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            for(int len = 1; len <= arr.length; len++){
                int temp = 0;
                for(int j = 0; j < len; j++){
                    temp += arr[(i+j) % arr.length];
                }
                ans = Math.max(ans,temp);
            }
        }
        return ans;
    }

     // O(n^2)
    public static int getMaxCircularSubarraySumBetter(int[] arr){
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            int tempSum = 0;
            for(int j = 0; j < arr.length; j++){
                tempSum += arr[(i+j) % arr.length];
                ans = Math.max(ans,tempSum);
            }
        }
        return ans;
    }

     // O(n)
    public static int getMaxCircularSubarraySumEfficient(int[] arr){
        int maxNormalSum = maxSubarraySum(arr);
        int minSum = minSubarraySum(arr);
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        int maxCircularSum = sum - minSum;
        if(maxCircularSum == 0){
            return maxNormalSum;
        }
        return Math.max(maxNormalSum,maxCircularSum);
    }

    // O(n)
     public static int maxSubarraySum(int[] arr) {
        int sum = arr[0];
        int tempSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            tempSum = Math.max(tempSum + arr[i], arr[i]);
            sum = Math.max(sum, tempSum);
        }
        return sum;
    }

    // O(n)
    public static int minSubarraySum(int[] arr) {
        int sum = arr[0];
        int tempSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            tempSum = Math.min(tempSum + arr[i], arr[i]);
            sum = Math.min(sum, tempSum);
        }
        return sum;
    }

   
}