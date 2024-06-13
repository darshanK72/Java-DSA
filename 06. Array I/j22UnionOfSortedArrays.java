import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class j22UnionOfSortedArrays{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[m];
        for(int i = 0; i < n; i++){
            arr1[i] = in.nextInt();
        }

        for(int i = 0; i < m; i++){
            arr2[i] = in.nextInt();
        }

        System.out.println(unionOfArrays(arr1,arr2));
        System.out.println(unionOfArrayEfficient(arr1, arr2));

        in.close();
    }

    public static ArrayList<Integer> unionOfArrays(int[] arr1,int[] arr2){
        HashSet<Integer> set = new HashSet<>();
        
        for(int i = 0; i < arr1.length; i++){
            set.add(arr1[i]);
        }
            
        for(int i = 0; i < arr2.length; i++){
            set.add(arr2[i]);
        }
        ArrayList<Integer> output = new ArrayList<>();
        for(Integer i:set){
            output.add(i);
        }
        return output;
    }

    public static ArrayList<Integer> unionOfArrayEfficient(int[] arr1,int[] arr2){
        ArrayList<Integer> output = new ArrayList<Integer>();

        int i = 0;
        int j = 0;
        while(i < arr1.length && j < arr2.length){
            if(arr1[i] < arr2[j]){
                if(output.size() == 0 || output.get(output.size()-1) != arr1[i])
                    output.add(arr1[i]);
                i++;
            }else if(arr1[i] >= arr2[j]){
                if(output.size() == 0 || output.get(output.size()-1) != arr2[j])
                    output.add(arr2[j]);
                j++;
            }
        }

        if(i == arr1.length){
            for(; j < arr2.length; j++){
                if(output.get(output.size()-1) != arr2[j])
                    output.add(arr2[j]);
            }
        }else{
            for(; i < arr1.length; i++){
                if(output.get(output.size()-1) != arr1[i])
                    output.add(arr1[i]);
            }
        }
        return output;
    }
}