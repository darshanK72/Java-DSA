import java.util.Scanner;
public class j13SwapOddEvenBits{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int result = swapOddEvenBits(n);
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(result));
        in.close();
    }

    // O(32) or O(logN)
    public static int swapOddEvenBitsNive(int n){
         for(int i = 0; i < 32; i+= 2){
	        int bit1 = n & (1 << i);
	        int bit2 = n & (1 << (i+1));
	        
	        if((bit1 != 0) != (bit2 != 0)){
	            n = n ^ (1 << i);
	            n = n ^ (1 << (i+1));
	        }
	    }
	    return n; 
    }
    // O(1)
    public static int swapOddEvenBitsEfficient(int n){
        int oddPreservingMask = 0x55555555;  // 01010101010101010101010101010101
        System.out.println(Integer.toBinaryString(oddPreservingMask));
        int evenPreversingMask = 0xAAAAAAAA; // 10101010101010101010101010101010
        System.out.println(Integer.toBinaryString(evenPreversingMask));

        int odd = n & oddPreservingMask;
        System.out.println(Integer.toBinaryString(odd));
        int even = n & evenPreversingMask;
        System.out.println(Integer.toBinaryString(even));
        odd <<= 1;
        even >>= 1;

        return even | odd;
    }
}