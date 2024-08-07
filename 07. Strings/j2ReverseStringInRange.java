import java.util.Scanner;

public class j2ReverseStringInRange {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int k = in.nextInt();
        System.out.println(reverseStringInRange(s,k));
        in.close();
    }

    // O(str.length())
    public static String reverseStringInRange(String str,int k) {
        char[] out = str.toCharArray();
        for (int i = 0; i < str.length(); i += (2 * k)) {
            int s = i;
            int e = Math.min(str.length() - 1, i + k - 1);
            while (s < e) {
                char t = out[s];
                out[s] = out[e];
                out[e] = t;
                s++;
                e--;
            }
        }
        return new String(out);
    }

}
