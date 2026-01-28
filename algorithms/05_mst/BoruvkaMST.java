package algorithms.mst;

/**
 * Boruvka's Algorithm for Minimum Spanning Tree
 * Time Complexity: O(E log V)
 */
public class BoruvkaMST {

    class Edge {
        int src, dest, weight;
    }

    class Subset {
        int parent, rank;
    }

    int V, E;
    Edge[] edge;

    BoruvkaMST(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
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

    void boruvkaMST() {
        Subset[] subsets = new Subset[V];
        int[] cheapest = new int[V];

        for (int v = 0; v < V; ++v) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        int numTrees = V;
        int MSTweight = 0;

        System.out.println("Boruvka's MST Edges:");

        while (numTrees > 1) {
            // Initialize cheapest[] for every component
            for (int v = 0; v < V; ++v)
                cheapest[v] = -1;

            // Traverse all edges and update cheapest of every component
            for (int i = 0; i < E; i++) {
                int set1 = find(subsets, edge[i].src);
                int set2 = find(subsets, edge[i].dest);

                if (set1 == set2)
                    continue;

                if (cheapest[set1] == -1 || edge[cheapest[set1]].weight > edge[i].weight)
                    cheapest[set1] = i;

                if (cheapest[set2] == -1 || edge[cheapest[set2]].weight > edge[i].weight)
                    cheapest[set2] = i;
            }

            // Consider the cheapest edges and add to MST
            for (int i = 0; i < V; i++) {
                if (cheapest[i] != -1) {
                    int set1 = find(subsets, edge[cheapest[i]].src);
                    int set2 = find(subsets, edge[cheapest[i]].dest);

                    if (set1 == set2)
                        continue;

                    MSTweight += edge[cheapest[i]].weight;
                    System.out.println("Edge " + edge[cheapest[i]].src + "-" +
                            edge[cheapest[i]].dest + " with weight " + edge[cheapest[i]].weight);

                    union(subsets, set1, set2);
                    numTrees--;
                }
            }
        }
        System.out.println("Total MST Weight: " + MSTweight);
    }

    public static void main(String[] args) {
        BoruvkaMST graph = new BoruvkaMST(4, 5);

        // Edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 10;
        // Edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 6;
        // Edge 0-3
        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 5;
        // Edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 15;
        // Edge 2-3
        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 4;

        graph.boruvkaMST();
    }
}
