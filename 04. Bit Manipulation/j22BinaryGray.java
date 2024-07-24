import java.util.Scanner;

public class j22BinaryGray {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String binary = in.next();
        String gray = in.next();
        System.out.println(binaryToGray(binary));
        System.out.println(grayToBinary(gray));
        in.close();
    }

    public static int binaryToGray(int n) {
        return n ^ (n >> 1);
    }

    public static String binaryToGray(String binary){
        StringBuilder gray = new StringBuilder();
        
        // First bit is same
        gray.append(binary.charAt(0));
        
        // Compute the remaining bits
        for (int i = 1; i < binary.length(); i++) {
            char prev = binary.charAt(i - 1);
            char curr = binary.charAt(i);
            gray.append(prev == curr ? '0' : '1');
        }
        
        return gray.toString();
    } 

    public static int grayToBinary(int gray) {
        int binary = gray;
        while ((gray >>= 1) != 0) {
            binary ^= gray;
        }
        return binary;
    }
    
    public static String grayToBinary(String gray){
        StringBuilder binary = new StringBuilder();
        
        // First bit is same
        binary.append(gray.charAt(0));
        
        // Compute the remaining bits
        for (int i = 1; i < gray.length(); i++) {
            char prev = binary.charAt(i - 1);
            char curr = gray.charAt(i);
            binary.append(prev == curr ? '0' : '1');
        }
        
        return binary.toString();
    }
}
