import java.util.Scanner;

public class j08BinarySubstringTillN {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int n = in.nextInt();
        System.out.println(queryString(s,n));
        in.close();
    }

    public static boolean queryString(String s, int n) {
        for (int i = 1; i <= n; i++) {
            String bin = Integer.toBinaryString(i);
            if (!s.contains(bin))
                return false;
        }
        return true;
    }
}
