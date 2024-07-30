import java.util.Scanner;

public class j37BuySellStocks1{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        System.out.println(getMaxProfit1(arr));
        System.out.println(getMaxProfit2(arr));
        in.close();
    }

    // TC : O(n) SP : O(n)
    public static int getMaxProfit2(int[] arr) {
        int[] prevMin = new int[arr.length];
        int min = arr[0];
        prevMin[0] = 0;
        for(int i = 1; i < arr.length; i++){
            prevMin[i] = min;
            min = Math.min(min,arr[i]);
        }
        int ans = 0;
        for(int i = 1; i < arr.length; i++){
            int m = arr[i] - prevMin[i];
            ans = Math.max(ans,m);
        }
        return ans;
    }

    // TC : O(n) SP : O(1) 
    public static int getMaxProfit1(int[] arr){
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