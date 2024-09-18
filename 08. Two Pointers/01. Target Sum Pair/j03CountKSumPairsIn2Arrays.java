import java.util.Arrays;
import java.util.Scanner;

public class j03CountKSumPairsIn2Arrays {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] arr2 = new int[m];
        for (int i = 0; i < m; i++) {
            arr2[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(countPairs(arr1, arr2, target));
        System.out.println(countPairsEfficient(arr1, arr2, target));
        in.close();
    }

    public static int countPairs(int[] arr1, int[] arr2, int x) {
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] + arr2[j] == x)
                    count++;
            }
        }
        return count;
    }

    public static int countPairsEfficient(int arr1[], int arr2[], int x) {
        int count = 0;
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int i = 0;
        int j = arr2.length - 1;
        while (i < arr1.length && j >= 0) {
            if (arr1[i] + arr2[j] == x) {
                count++;
                i++;
                j--;
            } else if (arr1[i] + arr2[j] > x)
                j--;
            else
                i++;
        }
        return count;
    }
}
