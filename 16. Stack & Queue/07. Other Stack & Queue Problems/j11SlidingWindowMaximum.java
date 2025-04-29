import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class j11SlidingWindowMaximum {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(maxSlidingWindow(nums, k));
        in.close();
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] out = new int[n - k + 1];
        int x = 0;
        for (int i = 0; i <= n - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            out[x++] = max;
        }
        return out;
    }

    public static int[] maxSlidingWindowQueue(int[] nums, int k) {
        int[] out = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.getFirst() <= (i - k)) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {
                deque.removeLast();
            }

            deque.addLast(i);
            if (i >= k - 1)
                out[j++] = nums[deque.getFirst()];
        }
        return out;
    }
}
