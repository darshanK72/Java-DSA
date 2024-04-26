import java.util.Scanner;
import java.util.Arrays;

public class j11MoveZerosToEnd{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(Arrays.toString(arr));
        moveZerosToEnd(arr);
        System.out.println(Arrays.toString(arr));
        in.close();
    }

    public static void moveZerosToEnd(int[] arr){
        int k = 0; 
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != 0){
                int temp = arr[k];
                arr[k] = arr[i];
                arr[i] = temp;
                k++;
            }
        }
    }
}