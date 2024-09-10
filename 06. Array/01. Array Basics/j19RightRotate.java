import java.util.Arrays;
import java.util.Scanner;

public class j19RightRotate {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        int k = in.nextInt();

        System.out.println(Arrays.toString(arr));
        rightRotate(arr,k);
        System.out.println(Arrays.toString(arr));
        in.close();
    }


    public static void rightRotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums,0,nums.length-k-1);
        reverse(nums,nums.length-k,nums.length-1);
        reverse(nums,0,nums.length-1);
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
