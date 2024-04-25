import java.util.Scanner;

public class j6Power {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        System.out.println(powerEfficient(n, p));
        in.close();
    }

    // O(n)
    public static int power(int n, int p) {
        if(p == 0) return 1;
        return n * power(n, p-1);
    }

    // O(log(n))
    public static int powerEfficient(int n,int p){
        if(p == 0) return 1;
        if((p & 1) == 0) return power(n, p/2) * power(n, p/2);
        else return n * power(n, p/2) * power(n,p/2);
    }
}
