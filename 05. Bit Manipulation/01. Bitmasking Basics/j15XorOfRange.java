import java.util.Scanner;

public class j15XorOfRange {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        // int n = in.nextInt();
        // System.out.println(xorFromZeroToN(n));

        int n1 = in.nextInt();
        int n2 = in.nextInt();

        System.out.println(xorFromN1ToN2(n1,n2));
        in.close();
    }

    public static int xorFromZeroToN(int n){
        switch (n % 4) {
            case 0 -> {return n;}
            case 1 -> {return 1;}
            case 2 -> {return n + 1;}
            default -> {return 0;}
        }
    }

    public static int xorFromN1ToN2(int n1,int n2){
        return xorFromZeroToN(n1-1) ^ xorFromZeroToN(n2);
    }
}
