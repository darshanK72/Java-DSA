import java.util.Scanner;

public class j22PrintGP {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int n = in.nextInt();
        int r = in.nextInt();

        printGp(a, n, r);
        System.out.println("Nth Term : " + getNthTerm(a, 52, r));
        System.out.println("Sum of N Terms : " + getSumOfNTerms(a, 23, r));


        in.close();
    }

    public static void printGp(int a,int n, int r){
        System.out.print("Seriese : ");
        for(int i = 0; i < n ; i++){
            System.out.print(a + " ");
            a *= r;
        }
        System.out.println();
    }

    public static double getNthTerm(int a, int n, int r){
        return a * Math.pow(r,n-1);
    }

    public static double getSumOfNTerms(int a, int n, int r){
        if(r < 1){
            return (a * (1 - Math.pow(r,n)))/(1-r);
        }
        else{
            return (a * (Math.pow(r,n) - 1))/(r-1);
        }
    }
}
