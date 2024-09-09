import java.util.Scanner;

public class j09Power {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        System.out.println(power(n, p));
        System.out.println(powerEfficient(n, p));
        System.out.println(powerMoreEfficient(n, p));
        in.close();
    }

    // O(n)
    public static int power(int n, int p) {
        if(p == 0) return 1;
        return n * power(n, p-1);
    }

    // O(n)
    public static int powerEfficient(int n,int p){
        if(p == 0) return 1;
        if((p & 1) == 0) return powerEfficient(n, p/2) * powerEfficient(n, p/2);
        else return n * powerEfficient(n, p/2) * powerEfficient(n,p/2);
    }

     // O(log(n))
     public static int powerMoreEfficient(int n,int p){
        if(p == 0) return 1;

        int power = powerMoreEfficient(n, p/2);
        if((p & 1) == 0) return power * power;
        else return n * power * power;
    }
}
