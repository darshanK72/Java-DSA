import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j01GetCombinations {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int k = in.nextInt();

        List<List<Integer>> set1 = new ArrayList<>();
        generateCombinations1(n, k, new ArrayList<Integer>(), set1);
        System.out.println(set1);

        List<List<Integer>> set2 = new ArrayList<>();
        generateCombinations2(1, n, k, new ArrayList<Integer>(), set2);
        System.out.println(set2);

        in.close();
    }

    public static void generateCombinations1(int n, int k, ArrayList<Integer> curr, List<List<Integer>> set) {
        if (k == 0) {
            set.add(new ArrayList<>(curr));
            return;
        }
        if (n == 0 || k > n) {
            return;
        }
        curr.add(n);
        generateCombinations1(n - 1, k - 1, curr, set);
        curr.remove(curr.size() - 1);
        generateCombinations1(n - 1, k, curr, set);
    }

    public static void generateCombinations2(int s, int n, int k, ArrayList<Integer> curr, List<List<Integer>> set) {
        if (k == 0) {
            set.add(new ArrayList<>(curr));
            return;
        }

        for (int i = s; i <= n; i++) {
            curr.add(i);
            generateCombinations2(i + 1, n, k - 1, curr, set);
            curr.remove(curr.size() - 1);
        }
    }
}
