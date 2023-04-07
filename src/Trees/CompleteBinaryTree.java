package Trees;

import java.util.*;
import java.util.function.Consumer;

public class CompleteBinaryTree<T extends Comparable<T>> extends BinaryTree implements Iterable<T> {
    private LinkedList<TreeNode<T>> list;
    private int size;

    public CompleteBinaryTree() {
        list = new LinkedList<>();
        list.add(null);
        size = 1;
    }

    public void add(T val) {
        TreeNode<T> node = new TreeNode<>(val);
        if (size == 1) {
            list.add(node);
            size++;
            return;
        }
        TreeNode<T> parent = list.get(size / 2);
        if (parent.left == null) parent.left = node;
        else parent.right = node;
        list.add(size++, node);
    }

    public T removeLast() {
        if(size==1) return null;
        if (size > 2) {
            TreeNode<T> node = list.get(size - 1);
            TreeNode<T> parent = list.get((size - 1) / 2);
            if (parent.left == node) parent.left = null;
            else parent.right = null;
            T val = list.removeLast().val;
            size--;
            return val;
        }
        size--;
        return list.removeLast().val;
    }

    public List<Object> inOrder() {
        return super.inOrder(list.get(1));
    }

    public List<Object> preOrder() {
        return super.preOrder(list.get(1));
    }

    public List<Object> postOrder() {
        return super.postOrder(list.get(1));
    }

    public List<List<Object>> levelOrder() {
        return super.levelOrder(list.get(1));
    }

    public List<Object> leftView() {
        return super.leftView(list.get(1));
    }

    public List<Object> rightView() {

        return super.rightView(list.get(1));
    }

    public int size() {
        return size - 1;
    }

    public List<Object> topView() {
        return super.topView(list.get(1));
    }

    public List<Object> bottomView() {
        return super.bottomView(list.get(1));
    }

    public int height() {
        return super.height(list.get(1));
    }

    public void clear() {
        list.set(1, null);
        size = 1;
    }

    private class TreeItr implements Iterator<T> {
        int cursor = 1;

        @Override
        public boolean hasNext() {
            return this.cursor != CompleteBinaryTree.this.list.size();
        }

        @Override
        public T next() {
            return CompleteBinaryTree.this.list.get(cursor++).val;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeItr();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        TreeItr it = new TreeItr();
        while (it.hasNext()) action.accept(it.next());
    }

    @Override
    public String toString() {
        return list.stream().skip(1).toList().toString();
    }
}
