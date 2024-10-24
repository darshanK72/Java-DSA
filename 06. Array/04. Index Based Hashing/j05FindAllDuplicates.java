import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j05FindAllDuplicates {
      public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(findDuplicates(arr));
        in.close();
    }
    public static List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++){
            int original = nums[i] % (nums.length + 1);
            nums[original-1] += (nums.length + 1); 
        }

        for(int i = 0; i < nums.length; i++){
            if(nums[i] / (nums.length + 1) > 1) {
                out.add(i+1);
            }
        }
        return out;
    }
}
