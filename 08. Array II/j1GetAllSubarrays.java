import java.util.Scanner;
import java.util.ArrayList;

public class j1GetAllSubarrays {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        printAllSubarrays(arr);
        System.out.println();
        System.out.println(getAllSubarrays(arr));
        in.close();
    }

    // O(n^2)
    public static void printAllSubarrays(int[] arr){
        for(int i = 0; i < arr.length; i++){
            String subarr = "";
            for(int j = i; j < arr.length; j++){
                subarr += arr[j] + " ";
                System.out.print(subarr + " ");
            }
        }
    }

    // O(n^2)
    public static ArrayList<ArrayList<Integer>> getAllSubarrays(int[] arr){
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            ArrayList<Integer> lst = new ArrayList<>();
            for(int j = i; j < arr.length; j++){
                lst.add(arr[j]);
                out.add(new ArrayList<>(lst));
            }
        }
        return out;
    }

}
