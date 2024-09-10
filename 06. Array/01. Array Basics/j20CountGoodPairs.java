import java.util.Scanner;

public class j20CountGoodPairs {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(countIdenticalPairs(arr));
        System.out.println(countIdenticalPairsEfficient(arr));
        in.close();
    }

    public static int countIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] == nums[j] && i != j)
                    count++;
            }
        }
        return count;
    }

    public static int countIdenticalPairsEfficient(int[] nums) {
        int count = 0;
        int[] ans = new int[101];
        for (int i = 0; i < nums.length; i++) {
            count += ans[nums[i]]++;
        }
        return count;
    }
}
