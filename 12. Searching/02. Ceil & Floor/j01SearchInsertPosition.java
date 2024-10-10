import java.util.Scanner;

public class j01SearchInsertPosition {

    // Lower Bound: Finds the index of the first element that is greater than or equal to the target.
    // Ceiling Index: Finds the index of the smallest element greater than or equal to the target.
    // Insert Index: This is where the target would be inserted while maintaining sorted order, 
    // which is exactly the position of the first element greater than or equal to the target.
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(searchInsert(arr, target));
        in.close();
    }

    public static int searchInsert(int[] nums, int target) {
        int s = 0;
        int e  = nums. length - 1;
        while(s <= e){
            int mid = (s + e) / 2; 
            if(nums[mid] < target){
                s  = mid + 1;
            }else{
                e = mid - 1;
            }
        }
        return s;
    }
}
