import java.util.LinkedList;

public class j02HashSetDesign {
    public static void main(String args[]) {
        HashSet<Integer> mySet = new HashSet<>();
        mySet.add(5);
        mySet.add(10);
        mySet.add(15);
        mySet.add(10);

        System.out.println("Set contains 10: " + mySet.contains(10));
        System.out.println("Set contains 20: " + mySet.contains(20));

        System.out.println(mySet);

        mySet.remove(10);
        System.out.println("After removing 10: " + mySet.contains(10));
    }

    public static class HashSet<K> {

        private LinkedList<Node>[] buckets;
        private int size;
        private int capacity;

        private class Node {
            K key;

            Node(K key) {
                this.key = key;
            }
        }

        @SuppressWarnings("unchecked")
        public HashSet() {
            this.capacity = 4;
            this.buckets = new LinkedList[capacity];
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
                    this.add(node.key);
                }
            }
        }

        public void add(K key) {
            if ((1.0 * size) / capacity > 0.75) {
                resize();
            }
            int bucketIndex = hashCode(key);
            boolean found = false;
            for (Node node : buckets[bucketIndex]) {
                if (node.key.equals(key)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                buckets[bucketIndex].addLast(new Node(key));
                this.size++;
            }
        }

        public boolean contains(K key) {
            int bucketIndex = hashCode(key);
            for (Node node : buckets[bucketIndex]) {
                if (node.key.equals(key)) {
                    return true;
                }
            }
            return false;
        }

        public void remove(K key) {
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
            }
        }

        public String toString() {
            StringBuilder out = new StringBuilder();
            out.append("[");
            int count = 0;
            for (int i = 0; i < capacity; i++) {
                for (Node node : buckets[i]) {
                    if (count != 0) {
                        out.append(", ");
                    }
                    out.append(node.key);
                    count++;
                }
            }
            out.append("]");
            return out.toString();
        }
    }
}
