import java.util.Scanner;

public class j10ZigZagConversion {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int numRows = in.nextInt();
        System.out.println(convert(s,numRows));
        in.close();
    }
    public static String convert(String s, int numRows) {
        if(numRows == 0 || s.length() < numRows) return s;
       StringBuilder[] zag = new StringBuilder[numRows];
       for(int i = 0; i < numRows; i++){
        zag[i] = new StringBuilder();
       }

       int i = 0;
       while(i < s.length()){
            for(int row = 0; row < numRows && i < s.length(); row++){
                zag[row].append(s.charAt(i++));
            }
            for(int row = numRows - 2; row > 0 && i < s.length(); row--){
                zag[row].append(s.charAt(i++));
            }
       }

       StringBuilder out = new StringBuilder();
       for(int j = 0; j < numRows; j++){
        out.append(zag[j]);
       }
       return out.toString();
    }
}

// The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

// P   A   H   N
// A P L S I I G
// Y   I   R
// And then read line by line: "PAHNAPLSIIGYIR"

// Write the code that will take a string and make this conversion given a number of rows:

// string convert(string s, int numRows);
