package Maps;
// <------Incomplete yet ---->
public class TreeMapCustom<K extends Comparable<K>, V> {

    Node root;

    private class Node<K, V> {

        K key;
        V val;
        Node<K, V> left, right;

        public Node(K key, V val, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private int compare(K a, K b) {
        return a.compareTo(b);
    }

    public void put(K key, V val) {
        Node node = put(key, val, root);
        root = node;
    }

    private Node put(K key, V val, Node node) {
        if (node == null) {
            return new Node(key, val);
        } else if (compare((K) node.key, key) > 1) {
            node.left = put(key, val, node.left);
        } else if (compare((K) node.key, key) < 1) {
            node.right = put(key, val, node.right);
        }
        return node;
    }

    public void  display() {
        System.out.print("[ ");
        display(root);
        System.out.println("]");
    }

    private void display(Node node) {
        if (node == null) {
            return;
        }
        display(node.left);
        System.out.print(node.key + "->" + node.val + " ");
        display(node.right);
    }

}
