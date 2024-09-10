import java.util.Scanner;
import java.util.Arrays;

public class j17LeaderInArray{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(Arrays.toString(leadersInArray(arr)));
        in.close();
    }

    public static int[] leadersInArray(int[] arr){
        int[] output = new int[arr.length];
        output[0] = arr[arr.length - 1];
        int k = 0;
        for(int i = arr.length - 2; i >= 0; i--){
            if(arr[i] > output[k]){
                output[k+1] = arr[i];
                k++;
            }
        }
        return output;
    }
}