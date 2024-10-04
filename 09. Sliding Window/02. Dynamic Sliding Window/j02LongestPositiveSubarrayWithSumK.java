import java.util.Scanner;

public class j02LongestPositiveSubarrayWithSumK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();

        System.out.println(longestSubarraySumK(arr, k));
        System.out.println(longestSubarraySumKEfficientForPositive1(arr, k));
        System.out.println(longestSubarraySumKEfficientForPositive2(arr, k));
        in.close();
    }

    // O(n^2)
    public static int longestSubarraySumK(int[] arr, int k) {
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }

    // O(n) -> Optimized for +ve (Two Pointers)
    public static int longestSubarraySumKEfficientForPositive1(int[] arr, int k) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int maxLength = 0;
        while (j < arr.length) {
            while (i <= j && sum > k) {
                sum -= arr[i];
                i++;
            }
            if (sum == k) {
                maxLength = Math.max(maxLength, j - i + 1);
            }
            if (j < arr.length)
                sum += arr[j];
            j++;
        }
        return maxLength;
    }

    // O(n) -> Optimized for +ve (Two Pointers)
    public static int longestSubarraySumKEfficientForPositive2(int[] arr, int k) {
        int i = 0; // Left pointer
        int j = 0; // Right pointer
        int sum = 0; // To track the sum of the current window
        int maxLength = 0; // To track the length of the longest subarray with sum exactly equal to k

        // Iterate over the array using two pointers
        while (j < arr.length) {
            // Add the current element to the sum
            sum += arr[j];

            // If sum exceeds k, move the left pointer to shrink the window
            while (i <= j && sum > k) {
                sum -= arr[i];
                i++;
            }

            // If the sum is exactly equal to k, update the maxLength
            if (sum == k) {
                maxLength = Math.max(maxLength, j - i + 1);
            }

            // Move the right pointer
            j++;
        }

        return maxLength;
    }

     // O(n) -> Optimized for +ve (Two Pointers)
     public static int longestSubarraySumKEfficientForPositive3(int[] arr, int k) {
        int i = 0; // Left pointer
        int j = 0; // Right pointer
        int sum = 0; // To track the sum of the current window
        int maxLength = 0; // To track the length of the longest subarray with sum exactly equal to k

        // Iterate over the array using two pointers
        while (j < arr.length) {
            // Add the current element to the sum
            sum += arr[j];

            // If sum exceeds k, move the left pointer to shrink the window
            if(i <= j && sum > k) {
                sum -= arr[i];
                i++;
            }

            // If the sum is exactly equal to k, update the maxLength
            if (sum == k) {
                maxLength = Math.max(maxLength, j - i + 1);
            }

            // Move the right pointer
            j++;
        }

        return maxLength;
    }
}
