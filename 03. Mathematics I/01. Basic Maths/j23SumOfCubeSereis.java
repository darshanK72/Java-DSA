import java.util.Scanner;

public class j23SumOfCubeSereis {
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(sumOfCubeSeries(n));
        in.close();
    }
    public static long sumOfCubeSeries(long n) {
        long sum = 0;
        for(long i = 1; i <= n; i++){
            sum += (i * i * i);
        }
        
        return sum;
    }
}
