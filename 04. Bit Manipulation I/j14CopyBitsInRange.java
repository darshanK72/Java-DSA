import java.util.Scanner;
public class j14CopyBitsInRange{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int left = in.nextInt();
        int right = in.nextInt();
        System.out.println(copyBitsInRange(n1,n2,left,right));
        in.close();
    }   
    public static int copyBitsInRange(int n1,int n2,int left,int right){
        int mask1 = ((1 << (right - left + 1)) - 1 ) << (right - 1);
        int mask2 = n1 & mask1;
        return n2 | mask2;
    }


    //   65  - 1000001
    //   66  - 1000010
    // mask1 - 0100000 
    //      -> 0011111
    //      -> 0111110
    // mask2 = 10000001 & 0111110
    //         10000001
    // n2 & mask2 = 10000001 | 1000010 = 1000010 = 66


}