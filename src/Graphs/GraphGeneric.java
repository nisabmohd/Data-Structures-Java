package Graphs;

import java.util.*;

public class GraphGeneric<T> {

    Map<Object, ArrayList<Object>> map;

    public GraphGeneric() {
        map = new HashMap<>();
    }

    public void addEdge(T start, T end) {
        if (end == null && start != null) {
            map.put(start, new ArrayList<>());
            return;
        }
        if (end == null && start != null) {
            map.put(end, new ArrayList<>());
            return;
        }
        if (end == null && start == null) {
            return;
        }
        if (map.containsKey(start)) {
            map.get(start).add(end);

        } else {
            map.put(start, new ArrayList<>(Arrays.asList(end)));
        }
        if (map.containsKey(end)) {
            map.get(end).add(start);
        } else {
            map.put(end, new ArrayList<>(Arrays.asList(start)));
        }
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
