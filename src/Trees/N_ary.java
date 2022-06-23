package Trees;

import java.util.*;

public class N_ary {

    static class Node {

        int val;
        ArrayList<Node> list;

        public Node(int val, ArrayList<Node> list) {
            this.val = val;
            this.list = list;
        }

    }

    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        for (Node item : root.list) {
            preOrder(item);
        }
    }

    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        for (Node item : root.list) {
            postOrder(item);
        }
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        N_ary tree = new N_ary();
        Node node4 = new Node(5, new ArrayList<>());
        Node node5 = new Node(6, new ArrayList<>());
        Node node1 = new Node(3, new ArrayList<>(Arrays.asList(node4, node5)));
        Node node2 = new Node(2, new ArrayList<>());
        Node node3 = new Node(4, new ArrayList<>());
        Node root = new Node(1, new ArrayList<>(Arrays.asList(node1, node2, node3)));
        tree.preOrder(root);
        System.out.println("");
        System.out.println("");
        tree.postOrder(root);

    }
}
