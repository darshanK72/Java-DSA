import java.util.HashSet;
import java.util.Scanner;

public class j02CountPairsWithXorK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(countPairsWithXorK(arr, k));
        in.close();
    }

    public static int countPairsWithXorK(int[] arr, int k) {
        HashSet<Integer> set = new HashSet<Integer>();
        int count = 0;
        for (int a : arr) {
            if (set.contains(a ^ k))
                count++;
            set.add(a);
        }
        return count;
    }
}
