import java.util.Scanner;
import java.lang.Math;

public class j14MaximumDifference{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(maxDiff(arr));
        in.close();
    }

    public static int maxDiff(int[] arr){
        int result =arr[1]- arr[0];
        int min = arr[0];
        for(int i = 1; i < arr.length - 1; i++){
            result = Math.max(result,arr[i]-min);
            min = Math.min(min,arr[i]);
        }

        return result;
    }
}