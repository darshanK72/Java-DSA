
public class j08MaximumGap {

    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        for (int i = 1; i <= (int) 1e8; i *= 10) {
            nums = redixSort(nums, i);
        }
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > gap) {
                gap = nums[i] - nums[i - 1];
            }
        }
        return gap;
    }

    public static int[] redixSort(int[] nums, int place) {
        int[] freq = new int[10];

        for (int ele : nums) {
            int digit = (ele % (10 * place)) / place;
            freq[digit]++;
        }

        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i - 1];
        }

        int[] out = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int ele = nums[i];
            int digit = (ele % (10 * place)) / place;
            int idx = --freq[digit];
            out[idx] = ele;
        }
        return out;
    }
}
