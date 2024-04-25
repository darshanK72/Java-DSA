import java.util.Scanner;
public class j13JohesephProblem{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        System.out.println(joesephKill(n,k));
        in.close();
    }

    public static int joesephKill(int n,int k){
        if(n == 1) return 0;
        return (joesephKill(n-1,k) + k)%n;
    }
}