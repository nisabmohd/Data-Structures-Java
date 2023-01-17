package Graphs;

import java.util.*;

public class GraphGeneric<T> {

    // example for generic graph is present in dir -> ./GenericGraphExample.java
    private Map<Object, ArrayList<Object>> map;
    private boolean bidirectional;

    public GraphGeneric() {
        map = new HashMap<>();
        this.bidirectional = true;
    }

    public GraphGeneric(boolean bidirectional) {
        map = new HashMap<>();
        this.bidirectional = bidirectional;
    }

    public void addEdge(T start, T end) {
        if (end == null && start != null) {
            map.put(start, new ArrayList<>());
            return;
        }
        if (start == null && end != null) {
            map.put(end, new ArrayList<>());
            return;
        }
        if (end == null && start == null) {
            return;
        }
        if (!map.containsKey(start)) {
            map.put(start, new ArrayList<>());
        }
        if (!map.containsKey(end)) {
            map.put(end, new ArrayList<>());
        }
        if (bidirectional) {
            map.get(start).add(end);
            map.get(end).add(start);
        } else {
            map.get(start).add(end);
        }
    }

    HashSet<T> vis;

    public ArrayList<ArrayList<T>> bfs() {
        vis = new HashSet<>();
        ArrayList<ArrayList<T>> ans = new ArrayList<>();
        map.forEach((k, v) -> {
            if (!vis.contains(k)) {
                ans.add(bfs((T) k));
            }
        });
        return ans;
    }

    public ArrayList<T> bfs(T source) {
        ArrayList<T> ans = new ArrayList<>();
        Queue<T> queue = new LinkedList<>();
        queue.add(source);
        vis.add(source);
        while (!queue.isEmpty()) {
            T node = queue.poll();
            ans.add(node);
            map.get(node).forEach(item -> {
                if (!vis.contains((T) item)) {
                    vis.add((T) item);
                    queue.add((T) item);
                }

            });
        }
        return ans;
    }

    public ArrayList<ArrayList<T>> dfs() {
        ArrayList<ArrayList<T>> ans = new ArrayList<>();
        HashSet<T> visited = new HashSet<>();
        map.forEach((k, v) -> {
            if (!visited.contains(k)) {
                ans.add(this.dfs((T) k, visited));
            }
        });

        return ans;
    }

    public ArrayList<T> dfs(T source) { // return only a component of graph containing that source in it
        if (!map.containsKey(source)) {
            return new ArrayList<>();
        }
        return this.dfs(source, new HashSet<>());
    }

    private ArrayList<T> dfs(T s, HashSet<T> visited) {
        ArrayList<T> ret = new ArrayList<>();
        ret.add(s);
        visited.add(s);
        map.get(s).forEach((Object item) -> {
            if (!visited.contains(item)) {
                ret.addAll(dfs((T) item, visited));
            }
        });
        return ret;
    }

    @Override // returns adjacency map 
    public String toString() {
        return map.toString();
    }

}
