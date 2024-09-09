import java.util.Scanner;
public class j02ReverseBits{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(reverseBitsNive(n)));
        System.out.println(Integer.toBinaryString(reverseBitsEfficient(n)));
        in.close();
    }


    // O(32) or O(logN)
    public static int reverseBitsNive(int n){
        int s = 0;
        int e = 31;
        while(s < e){
           n = swapBits(n,s,e);
           s++;
           e--;
        }
        return n;
    }

    // O(1)
    public static int swapBits(int n,int s,int e){
        int leftBit = n & (1 << s);
        int rightBit = n & (1 << e);

        if((leftBit != 0) != (rightBit != 0)){
            n = n ^ (1 << s);
            n = n ^ (1 << e);
        }
        return n;
    }

    // O(32) or O(logN)
    public static int reverseBitsEfficient(int n) {
     int ans = 0;
       for(int i = 0; i < 32; i++){
                ans <<= 1;
                ans |= (n & 1);
                n >>= 1;
       }
       return ans;

    }
}