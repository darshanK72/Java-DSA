import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class j15GetArrayPermutations {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        printArrayPermutations(arr,new ArrayList<Integer>(),new boolean[n]);
        System.out.println();
        System.out.println(arrayPermute(arr,new ArrayList<Integer>(),new boolean[n]));
        System.out.println(arrayPermuteSwap(arr, 0));
        in.close();
    }

    public static void printArrayPermutations(int[] arr,List<Integer> ans,boolean[] used){
        if(ans.size() == arr.length){
            System.out.print(ans + " ");
        }
        for(int i = 0; i < arr.length; i++){
            if(!used[i]){
                ans.add(arr[i]);
                used[i] = true;
                printArrayPermutations(arr, ans, used);
                ans.remove(ans.size()-1);
                used[i] = false;
            }
        }
    }

    public static List<List<Integer>> arrayPermute(int[] arr,List<Integer> ans,boolean[]  used){
        ArrayList<List<Integer>> output = new ArrayList<List<Integer>>();
        if(ans.size() == arr.length){
            output.add(new ArrayList<Integer>(ans));
            return output;
        }

        for(int i = 0; i < arr.length; i++){
            if(!used[i]){
                ans.add(arr[i]);
                used[i] = true;
                output.addAll(arrayPermute(arr,ans,used));
                ans.remove(ans.size()-1);
                used[i] = false;
            }
        }
        return output;
    }

    public static List<List<Integer>> arrayPermuteSwap(int[] arr,int index){
        ArrayList<List<Integer>> output = new ArrayList<List<Integer>>();
        if(index == arr.length){
            ArrayList<Integer> ans = new ArrayList<>();
            for(int i : arr){
                ans.add(i);
            }
            output.add(ans);
            return output;
        }

        for(int i = index; i < arr.length; i++){
            swap(arr,index,i);
            output.addAll(arrayPermuteSwap(arr, index+1));
            swap(arr,index,i);
        }
        return output;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
