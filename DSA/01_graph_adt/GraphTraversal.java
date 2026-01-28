package DSA.graph_adt;

import java.util.*;

/**
 * Graph Traversal Algorithms
 * BFS, DFS (Recursive + Iterative)
 */
public class GraphTraversal {

    public static void bfs(Graph graph, int startNode) {
        System.out.print("BFS(" + startNode + "): ");
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startNode);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");

            for (int v : graph.getAdjacentVertices(u)) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
        System.out.println();
    }

    public static void dfs(Graph graph, int startNode) {
        System.out.print("DFS(" + startNode + "): ");
        Set<Integer> visited = new HashSet<>();
        dfsRecursive(graph, startNode, visited);
        System.out.println();
    }

    private static void dfsRecursive(Graph graph, int u, Set<Integer> visited) {
        visited.add(u);
        System.out.print(u + " ");

        for (int v : graph.getAdjacentVertices(u)) {
            if (!visited.contains(v)) {
                dfsRecursive(graph, v, visited);
            }
        }
    }

    public static void iterativeDFS(Graph graph, int startNode) {
        System.out.print("Iterative DFS(" + startNode + "): ");
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(startNode);

        while (!stack.isEmpty()) {
            int u = stack.pop();

            if (!visited.contains(u)) {
                visited.add(u);
                System.out.print(u + " ");

                // Push neighbors (reverse order to match recursive intuition often)
                List<Integer> neighbors = graph.getAdjacentVertices(u);
                Collections.reverse(neighbors);
                for (int v : neighbors) {
                    if (!visited.contains(v)) {
                        stack.push(v);
                    }
                }
            }
        }
        System.out.println();
    }
}
