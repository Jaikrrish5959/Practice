# Rat in a Maze: Educational Guide (DSA & DAA)

## 1. Problem Statement
**Goal**: Navigate a "Rat" from a starting position `(0, 0)` to a destination `(N-1, M-1)` in a grid-based maze.
**Constraints**:
- `1` represents an open path.
- `0` represents a blocked wall.
- Movement is allowed in 4 directions: Down, Right, Up, Left.
**Objectives**:
1.  Find **All Possible Paths** (Demonstrating Backtracking).
2.  Find the **Shortest Path** (Demonstrating BFS Optimality).

---

## 2. DSA Concepts Used (Data Structures)
- **2D Array (Matrix)**: Used to represent the maze grid. Provides `O(1)` access to any cell `(r, c)`.
- **Stack (Implicit)**: The Call Stack used during **Recursion** in DFS. Stores the current path's state.
- **Queue**: A FIFO (First-In-First-Out) structure used in **BFS**. Ensures we explore nodes layer-by-layer to find the shortest path.
- **List / ArrayList**: Used to store the collection of all valid path strings found.
- **Boolean Matrix (Visited Array)**: A secondary 2D array to keep track of visited cells in the current traversal to prevent cycles.
- **Graph Abstraction**: The grid is treated as a generic graph where each cell is a node and adjacent open cells are edges.

---

## 3. DAA Concepts Used (Design & Analysis)
- **Problem Modeling**: Transforming a geometric grid problem into a **Graph Traversal** problem.
- **Backtracking**: An algorithmic paradigm that tries to build a solution incrementally. If a path fails (blocked or dead end), it "backtracks" (undoes the last move) to try a different direction.
- **Pruning**: Using the `isSafe()` function to cut off invalid branches early (e.g., checking bounds or walls), saving computation.
- **Greedy Choice**: In DFS, the order of directions (Down, Right, Up, Left) is a localized greedy choice. We prioritize moving towards the goal (usually Down-Right) first.
- **Optimality**: **BFS** is chosen for the Shortest Path because it explores the graph in expanding concentric circles (layers). The first time it reaches the target, it is guaranteed to be via the path with the fewest edges.

---

## 4. Algorithm Explanation

### Algorithm A: Depth First Search (DFS) with Backtracking
**Goal**: Find *all* paths.
1.  Start at `(0,0)`. Mark it as visited.
2.  Check if current cell is Destination. If yes, record path.
3.  Recursively move to all valid neighbors (D, R, U, L).
4.  **Backtrack**: After returning from a recursion, unmark the cell as visited. This allows this cell to be part of *other* valid paths.

### Algorithm B: Breadth First Search (BFS)
**Goal**: Find *shortest* path.
1.  Start at `(0,0)`. Add it to a Queue with `dist=0`.
2.  While Queue is not empty:
    - Dequeue current node.
    - If Destination, stop. We found the shortest distance.
    - Else, enqueue all unvisited VALID neighbors with `dist = current.dist + 1`.

---

## 5. Pseudo Code

### Backtracking (DFS)
```text
Function SolveDFS(row, col, path):
    If (row, col) is Goal:
        Save path
        Return
    
    Mark (row, col) as Visited
    
    For each direction (D, R, U, L):
        NextRow = row + dRow
        NextCol = col + dCol
        If (NextRow, NextCol) is Safe:
            SolveDFS(NextRow, NextCol, path + Direction)
    
    Mark (row, col) as Unvisited (BACKTRACK)
```

### BFS (Shortest Path)
```text
Function SolveBFS():
    Queue q
    q.enqueue((0, 0, dist=0))
    Visited[0][0] = True
    
    While q is not empty:
        Current = q.dequeue()
        
        If Current is Goal:
            Return Current.dist
        
        For each neighbor:
            If valid and not visited:
                Visited[neighbor] = True
                q.enqueue((neighbor, dist+1))
```

---

## 6. Full Source Code
(See `RatInAMaze.java` in your project folder)

---

## 7. Time & Space Complexity Analysis

### DFS (Backtracking)
- **Time Complexity**: **O(3^(N×M))**. In the worst case (empty maze), every cell has 3 choices (excluding the one we came from). This is exponential.
- **Space Complexity**: **O(N×M)** for the recursion stack and visited array.

### BFS (Shortest Path)
- **Time Complexity**: **O(N × M)**. Each cell is visited and processed exactly once.
- **Space Complexity**: **O(N × M)**. In the worst case, the Queue might store a significant portion of the grid nodes.

---

## 8. Sample Input & Output

**Input Maze**:
```text
1 0 0 0
1 1 0 1
0 1 0 0
1 1 1 1
```

**Output**:
```text
--- ALGORITHM 1: DFS (Backtracking) ---
All Possible Paths found: 2
 -> DDRD
 -> DDRRRD

--- ALGORITHM 2: BFS (Shortest Path) ---
Shortest Path Found! Length: 4
Path Trace: (0,0) -> (1,0) -> (1,1) -> (2,1) -> (3,1) -> (3,2) -> (3,3) -> DONE
```

---

## 9. Visualization Explanation
The console visualization works by re-printing the maze grid at every step of the algorithm.
- `.`: Open Path
- `#`: Wall
- `R`: Current position of the Rat
- `Thread.sleep(100)`: Delays execution so the human eye can track the movement.

---

## 10. Extension Ideas
1.  **Multiple Rats**: Run two BFS threads simultaneously from different starting points to see who reaches the center first.
2.  **Weighted Maze**: Assign "costs" to cells (e.g., mud = cost 5, road = cost 1). Replace BFS with **Dijkstra’s Algorithm**.
3.  **Heuristics**: Implement **A* Search** by adding a heuristic function `h(n) = ManhattanDistance(current, goal)` to prioritize nodes closer to the target.
