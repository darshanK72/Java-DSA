import java.util.Scanner;

// Complexity : O(1)

public class j11RotateNumber {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        System.out.println("Original : " + n);
        System.out.println("Rotated : " + rotateNumber(n,k));

        in.close();
    }

    public static int rotateNumber(int n,int k){
        int l = (int)(Math.log10(n)) + 1;
        k = k % l;
        if(k < 0){
            k += l;
        }
        int last = n % (int)Math.pow(10,k);
        int first = n /= (int)Math.pow(10,k);
        return last * (int)Math.pow(10,l-k) + first;
    }
}
