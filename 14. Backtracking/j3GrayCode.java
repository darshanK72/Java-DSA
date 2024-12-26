import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class j3GrayCode {
    public static void main(String args[]){
        System.out.println(generateGrayCodeIterative(4));
        System.out.println(generateGrayCodeBitwise(4));
        System.out.println(getGrayCodeRecursive(4));
    }

    // O(n x 2^n)
    public static List<String> generateGrayCodeIterative(int n){
        List<String> result = new ArrayList<String>();
        result.add("");
        for(int i = 0; i < n; i++){
            List<String> tempList = new ArrayList<String>();
            for(int j = result.size()-1;j >= 0; j--){
                tempList.add(result.get(j));
            }

            for(int j = 0; j < result.size(); j++){
                result.set(j,"0" + result.get(j));
            }

            for(int j = 0;j < result.size(); j++){
                tempList.set(j,"1" + tempList.get(j));
            }
            result.addAll(tempList);
        }
        return result;
    }

    // O(2^n x n)
     public static List<String> generateGrayCodeBitwise(int n) {
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

    // O(2^n)
    public static List<Integer> getGrayCodeRecursive(int n){
        if(n == 1){
            return new ArrayList<>(Arrays.asList(0,1));
        }
        else{

            List<Integer> out = getGrayCodeRecursive(n-1);
            for(int i = out.size()-1; i >= 0; i-- ){
                out.add(out.get(i) | (1 << (n-1)));
            }
            return out;

        }
    }

    public static List<String> generateGrayCodeBacktrack(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        if (n == 1) {
            result.add("0");
            result.add("1");
        } else {
            List<String> prevGrayCodes = generateGrayCodeBacktrack(n - 1);
            for (int i = 0; i < prevGrayCodes.size(); i++) {
                result.add("0" + prevGrayCodes.get(i));
            }
            for (int i = prevGrayCodes.size() - 1; i >= 0; i--) {
                result.add("1" + prevGrayCodes.get(i));
            }
        }
        return result;
    }
}
