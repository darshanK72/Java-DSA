import java.util.Scanner;

public class j14AndOfNumbersInRange{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int l = in.nextInt();
        int r = in.nextInt();

        System.out.println(bitwiseAndOfNumbersInRange(l,r));
        in.close();
    }

    public static int bitwiseAndOfNumbersInRange(int l,int r){
        while(l < r){
            r &= (r - 1);
        }
        return r;
    }
}