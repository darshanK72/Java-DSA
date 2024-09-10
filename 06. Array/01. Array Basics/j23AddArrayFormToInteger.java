import java.util.ArrayList;
import java.util.Scanner;

public class j23AddArrayFormToInteger {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(addToArrayForm(arr, k));
        in.close();
    }

    public static ArrayList<Integer> addToArrayForm(int[] num, int k) {
        int i = num.length - 1;
        int carry = 0;
        ArrayList<Integer> out = new ArrayList<Integer>();
        while (i >= 0 || carry > 0 || k > 0) {
            int d = carry;
            if (i >= 0)
                d += num[i];
            if (k > 0)
                d += k % 10;
            out.add(0, (d % 10));
            carry = d / 10;
            k /= 10;
            i--;
        }
        return out;
    }
}
