package Graphs;

import java.util.*;

public class Graph {

    private ArrayList<LinkedList<Integer>> list;

    public Graph(int size) {
        list = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            list.add(new LinkedList<>());
        }
    }

    public Graph(Graph o) {
        this.list = o.list;
    }

    public void addEdge(int source, int dest) {
        list.get(source).add(dest);
        list.get(dest).add(source);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < list.size(); i++) {
            builder.append(i);
            builder.append(" = ");
            int j = 0;
            for (; j < list.get(i).size() - 1; j++) {
                builder.append(list.get(i).get(j));
                builder.append("->");
            }
            builder.append(list.get(i).get(j));
            builder.append("\n");
        }
        return builder.toString();
    }

    public static String toString(Graph o) {
        return o.toString();
    }

    public void bfs(int source) {
        bfs(source, this);
    }

    public static void bfs(int source, Graph o) {
        ArrayList<LinkedList<Integer>> bfslist = o.list;
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < bfslist.size(); i++) {
            visited.add(false);
        }
        queue.add(source);
        visited.set(source, true);
        while (!queue.isEmpty()) {
            int node=queue.peek();
            for (int i = 0; i < bfslist.get(node).size(); i++) {
                if (visited.get(bfslist.get(node).get(i)) == false) {
                    queue.add(bfslist.get(node).get(i));
                    visited.set(bfslist.get(node).get(i), true);
                }
            }
             System.out.print(queue.poll() + " ");
        }

    }

}
