import java.util.HashSet;
import java.util.Scanner;

public class j04ContainsDuplicateI {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(containsDuplicate(arr));
        in.close();
    }

    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])) return true;
            else set.add(nums[i]);
        }
        return false;
    }
}
