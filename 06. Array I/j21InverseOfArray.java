import java.util.Scanner;
import java.util.Arrays;

public class j21InverseOfArray {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(Arrays.toString(arrayInverse(arr)));
        in.close();
    }

    public static int[] arrayInverse(int[] arr){
        int[] out = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            int index = arr[i];
            out[index] =  i;
        }
        return out;
    }
}
