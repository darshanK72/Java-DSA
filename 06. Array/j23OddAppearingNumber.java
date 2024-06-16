import java.util.Arrays;
import java.util.Scanner;

public class j23OddAppearingNumber{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] arr = new int[s];
        for(int i = 0; i < s; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(findOneOddAppearingNumber(arr));
        System.out.println(Arrays.toString((findTwoOddAppearingNumbers(arr))));
        System.out.println(findSingleNumberFromThriceAppearingNumbers(arr));
        System.out.println(findSingleNumberFromThriceAppearingNumbersEfficient(arr));
        in.close(); 
    }

    public static int findOneOddAppearingNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static int[] findTwoOddAppearingNumbers(int[] nums){
        int r = 0;
        for(int num:nums){
            r ^= num;
        }

        int rd = r & ~(r - 1); // rd = r & -r
        int r1 = 0;
        int r2 = 0;
        for(int i = 0; i < nums.length; i++){
            if((nums[i] & rd) == 0){
                r1 = r1 ^ nums[i];
            }
            else{
                r2 = r2 ^ nums[i];
            }
        }
        return new int[]{r1,r2};
    }

    public static int findSingleNumberFromThriceAppearingNumbers(int[] nums){
        int ans = 0;
        for(int i = 0; i < 32; i++){
            int count = 0;
            for(int num: nums){
              if((num & (1 << i)) != 0) count++;
            }
            if(count%3 == 1){
                ans = ans | (1 << i);
            } 
        }
        return ans;
    }

    public static int findSingleNumberFromThriceAppearingNumbersEfficient(int[] nums){
        int tn = Integer.MAX_VALUE;
        int tnp1 = 0;
        int tnp2 = 0;

        for(int num:nums){
            int ctn = num & tn;
            int ctn1 = num & tnp1;
            int ctn2 = num & tnp2;

            tn = tn & ~(ctn);
            tnp1 = tnp1 | ctn;

            tnp1 = tnp1 & ~(ctn1);
            tnp2 = tnp2 | ctn1;

            tnp2 = tnp2 & ~(ctn2);
            tn = tn | ctn2; 
        }

        return tnp1;
    }
}