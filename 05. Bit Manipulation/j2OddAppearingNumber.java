import java.util.Scanner;

public class j2OddAppearingNumber{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] arr = new int[s];
        for(int i = 0; i < s; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(findOneOddAppearingNumber(arr));
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

        int rd = r ^ ~(r - 1); // rd = r ^ -r
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
}