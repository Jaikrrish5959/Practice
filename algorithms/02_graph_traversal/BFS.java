package algorithms.graph_traversal;

import java.util.*;

/**
 * Breadth First Search (BFS) for Graph
 * Time Complexity: O(V + E)
 */
public class BFS {
    private int V;
    private LinkedList<Integer> adj[];

    @SuppressWarnings("unchecked")
    public BFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void runBFS(int s) {
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        System.out.println("BFS starting from vertex " + s + ":");
        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " ");

            for (Integer n : adj[s]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        System.out.println();
    }

    public static void main(String args[]) {
        BFS g = new BFS(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.runBFS(2);
    }
}
