import java.util.ArrayList;

public class j01AncestorsInBinaryTree {

    static class Node {
        int data;
        Node left, right;

        Node(int key) {
            data = key;
            left = right = null;
        }
    }

    public static void main(String args[]){
        
    }

    public static ArrayList<Integer> Ancestors(Node root, int target) {
        ArrayList<Integer> out = new ArrayList<>();
        getAncestors(root, target, out);
        return out;
    }

    public static boolean getAncestors(Node root, int target, ArrayList<Integer> out) {
        if (root == null)
            return false;
        if (root.data == target)
            return true;
        boolean left = getAncestors(root.left, target, out);
        boolean right = getAncestors(root.right, target, out);
        if (left || right) {
            out.add(root.data);
            return true;
        }
        return false;

    }
}
