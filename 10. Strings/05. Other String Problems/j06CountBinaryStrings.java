import java.util.Scanner;

public class j06CountBinaryStrings {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(countBinarySubstrings(s));
        in.close();
    }

    public static int countBinarySubstrings(String s) {
        int prevLen = 0;
        int currLen = 1;
        int count = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currLen++;
            } else {
                count += Math.min(currLen, prevLen);
                prevLen = currLen;
                currLen = 1;
            }
        }

        count += Math.min(currLen, prevLen);
        return count;
    }
}
