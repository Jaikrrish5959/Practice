package algorithms.shortest_path;

/**
 * Floyd Warshall Algorithm
 * All-Pairs Shortest Path
 * Time Complexity: O(V^3)
 */
public class FloydWarshall {
    final static int INF = 99999;
    final static int V = 4;

    void runFloydWarshall(int graph[][]) {
        int dist[][] = new int[V][V];
        int i, j, k;

        // Initialize dist matrix
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Main algorithm
        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        printSolution(dist);
    }

    void printSolution(int dist[][]) {
        System.out.println("All-Pairs Shortest Path Matrix:");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "   ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        FloydWarshall a = new FloydWarshall();
        int graph[][] = {
                { 0, 5, INF, 10 },
                { INF, 0, 3, INF },
                { INF, INF, 0, 1 },
                { INF, INF, INF, 0 }
        };
        a.runFloydWarshall(graph);
    }
}
