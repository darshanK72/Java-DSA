import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j01CountZeroSumSubmatrix {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            ArrayList<Integer> lst = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                lst.add(in.nextInt());
            }
            list.add(lst);
        }
        System.out.println(countZeroSumSubmatrix(list));
        in.close();
    }

    public static int countZeroSumSubmatrix(ArrayList<ArrayList<Integer>> A) {
        int m = A.size();
        if (m == 0)
            return 0;
        int n = A.get(0).size();
        int ans = 0;
        int[] sumPrefix = new int[n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(sumPrefix, 0);
            for (int j = i; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    sumPrefix[k] += A.get(j).get(k);
                }
                ans += subarraySumK(sumPrefix);
            }
        }
        return ans;
    }

    public static int subarraySumK(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum)) {
                count += map.get(sum);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
