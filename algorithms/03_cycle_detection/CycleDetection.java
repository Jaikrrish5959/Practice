package algorithms.cycle_detection;

import java.util.*;

/**
 * Cycle Detection in Graphs
 * 1. Directed Graph (using Recursion Stack)
 * 2. Undirected Graph (using Union-Find or DFS parent check)
 */
public class CycleDetection {

    // --- Directed Graph Cycle Detection ---
    static class DirectedGraph {
        private int V;
        private List<List<Integer>> adj;

        public DirectedGraph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++)
                adj.add(new ArrayList<>());
        }

        void addEdge(int source, int dest) {
            adj.get(source).add(dest);
        }

        boolean isCyclic() {
            boolean[] visited = new boolean[V];
            boolean[] recStack = new boolean[V];

            for (int i = 0; i < V; i++)
                if (isCyclicUtil(i, visited, recStack))
                    return true;

            return false;
        }

        private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {
            if (recStack[i])
                return true;
            if (visited[i])
                return false;

            visited[i] = true;
            recStack[i] = true;

            for (Integer c : adj.get(i)) {
                if (isCyclicUtil(c, visited, recStack))
                    return true;
            }

            recStack[i] = false;
            return false;
        }
    }

    // --- Undirected Graph Cycle Detection (using DFS) ---
    static class UndirectedGraph {
        private int V;
        private List<List<Integer>> adj;

        public UndirectedGraph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++)
                adj.add(new ArrayList<>());
        }

        void addEdge(int v, int w) {
            adj.get(v).add(w);
            adj.get(w).add(v);
        }

        boolean isCyclic() {
            boolean[] visited = new boolean[V];
            for (int u = 0; u < V; u++) {
                if (!visited[u])
                    if (isCyclicUtil(u, visited, -1))
                        return true;
            }
            return false;
        }

        // Returns true if the graph contains a cycle
        boolean isCyclicUtil(int v, boolean[] visited, int parent) {
            visited[v] = true;

            for (Integer i : adj.get(v)) {
                if (!visited[i]) {
                    if (isCyclicUtil(i, visited, v))
                        return true;
                } else if (i != parent) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Directed Graph Cycle Check ---");
        DirectedGraph g1 = new DirectedGraph(4);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0); // Cycle here
        g1.addEdge(2, 3);
        g1.addEdge(3, 3); // Self loop

        if (g1.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contain cycle");

        System.out.println("\n--- Undirected Graph Cycle Check ---");
        UndirectedGraph g2 = new UndirectedGraph(5);
        g2.addEdge(1, 0);
        g2.addEdge(0, 2);
        g2.addEdge(2, 1); // Cycle 0-1-2
        g2.addEdge(0, 3);
        g2.addEdge(3, 4);

        if (g2.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contain cycle");
    }
}
