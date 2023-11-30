import java.util.Scanner;

public class j32Pattern {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();


        int a = 0;
        int b = 1; 
        int c = a + b;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                System.out.print(a + " ");
                a = b;
                b = c;
                c = a + b;
            }
            System.out.println();
        }
        in.close();
    }
}

// 0 
// 1 1 
// 2 3 5 
// 8 13 21 34 
// 55 89 144 233 377