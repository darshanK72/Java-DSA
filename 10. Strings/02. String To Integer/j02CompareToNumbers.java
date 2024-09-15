import java.util.Scanner;

public class j02CompareToNumbers {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(compare(s1, s2));
        in.close();
    }

    public static int compare(String a, String b) {
        StringBuilder aa = new StringBuilder(a);
        StringBuilder bb = new StringBuilder(b);

        while (aa.length() > 0 && aa.charAt(0) == '0') {
            aa.deleteCharAt(0);
        }
        while (bb.length() > 0 && bb.charAt(0) == '0') {
            bb.deleteCharAt(0);
        }

        // System.out.println(aa + " " + bb);
        // return 1;

        if (aa.length() < bb.length())
            return 1;
        else if (aa.length() > bb.length())
            return 2;
        else {
            for (int i = 0; i < aa.length(); i++) {
                if (aa.charAt(i) < bb.charAt(i))
                    return 1;
                else if (aa.charAt(i) > bb.charAt(i))
                    return 2;
            }
        }
        return 3;
    }
}
