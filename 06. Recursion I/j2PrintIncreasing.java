import java.util.Scanner;

public class j2PrintIncreasing {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        printIncreasing(n);
        in.close();
    }

    // O(n)
    public static void printIncreasing(int n){
        if(n == 0){
            return;
        }
        printIncreasing(n-1);
        System.out.println(n);
    }
}
