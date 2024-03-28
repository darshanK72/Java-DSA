import java.util.Scanner;
import java.util.Arrays;

public class j29SmallestPrimeFactor{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int q = in.nextInt();
        int[] quries = new int[q];
        for(int i = 0; i < q; i++){
            quries[i] = in.nextInt();
        }

        int[] seive = getSeive(1000);
        // System.out.println(Arrays.toString(seive));
        for(int i = 0; i < q; i++){
            System.out.println(seive[quries[i]]);
        }

        in.close();
    }

    public static int[] getSeive(int n){
        int[] seive = new int[n + 1];
        for(int i = 2; i <= n; i++){
            seive[i] = i;
        }
        for(int i = 2; i*i <= n; i++){
            if(seive[i] == i){
                for(int j = i*i; j <= n; j+=i){
                    if(seive[j] == j){
                        seive[j] = i;
                    }
                }
            }
        }
        return seive;
    } 
}

