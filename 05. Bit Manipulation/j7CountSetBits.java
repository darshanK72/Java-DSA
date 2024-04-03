import java.util.Scanner;
public class j7CountSetBits{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(countSetBits(n));

        in.close();
    }

    public static int countSetBits(int n){
        int count = 0;
        while(n > 0){
            if((n & 1) == 1) count++;
            n >>= 1;
        }
        return count;
    }
}