import java.util.Scanner;

public class j27Permutations {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int r = in.nextInt();
        
        System.out.println(n + "P" + r + " = " + nPr(n,r));
        in.close();
    }

    public static int nPr(int n,int r){
        return fact(n) / fact(n - r);
    }

    public static int fact(int n){
        if(n == 1){
            return 1;
        }
        return n * fact(n -1);
    }
}
