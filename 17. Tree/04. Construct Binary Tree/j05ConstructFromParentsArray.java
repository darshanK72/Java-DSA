public class j05ConstructFromParentsArray {

    static class Node {
        int val;
        Node left;
        Node right;

        Node(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

     public static Node createTree(int parent[]) {
        Node[] nodes = new Node[parent.length];
        for(int i = 0; i < parent.length; i++){
            nodes[i] = new Node(i);
        }
        Node root = null;
        for(int i = 0; i < parent.length; i++){
            if(parent[i] == -1){
                root = nodes[i]; 
            }else{
                Node parentNode = nodes[parent[i]];
                if(parentNode.left == null){
                    parentNode.left = nodes[i];
                }else{
                    parentNode.right = nodes[i];
                }
            }
        }
        return root;
    }
}
