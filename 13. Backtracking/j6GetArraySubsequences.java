import java.util.Scanner;
import java.util.ArrayList;
public class j6GetArraySubsequences {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        printArraySubseqences(arr,0,new ArrayList<Integer>());
        System.out.println(getArraySubseqences(arr, 0, new ArrayList<Integer>()));
        in.close();
    }

    public static void printArraySubseqences(int[] arr,int index,ArrayList<Integer> set){
        if(index == arr.length){
            System.out.println(set);
            return;
        }
        set.add(arr[index]);
        printArraySubseqences(arr, index+1, set);
        set.remove(set.size()-1);
        printArraySubseqences(arr, index+1, set);
    }  

    public static ArrayList<ArrayList<Integer>> getArraySubseqences(int[] arr,int index,ArrayList<Integer> set){
        ArrayList<ArrayList<Integer>> output = new ArrayList<>();
        if(index == arr.length){
           output.add(new ArrayList<>(set));
           return output;
        }
        set.add(arr[index]);
        output.addAll(getArraySubseqences(arr, index+1, set));
        set.remove(set.size()-1);
        output.addAll(getArraySubseqences(arr, index+1, set));

        return output; 
    } 
}
