import java.util.Scanner;

public class j25Pattern {
    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        int ch = 0;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                if(ch%2 == 0){
                    System.out.print((char)(97+ch) + " ");
                }
                else{
                    System.out.print((char)(65+ch) + " ");
                }
                ch++;
            }
            System.out.println();
        }

        in.close();
    }
}

// 33.    a
//        B c
//        D e F
//        g H i J
//        k L m N o