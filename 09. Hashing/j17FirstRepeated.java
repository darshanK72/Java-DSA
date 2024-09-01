import java.util.HashMap;
import java.util.Scanner;

public class j17FirstRepeated {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(firstRepeated(arr));
        in.close();
    }

     public static int firstRepeated(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        int out = -1;
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i]) && (map.get(nums[i]) < out || out == -1)) {
                out = map.get(nums[i]);
            }
            else map.put(nums[i],i + 1);
        }
        return out;
    }
}
