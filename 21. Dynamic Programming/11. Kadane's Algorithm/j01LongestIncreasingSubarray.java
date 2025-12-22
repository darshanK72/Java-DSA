public class j01LongestIncreasingSubarray {
    
    public static int findLengthOfLCIS(int[] nums) {
        int maxLength = 1;
        int currLength = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                currLength++;
            } else {
                maxLength = Math.max(currLength, maxLength);
                currLength = 1;
            }
        }
        return Math.max(currLength, maxLength);
    }
}
