package Graphs;

import java.util.*;

public class GraphGeneric<T> {

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


    public List<List<T>> bfs() {
        HashSet<T> vis;
        vis = new HashSet<>();
        List<List<T>> ans = new ArrayList<>();
        map.forEach((k, v) -> {
            if (!vis.contains(k)) {
                ans.add(bfs((T) k, vis));
            }
        });
        return ans;
    }

    public List<T> bfs(T source) {
        if (!map.containsKey(source)) {
            return new ArrayList<>();
        }
        HashSet<T> vis = new HashSet<>();
        return this.bfs(source, vis);
    }

    private List<T> bfs(T source, HashSet<T> vis) {
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

    public List<List<T>> dfs() {
        List<List<T>> ans = new ArrayList<>();
        HashSet<T> visited = new HashSet<>();
        map.forEach((k, v) -> {
            if (!visited.contains(k)) {
                ans.add(this.dfs((T) k, visited));
            }
        });
        return ans;
    }

    // return only a component of graph containing that source in it
    public List<T> dfs(T source) {
        if (!map.containsKey(source)) {
            return new ArrayList<>();
        }
        HashSet<T> vis = new HashSet<>();
        return this.dfs(source, new HashSet<>());
    }

    private List<T> dfs(T s, HashSet<T> visited) {
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