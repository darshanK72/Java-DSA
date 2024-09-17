import java.util.Scanner;

public class j04MagicNumber{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int ans = 0;
        int i = 5;
        while(n > 0){
            int d = n & 1;
            ans = d * i + ans;
            n >>= 1;
            i *= 5;
        }

        System.out.println(ans);
        in.close();
    }
}