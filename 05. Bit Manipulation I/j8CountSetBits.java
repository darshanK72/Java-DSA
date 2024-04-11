import java.util.Scanner;
public class j8CountSetBits{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(countSetBitsEfficient(n));

        in.close();
    }

    public static int countSetBits(int n){
        int count = 0;
        while(n > 0){
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    // Kahingam Algorithms
    public static int countSetBitsEfficient(int n){
        int count = 0;
        while(n > 0){
            n &= (n-1);
            count++;
        }
        return count;
    }

    // Lookup table for number of set bits in a byte
    private static final int[] lookupTable = new int[256];

    static {
        // Precompute the lookup table
        for (int i = 0; i < 256; i++) {
            lookupTable[i] = (i & 1) + lookupTable[i / 2];
            // Count set bits in each byte value i:
            // i & 1 will give 1 if the least significant bit is set, otherwise 0.
            // i / 2 will shift i to the right by 1 bit.
            // By adding the LSB and recursively counting set bits in the shifted value, we count the total set bits.
        }
    }

    public static int countSetBitsLookupTable(int num) {
        int count = 0;
        // Count set bits in each byte of the number
        count += lookupTable[num & 0xFF];           // Count set bits in the least significant byte
        count += lookupTable[(num >> 8) & 0xFF];    // Count set bits in the second byte
        count += lookupTable[(num >> 16) & 0xFF];   // Count set bits in the third byte
        count += lookupTable[(num >> 24) & 0xFF];   // Count set bits in the most significant byte
        return count;
    }
} 