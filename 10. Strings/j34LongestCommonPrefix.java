public class j34LongestCommonPrefix {
    public static void main(String args[]) {
        String[] strs = new String[] { "flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {

        String out = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(out) != 0) {
                out = out.substring(0, out.length() - 1);
                if (out.isEmpty())
                    return "";
            }
        }
        return out;
    }
}
