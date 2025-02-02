
public class j07RedixSort {

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

    public int[] sortArray(int[] nums) {
        for (int i = 1; i <= (int) 1e8; i *= 10) {
            nums = redixSort(nums, i);
        }
        return nums;
    }
}
