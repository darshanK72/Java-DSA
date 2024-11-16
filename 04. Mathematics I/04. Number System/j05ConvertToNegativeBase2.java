import java.util.Scanner;

public class j05ConvertToNegativeBase2 {
      public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(baseNeg2(n));
        in.close();
    }
    public static String baseNeg2(int n) {
        if(n == 0) return "0";
        StringBuilder output = new StringBuilder();
        while(n != 0){
            int d = n % (-2);
            n /= (-2);
            if(d < 0){
                d += 2;
                n++;
            }
            output.insert(0,d);
        }
       return output.toString();
    }
}
