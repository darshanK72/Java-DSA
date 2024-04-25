import java.util.Scanner;
public class j4SumOfNumbers{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(sumTillN(n));
        in.close();
    }

    // O(n)
    public static int sumTillN(int n){
        if(n == 1) return 1;
        return n + sumTillN(n-1);
    }
}