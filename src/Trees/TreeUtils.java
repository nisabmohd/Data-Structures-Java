package Trees;

import java.util.*;

public class TreeUtils {

    public static void levelorder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
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

    public static void leftView(TreeNode root) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        customleftviewpreorderhelper(root, map, 0);
        map.forEach((K, V) -> {
            System.out.print(V + " ");
        });
    }

    private static void customleftviewpreorderhelper(TreeNode node, Map<Integer, Integer> map, int level) {
        if (node == null) {
            return;
        }
        if (!map.containsKey(level)) {
            map.put(level, node.val);
        }
        customleftviewpreorderhelper(node.left, map, level + 1);
        customleftviewpreorderhelper(node.right, map, level + 1);
    }

    public static void rightView(TreeNode root) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        customrighttviewpostorderhelper(root, map, 0);
        map.forEach((K, V) -> {
            System.out.print(V + " ");
        });
    }

    private static void customrighttviewpostorderhelper(TreeNode node, Map<Integer, Integer> map, int level) {
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

    public static void topView(TreeNode node) {
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        queue.add(new Pair<>(node, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> t = queue.remove();
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

    public static void botttomView(TreeNode node) {
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        queue.add(new Pair<>(node, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> t = queue.remove();
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

    public static int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }
    private static int c = 0;

    public static int size(TreeNode root) {
        c = 0;
        count(root);
        return c;
    }

    private static void count(TreeNode node) {
        if (node == null) {
            return;
        }
        c++;
        count(node.left);
        count(node.right);
        return;

    }
}
