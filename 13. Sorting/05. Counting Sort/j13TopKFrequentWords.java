
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class j13TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        ArrayList<String>[] buckets = new ArrayList[11];
        for (int i = 0; i < 11; i++) {
            buckets[i] = new ArrayList<>();
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (String word : map.keySet()) {
            buckets[map.get(word)].add(word);
        }

        ArrayList<String> out = new ArrayList<>();

        for (int i = 10; i > 0; i--) {
            Collections.sort(buckets[i]);
            for (String word : buckets[i]) {
                out.add(word);
                k--;
                if (k == 0) {
                    break;
                }
            }
            if (k == 0) {
                break;
            }
        }
        return out;
    }
}
