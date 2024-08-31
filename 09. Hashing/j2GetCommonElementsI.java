import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j2GetCommonElementsI{
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
        ArrayList<Integer> common = getCommonElements(arr1,arr2);
        System.out.println(common);
        in.close();
    }

    // O(n + m)
    public static ArrayList<Integer> getCommonElements(int[] arr1,int[] arr2){
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < arr1.length; i++){
            if(!map.containsKey(arr1[i])) map.put(arr1[i],0);
            map.put(arr1[i],map.get(arr1[i]) + 1);
        }
        ArrayList<Integer> out = new ArrayList<Integer>();
        for(int i = 0; i < arr2.length; i++){
            if(map.containsKey(arr2[i])){
                out.add(arr2[i]);
                map.remove(arr2[i]);
            }
        }
        return out;
    }
}