import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class j03FactorsCombinations {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<ArrayList<Integer>> set = factorCombinations(n);
        System.out.println(set);
        in.close();
    }

    public static ArrayList<ArrayList<Integer>> factorCombinations(int n) {
        ArrayList<ArrayList<Integer>> set = new ArrayList<>();
        generateFactorCombinations(n, 2, new ArrayList<>(), set);

        Collections.sort(set, (a, b) -> {
            int minSize = Math.min(a.size(), b.size());
            for (int i = 0; i < minSize; i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return Integer.compare(a.get(i), b.get(i));
                }
            }
            return Integer.compare(a.size(), b.size());
        });

        return set;
    }

    public static void generateFactorCombinations(int n, int start, ArrayList<Integer> factors,
            ArrayList<ArrayList<Integer>> set) {
        if (factors.size() > 0) {
            factors.add(n);
            set.add(new ArrayList<>(factors));
            factors.remove(factors.size() - 1);
        }

        for (int i = start; i <= n / i; i++) {
            if (n % i == 0) {
                factors.add(i);
                generateFactorCombinations(n / i, i, factors, set);
                factors.remove(factors.size() - 1);
            }
        }
    }
}
