
// UVA #820 - Internet Bandwidth
// Garrett Openshaw

import java.util.*;

class Main {

	public static int numNodes;
	public static int[][] network = new int[101][101];
	public static int[][] residual = new int[101][101];
	public static int[] parent = new int[101];
	public static boolean[] wasVisited = new boolean[101];

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);

		numNodes = conIn.nextInt();
		int networkNumber = 1;

		while (numNodes != 0) {

			int source = conIn.nextInt();
			int destination = conIn.nextInt();

			int numConnections = conIn.nextInt();

			for (int i = 0; i < numConnections; i++) {
				int node1 = conIn.nextInt();
				int node2 = conIn.nextInt();
				int bandwidth = conIn.nextInt();

				network[node1][node2] += bandwidth;
				network[node2][node1] += bandwidth;
				residual[node1][node2] += bandwidth;
				residual[node2][node1] += bandwidth;
			}

			int maxBandwidth = getMaxBandwidth(source, destination);

			System.out.println("Network " + networkNumber);
			System.out.println("The bandwidth is " + maxBandwidth);
			System.out.println();

			numNodes = conIn.nextInt();

			if (numNodes != 0) {
				networkNumber++;
				for (int i = 1; i <= numNodes; i++) {
					for (int j = 1; j <= numNodes; j++) {
						network[i][j] = 0;
						residual[i][j] = 0;
					}
				}
			}
		}
	}

	public static int getMaxBandwidth(int source, int destination) {

		int maxBandwidth = 0;

		while (findPath(source, destination)) {
			int minFlow = getMinFlow(source, destination);
			updateResiduals(source, destination, minFlow);
			maxBandwidth += minFlow;
		}

		return maxBandwidth;
	}

	public static boolean findPath(int source, int destination) {
		
		for (int i = 1; i <= numNodes; i++)
			wasVisited[i] = false;

		wasVisited[source] = true;
		LinkedList<Integer> q = new LinkedList<Integer>();

		for (int i = 1; i <= numNodes; i++) {
			if (residual[source][i] != 0) {
				q.add(new Integer(i));
				wasVisited[i] = true;
				parent[i] = source;
			}
		}

		while(!q.isEmpty()) {
			int currentVertex = q.pop();
			if (currentVertex == destination)
				return true;

			for (int i = 1; i <= numNodes; i++) {
				if (residual[currentVertex][i] != 0 && !wasVisited[i]) {
					wasVisited[i] = true;
					q.add(new Integer(i));
					parent[i] = currentVertex;
				}
			}
		}

		return false;
	}

	public static int getMinFlow(int source, int dest) {
		int minFlow = Integer.MAX_VALUE;
		int current = dest;
		int previous;

		do {
			previous = parent[current];
			minFlow = Math.min(minFlow, residual[previous][current]);
			current = previous;
		} while (previous != source);

		return minFlow;
	}

	public static void updateResiduals(int source, int dest, int minFlow) {
		int current = dest;
		int previous;

		do {
			previous = parent[current];
			residual[previous][current] -= minFlow;
			residual[current][previous] += minFlow;
			current = previous;
		} while (previous != source);
	}
}