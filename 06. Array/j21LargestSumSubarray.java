import java.util.*;

public class j21LargestSumSubarray{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(getLargestSumSubarrayNive(arr));
        System.out.println(getLargestSumSubarrayEfficient(arr));
        in.close();
    }

    // O(n^2)
    public static int getLargestSumSubarrayNive(int[] arr){
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
    public static int getLargestSumSubarrayEfficient(int[] arr){
        int maxSum = 0;
        int maxSumBefore = 0;
        for(int i = 0; i < arr.length; i++){
            maxSumBefore = Math.max(maxSumBefore + arr[i],arr[i]);
            maxSum = Math.max(maxSum,maxSumBefore);
        }
        return maxSum;
    }

}