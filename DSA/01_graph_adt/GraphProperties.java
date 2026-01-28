package DSA.graph_adt;

import java.util.*;

/**
 * Graph Properties and Connectivity
 */
public class GraphProperties {

    public static boolean isConnected(Graph graph) {
        if (graph.getVertexCount() == 0)
            return true;

        int startNode = graph.getAdjList().keySet().iterator().next();
        Set<Integer> visited = new HashSet<>();

        // Run BFS/DFS
        Queue<Integer> q = new LinkedList<>();
        q.add(startNode);
        visited.add(startNode);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph.getAdjacentVertices(u)) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    q.add(v);
                }
            }
        }

        return visited.size() == graph.getVertexCount();
    }

    public static boolean isCyclic(Graph graph) {
        // Simple undirected cycle check using BFS for demo
        // (Full logic for directed/undirected requires separation, doing undirected
        // generically here)
        Set<Integer> visited = new HashSet<>();

        for (int v : graph.getAdjList().keySet()) {
            if (!visited.contains(v)) {
                if (checkCycle(graph, v, visited, -1))
                    return true;
            }
        }
        return false;
    }

    private static boolean checkCycle(Graph graph, int v, Set<Integer> visited, int parent) {
        visited.add(v);
        for (int neighbor : graph.getAdjacentVertices(v)) {
            if (!visited.contains(neighbor)) {
                if (checkCycle(graph, neighbor, visited, v))
                    return true;
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBipartite(Graph graph) {
        Map<Integer, Integer> color = new HashMap<>(); // 0 or 1

        for (int v : graph.getAdjList().keySet()) {
            if (!color.containsKey(v)) {
                // BFS
                Queue<Integer> q = new LinkedList<>();
                q.add(v);
                color.put(v, 0);

                while (!q.isEmpty()) {
                    int u = q.poll();
                    int c = color.get(u);

                    for (int neighbor : graph.getAdjacentVertices(u)) {
                        if (!color.containsKey(neighbor)) {
                            color.put(neighbor, 1 - c);
                            q.add(neighbor);
                        } else if (color.get(neighbor) == c) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
