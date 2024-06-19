## What is Backtracking ?
Backtracking is a problem-solving algorithmic technique that involves finding a solution incrementally by trying different options and undoing them if they lead to a dead end. It is commonly used in situations where you need to explore multiple possibilities to solve a problem, like searching for a path in a maze or solving puzzles like Sudoku. When a dead end is reached, the algorithm backtracks to the previous decision point and explores a different path until a solution is found or all possibilities have been exhausted.


***Backtracking can be defined as a general algorithmic technique that considers searching every possible combination in order to solve a computational problem.***


**Basic Terminologies**

- **Candidate:** A candidate is a potential choice or element that can be added to the current solution.
- **Solution:** The solution is a valid and complete configuration that satisfies all problem constraints.
- **Partial Solution:** A partial solution is an intermediate or incomplete configuration being constructed during the backtracking process.
- **Decision Space:** The decision space is the set of all possible candidates or choices at each decision point.
- **Decision Point:** A decision point is a specific step in the algorithm where a candidate is chosen and added to the partial solution.
- **Feasible Solution:** A feasible solution is a partial or complete solution that adheres to all constraints.
- **Dead End:** A dead end occurs when a partial solution cannot be extended without violating constraints.
- **Backtrack:** Backtracking involves undoing previous decisions and returning to a prior decision point.
- **Search Space:** The search space includes all possible combinations of candidates and choices.
- **Optimal Solution:** In optimization problems, the optimal solution is the best possible solution.

**Types of Backtracking Problems**

Problems associated with backtracking can be categorized into 3 categories:

- **Decision Problems:** Here, we search for a feasible solution.
- **Optimization Problems:** For this type, we search for the best solution.
- **Enumeration Problems:** We find set of all possible feasible solutions to the problems of this type.

**How does Backtracking works?**

*As we know backtracking algorithm explores each and every possible path in order to find a valid solution, this exploration of path can be easily understood via given images:*

![backtracking](https://media.geeksforgeeks.org/wp-content/uploads/20231010124142/backtracking.png)

#### Recursion vs Backtracking

|**Recursion**|**Backtracking**|
| :-: | :-: |
|Recursion does not always need backtracking|Backtracking always uses recursion to solve problems|
|Solving problems by breaking them into smaller, similar subproblems and solving them recursively.|Solving problems with multiple choices and exploring options systematically, backtracking when needed.|
|Controlled by function calls and call stack.|Managed explicitly with loops and state.|
|**Applications of Recursion:** Tree and Graph Traversal, Towers of Hanoi, Divide and Conquer Algorithms, Merge Sort, Quick Sort, and Binary Search.|**Application of Backtracking:** N Queen problem, Rat in a Maze problem, Knight’s Tour Problem, Sudoku solver, and Graph coloring problems.|

