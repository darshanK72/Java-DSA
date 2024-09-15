import java.util.Scanner;

public class j08ExcelColNumToTitle {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(convertToTitle(num));
        in.close();
    }

    public static String convertToTitle(int columnNumber) {
        StringBuilder ans = new StringBuilder("");
        while (columnNumber > 0) {
            columnNumber--;
            ans.append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }
        return ans.reverse().toString();
    }
}
