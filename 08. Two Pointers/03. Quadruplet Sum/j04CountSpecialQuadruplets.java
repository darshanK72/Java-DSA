import java.util.Scanner;

public class j04CountSpecialQuadruplets {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(countQuadruplets(arr));
        // System.out.println(countQuadrupletsEfficient(arr));
        in.close();
    }

    public static int countQuadruplets(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        int sum = nums[i] + nums[j] + nums[k];
                        if (sum == nums[l])
                            count++;
                    }
                }
            }
        }
        return count;
    }

    // public static int countQuadrupletsEfficient(int[] nums){

    // }
}
