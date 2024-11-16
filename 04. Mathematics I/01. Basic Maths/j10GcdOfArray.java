import java.util.Scanner;

public class j10GcdOfArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(gcdOfArray(arr));
        in.close();
    }

    public static int gcd(int a,int b){
        while(b != 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static int gcdOfArray(int[] arr) {
        int gcd = 0;
        for (int i = 0; i < arr.length; i++) {
            gcd = gcd(gcd, arr[i]);
        }
        return gcd;
    }
}
