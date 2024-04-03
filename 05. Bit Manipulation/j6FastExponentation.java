import java.util.Scanner;
public class j6FastExponentation{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();

        System.out.println(fastExp(n,p));
        in.close();
    }

    public static int fastExp(int n,int p){
        int ans = 1;
        while(p > 0){
            if((p & 1) == 1) ans = ans * n;
            n = n * n;
            p >>= 1;
        }
        return ans;
    }
}