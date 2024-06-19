import java.util.Scanner;
public class j14TargetSubsetSum{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        targetSum(arr,0,"",0,k);
        System.out.println(countTargetSum(arr,0,0,k));
        in.close();
    }

    public static int countTargetSum(int[] arr,int index,int sum,int k){
        if(index == arr.length){
            return (k == 0) ? 1 : 0;
        }

        return  countTargetSum(arr,index+1,sum + arr[index],k - arr[index]) + 
                countTargetSum(arr,index+1,sum,k);
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