import java.util.Scanner;

public class j36BuySellStocks1{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        System.out.println(getMaxProfit(arr));
        in.close();
    }

    // TC : O(n) SP : O(1) 
    public static int getMaxProfit(int[] arr){
        int min = arr[0];
        int ans = 0;
        for(int i = 1; i < arr.length; i++){
            int d = arr[i] - min;
            ans = Math.max(ans,d);
            min = Math.min(min,arr[i]);
        }
        return ans;
    }
}