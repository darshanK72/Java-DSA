import java.util.Scanner;

// Copmplexity : O(Min(a,b))

public class j10GcdLcm {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();

        int hcf = gcd(a,b);
        int lcm = lcm(a,b);

        System.out.printf("HCF of (%d,%d) = %d\n",a,b,hcf);
        System.out.printf("LCM of (%d,%d) = %d",a,b,lcm);
        
        in.close();
    }  

    public static int gcd(int a,int b){
        if(a == 0 || b == 0){
            return a == 0 ? b : a;
        }
        else if(a > b){
            return gcd(a%b,b);
        }
        else{
            return gcd(b%a,a);
        }
    }

    public static int lcm(int a,int b){
        return (a * b) / gcd(a,b);
    }
}
