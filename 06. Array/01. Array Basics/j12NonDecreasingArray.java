public class j12NonDecreasingArray {
    public static boolean checkNonDecreasing(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int current = arr[i];
            int next = arr[i + 1];
            if (current > next) {
                arr[i] = next;
                if (isSorted(arr)) {
                    return true;
                }
                arr[i] = current;
                arr[i + 1] = current;
                return isSorted(arr);
            }
        }
        return true;
    }

    private static boolean isSorted(int[] nums) {
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean checkNonDecreasingEfficient(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                if (count > 0)
                    return false;
                count++;

                if (i == 0 || arr[i - 1] <= arr[i + 1]) {
                    arr[i] = arr[i + 1];
                } else {
                    arr[i + 1] = arr[i];
                }
            }
        }
        return true;
    }
}
