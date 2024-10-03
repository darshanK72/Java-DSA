import java.util.Scanner;

public class j04LongPressedName {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        String typed = in.nextLine();
        System.out.println(isLongPressedName(name,typed));
        in.close();
    }

    public static boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }

        return i == name.length();
    }
}
