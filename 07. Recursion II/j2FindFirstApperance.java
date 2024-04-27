import java.util.Scanner;
public class j2FindFirstApperance{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(find(arr,0,k));
        in.close();
    }

    public static int find(int[] arr,int index,int k){
        if(index == arr.length) return -1;
        if(arr[index] == k) return index;
        else {
            int ind = find(arr,index+1,k);
            return ind;
        }
       
    }
}