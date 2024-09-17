import java.util.Scanner;

public class j05NegativeNumber{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        System.out.println("Positive Number (" + n + ") : " + Integer.toBinaryString(n));
        System.out.println("Negative Number (" + getNegativeNumber(n) + ") : " + Integer.toBinaryString(getNegativeNumber(n)));

        in.close();
    }

    public static int getNegativeNumber(int n){
        int ones = ~n; // Ones Complement
        int twos = ones+1; // Twos Complement
        return twos;
    }

}