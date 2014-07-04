
// UVA #820 - Internet Bandwidth
// Garrett Openshaw

import java.util.*;

class Main {

	public static int numNodes;
	public static int[][] network;
	public static int[][] residual;

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
			}

			int maxBandwidth = getMaxBandwidth();

			System.out.println("Network " + networkNumber);
			System.out.println("The bandwidth is " + maxBandwidth);
			System.out.println();

			numNodes = conIn.nextInt();
			networkNumber++;
		}
	}

	public static int getMaxBandwidth() {
		return 0;
	}
}