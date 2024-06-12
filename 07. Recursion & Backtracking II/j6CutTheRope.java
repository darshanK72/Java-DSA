import java.util.Scanner;
public class j6CutTheRope{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        System.out.println(cutRopeInMaxPeices(n,a,b,c));
        in.close();
    }

    public static int cutRopeInMaxPeices(int n,int a,int b,int c){
        if(n == 0) return 0;
        if(n < 0) return -1;
        int result = Math.max(cutRopeInMaxPeices(n-a,a,b,c),
                    Math.max(cutRopeInMaxPeices(n-b,a,b,c),cutRopeInMaxPeices(n-c,a,b,c)));
        
        if(result == -1) return -1;
        return 1 + result;
    }
}