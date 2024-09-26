import java.util.HashSet;
import java.util.Scanner;

public class j02IsArithematicSequence {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(isArithmaticSequence(arr));
        in.close();
    }

    public static boolean isArithmaticSequence(int[] arr) {
        if(arr.length <= 1) return true;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
            set.add(arr[i]);
        }
        int d = (max - min) / (arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            int ele = min + i * d;
            if (!set.contains(ele))
                return false;
        }
        return true;
    }

}
