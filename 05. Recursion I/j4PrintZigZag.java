import java.util.Scanner;

public class j4PrintZigZag {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        printZigZag(n);
        in.close();
    }

    // O(2 ^ n)
    public static void printZigZag(int n){
        if(n == 0){return;}
        System.out.println(n);
        printZigZag(n-1);
        System.out.println(n);
        printZigZag(n-1);
        System.out.println(n);
    }
}
