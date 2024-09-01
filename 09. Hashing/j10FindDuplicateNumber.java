import java.util.Scanner;

public class j10FindDuplicateNumber {
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(findDuplicate(arr));
        in.close();
    }
    public static int findDuplicate(int[] nums) {
        for(int i = 0; i < nums.length; i++){
          int original = nums[i] % (nums.length + 1);
          nums[original] += (nums.length + 1); 
        }
  
        for(int i = 0; i < nums.length; i++){
          if(nums[i] / (nums.length + 1) > 1) return i;
        }
        return -1;
      } 
}
