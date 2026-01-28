# Rat in a Maze (DSA & DAA Demo)

An educational Java project demonstrating DFS, BFS, and Backtracking algorithms.

## Problem Statement
Navigate a "Rat" from source `(0, 0)` to `(N-1, M-1)` in a grid.
- `1` = Open Path
- `0` = Blocked Wall

## Key Concepts

### DSA (Data Structures)
- **2D Array**: Grid representation.
- **Stack**: Implicit call stack for DFS recursion.
- **Queue**: FIFO structure for BFS level-order traversal.

### DAA (Algorithms)
1.  **DFS (Backtracking)**: Finds **all** possible paths by exploring deep & backtracking on dead ends.
    -   *Complexity*: $O(3^{N \times M})$ (Exponential)
2.  **BFS (Shortest Path)**: Finds the **shortest** path by exploring layer-by-layer.
    -   *Complexity*: $O(N \times M)$ (Linear)
3.  **Heuristics (Interactive)**: When you play manually, you use mental heuristics (greedy choices) to find the path.

## How to Run

1.  **Compile & Run**:
    ```bash
    javac RatInAMaze.java
    java RatInAMaze
    ```
2.  **Modes**:
    -   **Watch Algorithms**: Visualizes DFS finding all paths, then BFS finding shortest path.
    -   **Interactive**: You play using `W/A/S/D`.

## Sample Output
```text
Shortest Path Found by BFS! Length: 6
Optimal Path Trace: (0,0) -> (1,0) -> (1,1) -> (2,1) -> (3,1) -> (3,2) -> (3,3) -> DONE
```
