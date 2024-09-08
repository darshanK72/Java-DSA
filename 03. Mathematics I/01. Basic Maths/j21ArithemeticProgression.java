import java.util.Scanner;

public class j21ArithemeticProgression{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int n = in.nextInt();
        int d = in.nextInt();

        printAP(a, n, d);
        System.out.println("Nth Term : " + getNthTerm(a, 52, d));
        System.out.println("Sum of N Terms : " + getSumOfNTerms(a, 23, d));

        in.close();
    }

    public static void printAP(int a,int n,int d){
        System.out.print("Series : ");
        for(int i = 0; i < n; i++){
            System.out.print(a + " ");
            a += d;
        }
        System.out.println();
    }

    public static int getNthTerm(int a, int n, int d){
        return a + (n - 1) * d;
    }

    public static int getSumOfNTerms(int a,int n, int d){
        return (n/2)*(2*a + (n - 1)*d);
    }
}