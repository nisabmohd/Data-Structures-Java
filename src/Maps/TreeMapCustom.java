package Maps;

import java.util.*;
import java.util.Comparator;

public class TreeMapCustom<K extends Comparable<K>, V> {

    private Node root;
    private int size = 0;

    private class Node<K, V> {

        K key;
        V val;
        Node left, right;

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
    Comparator<K> comparator = (o1, o2) -> {
        return o1.compareTo(o2);
    };

    public TreeMapCustom(Comparator<? extends K> comparator) {
        this.comparator = (Comparator<K>) comparator;
    }

    public TreeMapCustom() {
    }

    public boolean put(K key, V val) {
        Node node = put(key, val, root);
        root = node;
        return node != null;
    }

    private Node put(K key, V val, Node node) {
        if (node == null) {
            node = new Node(key, val);
            size++;
        } else {
            if (comparator.compare(key, (K) node.key) < 0) {
                node.left = put(key, val, node.left);
            } else if (comparator.compare(key, (K) node.key) > 0) {
                node.right = put(key, val, node.right);
            } else {
                node.val = val;
            }
        }
        return node;
    }

    public boolean containsKey(K key) {
        return get(key, root) != null;
    }

    public V get(K key) {
        return get(key, root);
    }

    public int size() {
        return size;
    }

    private V get(K key, Node node) {
        if (node == null) {
            return null;
        }
        if (comparator.compare(key, (K) node.key) == 0) {
            return (V) node.val;
        } else if (comparator.compare(key, (K) node.key) > 0) {
            return get(key, node.right);
        }
        return get(key, node.left);
    }

    boolean that = false;

    public V remove(K key) {
        V val = get(key);
        if (val == null) {
            return null;
        }
        that = false;
        root = remove(key, root);
        if (that) {
            size++;
        }
        return val;
    }

    private Node remove(K key, Node node) {
        if (node == null) {
            return null;
        }
        if (comparator.compare(key, (K) node.key) > 0) {
            node.right = remove(key, node.right);
        } else if (comparator.compare(key, (K) node.key) < 0) {
            node.left = remove(key, node.left);
        } else {
            if (node.left == null && node.right == null) {
                size--;
                return null;
            } else if (node.left == null && node.right != null) {
                size--;
                return node.right;
            } else if (node.left != null && node.right == null) {
                size--;
                return node.left;
            } else {
                size--;
                that = true;
                Node minKey = getMin(node.right);
                node.right = remove((K) minKey.key, node.right);
                node.key = minKey.key;
                node.val = minKey.val;
                return node;
            }
        }
        return node;
    }

    private Node getMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    public void clear() {
        root = null;
    }

    private void display(Node node, StringBuilder helper) {
        if (node == null) {
            return;
        }
        display(node.left, helper);
        helper.append(node.key + "=" + node.val + ",");
        display(node.right, helper);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        StringBuilder temp = new StringBuilder();
        display(root, temp);
        builder.append(temp);
        if(builder.length()==1) builder.append(" ");
        String t = builder.substring(0, builder.length() - 1);
        return (new StringBuilder(t)).append("}").toString();
    }

    public ArrayList<K> toKeyArray() {
        ArrayList<K> list = new ArrayList<>();
        helperTraverse(list, root);
        return list;
    }

    private void helperTraverse(ArrayList<K> list, Node node) {
        if (node == null) {
            return;
        }
        helperTraverse(list, node.left);
        list.add((K) node.key);
        helperTraverse(list, node.right);
    }

}
