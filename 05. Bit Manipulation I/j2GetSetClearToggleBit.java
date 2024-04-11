import java.util.Scanner;

public class j2GetSetClearToggleBit{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        int n = in.nextInt();

        int getBit = number & (1 << (n-1));
        int setBit = number | (1 << (n-1));
        int clearBit = number & ~((1 << (n-1)));
        int toggleBit = number ^ (1 << (n-1));

        System.out.println(Integer.toBinaryString(number));
        System.out.println(Integer.toBinaryString(getBit));
        System.out.println(Integer.toBinaryString(setBit));
        System.out.println(Integer.toBinaryString(clearBit));
        System.out.println(Integer.toBinaryString(toggleBit));
        in.close();
    }
}