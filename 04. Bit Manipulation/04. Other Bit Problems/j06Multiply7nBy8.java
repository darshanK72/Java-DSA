import java.util.Scanner;

public class j06Multiply7nBy8 {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(get7nBy8(n));
        in.close();
    }

    public static int get7nBy8(int n){
        return ((n << 3) - n) >> 3;
    }
}
