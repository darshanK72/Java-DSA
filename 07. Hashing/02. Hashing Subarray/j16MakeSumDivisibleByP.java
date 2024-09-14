import java.util.HashMap;
import java.util.Scanner;

public class j16MakeSumDivisibleByP {

    // Given an array of positive integers nums, remove the smallest subarray
    // (possibly empty) such that
    // the sum of the remaining elements is divisible by p. It is not allowed to
    // remove the whole array.
    // Return the length of the smallest subarray that you need to remove, or -1 if
    // it's impossible.

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int p = in.nextInt();
        System.out.println(minSubarrayRemoved(arr, p));
        in.close();
    }

    public static int minSubarrayRemoved(int[] nums, int p) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int k = (int) (sum % p);
        if (k == 0)
            return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        long s = 0;
        int minL = nums.length;
        for (int i = 0; i < nums.length; i++) {
            s += nums[i];
            int currentMod = (int) (s % p);
            int rem = ((currentMod - k) % p + p) % p;
            if (map.containsKey(rem)) {
                minL = Math.min(minL, i - map.get(rem));
            }
            map.put(currentMod, i);
        }
        return minL == nums.length ? -1 : minL;
    }
}
