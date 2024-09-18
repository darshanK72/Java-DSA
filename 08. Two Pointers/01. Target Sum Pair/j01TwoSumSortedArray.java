import java.util.Arrays;
import java.util.Scanner;

public class j01TwoSumSortedArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(Arrays.toString(twoSum(numbers, target)));
        System.out.println(Arrays.toString(twoSumEfficient(numbers, target)));
        in.close();
    }

    public static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target)
                    return new int[] { i + 1, j + 1 };
                else if (numbers[i] + numbers[j] > target)
                    break;
            }
        }
        return new int[] { -1, -1 };
    }

    public static int[] twoSumEfficient(int[] numbers, int target) {
        int s = 0;
        int e = numbers.length - 1;
        while (s < e) {
            if (numbers[s] + numbers[e] == target)
                return new int[] { s + 1, e + 1 };
            else if (numbers[s] + numbers[e] > target)
                e--;
            else
                s++;
        }
        return new int[] { -1, -1 };
    }
}
