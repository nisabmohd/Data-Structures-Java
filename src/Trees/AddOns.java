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
}
