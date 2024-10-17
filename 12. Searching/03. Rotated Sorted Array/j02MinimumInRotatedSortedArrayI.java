import java.util.Scanner;

public class j02MinimumInRotatedSortedArrayI {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(findMin1(arr));
        System.out.println(findMin2(arr));
        System.out.println(findMin3(arr));
        System.out.println(findMin3(arr));
        in.close();
    }

    public static int findMin1(int[] nums) {
        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] >= nums[0]) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        if (s == nums.length)
            return nums[0];
        return nums[s];
    }

    public static int findMin2(int[] nums) {
        int s = 0;
        int e = nums.length - 1;

        while (s < e) {
            int mid = s + (e - s) / 2;

            if (nums[mid] > nums[e]) {
                s = mid + 1; // The minimum is in the right half
            } else if (nums[mid] < nums[e]) {
                e = mid; // The minimum is in the left half, including mid
            }
        }

        return nums[s]; // The minimum will be at index s after the loop ends
    }

    public static int findMin3(int[] nums) {
        int s = 0;
        int e = nums.length - 1;
        int ans = Integer.MAX_VALUE;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if(nums[s] <= nums[mid]){
                ans = Math.min(ans,nums[s]);
                s = mid + 1;
            }else{
                ans = Math.min(ans,nums[mid]);
                e = mid - 1;
            }
        }
        
        return ans;
    }
}
