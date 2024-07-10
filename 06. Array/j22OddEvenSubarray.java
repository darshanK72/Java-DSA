import java.util.Scanner;

public class j22OddEvenSubarray{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        System.out.println(largestOddEvenSubarrayLengthNive(arr));
        System.out.println(largestOddEvenSubarrayLengthEfficient(arr));
        in.close();
    }

    // O(n^2)
    public static int largestOddEvenSubarrayLengthNive(int[] arr){
        int ans = 0;
        for(int i = 0; i < arr.length; i++){
            int l = 1;
            for(int j = i+1; j < arr.length; j++){
                if((arr[j] % 2 == 0 && arr[j-1] % 2 == 1) || (arr[j] % 2 == 1 && arr[j-1] % 2 == 0)){
                    l++;
                }else{
                    break;
                }
            }
            ans = Math.max(ans,l);
        }
        return ans;
    }

    // O(n)
    public static int largestOddEvenSubarrayLengthEfficient(int[] arr){
        int ans = 0;
        int l = 1;
        for(int i = 0; i < arr.length - 1; i++){
            if((arr[i] + arr[i+1]) % 2 == 1){
                l++;
            }else{
                ans = Math.max(l,ans);
                l = 0;
            }

        }
        return ans;
    }
}