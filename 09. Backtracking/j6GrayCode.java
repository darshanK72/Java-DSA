import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class j6GrayCode {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        System.out.println(getGrayCode(1));
        System.out.println(getGrayCode(2));
        System.out.println(getGrayCode(3));
        System.out.println(getGrayCode(4));
        in.close();
    }

    public static List<Integer> getGrayCode(int n){
        if(n == 1){
            return new ArrayList<>(Arrays.asList(0,1));
        }
        else{

            List<Integer> out = getGrayCode(n-1);
            for(int i = out.size()-1; i >= 0; i-- ){
                out.add(out.get(i) | (1 << (n-1)));
            }
            return out;

        }
    }
}
