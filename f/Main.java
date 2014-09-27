/*
The problem domain is a set of people. The people write reports and send the reports to
a set of people.

Some people write and receive reports. For these people, the reports that they send
summarize all of the reports they have received.

The problem provides a list of all of the people and flow of reports (a set of edges).
The solution to the problem is the set of exchanges between people that can be
eliminated without harming overall flow of information.

For example if Alice sends a report to Bob and Charlie, and Bob send a report to Charlie,
then the Alice does not need to send the report to Charlie, because the information
is already summarized in Bob's report to Charlie.

We will represent the set of people and interactions as a directed graph.
The people will be vertices and the trasmissions of reports will be edges.

In the domain of the graph, we can say that an edge from i to j, e(i,j)
should be removed if there exists a path from i to j not including e(i,j).

This would be a complicated way to solve the problem, because the path from i to j
could involve any number of vertices. To simplify the algorithm, we will first create
a transitive closure on the graph.

With the transitive closure, we can say that an edge, e(i,j) can be removed
if a pair of edges, e(i,k) and e(k,j), exist on the transitive closure,
where k a vertex on the graph other than i and k.
*/

import java.util.*;

class Main {
	static Scanner conIn;
	static final int MAX_V = 200;
	static int numConnections;
	static HashMap<String, Integer> nameToIndex = new HashMap<String, Integer>();
	static int numVertex;
	static boolean[][] graph = new boolean[MAX_V][MAX_V];
	static boolean[][] trans = new boolean[MAX_V][MAX_V];
	
	public static void main(String[] args) {
		numVertex = 0;
		conIn = new Scanner(System.in);
		
		numConnections = conIn.nextInt();
		while (numConnections != 0) {
			solveProblem();
			numConnections = conIn.nextInt();
		}
	}

	static void solveProblem() {
		getInput();
		buildTransitiveClosure();
		trimEdges();
		printTrimmedEdges();
	}

	static void getInput() {
		for (int i = 0; i < numConnections; i++) {
			String s1 = conIn.next();
			if (nameToIndex.get(s1) == null) {
				nameToIndex.put(s1, numVertex++);
			}
			String s2 = conIn.next();
			if (nameToIndex.get(s2) == null) {
				nameToIndex.put(s2, numVertex++);
			}
			int i1 = nameToIndex.get(s1);
			int i2 = nameToIndex.get(s2);
			graph[i1][i2] = true;
			trans[i1][i2] = true;
		}
	}

	static void buildTransitiveClosure() {
		for (int k = 0; k < numVertex; k++) {
			for (int i = 0; i < numVertex; i++) {
				for (int j = 0; j < numVertex; j++) {
					if (trans[i][k] && trans[k][j])
						trans[i][j] = true;
				}
			}
		}
	}

	static void trimEdges() {}
	static void printTrimmedEdges() {}
}
