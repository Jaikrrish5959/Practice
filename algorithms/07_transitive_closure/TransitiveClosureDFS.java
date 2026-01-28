package algorithms.transitive_closure;

import java.util.*;

/**
 * Transitive Closure using DFS
 * Time Complexity: O(V * (V + E))
 */
public class TransitiveClosureDFS {
    private int V;
    private ArrayList<ArrayList<Integer>> adj;
    private int[][] tc; // Transitive Closure Matrix

    public TransitiveClosureDFS(int V) {
        this.V = V;
        this.adj = new ArrayList<>();
        this.tc = new int[V][V];

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    /**
     * DFS util to reach all vertices reachable from s
     */
    private void dfs(int s, int v) {
        tc[s][v] = 1;

        for (int neighbor : adj.get(v)) {
            if (tc[s][neighbor] == 0) {
                dfs(s, neighbor);
            }
        }
    }

    public void findTransitiveClosure() {
        for (int i = 0; i < V; i++) {
            dfs(i, i); // Run DFS for every vertex
        }

        printTransitiveClosure();
    }

    private void printTransitiveClosure() {
        System.out.println("Transitive Closure Matrix (DFS Reachability):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(tc[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TransitiveClosureDFS g = new TransitiveClosureDFS(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.findTransitiveClosure();
    }
}
