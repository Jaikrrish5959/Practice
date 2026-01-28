import java.util.*;
import java.io.*;

/**
 * Rat in a Maze - Educational Demonstration of DSA & DAA
 * 
 * PROBLEM STATEMENT:
 * A Rat starts at source (0,0) and needs to reach destination (N-1, M-1).
 * The maze contains 1 (open) and 0 (blocked).
 * We must demonstrate:
 * 1. Finding ALL paths (Backtracking/DFS)
 * 2. Finding SHORTEST path (BFS)
 * 3. Interactive Play (Human Heuristic vs Algorithm)
 */
public class RatInAMaze {

    // DSA: Matrix (2D Array) - Used to represent the maze grid efficiently.
    // DAA: Problem Modeling - Mapping the real-world maze to a graph grid.
    static final int OPEN = 1;
    static final int BLOCKED = 0;

    // Directions: Down, Right, Up, Left
    static final int[] dRow = { 1, 0, -1, 0 };
    static final int[] dCol = { 0, 1, 0, -1 };
    static final String[] dName = { "D", "R", "U", "L" };

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Sample Maze
        // 1 0 0 0
        // 1 1 0 1
        // 0 1 0 0
        // 1 1 1 1
        int[][] maze = {
                { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 0, 1, 0, 0 },
                { 1, 1, 1, 1 }
        }; // 4x4

        System.out.println("=== RAT IN A MAZE (Educational) ===");
        System.out.println("Select Mode:");
        System.out.println("1. Watch Algorithms (DFS & BFS)");
        System.out.println("2. Play Yourself (Interactive vs BFS)");
        System.out.print("Enter choice: ");

