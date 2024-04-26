import java.util.Scanner;
import java.util.Arrays;
public class j9ReverseArray{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(Arrays.toString(arr));
        reverseArrayInplace(arr);
        System.out.println(Arrays.toString(arr));
        in.close();
    }

    public static int[] reverseArray(int[]arr){
        int[] output = new int[arr.length];
        for(int i = 0; i < arr.length ;i++){
            output[i] = arr[arr.length - i - 1];
        }
        return output;
    }

      // Inplace Array Reverse
    public static void reverseArrayInplace(int[] arr){
        int s = 0;
        int e = arr.length -1;
        while(s < e){
            swap(arr,s,e);
            s++;
            e--;
        }
    }
    // Inplace Array Reverse Recursive
    public static void reverseArrayInplaceRec(int[] arr,int i){
        if(i > arr.length/2) return;
        swap(arr,i,arr.length-i-1);
        reverseArrayInplaceRec(arr,i+1);
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}