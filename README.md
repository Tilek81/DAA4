DAA4 — Graph Algorithms for Task Scheduling
Project Overview

This project implements three fundamental graph algorithms for task scheduling in smart city/campus environments where dependencies may contain cycles:

Strongly Connected Components (SCC) - Tarjan's Algorithm

Topological Sorting - Kahn's Algorithm

Shortest/Longest Paths in DAGs - Dynamic Programming on Topological Order

These algorithms are used for task scheduling, detecting circular dependencies, and finding critical paths in DAGs (Directed Acyclic Graphs).

Project Structure
graph-algorithms/
│
├── data/                           
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
├── src/
│   ├── main/java/
│   │   ├── Main.java              
│   │   └── graph/
│   │       ├── common/
│   │       │   ├── Graph.java              
│   │       │   ├── Metrics.java            
│   │       │   ├── DataLoader.java         
│   │       │   └── DatasetGenerator.java   
│   │       ├── scc/
│   │       │   └── TarjanSCC.java          
│   │       ├── topo/
│   │       │   └── TopologicalSort.java    
│   │       └── dagsp/
│   │           └── DAGShortestPath.java    
│   └── test/java/
│       └── GraphAlgorithmsTest.java        
│
├── pom.xml                         
└── README.md

Requirements

Java 11 or higher

Maven 3.6+

JUnit 5.9.3 (included in pom.xml)

Gson 2.10.1 (included in pom.xml)

Building and Running
Step 1: Clone Repository
git clone <your-repository-url>
cd graph-algorithms

Step 2: Generate Datasets

Run the following Maven commands to generate datasets:

mvn clean compile
mvn exec:java -Dexec.mainClass="graph.common.DatasetGenerator"


This creates 9 JSON files in the data/ folder.

Step 3: Run Main Application

Run the main application to process the datasets:

mvn exec:java -Dexec.mainClass="Main"


This processes all datasets and outputs the following:

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
All datasets include:

Node IDs and task names

Edge weights (1-6 units) representing duration/cost

Mix of sparse (density < 0.3) and dense (density > 0.4) structures

Algorithm Details
1. Tarjan's SCC Algorithm

Purpose: Identifies strongly connected components (cycles) in directed graphs

Time Complexity: O(V + E)

Space Complexity: O(V)

Key Features:

Single DFS pass

Uses low-link values to detect cycles

Builds condensation DAG from SCCs

2. Kahn's Topological Sort

Purpose: Orders tasks respecting dependencies (works only on DAGs)

Time Complexity: O(V + E)

Space Complexity: O(V)

Key Features:

BFS-based approach

Uses in-degree counting

Returns null if cycle detected

3. DAG Shortest/Longest Paths

Purpose: Finds optimal paths for scheduling and critical path analysis

Time Complexity: O(V + E)

Space Complexity: O(V)

Key Features:

Processes vertices in topological order

Single pass for all distances from source

Supports both shortest and longest path queries

Uses edge weights for path computation

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
Observation Analysis

Time Complexity Validation:
All algorithms show linear scaling with respect to V + E.

Effect of Graph Density:
Sparse graphs (density < 0.3) are faster with fewer edge traversals, while dense graphs (density > 0.4) require more traversals and thus take longer.

Impact of Strongly Connected Components:
Graphs with fewer large SCCs simplify the DAG and improve task ordering efficiency.

Algorithm-Specific Bottlenecks:

SCC Detection: Bottleneck occurs during edge traversals in dense graphs.

Topological Sort: Queue operations are the bottleneck in graphs with many vertices.

DAG Shortest Paths: Relaxation operations take more time in dense graphs.

Practical Recommendations

When to Use SCC Detection: For detecting cycles, deadlock detection, and simplifying cyclic graphs.

When to Use Topological Sort: For task scheduling, course scheduling, and data processing pipelines.

When to Use DAG Shortest/Longest Paths: For critical path method, resource optimization, and program evaluation techniques (PERT).

Testing

The test suite includes:

testSimpleDAG: Validates basic topological sorting.

testSingleSCC: Validates cycle detection in a strongly connected graph.

testMultipleSCCs: Tests detection of multiple SCCs.

testShortestPath: Ensures correctness of shortest path calculation.

testLongestPath: Validates longest (critical) path calculation.

testCycleDetection: Verifies topological sorting on cyclic graphs.

testEmptyGraph: Tests edge case handling.

testSingleVertex: Validates minimal graphs.

testDisconnectedGraph: Tests handling of multiple components.

All tests pass with 100% success rate.

Conclusions

This implementation successfully demonstrates:

Algorithmic Correctness: All algorithms produce correct results across diverse graph structures.

Efficiency: Achieves O(V + E) time complexity for all operations.

Scalability: Handles graphs from 6 to 40+ vertices efficiently.

Robustness: Proper handling of edge cases (empty graphs, cycles, disconnected components).

Practical Utility: Applicable to real-world scheduling problems.

Key Takeaways

Graph structure matters: Density and SCC count impact performance.

Preprocessing pays off: SCC compression simplifies downstream operations.

Linear scalability: All algorithms maintain O(V + E) complexity.

Tool selection: Choose algorithms based on graph characteristics.