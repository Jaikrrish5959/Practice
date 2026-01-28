package algorithms.transitive_closure;

/**
 * Transitive Closure of a Graph
 * Using Floyd Warshall Algorithm logic
 * Time Complexity: O(V^3)
 */
public class TransitiveClosure {
    final static int V = 4;

    void transitiveClosure(int graph[][]) {
        int reach[][] = new int[V][V];
        int i, j, k;

        // Initialize reachability matrix same as input graph
        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                reach[i][j] = graph[i][j];

        // Floyd Warshall logic
        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    // Update reachability: if i reaches k and k reaches j, then i reaches j
                    // Using bitwise OR for boolean logic (1 if reachable, 0 otherwise)
                    // If reach[i][j] is already 1, it stays 1.
                    // If not, we check if i->k AND k->j exists.
                    reach[i][j] = (reach[i][j] != 0) || ((reach[i][k] != 0) && (reach[k][j] != 0)) ? 1 : 0;
                }
            }
        }

        printSolution(reach);
    }

    void printSolution(int reach[][]) {
        System.out.println("Transitive Closure Matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j)
                    System.out.print("1 "); // Self-loop always reachable
                else
                    System.out.print(reach[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TransitiveClosure tc = new TransitiveClosure();

        // 1 represents edge, 0 represents no edge
        int graph[][] = {
                { 1, 1, 0, 1 },
                { 0, 1, 1, 0 },
                { 0, 0, 1, 1 },
                { 0, 0, 0, 1 }
        };

        tc.transitiveClosure(graph);
    }
}
