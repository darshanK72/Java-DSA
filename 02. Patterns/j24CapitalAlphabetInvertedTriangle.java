import java.util.Scanner;

public class j24CapitalAlphabetInvertedTriangle {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        for(int i = 1; i <= n; i++){
            for(int j = (n-i+1); j >= 1; j--){
                System.out.print((char)(64+j) + " ");
            }
            System.out.println();
        }

        in.close();
    }
}


     
// 34.    E D C B A
//        D C B A
//        C B A
//        B A
//        A