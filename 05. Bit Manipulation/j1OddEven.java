import java.util.Scanner;
public class j1OddEven{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(isOdd(n));
        in.close(); 
    }

    public static boolean isOdd(int n){
        return (n & 1) == 1;
    }
}