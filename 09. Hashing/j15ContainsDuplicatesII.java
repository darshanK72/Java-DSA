import java.util.HashMap;
import java.util.Scanner;

public class j15ContainsDuplicatesII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();

        System.out.println(containsNearbyDuplicate(arr,k));
        in.close();
    }
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                if(i - map.get(nums[i]) <= k) return true;
                else map.put(nums[i],i);
            }else{
                map.put(nums[i],i);
            }
        }
        return false;
    }
}
