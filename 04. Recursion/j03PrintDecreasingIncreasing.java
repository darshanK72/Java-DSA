import java.util.Scanner;

public class j03PrintDecreasingIncreasing {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        printDecreasingIncreasing(n);
        in.close();
    }

    // O(n)
    public static void printDecreasingIncreasing(int n){
        if(n == 0){return;}
        System.out.println(n);
        printDecreasingIncreasing(n-1);
        System.out.println(n);
    }
}
