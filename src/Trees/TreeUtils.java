package Trees;

import java.util.*;

public class AddOns {

    protected void levelorder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            if (queue.peek().left != null) {
                queue.add(queue.peek().left);
            }
            if (queue.peek().right != null) {
                queue.add(queue.peek().right);
            }
            System.out.print(queue.remove().val + " ");
        }
    }

    protected void leftView(Node root) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        customleftviewpreorderhelper(root, map, 0);
        map.forEach((K, V) -> {
            System.out.print(V + " ");
        });
    }

    private void customleftviewpreorderhelper(Node node, Map<Integer, Integer> map, int level) {
        if (node == null) {
            return;
        }
        if (!map.containsKey(level)) {
            map.put(level, node.val);
        }
        customleftviewpreorderhelper(node.left, map, level + 1);
        customleftviewpreorderhelper(node.right, map, level + 1);
    }

    protected void rightView(Node root) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        customrighttviewpostorderhelper(root, map, 0);
        map.forEach((K, V) -> {
            System.out.print(V + " ");
        });
    }

    private void customrighttviewpostorderhelper(Node node, Map<Integer, Integer> map, int level) {
        if (node == null) {
            return;
        }
        if (!map.containsKey(level)) {
            map.put(level, node.val);
        }
        customrighttviewpostorderhelper(node.right, map, level + 1);
        customrighttviewpostorderhelper(node.left, map, level + 1);

    }

    static class Pair<K, V> {

        private K key;
        private V val;

        public Pair(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.val;
        }

    }

    protected void topView(Node node) {
        Queue<Pair<Node, Integer>> queue = new LinkedList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        queue.add(new Pair<>(node, 0));
        while (!queue.isEmpty()) {
            Pair<Node, Integer> t = queue.remove();
            if (!map.containsKey(t.getValue())) {
                map.put(t.getValue(), t.getKey().val);
            }
            if (t.getKey().left != null) {
                queue.add(new Pair<>(t.getKey().left, t.getValue() - 1));
            }
            if (t.getKey().right != null) {
                queue.add(new Pair<>(t.getKey().right, t.getValue() + 1));
            }
        }
        map.forEach((key, val) -> System.out.print(val + " "));
    }

    protected void botttomView(Node node) {
        Queue<Pair<Node, Integer>> queue = new LinkedList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        queue.add(new Pair<>(node, 0));
        while (!queue.isEmpty()) {
            Pair<Node, Integer> t = queue.remove();
            map.put(t.getValue(), t.getKey().val);
            if (t.getKey().left != null) {
                queue.add(new Pair<>(t.getKey().left, t.getValue() - 1));
            }
            if (t.getKey().right != null) {
                queue.add(new Pair<>(t.getKey().right, t.getValue() + 1));
            }
        }
        map.forEach((key, val) -> System.out.print(val + " "));
    }

    protected int height(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }
    private int c = 0;

    protected int size(Node root) {
        c = 0;
        count(root);
        return c;
    }

    private void count(Node node) {
        if (node == null) {
            return;
        }
        c++;
        count(node.left);
        count(node.right);
        return;

    }
}
