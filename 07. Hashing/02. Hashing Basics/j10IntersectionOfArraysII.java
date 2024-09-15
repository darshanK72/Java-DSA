import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j10IntersectionOfArraysII {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr1 = new int[n];
        for(int i = 0; i < n; i++){
            arr1[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] arr2 = new int[m];
        for(int i = 0; i < m; i++){
            arr2[i] = in.nextInt();
        }
        int[] common = inersection(arr1,arr2);
        System.out.println(Arrays.toString(common));
        in.close();
    }

    // O(n + m)
    public static int[] inersection(int[] arr1,int[] arr2){
        ArrayList<Integer> common = new ArrayList<Integer>();
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i < arr1.length; i++){
            map.put(arr1[i],map.getOrDefault(arr1[i],0) + 1);
        }

        for(int i = 0; i < arr2.length; i++){
            if(map.containsKey(arr2[i])){
                if(map.get(arr2[i]) > 0){
                    common.add(arr2[i]);
                }
                map.put(arr2[i],map.get(arr2[i]) - 1);
            }
        }
        int[] out = new int[common.size()];
        for(int i = 0; i < common.size(); i++){
            out[i] = common.get(i);
        }
        return out;
    }
}
