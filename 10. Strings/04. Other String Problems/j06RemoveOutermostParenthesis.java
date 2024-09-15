public class j06RemoveOutermostParenthesis{
    public static void main(String args[]){
        System.out.println(removeOuterParentheses("(()())(())(()(()))"));
    }
    public static String removeOuterParentheses(String s) {
        if(s.length() == 0) return "";

        StringBuilder out = new StringBuilder("");

        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                if(count > 0){
                    out.append("(");
                }
                count++;
            }else if(s.charAt(i) == ')'){
                count--;
                if(count > 0){
                    out.append(")");
                }
            }
        }
        return out.toString();
    }
}