import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class j20TwoOutOfThree {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();
        ArrayList<Integer> C = new ArrayList<>();
        
        int n1 = in.nextInt();
        for(int i = 0; i < n1; i++){
            A.add(in.nextInt());
        }
        int n2 = in.nextInt();
        for(int i = 0; i < n2; i++){
            B.add(in.nextInt());
        }
        int n3 = in.nextInt();
        for(int i = 0; i < n3; i++){
            C.add(in.nextInt());
        }
        System.out.println(commonInTwoOutOfThree(A,B,C));
        in.close();
    }

    
    public static ArrayList<Integer> commonInTwoOutOfThree(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        HashMap<Integer,Integer> map = new HashMap<>();
        
        addToMap(map,A);
        addToMap(map,B);
        addToMap(map,C);
       
        ArrayList<Integer> out = new ArrayList<Integer>();
        for(int key : map.keySet()){
            if(map.get(key) >= 2)
                out.add(key);
        }
        
        Collections.sort(out);
        return out;
    }

    private static void addToMap(HashMap<Integer, Integer> map, ArrayList<Integer> list) {
        Set<Integer> set = new HashSet<>();
        for (int i : list) {
            if (!set.contains(i)) {
                map.put(i, map.getOrDefault(i, 0) + 1);
                set.add(i);
            }
        }
    }
}
