
// UVA #820 - Internet Bandwidth
// Garrett Openshaw

import java.util.*;

class Main {

	public static int numNodes;
	public static int[][] network = new int[101][101];
	public static int[][] residual = new int[101][101];

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
			}

			int maxBandwidth = getMaxBandwidth();

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

	public static int getMaxBandwidth() {
		return 0;
	}
}