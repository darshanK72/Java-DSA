import java.util.Scanner;
import java.util.Arrays;

public class j2LinearSearch {
   public static void main(String args[]){
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] arr = new int[n];
    for(int i = 0; i < n; i++){
        arr[i] = in.nextInt();
    }
    int k = in.nextInt();
    System.out.println(linearSearch(arr,k));
    System.out.println(Arrays.toString(firstAndLastApperance(arr,k)));
    in.close();
   } 

   public static int linearSearch(int[] arr,int k){
    for(int i = 0; i < arr.length; i++){
        if(arr[i] == k) return i;
    }
    return -1;
   }

   public static int[] firstAndLastApperance(int[] arr,int k){
    int first = -1;
    int last = -1;
    for(int i = 0; i < arr.length;i++){
        if(first == -1 && arr[i] == k){
            first = i;
            last = i;
        }
        else if(first != -1 && arr[i] == k){
            last = i;
        }
    }
    return new int[]{first,last};
   }
}
