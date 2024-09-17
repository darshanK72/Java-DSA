import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j03KSumSubmatrix {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        int k = in.nextInt();
        System.out.println(numSubmatrixSumTarget(matrix, k));
        in.close();
    }

    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] preSumRow = new int[n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            Arrays.fill(preSumRow, 0);
            for (int j = i; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    preSumRow[k] += matrix[j][k];
                }
                ans += subarraySumK(preSumRow, target);
            }
        }
        return ans;
    }

    public static int subarraySumK(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        map.put(sum, 1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
