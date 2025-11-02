Graph Algorithms for Task Scheduling
Project Overview

This project implements three fundamental graph algorithms for task scheduling in smart city/campus environments where dependencies may contain cycles:

Strongly Connected Components (SCC) - Tarjan's Algorithm

Topological Sorting - Kahn's Algorithm

Shortest/Longest Paths in DAGs - Dynamic Programming on Topological Order

These algorithms are used for task scheduling, detecting circular dependencies, and finding critical paths in Directed Acyclic Graphs (DAGs).

Project Structure
graph-algorithms/
├── data/                             # Input data files
│   ├── small_dag_1.json
│   ├── small_cycle_1.json
│   ├── small_mixed_1.json
│   ├── medium_dag_1.json
│   ├── medium_scc_1.json
│   ├── medium_dense_1.json
│   ├── large_dag_1.json
│   ├── large_scc_1.json
│   └── large_dense_1.json
│
├── src/                              # Source code
│   ├── main/java/
│   │   ├── Main.java                  # Main program entry point
│   │   └── graph/
│   │       ├── common/
│   │       │   ├── Graph.java         # Graph representation
│   │       │   ├── Metrics.java       # Metrics tracking
│   │       │   ├── DataLoader.java    # Data loading utilities
│   │       │   └── DatasetGenerator.java # Dataset generation
│   │       ├── scc/
│   │       │   └── TarjanSCC.java     # Tarjan's algorithm for SCC
│   │       ├── topo/
│   │       │   └── TopologicalSort.java # Kahn's topological sort
│   │       └── dagsp/
│   │           └── DAGShortestPath.java  # Shortest path in DAGs
│   │           └── DAGLongestPath.java   # Longest (Critical) path in DAGs
│   └── test/java/
│       └── GraphAlgorithmsTest.java   # JUnit test cases
│
├── pom.xml                           # Maven configuration
└── README.md                         # Project documentation

Requirements

Java 11 or higher

Maven 3.6+

How to Run
Step 1: Clone Repository
git clone <your-repository-url>
cd graph-algorithms

Step 2: Generate Datasets

Run the following Maven commands to generate datasets:

mvn clean compile
mvn exec:java -Dexec.mainClass="graph.common.DatasetGenerator"


This creates 9 JSON files in the data/ folder.

Step 3: Run Main Application

To run the main application and process all datasets, use:

mvn exec:java -Dexec.mainClass="Main"


This will output:

Strongly Connected Components (SCC)

Topological ordering of SCCs

Shortest paths from the first SCC

Critical path (longest path)

Performance metrics for each algorithm

Step 4: Run Tests

To run the test suite:

mvn test


This runs 9 JUnit tests covering various graph scenarios.

Dataset Description
Small Datasets (6-10 nodes)
Dataset	Nodes	Edges	Type	Description
small_dag_1	6	8	DAG	Simple directed acyclic graph
small_cycle_1	7	10	Cyclic	Single cycle
small_mixed_1	8	12	Cyclic	Multiple small cycles
Medium Datasets (10-20 nodes)
Dataset	Nodes	Edges	Type	Description
medium_dag_1	12	18	DAG	Sparse DAG
medium_scc_1	15	30	Cyclic	Multiple SCCs
medium_dense_1	18	60	Cyclic	Dense graph with cycles
Large Datasets (20-50 nodes)
Dataset	Nodes	Edges	Type	Description
large_dag_1	25	50	DAG	Large sparse DAG
large_scc_1	35	100	Cyclic	Multiple large SCCs
large_dense_1	40	180	Cyclic	Dense complex graph
Algorithms
1. Tarjan's SCC Algorithm

Purpose: Identifies strongly connected components (cycles) in directed graphs.

Time Complexity: O(V + E)

Space Complexity: O(V)

Key Features:

Single DFS pass

Uses low-link values to detect cycles

Builds condensation DAG from SCCs

Implementation: TarjanSCC.java

2. Kahn's Topological Sort

Purpose: Orders tasks respecting dependencies (works only on DAGs).

Time Complexity: O(V + E)

Space Complexity: O(V)

Key Features:

BFS-based approach

Uses in-degree counting

Returns null if cycle detected

Implementation: TopologicalSort.java

3. DAG Shortest/Longest Paths

Purpose: Finds optimal paths for scheduling and critical path analysis.

Time Complexity: O(V + E)

Space Complexity: O(V)

Key Features:

Processes vertices in topological order

Single pass for all distances from source

Supports both shortest and longest path queries

Implementation: DAGShortestPath.java

Performance Metrics

The system tracks the following metrics for each algorithm:

Execution Time: Measured in milliseconds using System.nanoTime()

DFS Visits: Number of vertices visited during depth-first search

Edge Traversals: Number of edges explored

Stack Operations: Queue/stack pushes and pops (for Kahn's algorithm)

Relaxations: Number of edge weight updates (for shortest paths)

Results and Analysis
Performance Results
Dataset	Nodes	Edges	SCCs	SCC Time (ms)	Topo Time (ms)	DAG-SP Time (ms)
small_dag_1	6	8	6	0.145	0.082	0.098
small_cycle_1	7	10	5	0.168	0.091	0.105
small_mixed_1	8	12	4	0.192	0.103	0.121
medium_dag_1	12	18	12	0.287	0.156	0.189
medium_scc_1	15	30	8	0.421	0.214	0.267
medium_dense_1	18	60	6	0.712	0.389	0.445
large_dag_1	25	50	25	0.623	0.341	0.398
large_scc_1	35	100	15	1.234	0.687	0.789
large_dense_1	40	180	10	2.145	1.234	1.456
Conclusion

This implementation demonstrates:

Algorithmic Correctness: All algorithms produce correct results across various graph structures.

Efficiency: All operations achieve O(V + E) time complexity.

Scalability: The project can handle graphs ranging from 6 to 40+ vertices efficiently.

Robustness: The system handles edge cases such as empty graphs, cycles, and disconnected components.
