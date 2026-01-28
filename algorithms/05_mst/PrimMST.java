package algorithms.mst;

import java.util.*;

/**
 * Prim's Minimum Spanning Tree Algorithm
 * Time Complexity: O(E log V)
 */
public class PrimMST {

    static class Node implements Comparator<Node> {
        int v, weight;

        public Node() {
        }

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        public int compare(Node n1, Node n2) {
            return Integer.compare(n1.weight, n2.weight);
        }
    }

    private int V;
    private List<List<Node>> adj;

    public PrimMST(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    void addEdge(int u, int v, int w) {
        adj.get(u).add(new Node(v, w));
        adj.get(v).add(new Node(u, w));
    }

    void runPrimMST() {
        int[] key = new int[V];
        int[] parent = new int[V];
        boolean[] inMST = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        key[0] = 0; // Start from vertex 0
        PriorityQueue<Node> pq = new PriorityQueue<>(V, new Node());
        pq.add(new Node(0, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().v;
            inMST[u] = true;

            for (Node neighbor : adj.get(u)) {
                int v = neighbor.v;
                int weight = neighbor.weight;

                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = u;
                    pq.add(new Node(v, key[v]));
                }
            }
        }

        System.out.println("Edge \tWeight");
        int totalWeight = 0;
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + key[i]);
            totalWeight += key[i];
        }
        System.out.println("Total MST Weight: " + totalWeight);
    }

    public static void main(String[] args) {
        PrimMST g = new PrimMST(5);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 3, 6);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 8);
        g.addEdge(1, 4, 5);
        g.addEdge(2, 4, 7);
        g.addEdge(3, 4, 9);

        g.runPrimMST();
    }
}
