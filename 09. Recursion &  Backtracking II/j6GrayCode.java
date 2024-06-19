import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class j6GrayCode {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        System.out.println(getGrayCode(1));
        System.out.println(generateGrayCode(1));
        System.out.println(getGrayCode(2));
        System.out.println(generateGrayCode(2));
        System.out.println(getGrayCode(3));
        System.out.println(generateGrayCode(3));
        System.out.println(getGrayCode(4));
        System.out.println(generateGrayCode(4));
        in.close();
    }

     public static List<String> generateGrayCode(int n) {
        List<String> result = new ArrayList<>();
        int numOfGrayCodes = 1 << n; // 2^n

        for (int i = 0; i < numOfGrayCodes; i++) {
            int grayCode = i ^ (i >> 1);
            result.add(toBinaryString(grayCode, n));
        }

        return result;
    }

    private static String toBinaryString(int number, int length) {
        String binaryString = Integer.toBinaryString(number);
        int paddingLength = length - binaryString.length();
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < paddingLength; i++) {
            padding.append("0");
        }
        return padding + binaryString;
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
