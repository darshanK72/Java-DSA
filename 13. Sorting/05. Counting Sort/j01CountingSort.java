import  java.util.Arrays;
import java.util.Scanner;

public class j01CountingSort{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = in.nextInt(); // Input: size of the array

        int[] arr = new int[n];
        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Output the rearranged array
        System.out.println("Sorted array: " + Arrays.toString(countingSort(arr)));

        in.close();
    }

    public static int[] countingSort(int[] nums) {
        int min = -50000;
        int max = 50000;
        int[] freq = new int[max - min + 1];

        for(int ele : nums){
            freq[ele - min]++;
        }

        int idx = 0;
        for(int i = min; i <= max; i++){
            while(freq[i - min] > 0){
                nums[idx] = i;
                idx++;
                freq[i - min]--;
            }
        }
        return nums;
    }

     public static int[] countingSortStable(int[] nums) {
        int min = -50000;
        int max = 50000;
        int[] freq = new int[max - min + 1];

        for(int ele : nums){
            freq[ele - min]++;
        }

        for(int i = 1; i < freq.length; i++){
            freq[i] += freq[i - 1];
        }

        int[] out = new int[nums.length];
        for(int i = nums.length - 1; i >= 0; i--){
            int ele = nums[i];
            int idx = --freq[ele - min];
            out[idx] = ele;
        }
        return out;
    }
}