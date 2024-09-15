import java.util.LinkedList;
import java.util.Scanner;

public class j01HashMapDesign {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        HashMap<Integer, Integer> myMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            myMap.put(arr[i], myMap.getOrDefault(arr[i], 0) + 1);
        }
        System.err.println(myMap.toString());
        in.close();
    }

    public static class HashMap<K, V> {

        private LinkedList<Node>[] buckets;
        private int size;
        private int capacity;

        private class Node {
            K key;
            V value;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        @SuppressWarnings("unchecked")
        public HashMap() {
            this.capacity = 4;
            this.buckets = new LinkedList[4];
            for (int i = 0; i < this.capacity; i++) {
                buckets[i] = new LinkedList<>();
            }
        }

        public int size() {
            return this.size;
        }

        public int hashCode(K key) {
            return Math.abs(key.hashCode()) % capacity;
        }

        @SuppressWarnings("unchecked")
        public void resize() {
            LinkedList<Node>[] oldBuckets = buckets;
            this.capacity = this.capacity * 2;
            this.buckets = new LinkedList[capacity];
            for (int i = 0; i < this.capacity; i++) {
                buckets[i] = new LinkedList<>();
            }
            this.size = 0;
            for (int i = 0; i < oldBuckets.length; i++) {
                for (Node node : oldBuckets[i]) {
                    this.put(node.key, node.value);
                }
            }
        }

        public String toString() {
            StringBuilder out = new StringBuilder();
            out.append("{");
            int count = 0;
            for (int i = 0; i < capacity; i++) {
                for (Node node : buckets[i]) {
                    if (count != 0)
                        out.append(",");
                    out.append(node.key + " = " + node.value);
                    count++;
                }
            }
            out.append("}");
            return out.toString();
        }

        public boolean containsKey(K key) {
            int bucketIndex = hashCode(key);
            for (Node node : buckets[bucketIndex]) {
                if (node.key.equals(key)) {
                    return true;
                }
            }
            return false;
        }

        public void put(K key, V value) {
            if ((1.0 * size) / capacity > 0.75) {
                resize();
            }
            Node n = new Node(key, value);
            boolean found = false;
            for (Node node : buckets[hashCode(key)]) {
                if (node.key.equals(key)) {
                    node.value = value;
                    found = true;
                    break;
                }
            }
            if (!found) {
                buckets[hashCode(key)].addLast(n);
                this.size++;
            }
        }

        public V get(K key) throws Exception {
            for (Node node : buckets[hashCode(key)]) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
            throw new Exception("No element foud with key");
        }

        public V getOrDefault(K key, V value) {
            for (Node node : buckets[hashCode(key)]) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
            return value;
        }

        public V remove(K key) {
            int bucketIndex = hashCode(key);
            Node toRemove = null;
            for (Node node : buckets[bucketIndex]) {
                if (node.key.equals(key)) {
                    toRemove = node;
                    break;
                }
            }

            if (toRemove != null) {
                buckets[bucketIndex].remove(toRemove);
                this.size--;
                return toRemove.value;
            } else {
                return null; // Key not found
            }
        }

    }

}