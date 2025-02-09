import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j02SubsetsOfGivenSize {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int k = in.nextInt();

        List<List<Integer>> set1 = new ArrayList<>();
        generateCombinations1(arr, 0, k, new ArrayList<Integer>(), set1);
        System.out.println(set1);

        List<List<Integer>> set2 = new ArrayList<>();
        generateCombinations2(arr, 0, arr.length, k, new ArrayList<Integer>(), set2);
        System.out.println(set2);

        in.close();
    }

    public static void generateCombinations1(int[] arr, int i, int k, ArrayList<Integer> curr,
            List<List<Integer>> set) {
        if (k == 0) {
            set.add(new ArrayList<>(curr));
            return;
        }
        if (i == arr.length) {
            return;
        }
        curr.add(arr[i]);
        generateCombinations1(arr, i + 1, k - 1, curr, set);
        curr.remove(curr.size() - 1);
        generateCombinations1(arr, i + 1, k, curr, set);
    }

    public static void generateCombinations2(int[] arr, int s, int n, int k, ArrayList<Integer> curr,
            List<List<Integer>> set) {
        if (k == 0) {
            set.add(new ArrayList<>(curr));
            return;
        }

        for (int i = s; i < n; i++) {
            curr.add(arr[i]);
            generateCombinations2(arr, i + 1, n, k - 1, curr, set);
            curr.remove(curr.size() - 1);
        }
    }

}
