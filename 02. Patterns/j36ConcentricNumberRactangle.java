import java.util.Scanner;

public class j36ConcentricNumberRactangle {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        printConcentricNumberPattern(n);
        // int or = n;

        // n = n*2;

        // for(int i = 0; i <= n; i++){
        //     for(int j = 0; j <= n; j++){
        //         System.out.print(or - Math.min(Math.min(i,j),Math.min(n-i,n-j)) + " ");
        //     }
        //     System.out.println();
        // }

        in.close();
    }

    public static void printConcentricNumberPattern(int n){
        int n2 = n * 2;
        for(int i = 0; i <= n2; i++){
            for(int j = 0; j <= n2; j++){
                System.out.print(n - Math.min(Math.min(i,j),Math.min(n2-i,n2-j)) + " ");
            }
            System.out.println();
        }
    }
}

// 31.      4 4 4 4 4 4 4  
//          4 3 3 3 3 3 4   
//          4 3 2 2 2 3 4   
//          4 3 2 1 2 3 4   
//          4 3 2 2 2 3 4   
//          4 3 3 3 3 3 4   
//          4 4 4 4 4 4 4 
