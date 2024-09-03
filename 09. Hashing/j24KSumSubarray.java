import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j24KSumSubarray {

     public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(subarraySumK(arr, n,k));
        System.out.println(subarraySumKHashMap(arr, n,k));
        in.close();
    }

    public static ArrayList<Integer> subarraySumK(int[] arr, int n, int k) {
        for(int i = 0; i < n; i++){
            int s = 0;
            for(int j = i; j < n; j++){
                s += arr[j];
                if(s == k){
                    return new ArrayList<Integer>(Arrays.asList(i+1,j+1));
                }
            }
        }
        return new ArrayList<Integer>(Arrays.asList(-1));
    }

    public static ArrayList<Integer> subarraySumKHashMap(int[] arr, int n, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int s = 0;
        for(int i = 0; i < n; i++){
            s += arr[i];
            if(s == k){
                return new ArrayList<Integer>(Arrays.asList(0,i));
            }else if(map.containsKey(s - k)){
                return new ArrayList<Integer>(Arrays.asList(map.get(s-k)+1,i+1));
            }else{
                map.put(s,i);
            }
        }
        return new ArrayList<Integer>(Arrays.asList(-1));
    }
}
