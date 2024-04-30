import java.util.Scanner;
public class j14SubsetSum{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(countSubsetWithSum(arr,arr.length ,k));
        in.close();
    }

    public static int countSubsetWithSum(int[] arr,int current,int k){
        if(current == 0){
            return (k == 0) ? 1 : 0;
        }

        return  countSubsetWithSum(arr,current-1,k) + 
                countSubsetWithSum(arr,current-1,k-arr[current-1]);
    }
}