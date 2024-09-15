public class j05CheckPanagram {
    public boolean checkIfPangram(String sentence) {
        int[] hash = new int[26];
        for(int i = 0; i < sentence.length(); i++){
            hash[sentence.charAt(i) - 'a']++;
        }

        for(int i = 0; i < 26; i++){
            if(hash[i] == 0) return false;
        }
        return true;
    }
}
