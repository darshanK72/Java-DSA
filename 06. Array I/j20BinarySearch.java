import java.util.Scanner;

public class j20BinarySearch {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();

        System.out.println(binarySearch(arr,k));
        in.close();
    }

    public static int binarySearch(int[] arr,int k){
        int s = 0;
        int e = arr.length - 1;
        while(s < e){
            int m = (s + e) / 2;
            if(arr[m] == k){
                return m;
            }
            else if(arr[m] > k){
                e = m - 1;
            }else{
                s = m + 1;
            }
        }
        return -1;
    }
}
