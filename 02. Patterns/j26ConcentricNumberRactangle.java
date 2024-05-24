import java.util.Scanner;

public class j26ConcentricNumberRactangle {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int or = n;

        n = n*2;

        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= n; j++){
                System.out.print(or -Math.min(Math.min(i,j),Math.min(n-i,n-j)) + " ");
            }
            System.out.println();
        }

        in.close();
    }
}

// 31.      4 4 4 4 4 4 4  
//          4 3 3 3 3 3 4   
//          4 3 2 2 2 3 4   
//          4 3 2 1 2 3 4   
//          4 3 2 2 2 3 4   
//          4 3 3 3 3 3 4   
//          4 4 4 4 4 4 4 
