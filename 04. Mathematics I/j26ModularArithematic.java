public class j26ModularArithematic {

    public static long modAdd(long a, long b, long m) {
        return (a % m + b % m) % m;
    }

    public static long modSub(long a,long b, long m){
        return (a % m - b % m) % m;
    }

    public static long modMul(long a,long b, long m){
        return (a % m * b % m) % m;
    }

    public static long fastExponention(long a,long b,long m){
        long result = 1;
        while(b > 0){
            if(b % 2 == 1){
                result = modMul(result, a, m);
            }
            a = modMul(a, a, m);
            b /= 2;
        }
        return result;
    }

    public static void main(String args[]){
        System.out.println(fastExponention(5, 2, 7));
    }
}
