import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class j01LinearSearch {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(findFirstLinear(arr, k));
        System.out.println(findFirstRecursive(arr, 0, k));
        System.out.println(findLastLinear(arr, k));
        System.out.println(findLastRecursive(arr,n-1, k));
        System.out.println(Arrays.toString(findFirstAndLast(arr, k)));
        System.out.println(findAllLinear(arr, k));
        System.out.println(findAllRecursive(arr, k, 0));
        in.close();
    }

    public static int findFirstLinear(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == k)
                return i;
        }
        return -1;
    }

    public static int findFirstRecursive(int[] arr, int index, int k) {
        if (index == arr.length)
            return -1;
        if (arr[index] == k)
            return index;
        return findFirstRecursive(arr, index + 1, k);
    }

    public static int findLastLinear(int[] arr, int k) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == k)
                return i;
        }
        return -1;
    }

    public static int findLastRecursive(int[] arr, int index, int k) {
        if (index < 0)
            return -1;
        if (arr[index] == k)
            return index;
        return findFirstRecursive(arr, index - 1, k);
    }

    public static int[] findFirstAndLast(int[] arr, int k) {
        int[] result = {-1, -1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == k) {
                if (result[0] == -1) {
                    result[0] = i;
                }
                result[1] = i;
            }
        }
        return result;
    }

    public static ArrayList<Integer> findAllLinear(int[] arr,int k){
        ArrayList<Integer> out = new ArrayList<Integer>();
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == k){
                out.add(i);
            }
        }
        return out;
    }

    public static ArrayList<Integer> findAllRecursive(int[] arr,int k,int index){
        if(index == arr.length){
            return new ArrayList<>();
        }
        ArrayList<Integer> res = findAllRecursive(arr,k,index+1);
        if(arr[index] == k){
            res.add(0,index);
        }
        return res;
    }
}
