import java.util.HashMap;
import java.util.Scanner;

public class j08FirstNonRepeated {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(firstNonRepeating(arr));
        in.close();
    }

    public static int firstNonRepeating(int[] arr) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i < arr.length; i++){
            map.put(arr[i],map.getOrDefault(arr[i],0) + 1);
        }
        
        for(int i = 0; i < arr.length; i++){
            if(map.get(arr[i]) == 1) return arr[i]; 
        }
        return 0;
    }
}
