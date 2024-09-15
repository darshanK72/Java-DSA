import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j04PairsWithEqualSum{
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        pairsWithEqualSum(arr);
        in.close();
    }

    public static void pairsWithEqualSum(int[] nums) {
        HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length ; j++){
                int s = nums[i] + nums[j];
                if(map.containsKey(s)){
                    map.get(s).add(new int[]{nums[i],nums[j]});
                }else{
                    ArrayList<int[]> list = new ArrayList<>();
                    list.add(new int[]{nums[i],nums[j]});
                    map.put(s,list);
                }
            }
        }
        for(int key : map.keySet()){
            System.out.print("Sum = " + key + " : Pairs = ");
            for(int[] pair : map.get(key)){
                System.out.print("(" + pair[0] +"," + pair[1] + ")");
            }
            System.out.println();
        }
    }
}
