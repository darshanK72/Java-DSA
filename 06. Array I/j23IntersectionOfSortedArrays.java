import java.util.Scanner;
import java.util.ArrayList;

public class j23IntersectionOfSortedArrays {
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

        System.out.println(intersectionOfArrays(arr1,arr2));
        System.out.println(intersectionOfArraysEfficient(arr1, arr2));
        System.out.println(intersectionOfArrayTwoPointers(arr1, arr2));

        in.close();
    }

    public static ArrayList<Integer> intersectionOfArrays(int[] arr1,int[] arr2){
        ArrayList<Integer> output = new ArrayList<>();
        boolean[] visited = new boolean[arr2.length];
        for(int i = 0; i < arr1.length; i++){
            for(int j = 0; j < arr2.length; j++){
                if(arr1[i] == arr2[j] && !visited[j]){
                    output.add(arr1[i]);
                    visited[j] = true;
                    break;
                }
            }
        }
        return output;
    }
    public static ArrayList<Integer> intersectionOfArraysEfficient(int[] arr1,int[] arr2){
        ArrayList<Integer> output = new ArrayList<>();
        boolean[] visited = new boolean[arr2.length];
        int j = 0;
        for(int i = 0; i < arr1.length; i++){
            while(j < arr2.length){
                if(arr2[j] <= arr1[i]){
                    if(arr2[j] == arr1[i] && !visited[j]){
                        output.add(arr1[i]);
                        visited[j] = true;
                        break;
                    }
                }else{
                    break;
                }
                j++;
            }
        }
        return output;
    }

    public static ArrayList<Integer> intersectionOfArrayTwoPointers(int[] arr1,int arr2[]){
        ArrayList<Integer> output = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < arr1.length && j < arr2.length){
            if(arr1[i] < arr2[j]){
                i++;
            }else if(arr1[i] > arr2[j]){
                j++;
            }else{
                output.add(arr1[i]);
                i++;
                j++;
            }
        }
        return output;
    }
}
