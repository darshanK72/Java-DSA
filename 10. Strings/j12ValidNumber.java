import java.util.Scanner;

public class j12ValidNumber {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(validNumber(s));
        in.close();
    }

    // O(s.length())
    public static boolean validNumber(String s) {
        if (s.isEmpty())
            return false;

        boolean hasNum = false;
        boolean hasDot = false;
        boolean hasExp = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {
                hasNum = true;
            } else if (c == '.') {
                if (hasDot || hasExp)
                    return false;
                hasDot = true;
            } else if (c == 'e' || c == 'E') {
                if (hasExp || !hasNum)
                    return false;
                hasExp = true;
                hasNum = false;
            } else if (c == '+' || c == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E')
                    return false;
            } else {
                return false;
            }
        }
        return hasNum;
    }
}