package Trees;

import java.util.List;

public interface BTree {

    public static class TreeNode<T extends Comparable<T>> implements Comparable<T> {

        T val;
        TreeNode left, right;

        public TreeNode() {
        }

        public TreeNode(T val) {
            this.val = val;
        }

        public TreeNode(T val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(T o) {
            return this.val.compareTo(o);
        }

    }

    public List<Object> inOrder(TreeNode node);

    public List<Object> preOrder(TreeNode node);

    public List<Object> postOrder(TreeNode node);

    public List<List<Object>> levelOrder(TreeNode node);

    public List<Object> leftView(TreeNode node);

    public List<Object> rightView(TreeNode node);

    public List<Object> topView(TreeNode node);

    public List<Object> botttomView(TreeNode node);

    public int height(TreeNode node);

    public int size(TreeNode node);
}
