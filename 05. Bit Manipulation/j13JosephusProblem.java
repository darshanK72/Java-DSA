import java.util.Scanner;
public class j13JosephusProblem {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(josephusProb(n));
        in.close();
    }

    public static int josephusProb(int n){
        int p = 1;
        while(p* 2 < n){
            p *= 2;
        }
        System.out.println(p);
        int l = n - p;
        return 2*l+1;
    }
}
