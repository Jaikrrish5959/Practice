package algorithms.mst;

import java.util.*;

/**
 * Kruskal's Minimum Spanning Tree Algorithm
 * Uses Union-Find Data Structure
 * Time Complexity: O(E log E) or O(E log V)
 */
public class KruskalMST {

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    static class Subset {
        int parent, rank;
    }

    private int V, E;
    Edge[] edges;

    KruskalMST(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
        for (int i = 0; i < e; ++i)
            edges[i] = new Edge();
    }

    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    void runKruskalMST() {
        Edge[] result = new Edge[V];
        int e = 0;
        int i = 0;
        for (i = 0; i < V; ++i)
            result[i] = new Edge();

        Arrays.sort(edges);

        Subset[] subsets = new Subset[V];
        for (i = 0; i < V; ++i) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        i = 0;
        while (e < V - 1 && i < E) {
            Edge next_edge = edges[i++];
            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            if (x != y) {
                result[e++] = next_edge;
                union(subsets, x, y);
            }
        }

        System.out.println("Edges in MST:");
        int minimumCost = 0;
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Total Cost: " + minimumCost);
    }

    public static void main(String[] args) {
        int V = 4;
        int E = 5;
        KruskalMST graph = new KruskalMST(V, E);

        // Edge 0-1
        graph.edges[0].src = 0;
        graph.edges[0].dest = 1;
        graph.edges[0].weight = 10;

        // Edge 0-2
        graph.edges[1].src = 0;
        graph.edges[1].dest = 2;
        graph.edges[1].weight = 6;

        // Edge 0-3
        graph.edges[2].src = 0;
        graph.edges[2].dest = 3;
        graph.edges[2].weight = 5;

        // Edge 1-3
        graph.edges[3].src = 1;
        graph.edges[3].dest = 3;
        graph.edges[3].weight = 15;

        // Edge 2-3
        graph.edges[4].src = 2;
        graph.edges[4].dest = 3;
        graph.edges[4].weight = 4;

        graph.runKruskalMST();
    }
}
