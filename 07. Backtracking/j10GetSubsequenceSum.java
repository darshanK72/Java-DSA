import java.util.Scanner;
import java.util.ArrayList;
public class j10GetSubsequenceSum{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();

        System.out.println(countSubsequencesWithSum(arr,0,0,k));
        System.out.println(getArraySubseqencesWithSum(arr, 0, new ArrayList<Integer>(),0,k));
        in.close();
    }

    public static int countSubsequencesWithSum(int[] arr,int index,int sum,int k){
        if(index == arr.length){
            if(sum == k){
                return 1;
            }
            return 0;
        }
        int count1 = countSubsequencesWithSum(arr, index+1, sum + arr[index],k);
        int count2 = countSubsequencesWithSum(arr, index+1, sum,k);
        return count1 + count2;
    }  

    public static ArrayList<ArrayList<Integer>> getArraySubseqencesWithSum(int[] arr,int index,ArrayList<Integer> set,int sum,int k){
        ArrayList<ArrayList<Integer>> output = new ArrayList<>();
        if(index == arr.length){
            if(sum == k){
                output.add(new ArrayList<>(set));
            }
            return output;
        }
        set.add(arr[index]);
        output.addAll(getArraySubseqencesWithSum(arr, index+1, set,sum + arr[index],k));
        set.remove(set.size()-1);
        output.addAll(getArraySubseqencesWithSum(arr, index+1, set,sum, k));

        return output; 
    } 
}
