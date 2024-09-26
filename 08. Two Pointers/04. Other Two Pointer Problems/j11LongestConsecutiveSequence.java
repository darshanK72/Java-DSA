import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;

public class j11LongestConsecutiveSequence {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(longestConsecutiveSequenceBruitForce(arr));
        System.out.println(longestConsecutiveSequenceUsingSorting(arr));
        System.out.println(longestConsecutiveSequenceUsingHashing(arr));
        in.close();
    }

    // O(n^2)
    public static int longestConsecutiveSequenceBruitForce(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];
            int l = 1;
            while (search(arr, n + 1)) {
                n++;
                l++;
            }
            ans = Math.max(ans, l);
        }
        return ans;
    }

    // O(n * log n)
    public static int longestConsecutiveSequenceUsingSorting(int[] arr) {
        Arrays.sort(arr);
        int l = 0;
        int ans = 0;
        int last = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (last == arr[i] - 1) {
                l++;
                last = arr[i];
            } else {
                ans = Math.max(ans, l);
                l = 1;
                last = arr[i];
            }
        }
        return ans;
    }

    public static int longestConsecutiveSequenceTwoPointers(int[] arr) {
        if (arr.length == 0)
            return 0;
        Arrays.sort(arr);
        int l = 1;
        int tempL = 1;
        int i = 0;
        while (i < arr.length - 1) {
            if (arr[i] == arr[i + 1]) {
                while (i + 1 < arr.length && arr[i] == arr[i + 1])
                    i++;
                continue;
            }
            if (arr[i] + 1 == arr[i + 1]) {
                tempL++;
            } else {
                l = Math.max(l, tempL);
                tempL = 1;
            }
            i++;
        }
        l = Math.max(l, tempL);
        return l;
    }

    // O(n)
    public static int longestConsecutiveSequenceUsingHashing(int[] arr) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        int ans = 0;
        for (int n : set) {
            if (!set.contains(n - 1)) {
                int l = 1;
                int x = n;
                while (set.contains(x + 1)) {
                    x++;
                    l++;
                }
                ans = Math.max(ans, l);
            }
        }
        return ans;
    }

    public static boolean search(int[] arr, int n) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == n)
                return true;
        }
        return false;
    }
}