package Graphs;

import java.util.*;

public class Graph {

    // Adjacency List
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

    public void addEdge(Integer source, Integer dest) {
        if (source == null || dest == null) {
            return;
        }
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

    public List<Integer> bfs(int source) {
        return Graph.bfs(source, this);
    }

    public static List<Integer> bfs(int source, Graph o) {
        ArrayList<LinkedList<Integer>> bfslist = o.list;
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < bfslist.size(); i++) {
            visited.add(false);
        }
        queue.add(source);
        visited.set(source, true);
        while (!queue.isEmpty()) {
            int node = queue.remove();
            for (int i = 0; i < bfslist.get(node).size(); i++) {
                if (visited.get(bfslist.get(node).get(i)) == false) {
                    queue.add(bfslist.get(node).get(i));
                    visited.set(bfslist.get(node).get(i), true);
                }
            }
            result.add(node);
        }
        return result;
    }

    public ArrayList<Integer> dfs(int source) {
        return Graph.dfs(source, this);
    }

    public static ArrayList<Integer> dfs(int source, Graph o) {
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < o.list.size(); i++) {
            visited.add(false);
        }
        return helperdfs(o.list, source, visited);
    }

    private static ArrayList<Integer> helperdfs(ArrayList<LinkedList<Integer>> adjList, int vertex, ArrayList<Boolean> visited) {
        visited.set(vertex, true);
        ArrayList<Integer> output = new ArrayList<>();
        output.add(vertex);
        for (int i = 0; i < adjList.get(vertex).size(); i++) {
            if (visited.get(adjList.get(vertex).get(i)) == false) {
                output.addAll(helperdfs(adjList, adjList.get(vertex).get(i), visited));
            }
        }
        return output;
    }

}
