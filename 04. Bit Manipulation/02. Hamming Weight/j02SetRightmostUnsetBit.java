import java.util.Scanner;

public class j02SetRightmostUnsetBit {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(setRightmostUnsetBit(n));
        in.close();
    }

    public static int setRightmostUnsetBit(int n) {
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) == 0) {
                n |= (1 << i);
                break;
            }
        }
        return n;
    }
}
