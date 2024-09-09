import java.util.Scanner;

public class j04SwapNibbles {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int result = swapNibbles(n);
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(result));
        in.close();
    }
    
    public static int swapNibbles(int n) {
        int left = (n & 15) << 4;
        int right = (n & (15 << 4)) >> 4;
        return left | right;
    }
}
