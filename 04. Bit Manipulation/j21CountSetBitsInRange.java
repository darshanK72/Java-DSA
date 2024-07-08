import java.util.Scanner;
import java.util.Arrays;

public class j21CountSetBitsInRange {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(countSetBitsInRangeNive(n));
        System.out.println(Arrays.toString(countSetBitsInRagneEfficient(n)));

        in.close();
    }


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
    public static int countSetBitsInRangeEfficient(int n){
        int count = 0;
        int x = 1;
        int i = 1;
        while((x << i) <= n){
            i++;
        }
        
        return count;
    }

    public static int[] countSetBitsInRagneEfficient(int n){
        int[] out = new int[n+1];
        for(int i = 0; i <= n; i++){
            out[i] = out[(i >> 1)] + (i & 1);
        }
        return out;
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
