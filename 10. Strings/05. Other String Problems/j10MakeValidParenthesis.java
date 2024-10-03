public class j10MakeValidParenthesis {
    public static void main(String args[]){
        System.out.println(minAddToMakeValid("()))(())(()(()))"));
    }
    public static int minAddToMakeValid(String s) {
        int c = 0;
        int t = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                c++;
            }else{
                c--;
            }
            if(c < 0){
                t++;
                c++;
            }
        }
        return c + Math.abs(t);
    }
}
