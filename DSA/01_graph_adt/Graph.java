package DSA.graph_adt;

import java.util.*;

/**
 * Graph ADT - Core Operations
 * Handles Creation, Vertex/Edge management.
 */
public class Graph {
    private Map<Integer, List<Edge>> adjList;
    private boolean isDirected;
    private boolean isWeighted;
    private int vertexCount;

    // inner Edge class
    static class Edge {
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + dest + ", w:" + weight + ")";
        }
    }

    public Graph(boolean isDirected, boolean isWeighted) {
        this.adjList = new HashMap<>();
        this.isDirected = isDirected;
        this.isWeighted = isWeighted;
        this.vertexCount = 0;
    }

    // --- Vertex Operations ---

    public void addVertex(int v) {
        if (!adjList.containsKey(v)) {
            adjList.put(v, new ArrayList<>());
            vertexCount++;
        }
    }

    public void removeVertex(int v) {
        if (!adjList.containsKey(v))
            return;

        // Remove vertex
        adjList.remove(v);
        vertexCount--;

        // Remove incoming edges
        for (List<Edge> edges : adjList.values()) {
            edges.removeIf(e -> e.dest == v);
        }
    }

    public boolean vertexExists(int v) {
        return adjList.containsKey(v);
    }

    public int getVertexCount() {
        return vertexCount;
    }

    // --- Edge Operations ---

    public void addEdge(int src, int dest, int weight) {
        addVertex(src);
        addVertex(dest);

        int w = isWeighted ? weight : 1;

        adjList.get(src).add(new Edge(dest, w));

        if (!isDirected) {
            adjList.get(dest).add(new Edge(src, w));
        }
    }

    public void removeEdge(int src, int dest) {
        if (adjList.containsKey(src)) {
            adjList.get(src).removeIf(e -> e.dest == dest);
        }

        if (!isDirected && adjList.containsKey(dest)) {
            adjList.get(dest).removeIf(e -> e.dest == src);
        }
    }

    public boolean edgeExists(int src, int dest) {
        if (adjList.containsKey(src)) {
            for (Edge e : adjList.get(src)) {
                if (e.dest == dest)
                    return true;
            }
        }
        return false;
    }

    // --- Structural Queries ---

    public List<Integer> getAdjacentVertices(int v) {
        List<Integer> neighbors = new ArrayList<>();
        if (adjList.containsKey(v)) {
            for (Edge e : adjList.get(v)) {
                neighbors.add(e.dest);
            }
        }
        return neighbors;
    }

    public int getDegree(int v) {
        if (isDirected)
            return getOutDegree(v) + getInDegree(v);
        return getOutDegree(v);
    }

    public int getOutDegree(int v) {
        return adjList.containsKey(v) ? adjList.get(v).size() : 0;
    }

    public int getInDegree(int v) {
        int count = 0;
        for (List<Edge> edges : adjList.values()) {
            for (Edge e : edges) {
                if (e.dest == v)
                    count++;
            }
        }
        return count;
    }

    public void clear() {
        adjList.clear();
        vertexCount = 0;
    }

    public Map<Integer, List<Edge>> getAdjList() {
        return adjList;
    }

    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int v : adjList.keySet()) {
            sb.append(v).append(" -> ").append(adjList.get(v)).append("\n");
        }
        return sb.toString();
    }
}
