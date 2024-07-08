import java.util.Scanner;
import java.util.ArrayList;

public class j10GetArraySubsets{
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        printArraySubsets(arr,0,new ArrayList<Integer>());
        System.out.println(getArraySubsets(arr, 0, new ArrayList<Integer>()));
        in.close();
    }

    public static void printArraySubsets(int[] arr,int index,ArrayList<Integer> set){
        if(index == arr.length){
            System.out.println(set);
            return;
        }
        set.add(arr[index]);
        printArraySubsets(arr,index+1,set);
        set.remove(set.size()-1);
        printArraySubsets(arr,index+1,set);
    }

    public static ArrayList<ArrayList<Integer>> getArraySubsets(int[] arr,int index,ArrayList<Integer> set){
        ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
        if(index == arr.length){
            output.add(new ArrayList<Integer>(set));
            return output;
        }
        set.add(arr[index]);
        output.addAll(getArraySubsets(arr,index+1,set));
        set.remove(set.size()-1);
        output.addAll(getArraySubsets(arr,index+1,set));
        return output;
    }

}