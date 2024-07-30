import java.util.Scanner;

public class j35ArrayLRSumQuery{
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int t = in.nextInt();
        int[] rangeSum = getRangeSum(arr);
        while((t--) > 0){
            int l = in.nextInt();
            int r = in.nextInt();
            if(l == 0) System.out.println("L-R Sum = " + rangeSum[r]);
            else System.out.println("L-R Sum = " + (rangeSum[r] - rangeSum[l - 1]));
        }

        in.close();

    }

    public static int[] getRangeSum(int[] arr){
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for(int i = 1; i < arr.length; i++){
            preSum[i] = arr[i] + preSum[i-1];
        }
        return preSum;
    }

}