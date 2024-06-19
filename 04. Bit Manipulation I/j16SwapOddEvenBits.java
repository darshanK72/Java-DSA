import java.util.Scanner;
public class j16SwapOddEvenBits{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int result = swapOddEvenBits(n);
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(result));
        in.close();
    }

    // O(1)
    public static int swapOddEvenBits(int n){
        int oddMask = 0x55555555; // 010101010101
        int evenMask = 0xAAAAAAAA; // 101010101010

        int odd = n & oddMask;
        int even = n & evenMask;
        odd >>= 1;
        even <<= 1;

        return even | odd;
    }
}