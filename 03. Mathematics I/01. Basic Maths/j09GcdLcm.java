import java.util.Scanner;

public class j09GcdLcm {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();

        int hcf = gcd(a,b);

        System.out.printf("HCF of (%d,%d) = %d\n",a,b,hcf);
        
        in.close();
    }  

    // Repeted Subtraction
    public static int gcd(int a,int b){
        while(a != b){
            if(a > b){
                a = a - b;
            }else{
                b = b - a;
            }
        }
        return a;
    }
    
    // Repeated Modulo
    public static int gcdMod(int a,int b){
        while(b != 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Recursive Modulo
    public static int gcdRec(int a,int b){
        if(b == 0)
            return a;
        return gcdRec(b,a % b);
        
    }

    // Recursive LCM
    public static int lcm(int a,int b){
        return (a * b) / gcdRec(a,b);
    }

}
