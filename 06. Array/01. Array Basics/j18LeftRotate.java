import java.util.Scanner;
import java.util.Arrays;

public class j18LeftRotate{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        int k = in.nextInt();

        System.out.println(Arrays.toString(arr));
        leftRotateMoreEfficient(arr,k);
        System.out.println(Arrays.toString(arr));
        in.close();
    }

    // O(n*k)
    public static void leftRotate(int[] arr,int k){
        while(k > 0){
            int i;
            int temp = arr[0];
            for(i = 1; i < arr.length; i++){
                arr[i-1] = arr[i];
            }
            arr[arr.length - 1] = temp;
            k--;
        }
    }


    // O(n)
    public static void leftRotateEfficient(int[] arr,int k){
        int[] temp = new int[k];    
        for(int i = 0; i < k; i++){
            temp[i] = arr[i];
        }
        for(int i = k; i < arr.length; i++){
            arr[i-k] = arr[i];
        }
        for(int i = 0; i < k; i++){
            arr[arr.length - k + i] = temp[i];
        }
    }

    // O(n)
    public static void leftRotateMoreEfficient(int[] arr,int k){
        reverse(arr,0,k-1);
        reverse(arr,k,arr.length-1);
        reverse(arr,0,arr.length-1);
    }

    public static void reverse(int[] arr,int s,int e){
        while(s < e){
           int temp = arr[s];
           arr[s] = arr[e];
           arr[e] = temp;
           s++;
           e--;
        }
    }
}