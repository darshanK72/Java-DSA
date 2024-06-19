import java.util.Scanner;
public class j3FindLastAperance{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(findLast(arr,0,k));
        in.close();
    }

    public static int findLast(int[] arr,int index,int k){
        if(index == arr.length) return -1;
        int idx = findLast(arr,index+1,k);
        if(idx != -1){
            return idx;
        }else{
            if(arr[index] == k) return index;
            return -1;
        }
    }
}