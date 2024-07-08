import java.util.Scanner;

// Copmplexity : O(Min(a,b))

public class j10GcdLcm {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();

        // int hcf = gcdRec(a,b);
        int hcf = gcdNive(a,b);
        int lcm = lcm(a,b);

        System.out.printf("HCF of (%d,%d) = %d\n",a,b,hcf);
        System.out.printf("LCM of (%d,%d) = %d",a,b,lcm);
        
        in.close();
    }  

    public static int gcdNive(int a,int b){
        while(a != b){
            if(a > b){
                a = a - b;
            }else{
                b = b - a;
            }
        }
        return a;
    }

    public static int gcdSimple(int a,int b){
        while(b != 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int gcdRec(int a,int b){
        if(b == 0)
            return a;
        return gcdRec(b,a % b);
        
    }

    public static int lcm(int a,int b){
        return (a * b) / gcdRec(a,b);
    }
}
