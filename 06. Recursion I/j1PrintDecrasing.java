import java.util.Scanner;

public class j1PrintDecrasing {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        printDecreasing(n);
        in.close();
    }

    // O(n)
    public static void printDecreasing(int n){
        if(n == 0){
            return;
        }
        System.out.println(n);
        printDecreasing(n-1);
    }
}
