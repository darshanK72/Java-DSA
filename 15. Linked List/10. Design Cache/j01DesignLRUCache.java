import java.util.HashMap;

public class j01DesignLRUCache {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1); // cache is {1=1}
        cache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(cache.get(1)); // return 1
        cache.put(3, 3); // evicts key 2, cache is {1=1, 3=3}
        System.out.println(cache.get(2)); // return -1 (not found)
        cache.put(4, 4); // evicts key 1, cache is {4=4, 3=3}
        System.out.println(cache.get(1)); // return -1 (not found)
        System.out.println(cache.get(3)); // return 3
        System.out.println(cache.get(4)); // return 4
    }

    static class LRUCache {

        static class Node {
            public int key;
            public int value;
            public Node next;
            public Node prev;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.next = null;
                this.prev = null;
            }

        }

        public int capacity;
        public int size;
        public Node head;
        public Node tail;
        public HashMap<Integer, Node> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            this.head.next = this.tail;
            this.tail.prev = this.head;
            this.map = new HashMap<>();
        }

        public int get(int key) {
            if (!map.containsKey(key))
                return -1;
            Node node = map.get(key);
            delete(node);
            insert(node);
            print();
            return node.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                delete(node);
                node.value = value;
                insert(node);
            } else {
                Node node = new Node(key, value);
                if (this.size == this.capacity) {
                    Node evitNode = tail.prev;
                    delete(evitNode);
                    map.remove(evitNode.key);
                    this.size--;
                }
                map.put(key, node);
                insert(node);
                this.size++;
            }
            print();
        }

        public void insert(Node node) {
            node.next = this.head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        public void delete(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public void print() {
            Node temp = this.head;
            while (temp != null) {
                System.out.print("( " + temp.key + " <--> " + temp.value + " ),");
                temp = temp.next;
            }
            System.out.println();
        }
    }

}