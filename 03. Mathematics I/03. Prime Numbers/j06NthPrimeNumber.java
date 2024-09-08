import java.util.Scanner;

public class j06NthPrimeNumber{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(findNthPrime(n));
        in.close();
    }

    public static int findNthPrime(int n) {
		if(n < 1) return -1;
		boolean[] seive = new boolean[1000000];
        for(int i = 2; i*i < 1000000;i++){
            if(!seive[i]){
                for(int j = i*i; j < 1000000; j += i){
                    seive[j] = true;
                }
            }
        }

		int count = 0;
		for(int i = 2; i <= 10000; i++){
			if(!seive[i]) count++;
			if(count == n && !seive[i]) return i;
		}
		return -1;
	}
}