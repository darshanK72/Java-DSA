import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class j63CountPairsWithGivenXor {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        
        in.close();
    }
    public int countPairsWithXorB(ArrayList<Integer> A, int B) {
        HashSet<Integer> set = new HashSet<Integer>();
        int count = 0;
        for (int a : A) {
            if (set.contains(a ^ B))
                count++;
            set.add(a);
        }
        return count;
    }
}
