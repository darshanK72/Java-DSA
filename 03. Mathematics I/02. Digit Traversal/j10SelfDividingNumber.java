import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j10SelfDividingNumber {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int left = in.nextInt();
        int right = in.nextInt();
        System.out.println(selfDividingNumbers(left,right)); 
        in.close();
    }
    
    public static List<Integer> selfDividingNumbers(int left, int right) {
        ArrayList<Integer> output = new ArrayList<Integer>();
        for(int i = left; i <= right; i++){
            int x = i;
            while(x > 0){
                if((x%10) == 0 || i % (x % 10) != 0) break;
                x /= 10;
            }

            if(x == 0) output.add(i);
        }
        return output;
    }
}
