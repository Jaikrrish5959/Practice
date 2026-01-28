package algorithms.graph_traversal;

import java.util.*;

/**
 * Depth First Search (DFS) for Graph
 * Time Complexity: O(V + E)
 */
public class DFS {
    private int V;
    private LinkedList<Integer> adj[];

    @SuppressWarnings("unchecked")
    public DFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(v + " ");

        for (Integer n : adj[v]) {
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    void runDFS(int v) {
        boolean visited[] = new boolean[V];
        System.out.println("DFS starting from vertex " + v + ":");
        DFSUtil(v, visited);
        System.out.println();
    }

    public static void main(String args[]) {
        DFS g = new DFS(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.runDFS(2);
    }
}
