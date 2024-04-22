import java.util.Scanner;
import java.util.Arrays;
public class j6ReverseArray{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        System.out.println(Arrays.toString(arr));
        reverseArray(arr,0);
        System.out.println(Arrays.toString(arr));

        in.close();
    }

    public static void reverseArray(int[] arr,int i){
        if(i > arr.length/2) return;
        swap(arr,i,arr.length-i-1);
        reverseArray(arr,i+1);
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}