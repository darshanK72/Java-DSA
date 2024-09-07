import java.util.*;

public class j37PrefixSum{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(Arrays.toString(prefixSum(arr)));
        in.close();
    }

    public static int[] prefixSum(int[] arr){
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for(int i = 1; i < arr.length; i++){
            preSum[i] = arr[i] + preSum[i-1];
        }
        return preSum;
    }
}