        int choice = 1;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Clear buffer
        }

        if (choice == 2) {
            playInteractive(maze, scanner);
        } else {
            runAlgorithms(maze);
        }
        scanner.close();
    }

    // ==========================================
    // MODE: INTERACTIVE PLAY
    // ==========================================
    static void playInteractive(int[][] maze, Scanner scanner) throws InterruptedException {
        int N = maze.length;
        int M = maze[0].length;
        int r = 0, c = 0;
        int steps = 0;

        System.out.println("\n=== INTERACTIVE MODE ===");
        System.out.println("Controls: W (Up), A (Left), S (Down), D (Right)");
        System.out.println("Goal: Reach bottom-right (" + (N - 1) + "," + (M - 1) + ")");

        while (r != N - 1 || c != M - 1) {
            printMaze(maze, r, c);
            System.out.println("Steps taken: " + steps);
            System.out.print("Move (WASD): ");
            String move = scanner.next().toUpperCase();

            int nr = r, nc = c;
            if (move.equals("W"))
                nr--;
            else if (move.equals("S"))
                nr++;
            else if (move.equals("A"))
                nc--;
            else if (move.equals("D"))
                nc++;
            else {
                System.out.println("Invalid input! Use W, A, S, or D.");
                continue;
            }

            // DAA: Validity Check (Constraint Satisfaction)
            if (isValid(maze, nr, nc, N, M)) {
                r = nr;
                c = nc;
                steps++;
            } else {
                System.out.println(">> BONK! You hit a wall or boundary. Try again.");
            }
        }

        printMaze(maze, r, c);
        System.out.println("\n🎉 CONGRATULATIONS! You escaped in " + steps + " steps.");

        System.out.println("\n--- ALGORITHM ANALYSIS ---");
        System.out.println("Now let's see how the Computer's BFS (Optimization Algorithm) performs...");
        Thread.sleep(1000);
        solveBFS(maze, N, M);
    }

    // ==========================================
    // MODE: WATCH ALGORITHMS
    // ==========================================
    static void runAlgorithms(int[][] maze) throws InterruptedException {
        int N = maze.length;
        int M = maze[0].length;

        printMaze(maze, -1, -1);

        // --- PART 1: DFS ---
        System.out.println("\n--- ALGORITHM 1: DFS (Backtracking) ---");
        System.out.println("DAA Concept: Exhaustive Search & Backtracking");
        boolean[][] visited = new boolean[N][M];
        List<String> allPaths = new ArrayList<>();
        solveDFS(maze, 0, 0, visited, "", allPaths, N, M);
        System.out.println("All Possible Paths found: " + allPaths.size());
        for (String p : allPaths)
            System.out.println(" -> " + p);

        // --- PART 2: BFS ---
        System.out.println("\n--- ALGORITHM 2: BFS (Shortest Path) ---");
        System.out.println("DAA Concept: Optimality");
        solveBFS(maze, N, M);
    }

    static void printMaze(int[][] maze, int r, int c) {
        System.out.println("\n--------------------------------");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i == r && j == c) {
                    System.out.print("R ");
                } else if (maze[i][j] == BLOCKED) {
                    System.out.print("# ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    // ==========================================
    // ALGORITHM 1: DFS
    // ==========================================
    static void solveDFS(int[][] maze, int r, int c, boolean[][] visited, String path, List<String> allPaths, int N,
            int M) throws InterruptedException {
        if (r == N - 1 && c == M - 1) {
            allPaths.add(path);
            visualizeStep("DFS Reached Goal!", maze, r, c);
            return;
        }

        if (isSafe(maze, r, c, visited, N, M)) {
            visited[r][c] = true;
            for (int i = 0; i < 4; i++) {
                int nextR = r + dRow[i];
                int nextC = c + dCol[i];
                if (isSafe(maze, nextR, nextC, visited, N, M)) {
                    solveDFS(maze, nextR, nextC, visited, path + dName[i], allPaths, N, M);
                }
            }
            visited[r][c] = false; // Backtrack
        }
    }

    // ==========================================
    // ALGORITHM 2: BFS
    // ==========================================
    static void solveBFS(int[][] maze, int N, int M) throws InterruptedException {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        Node[][] parentMap = new Node[N][M];

        q.add(new Node(0, 0, 0));
        visited[0][0] = true;
        parentMap[0][0] = null;

        boolean found = false;
        Node finalNode = null;

        while (!q.isEmpty()) {
            Node current = q.poll();
            int r = current.r;
            int c = current.c;

            visualizeStep("BFS Exploring Layer " + current.dist + " @ (" + r + "," + c + ")", maze, r, c);

            if (r == N - 1 && c == M - 1) {
                found = true;
                finalNode = current;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = r + dRow[i];
                int nextC = c + dCol[i];
                if (isValid(maze, nextR, nextC, N, M) && !visited[nextR][nextC]) {
                    visited[nextR][nextC] = true;
                    q.add(new Node(nextR, nextC, current.dist + 1));
                    parentMap[nextR][nextC] = current;
                }
            }
        }

        if (found) {
            System.out.println("Shortest Path Found by BFS! Length: " + finalNode.dist);
            reconstructAndPrintPath(parentMap, finalNode, maze);
        } else {
            System.out.println("No Path Exists.");
        }
    }

    static class Node {
        int r, c, dist;

        Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }

    // ==========================================
    // UTILITIES
    // ==========================================
    static boolean isSafe(int[][] maze, int r, int c, boolean[][] visited, int N, int M) {
        return (r >= 0 && r < N && c >= 0 && c < M && maze[r][c] == OPEN && !visited[r][c]);
    }

    static boolean isValid(int[][] maze, int r, int c, int N, int M) {
        return (r >= 0 && r < N && c >= 0 && c < M && maze[r][c] == OPEN);
    }

    static void visualizeStep(String message, int[][] maze, int ratR, int ratC) throws InterruptedException {
        // Reduced wait time for smoother experience
        System.out.println("\n" + message);
        printMaze(maze, ratR, ratC);
        Thread.sleep(50);
    }

    static void reconstructAndPrintPath(Node[][] parentMap, Node end, int[][] maze) {
        List<Node> path = new ArrayList<>();
        Node curr = end;
        while (curr != null) {
            path.add(curr);
            curr = parentMap[curr.r][curr.c];
        }
        Collections.reverse(path);

        System.out.print("Optimal Path Trace: ");
        for (Node n : path) {
            System.out.print("(" + n.r + "," + n.c + ") -> ");
        }
        System.out.println("DONE");
    }
}
