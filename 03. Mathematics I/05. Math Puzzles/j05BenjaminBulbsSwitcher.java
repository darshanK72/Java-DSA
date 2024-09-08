import java.util.Scanner;

// Complexity : O(sqrt(N))
public class j05BenjaminBulbsSwitcher {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(bulbSwitch(n));
        in.close();
    }

    public static int bulbSwitch(int n) {
        int c = 0;
        for (int i = 1; i * i <= n; i++)
            c++;
        return c;
    }
}
