import java.util.Scanner;

public class j09ExcelColTitleToNum {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String title = in.nextLine();
        System.out.println(titleToNumber(title));
        in.close();
    }

    public static int titleToNumber(String columnTitle) {
        int i = columnTitle.length() - 1;
        int b = 1;
        int ans = 0;
        while (i >= 0) {
            ans = ans + (columnTitle.charAt(i) - 'A' + 1) * b;
            b *= 26;
            i--;
        }
        return ans;
    }
}
