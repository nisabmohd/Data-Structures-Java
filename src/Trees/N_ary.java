package Trees;

import java.util.*;

public class N_ary<E> {

    public class Node<T extends E> {

        T val;
        ArrayList<Node> list;

        public Node(T val, ArrayList<Node> list) {
            this.val = val;
            this.list = list;
        }

    }

    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        root.list.forEach(item -> {
            preOrder((Node) item);
        });
    }

    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        root.list.forEach((item) -> {
            postOrder((Node) item);
        });
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        N_ary<Integer> tree = new N_ary<>();
        N_ary.Node node4 = tree.new Node<>(5, new ArrayList<>());
        N_ary.Node node5 = tree.new Node<>(6, new ArrayList<>());
        N_ary.Node node1 = tree.new Node<>(3, new ArrayList<>(Arrays.asList(node4, node5)));
        N_ary.Node node2 = tree.new Node<>(2, new ArrayList<>());
        N_ary.Node node3 = tree.new Node<>(4, new ArrayList<>());
        N_ary.Node root = tree.new Node<>(1, new ArrayList<>(Arrays.asList(node1, node2, node3)));
        tree.preOrder(root);
        System.out.println("");
        System.out.println("");
        tree.postOrder(root);

    }
}
