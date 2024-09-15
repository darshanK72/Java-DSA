import java.util.Scanner;

public class j05ReverseVowels {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(reverseVowels(s));
        in.close();
    }

    public static String reverseVowels(String string) {
        char[] str = string.toCharArray();
        int s = 0;
        int e = string.length() - 1;

        while (s < e) {
            if (isVovel(str[s]) && isVovel(str[e])) {
                replace(str, s, e);
                s++;
                e--;
            }
            if (!isVovel(str[s])) {
                s++;
            }
            if (!isVovel(str[e])) {
                e--;
            }
        }
        return new String(str);
    }

    public static boolean isVovel(char c) {
        if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u'
                || c == 'U') {
            return true;
        }
        return false;
    }

    public static void replace(char[] str, int s, int e) {
        char temp = str[s];
        str[s] = str[e];
        str[e] = temp;
    }
}
