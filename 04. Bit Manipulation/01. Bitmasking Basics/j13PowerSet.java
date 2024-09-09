import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class j13PowerSet {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(getAllPossibleSubsequences(s));
        in.close();
    }

    public static ArrayList<String> getAllPossibleSubsequences(String s) {
        int n = s.length();
        ArrayList<String> output = new ArrayList<String>();
        for (int i = 1; i < Math.pow(2, n); i++) {
            StringBuilder res = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    res.append(s.charAt(j));
                }
            }
            output.add(res.toString());
        }
        Collections.sort(output);
        return output;
    }
}
