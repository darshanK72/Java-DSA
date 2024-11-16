import java.util.Scanner;

public class j18WhoHasBellAfterKSeconds {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        System.out.println(numberOfChild(n,k));
        in.close();
    }

    public static int numberOfChild(int n, int k) {
        int start = 0;
        boolean flag = true;
        while (k > 0) {
            if (flag) {
                start++;
                if (start == n - 1)
                    flag = false;
            } else {
                start--;
                if (start == 0)
                    flag = true;
            }
            k--;
        }
        return start;
    }
}
