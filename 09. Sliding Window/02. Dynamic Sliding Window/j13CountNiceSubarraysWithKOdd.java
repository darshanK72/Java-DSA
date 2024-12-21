import java.util.Scanner;

public class j13CountNiceSubarraysWithKOdd {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // The length of the array
        int[] arr = new int[n]; // Array to store the elements
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Fill the array
        }
        int k = in.nextInt(); // The number of odd numbers required in subarrays
        System.out.println(numberOfSubarrays(arr, k)); // Print the result
        in.close();
    }

    // Function to count the number of subarrays with exactly k odd numbers
    public static int numberOfSubarrays(int[] nums, int k) {
        return atMostKOdd(nums, k) - atMostKOdd(nums, k - 1); // Return the difference
    }

    // Function to count subarrays with at most k odd numbers
    public static int atMostKOdd(int[] nums, int k) {
        int odds = 0; // Counter for odd numbers
        int ans = 0; // Result counter
        int j = 0; // Left pointer for the sliding window

        // Iterate through the array with the right pointer `i`
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) // If the element is odd
                odds++; // Increment the odd count

            // Shrink the window if the number of odd elements exceeds k
            while (odds > k && j <= i) {
                if (nums[j] % 2 == 1) {
                    odds--; // Decrease the odd count
                }
                j++; // Move the left pointer to shrink the window
            }

            // Add the number of subarrays ending at `i`
            ans += (i - j + 1);
        }
        return ans; // Return the total count
    }
}
