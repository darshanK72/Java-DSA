import java.util.Scanner;

public class j07ValidPalindrome1 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(isPalindrome(s));
        in.close();
    }

    public static boolean isPalindrome(String str, int s, int e) {
        if (s >= e)
            return true;
        return (str.charAt(s) == str.charAt(e)) && isPalindrome(str, ++s, --e);
    }

    // O(str.length())
    public static boolean isPalindrome(String str) {
        str = str.toLowerCase();
        int s = 0;
        int e = str.length() - 1;
        while (s < e) {
            char c1 = str.charAt(s);
            char c2 = str.charAt(e);
            if (!isAlphaNumeric(c1)) {
                s++;
                continue;
            }
            if (!isAlphaNumeric(c2)) {
                e--;
                continue;
            }
            if (str.charAt(s) != str.charAt(e))
                return false;
            else {
                s++;
                e--;
            }
        }
        return true;
    }

    public static boolean isAlphaNumeric(char c) {
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
            return true;
        }
        return false;

    }
}
