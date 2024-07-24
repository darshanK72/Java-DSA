import java.util.Scanner;

public class j26ModularArithematic {

    public static long modAdd(long a, long b, long m) {
        return (a % m + b % m) % m;
    }

    public static long modSub(long a,long b, long m){
        return (a % m - b % m) % m;
    }

    public static long modMul(long a,long b, long m){
        return (a % m * b % m) % m;
    }

    public static void main(String args[]){
        long mod = 1000000007L;

        Scanner in = new Scanner(System.in);

        long a = in.nextLong();
        long b = in.nextLong();

        System.out.println(modAdd(a, b, mod));
        System.out.println(modSub(a, b, mod));
        System.out.println(modMul(a, b, mod));

        in.close();
    }
}
