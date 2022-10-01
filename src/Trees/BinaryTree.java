package Trees;

import java.util.*;

public class BinaryTree implements BTree {

    @Override
    public List<Object> inOrder(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<Object> list = new ArrayList<>();
        list.addAll(inOrder(node.left));
        list.add(node.val);
        list.addAll(inOrder(node.right));
        return list;
    }

    @Override
    public List<Object> preOrder(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<Object> list = new ArrayList<>();
        list.add(node.val);
        list.addAll(preOrder(node.left));
        list.addAll(preOrder(node.right));
        return list;
    }

    @Override
    public List<Object> postOrder(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<Object> list = new ArrayList<>();
        list.addAll(postOrder(node.left));
        list.addAll(postOrder(node.right));
        list.add(node.val);
        return list;
    }
}
