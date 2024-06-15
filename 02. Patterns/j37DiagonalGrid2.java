import java.util.Scanner;

public class j37DiagonalGrid2{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print((i-j) + " ");
            }
            System.out.println();
        }
        in.close();
    }
}

// 5
// 0 1 2 3 4 
// 1 2 3 4 5
// 2 3 4 5 6
// 3 4 5 6 7
// 4 5 6 7 8