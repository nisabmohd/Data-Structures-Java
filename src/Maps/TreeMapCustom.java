package Maps;

import java.util.ArrayList;

public class TreeMapCustom<K extends Comparable<K>, V> {

    Node root;
    int size = 0;

    private class Node<K extends Comparable<K>, V> implements Comparable<K> {

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

        @Override
        public int compareTo(K o) {
            return this.key.compareTo(o);
        }
    }

    public boolean put(K key, V val) {
        Node node = put(key, val, root);
        root = node;
        return node != null;
    }

    private Node put(K key, V val, Node node) {
        if (node == null) {
            node = new Node<>(key, val);
            size++;
        } else {
            if (node.compareTo(key) > 0) {
                node.left = put(key, val, node.left);
            } else if (node.compareTo(key) < 0) {
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
        if (node.compareTo(key) == 0) {
            return (V) node.val;
        } else if (node.compareTo(key) < 0) {
            return get(key, node.right);
        }
        return get(key, node.left);
    }

    public V remove(K key) {
        return (V) remove(key, root).val;
    }

    private Node remove(K key, Node node) {
        if (node == null) {
            return null;
        }
        if (node.compareTo(key) < 0) {
            node.right = remove(key, node.right);
        } else if (node.compareTo(key) > 0) {
            node.left = remove(key, node.left);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null && node.right != null) {
                return node.right;
            } else if (node.left != null && node.right == null) {
                return node.left;
            } else {
                K minKey = getMin(root.right);
                node.right = remove(minKey, root);
                node.val = minKey;
            }
        }
        return node;
    }

    private K getMin(Node node) {
        if (node.left == null) {
            return (K) node.key;
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
        helper.append(node.key + "->" + node.val + " ");
        display(node.right, helper);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        StringBuilder temp = new StringBuilder();
        display(root, temp);
        builder.append(temp);
        builder.append("]");
        return builder.toString();
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
