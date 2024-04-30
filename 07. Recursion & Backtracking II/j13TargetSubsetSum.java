import java.util.Scanner;
public class j13TargetSubsetSum{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        targetSum(arr,0,"",0,k);
        in.close();
    }

    public static void targetSum(int[] arr,int index,String set,int sum,int k){
      if(index == arr.length){
        if(sum == k){
            System.out.println(set);
        }
        return;
      }

      targetSum(arr,index + 1,set + arr[index] + "-",sum + arr[index],k);
      targetSum(arr,index + 1,set,sum,k);
    }
}