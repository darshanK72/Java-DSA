import java.util.Scanner;

public class j27Power {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int x = in.nextInt();
        int p = in.nextInt();

        System.out.println(powerNaive(x,p));
        System.out.println(powerEfficient(x,p));
        System.out.println(powerBinaryExponentiation(x,p));

        in.close();
    }

    // O(n)
    public static long powerNaive(int x,int p){
        long res = 1; 
        for(int i = 1; i <= p; i++){
            res *= x;
        }
        return res;
    }

    // O(log(n))
    public static long powerEfficient(int x,int p){
        if(p == 0){
            return 1;
        }
        long temp = powerEfficient(x, p/2);
        temp = temp * temp;
        if(p % 2 == 0){
            return temp;
        }
        else{
            return temp * x;
        }
    }

    // O(log(n))
    public static long powerBinaryExponentiation(int x,int p){
        long result = 1;
        while(p > 0){
            if(p % 2 == 1){ // p & 1
                result = result * x;
            }
            x = x * x;
            p /= 2; // p >> 1
        }
        return result;
    }
}
