public class j19KAnagrams{
    boolean areKAnagrams(String s1, String s2, int k) {
        if(s1.length() != s2.length()) return false;
        int hash1[] = new int[26];
        int hash2[] = new int[26];
        
        for(int i = 0; i < s1.length();i++){
            hash1[s1.charAt(i) - 'a']++;
            hash2[s2.charAt(i) - 'a']++;
        }
        
        int changes = 0;
        for(int i = 0; i < 26; i++){
            if(hash1[i] > hash2[i]){
                changes += hash1[i] - hash2[i];   
            }
        }
        return changes <= k;
    }
}