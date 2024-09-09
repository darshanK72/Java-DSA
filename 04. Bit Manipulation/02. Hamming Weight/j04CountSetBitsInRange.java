import java.util.Scanner;

public class j04CountSetBitsInRange {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(countSetBitsInRangeNive(n));
        System.out.println(countSetBitsInRangeEfficient1(n));
        System.out.println(countSetBitsInRangeEfficient2(n));
        in.close();
    }

    // O(n * 32)
    public static int countSetBitsInRangeNive(int n){
        int count = 0;
        for(int i = 1; i <= n; i++){
            int x = i;
            while(x != 0){
                x &= (x - 1);
                count++;
            }
        }
        return count;
    }

    // O(n)
    public static int countSetBitsInRangeEfficient1(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += Integer.bitCount(i);
        }
        return count;
    }

    // O(sqrt(n))
    public static int countSetBitsInRangeEfficient2(int n) {
        int count = 0;
        for (int i = 0; (1 << i) <= n; i++) {
            // Calculate the size of the block where the bit at position i is set
            int blockSize = 1 << (i + 1);
            int completeBlocks = n / blockSize;
            
            // Count set bits from the complete blocks
            count += completeBlocks * (blockSize >> 1);
            
            // Handle remaining bits after the last complete block
            int remainder = n % blockSize;
            count += Math.max(0, remainder - (blockSize >> 1) + 1);
        }
        return count;
    }

    private static final int[] lookupTable = new int[256];

    static {
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
        count += lookupTable[(num >>> 8) & 0xFF];    // Count set bits in the second byte
        count += lookupTable[(num >>> 16) & 0xFF];   // Count set bits in the third byte
        count += lookupTable[(num >>> 24) & 0xFF];   // Count set bits in the most significant byte
        return count;
    }
}
