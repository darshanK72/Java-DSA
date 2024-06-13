import java.util.Scanner;

public class j3LongestSubarrayWithSumKPositive{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();

        System.out.println(longestSubarraySumK(arr,k));
        in.close();
    }

    public static int longestSubarraySumK(int[] arr,int k){
        int maxLength = 0;
        for(int i = 0; i < arr.length; i++){
            int sum = 0;
            for(int j = i; j < arr.length; j++){
                sum += arr[j];
                if(sum == k){
                    maxLength = Math.max(maxLength,j-i+1);
                }
            }
        }
        return maxLength;
    } 
}
