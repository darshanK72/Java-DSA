import java.util.HashMap;
import java.util.Scanner;

public class j69CountNicePairs {

    // You are given an array nums that consists of non-negative integers. Let us
    // define rev(x) as the reverse of the non-negative integer x. For example,
    // rev(123) = 321, and rev(120) = 21. A pair of indices (i, j) is nice if it
    // satisfies all of the following conditions:
    // 0 <= i < j < nums.length
    // nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
    // Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7.

 
     public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(countNicePairs(arr));
        in.close();
    }

    public static int countNicePairs(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            int revDiff = nums[i] - rev(nums[i]);
            if(map.containsKey(revDiff)){
                count = (count + map.get(revDiff)) % 1000000007;
                map.put(revDiff,map.get(revDiff) + 1); 
            }else{
                map.put(revDiff,1);
            }
        }
        return count;
    }

    public static int rev(int n){
        int rev = 0;
        while(n > 0){
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        return rev;
    }
}
