import java.util.Scanner;

public class j6RightmostSetBit {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(log2(rightMostSetBitMethod(n)));
        System.out.println(rightmostSetBitIndexNive(n));
        System.out.println(log2(rightmostSetBitEfficient(n)));
        
        in.close();
    }

    public static int rightMostSetBitMethod(int n){
        return Integer.lowestOneBit(n);
    }

    public static int rightmostSetBitIndexNive(int n){
        for(int i = 0; i < 32; i++){
            if((n & (1 << i)) != 0) return i;
        }
        return -1;
    }

    public static int rightmostSetBitEfficient(int n) {
        // Performing bitwise AND with two's complement to isolate rightmost set bit
        int rightmostSetBit = n & -n; // n & ~(n - 1)
        return rightmostSetBit;
    }

    public static int log2(int n){
        return (int)(Math.log(n) / Math.log(2));
    }

   
}