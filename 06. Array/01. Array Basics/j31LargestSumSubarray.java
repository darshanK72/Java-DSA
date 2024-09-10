import java.util.*;

public class j31LargestSumSubarray{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(getLargestSubarraySumNive(arr));
        System.out.println(getLargestSubarraySumEfficient1(arr));
        System.out.println(getLargestSubarraySumEfficient2(arr));
        System.out.println(Arrays.toString(getSubarrayWithLargestSum(arr)));
        in.close();
    }

    // O(n^2)
    public static int getLargestSubarraySumNive(int[] arr){
        int maxSum = 0;
        for(int i = 0; i < arr.length; i++){
            int sum = 0;
            for(int j = i; j < arr.length; j++){
                sum += arr[j];
                if(sum > maxSum) maxSum = sum;
            }
        }
        return maxSum;
    }

    // O(n) -> Kadane’s algorithm
    public static int getLargestSubarraySumEfficient1(int[] arr){
        int maxSum = 0;
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            if(sum > maxSum){
                maxSum = sum;
            }
            if(sum < 0){
                sum = 0;
            }
        }
        return maxSum;
    }

    // O(n) -> Kadane’s algorithm easy implementation
    public static int getLargestSubarraySumEfficient2(int[] arr){
        int maxSum = 0;
        int maxSumBefore = 0;
        for(int i = 0; i < arr.length; i++){
            maxSumBefore = Math.max(maxSumBefore + arr[i],arr[i]);
            maxSum = Math.max(maxSum,maxSumBefore);
        }
        return maxSum;
    }

    // O(n) 
    public static int[] getSubarrayWithLargestSum(int[] arr){
        int maxSum = 0;
        int sum = 0;
        int start = 0;
        int startIndex = -1;
        int endIndex = -1;
        for(int i = 0; i < arr.length; i++){
            if(sum == 0){
                start = i;
            } 
            sum += arr[i];
            if(sum > maxSum){
                maxSum = sum;
                startIndex = start;
                endIndex = i;
            }
            if(sum < 0){
                sum = 0;
            }
        }
        int s = endIndex - startIndex + 1;
        int[] out = new int[s];
        for(int i = 0; i < s; i++){
            out[i] = arr[startIndex+i];
        }
        return out;
    }
}