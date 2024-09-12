import java.util.ArrayList;
import java.util.Scanner;

public class j39PrettyJSON {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.print(prettyJSON(s));
        in.close();
    }

    public static ArrayList<String> prettyJSON(String s) {
        int tabs = 0;
        StringBuilder out = new StringBuilder();
        ArrayList<String> output = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '{' || ch == '[') {
                if (out.length() > 0) {
                    output.add(out.toString());
                    out = new StringBuilder();
                }
                addIndents(out, tabs);
                out.append(ch);
                output.add(out.toString());
                out = new StringBuilder();
                tabs++;
            } else if (ch == '}' || ch == ']') {
                if (out.length() > 0) {
                    output.add(out.toString());
                    out = new StringBuilder();
                }
                tabs--;
                addIndents(out, tabs);
                out.append(ch);
            } else if (ch == ',') {
                out.append(ch);
                output.add(out.toString());
                out = new StringBuilder();
            } else {
                if (out.length() == 0) {
                    addIndents(out, tabs);
                }
                out.append(ch);
            }
        }

        if (out.length() > 0) {
            output.add(out.toString());
        }

        return output;
    }

    private static void addIndents(StringBuilder sb, int count) {
        for (int i = 0; i < count; i++) {
            sb.append('\t');
        }
    }
}
