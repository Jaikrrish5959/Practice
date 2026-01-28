package algorithms.cycle_detection;

import java.util.*;

/**
 * Topological Sort (using DFS)
 * Only for Directed Acyclic Graph (DAG)
 * Time Complexity: O(V + E)
 */
public class TopologicalSort {
    private int V;
    private LinkedList<Integer> adj[];

    @SuppressWarnings("unchecked")
    TopologicalSort(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
        visited[v] = true;

        for (Integer i : adj[v]) {
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        stack.push(v);
    }

    void runTopologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[V];

        for (int i = 0; i < V; i++)
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);

        System.out.println("Topological Sort Order:");
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        g.runTopologicalSort();
    }
}
