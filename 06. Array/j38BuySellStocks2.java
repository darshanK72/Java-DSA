import java.util.Scanner;
import java.util.ArrayList;

public class j38BuySellStocks2{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        System.out.println(maxProfit(arr));
        System.out.println(stockBuySellPricesList(arr,n));
        in.close();
    }

    // O(n)
    public static int maxProfit(int[] prices) {
        int profit = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > prices[i-1]){
                profit += (prices[i] - prices[i-1]);
            }
        }
        return profit;
    }

     // O(n)
    public static ArrayList<ArrayList<Integer>> stockBuySellPricesList(int arr[], int n) {
        int i = 0;
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        while(i < (n-1)){
            ArrayList<Integer> l = new ArrayList<Integer>();
            if(arr[i+1] > arr[i]){
                l.add(i);
                while(((i < (n-1)) && arr[i+1] > arr[i])){
                    i++;
                }
                l.add(i);
            }
            i++;
            if(l.size() > 0)
                out.add(l);
        }
        return out;
    }
}