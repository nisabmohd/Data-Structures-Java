package Graphs;

import java.util.Date;

class User {  //   This is how social media connection is made

    String name;
    long uid;

    public User(String name, long uid) {
        this.name = name;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return this.name;
    }

}

public class GraphGenericExample {

    public static void main(String[] args) {
        GraphGeneric<User> graph = new GraphGeneric<>(false);
        User one = new User("Nisab", 05157435);
        User two = new User("Rahul", 65757567);
        User three = new User("James", 57576757);
        User four = new User("Tylor", 7575758);

        // Adding edge basically means making friends
        graph.addEdge(one, two);
        graph.addEdge(two, three);
        graph.addEdge(three, four);

        System.out.println(graph);

        // returns friends circle of just nisab
        System.out.println(graph.dfs(one));
    }
}
