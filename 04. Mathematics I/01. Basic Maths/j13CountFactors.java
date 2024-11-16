import java.util.Scanner;

public class j13CountFactors {
    
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(countFactors(n));
        in.close();
    }

    public static int countFactors(int N) {
        int c = 0;
        for(int i = 1; i * i <= N; i++){
            if(N % i == 0){
                if(N/i != i) c+=2;
                else c++;
            }
        }
        return c;
    }
}
