import java.util.Scanner;
public class j08IsArraySorted{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        System.out.println(isSortedRec(arr,0));
        in.close();
    }

    public static boolean isSorted(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] > arr[i+1]) return false;
        }
        return true;
    }

    // O(n)
    public static boolean isSortedRec(int[] arr,int i){
        if(i == arr.length-1) return true;
        return arr[i] < arr[i+1] && isSortedRec(arr,i+1);
    }
}