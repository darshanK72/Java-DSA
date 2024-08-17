import java.util.Scanner;

public class j33CompareVersions {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(compareVersion(s1,s2));
        in.close();
    }
    public static int compareVersion(String version1, String version2) {
        int i = 0;
        int j = 0;
        while(i < version1.length() || j < version2.length()){
            
            int v1 = 0;
            while(i < version1.length() && version1.charAt(i) != '.'){
                v1 = v1 * 10 + version1.charAt(i) - '0';
                i++;
            }
            i++;

            int v2 = 0;
            while(j < version2.length() && version2.charAt(j) != '.'){
                v2 = v2 * 10 + version2.charAt(j) - '0';
                j++;
            }
            j++;

            if(v1 > v2) return 1;
            if(v2 > v1) return -1;
        }

        return 0;
    }
}

// Example 1:
// Input: version1 = "1.2", version2 = "1.10"
// Output: -1
// Explanation:
// version1's second revision is "2" and version2's second revision is "10": 2 < 10, so version1 < version2.

// Example 2:
// Input: version1 = "1.01", version2 = "1.001"
// Output: 0
// Explanation:
// Ignoring leading zeroes, both "01" and "001" represent the same integer "1".

// Example 3:
// Input: version1 = "1.0", version2 = "1.0.0.0"
// Output: 0
// Explanation:
// version1 has less revisions, which means every missing revision are treated as "0".
