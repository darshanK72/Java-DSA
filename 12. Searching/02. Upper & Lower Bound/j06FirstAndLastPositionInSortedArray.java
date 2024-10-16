import java.util.Scanner;

public class j06FirstAndLastPositionInSortedArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();
        int[] out = searchRange1(arr, target);
        System.out.println("first : " + out[0]);
        System.out.println("last : " + out[1]);
        in.close();
    }

    public static int[] searchRange1(int[] nums, int target) {
        int first = -1;
        int last = -1;
        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] < target) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        first = s;

        s = 0;
        e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] > target) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        last = e;

        if (first > last)
            return new int[] { -1, -1 };
        return new int[] { first, last };
    }

    public int[] searchRange2(int[] nums, int target) {
        int[] ans = new int[]{-1,-1};
        ans[0] = findPosition(nums,target,true);
        ans[1] = findPosition(nums,target,false);
        return ans;
    }

    public static int findPosition(int[] nums,int target,boolean findFirst){
        int s = 0;
        int e = nums.length - 1;
        int pos = -1;
        while(s <= e){
            int mid = s + (e - s)/2;
            if(nums[mid] < target){
                s = mid + 1;
            }else if(nums[mid] > target){
                e = mid - 1;
            }else{
                pos = mid;
                if(findFirst){
                    e = mid - 1;
                }else{
                    s = mid + 1;
                }
            }
        }
        return pos;
    }
}
