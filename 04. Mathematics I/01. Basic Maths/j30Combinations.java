import java.util.Scanner;

public class j30Combinations {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int r = in.nextInt();
        
        System.out.println(n + "C" + r + " = " + nCr(n,r));
        in.close();
    }

    public static int nCr(int n,int r){
        return fact(n) / (fact(r) * fact(n - r));
    }

    public static int fact(int n){
        if(n == 1){
            return 1;
        }
        return n * fact(n -1);
    }
}
