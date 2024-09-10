import java.util.Scanner;
import java.util.Arrays;
public class j13RemoveDuplicateInSorted{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(Arrays.toString(arr));
        int k = removeDuplicates(arr);
        System.out.println(k);
        System.out.println(Arrays.toString(arr));
        in.close();
    }

    public static int removeDuplicates(int[] arr){
        int k = 1;
        for(int i = 1; i < arr.length; i++){
           if(arr[i] != arr[k-1]){
                arr[k] = arr[i];
                k++;
           }
        }
        return k;
    }


}