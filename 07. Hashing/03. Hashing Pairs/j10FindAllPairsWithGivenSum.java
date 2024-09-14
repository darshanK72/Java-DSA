import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

class Pair {
    long first, second;
    public Pair(long first, long second) {
        this.first = first;
        this.second = second;
    }
}

public class j10FindAllPairsWithGivenSum {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int[] arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = in.nextInt();
        }
        int n2 = in.nextInt();
        int[] arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            arr2[i] = in.nextInt();
        }
        int x = in.nextInt();
        Pair[] pairs = allPairs(x, arr1, arr2);
        for (Pair pair : pairs) {
            System.out.println("(" + pair.first + "," + pair.second + ")");
        }
        in.close();
    }

    public static Pair[] allPairs(int x, int arr1[], int arr2[]) {
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Pair> out = new ArrayList<Pair>();
        for (int ele : arr1) {
            set.add(ele);
        }
        for (int ele : arr2) {
            if (set.contains(x - ele)) {
                out.add(new Pair(x - ele, ele));
            }
        }

        Collections.sort(out, new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                if (p1.first == p2.first) {
                    return Long.compare(p1.second, p2.second);
                }
                return Long.compare(p1.first, p2.first);
            }
        });

        Pair[] out1 = new Pair[out.size()];
        for (int i = 0; i < out.size(); i++) {
            out1[i] = out.get(i);
        }
        return out1;
    }
}
