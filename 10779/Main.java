//
// UVA #10779 - Collector's Problem
// Garrett Openshaw
//

import java.util.*;

class Edge {
	public int source;
	public int destination;
	public int sticker;

	public Edge(int source, int destination, int stickers) {
		this.source = source;
		this.destination = destination;
		this.sticker = sticker;
	}
}

class Path {
	public ArrayList<Edge> edges;

	public Path() {
		this.edges = new ArrayList<Edge>();
	}
}
	

class Main {
	
	public static int[][] stickers = new int[10][25];
	public static int numPeople;
	public static int numStickers;

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();

		for (int n = 1; n <= numCases; n++) {
			numPeople = conIn.nextInt();
			numStickers = conIn.nextInt();

			for (int i = 0; i < numPeople; i++) {
				int count = conIn.nextInt();
				for (int c = 0; c < count; c++) {
					int value = conIn.nextInt();
					stickers[i][value]++;
				}
			}
			
			makeEdges();
			
			Path cycle = getCycle();
			while (cycle != null) {
				makeTrades(cycle);
				cycle = getCycle();
			}

			int count = 0;
			for (int i = 0 ; i < numStickers; i++) {
				if (stickers[0][i] != 0)
					count++;
			}

			System.out.println("Case #" + n + ": " + count);

			if (n != numCases) {
				for (int i = 0; i < numPeople; i++) {
					for (int j = 0; j < numStickers; j++) {
						stickers[i][j] = 0;
					}
				}
			}
		}
	}

	public static void makeEdges() {
		return;
	}

	public static Path getCycle() {
		//--use bfs

		return null;
	}

	public static void makeTrades(Path path) {
		return;
	}
}
