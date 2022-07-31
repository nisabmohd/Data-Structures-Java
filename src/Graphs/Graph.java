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
    
    public void bfs(){
        
    }

}
