package algorithms.strong_connectivity;

import java.util.*;

/**
 * Articulation Points (Cut Vertices) in a Graph
 * Time Complexity: O(V + E)
 */
public class ArticulationPoints {
    private int V;
    private LinkedList<Integer> adj[];
    int time = 0;

    @SuppressWarnings("unchecked")
    ArticulationPoints(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v); // Undirected
    }

    void APUtil(int u, boolean visited[], int disc[], int low[], int parent[], boolean ap[]) {
        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (Integer v : adj[u]) {
            if (v == parent[u])
                continue;

            if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                children++;
                parent[v] = u;
                APUtil(v, visited, disc, low, parent, ap);

                low[u] = Math.min(low[u], low[v]);

                if (parent[u] != -1 && low[v] >= disc[u])
                    ap[u] = true;
            }
        }

        if (parent[u] == -1 && children > 1)
            ap[u] = true;
    }

    void findAPs() {
        boolean visited[] = new boolean[V];
        int disc[] = new int[V];
        int low[] = new int[V];
        int parent[] = new int[V];
        boolean ap[] = new boolean[V];

        Arrays.fill(parent, -1);
        Arrays.fill(visited, false);
        Arrays.fill(ap, false);

        for (int i = 0; i < V; i++)
            if (!visited[i])
                APUtil(i, visited, disc, low, parent, ap);

        System.out.println("Articulation points in the graph:");
        for (int i = 0; i < V; i++)
            if (ap[i])
                System.out.print(i + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        System.out.println("Articulation points in graph 1 ");
        ArticulationPoints g1 = new ArticulationPoints(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        g1.findAPs();
    }
}
