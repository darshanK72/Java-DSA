import java.util.Scanner;
import java.util.ArrayList;

public class j33CircularSubarrays{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        printCircularSubarrays(arr);
        System.out.println(getCircularSubarrays(arr));
        System.out.println(getCircularSubarraysEfficient(arr));
        in.close();
    }

    // O(n^3)
    public static void printCircularSubarrays(int[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int len = 1; len <= arr.length; len++){
                ArrayList<Integer> list = new ArrayList<Integer>();
                for(int j = 0; j < len; j++){
                    list.add(arr[(i+j) % arr.length]);
                }
                System.out.println(list);
            }
        }
    }

    // O(n^3)
    public static ArrayList<ArrayList<Integer>> getCircularSubarrays(int[] arr){
        ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < arr.length; i++){
            for(int len = 1; len <= arr.length; len++){
                ArrayList<Integer> list = new ArrayList<Integer>();
                for(int j = 0; j < len; j++){
                    list.add(arr[(i+j) % arr.length]);
                }
                out.add(list);
            }
        }
        return out;
    }

    // O(n^2)
    public static ArrayList<ArrayList<Integer>> getCircularSubarraysEfficient(int[] arr){
        ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < arr.length; i++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int j = 0; j < arr.length; j++){
                list.add(arr[(i+j) % arr.length]);
                out.add(new ArrayList<>(list));
            }    
        }
        return out;
    }
   
}