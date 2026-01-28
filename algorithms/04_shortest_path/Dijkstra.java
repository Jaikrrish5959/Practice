package algorithms.shortest_path;

import java.util.*;

/**
 * Dijkstra's Shortest Path Algorithm
 * Time Complexity: O(E log V)
 */
public class Dijkstra {

    static class Node implements Comparator<Node> {
        public int node;
        public int cost;

        public Node() {
        }

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node n1, Node n2) {
            return Integer.compare(n1.cost, n2.cost);
        }
    }

    private int V;
    private List<List<Node>> adj;

    public Dijkstra(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    void addEdge(int u, int v, int w) {
        adj.get(u).add(new Node(v, w));
        adj.get(v).add(new Node(u, w)); // Undirected assumption for this demo
    }

    void runDijkstra(int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(V, new Node());
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().node;

            for (Node neighbor : adj.get(u)) {
                int v = neighbor.node;
                int weight = neighbor.cost;

                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        System.out.println("Vertex Distance from Source " + src);
        for (int i = 0; i < V; i++)
            System.out.println(i + "\t\t" + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
    }

    public static void main(String[] args) {
        Dijkstra g = new Dijkstra(5);
        g.addEdge(0, 1, 9);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 3, 5);
        g.addEdge(0, 4, 3);
        g.addEdge(2, 1, 2);
        g.addEdge(2, 3, 4);

        g.runDijkstra(0);
    }
}
