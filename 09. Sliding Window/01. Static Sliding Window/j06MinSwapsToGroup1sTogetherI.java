import java.util.Scanner;

public class j06MinSwapsToGroup1sTogetherI {
      public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        System.out.println(minSwap(nums));
        in.close();
    }

    public static int minSwap(int[] nums){
        int n = nums.length;
        int windowSize = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] == 1) windowSize++;
        }

        int currZeros = 0;
        for(int i = 0; i < windowSize; i++){
            if(nums[i] == 0) currZeros++;
        }

        int minZeros = currZeros;
        for(int i = windowSize; i < n; i++){
            if(nums[i - windowSize] == 0) currZeros--;
            if(nums[i] == 0) currZeros++;
            minZeros = Math.min(minZeros,currZeros);
        }
        return minZeros;
    }
}
