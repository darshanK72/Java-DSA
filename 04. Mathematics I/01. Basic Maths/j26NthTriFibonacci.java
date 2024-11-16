public class j26NthTriFibonacci {
    public int tribonacci(int n) {
        if(n == 0) return 0;
        if(n == 1 || n == 2) return 1;
        int a = 0;
        int b = 1;
        int c = 1;
        int d = a + b + c;
        for(int i = 3; i < n; i++){
            a = b;
            b = c;
            c = d;
            d = a + b + c;
        }
        return d;
    }
}
