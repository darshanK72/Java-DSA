import java.util.Scanner;
public class j12CopySetBitsInRange{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int left = in.nextInt();
        int right = in.nextInt();
        System.out.println(n1 + "(" + Integer.toBinaryString(n1) + ")");
        System.out.println(n2 + "(" + Integer.toBinaryString(n2) + ")");
        System.out.println(copySetBitsInRange(n1,n2,left,right) + "(" + Integer.toBinaryString(copySetBitsInRange(n1,n2,left,right)) + ")" );
        System.out.println(copySetBitsInRangeEfficient(n1,n2,left,right) + "(" + Integer.toBinaryString(copySetBitsInRangeEfficient(n1,n2,left,right)) + ")" );;
        in.close();
    }  

    public static int copySetBitsInRange(int n1,int n2,int left,int right){
        for(int i = left - 1; i <= right - 1; i++){
            int bit = n2 & (1 << i);
            if(bit != 0){
                n1 = n1 | bit; 
            }
        }
        return n1;
    }

    public static int copySetBitsInRangeEfficient(int n1,int n2,int left,int right){
        int mask1 = ((1 << (right - left + 1)) - 1 ) << (left - 1);
        System.out.println(Integer.toBinaryString(mask1));
        int mask2 = n2 & mask1;
         System.out.println(Integer.toBinaryString(mask2));
        return n1 | mask2;
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