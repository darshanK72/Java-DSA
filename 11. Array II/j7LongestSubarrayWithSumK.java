import java.util.Scanner;
import java.util.HashMap;

public class j7LongestSubarrayWithSumK{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();

        System.out.println(longestSubarraySumK(arr,k));
        System.out.println(longestSubarraySumKEfficient(arr,k));
        System.out.println(longestSubarraySumKEfficientForPositive(arr,k));
        in.close();
    }

    // O(n^2)
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

    // O(n) -> Optimized for both +ve and -ve numbers
    public static int longestSubarraySumKEfficient(int[] arr,int k){
        int maxLength = 0;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            if(sum == k){
                maxLength = Math.max(maxLength,i+1);
            }
            else if(map.containsKey(sum - k)){
                maxLength = Math.max(maxLength,i - map.get(sum - k));
            }
            map.put(sum,i);
        }
        return maxLength;
    }

    // O(n) -> Optimized for +ve (Two Pointers)
    public static int longestSubarraySumKEfficientForPositive(int[] arr,int k){
        int i = 0; 
        int j = 0;
        int sum = 0;
        int maxLength = 0;
        while(j < arr.length){
            while(i <= j && sum > k){
                sum -= arr[i];
                i++;
            }
            if(sum == k){
                maxLength = Math.max(maxLength,j-i+1);
            }
            if(j < arr.length) sum += arr[j];
            j++;
        }
        return maxLength;
    }
}
