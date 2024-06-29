import java.util.Scanner;
public class j10MinimumBitFlips{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int start = in.nextInt();
        int goal = in.nextInt();

        System.out.println(minBitFlipsEfficient(start,goal));
        in.close();
    }

    // Hamming Distance

    // O(number of bits)
    public static int minBitFlips(int start,int goal){
        int count = 0;
        while(start > 0 || goal > 0){
            if((start & 1) != (goal & 1)) count++;
            start >>= 1;
            goal >>= 1;
        }
        return count;
    }

    // O(number of set bits)
    public static int minBitFlipsEfficient(int start,int goal){
        int out = start ^ goal;
        int count = 0;
        while(out > 0){
            out &= (out - 1);
            count++;
        }
        return count;
    }

    // using bitcount method of java
    public static int minBitFlipsMethod(int start,int goal){
        return Integer.bitCount(start ^ goal);
    }
}