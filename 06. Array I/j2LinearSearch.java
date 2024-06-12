import java.util.Scanner;

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
    in.close();
   } 

   public static int linearSearch(int[] arr,int k){
    for(int i = 0; i < arr.length; i++){
        if(arr[i] == k) return i;
    }
    return -1;
   }
}
