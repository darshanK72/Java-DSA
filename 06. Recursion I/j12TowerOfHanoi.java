import java.util.Scanner;

public class j12TowerOfHanoi{

    public static void main(String args[]){
       Scanner in = new Scanner(System.in);
       int n = in.nextInt();
       System.out.println(toh(n,1,2,3));
       in.close(); 
    }

    public static long toh(int N, int from, int to, int aux) {
       return tohCount(N,from,to,aux,0);
    }
    
    public static long tohCount(int N,int from,int to,int aux,long count){
       if(N == 0) return count;
       count += toh(N - 1,from,aux,to);
       System.out.println("move disk "+ N +" from rod "+ from +" to rod " + to);
       count++;
       count += toh(N - 1,aux,to,from);
       return count;
    }
}