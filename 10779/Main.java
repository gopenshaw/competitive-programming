//
// UVA #10779 - Collector's Problem
// Garrett Openshaw
//

import java.util.*;

class Trade {
	public int source;
	public ArrayList<Integer> targets;

	//--no edge have 0 as a target, so 0 represent no target
	public int target;

	public int sticker;

	public Trade previous;

	public Trade (int source, int sticker) {
		this.source = source;
		this.sticker = sticker;
		this.targets = new ArrayList<Integer>();
	}
}

class Person {
	public ArrayList<Trade> tradesToMe;
	public ArrayList<Trade> tradesFromMe;

	public Person() {
		this.tradesToMe = new ArrayList<Trade>();
		this.tradesFromMe = new ArrayList<Trade>();
	}
}

class Main {
	
	//--The current sticker count for each person
	public static int[][] stickers = new int[10][26];

	//--Person[10] will represent the destination
	public static Person[] people = new Person[11];

	public static int numPeople;
	public static int numStickers;

	public static void main(String[] args) {

		for (int i = 0 ; i < 11; i++)
			people[i] = new Person();

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
			
			buildGraph();

			//bellmanFord();
			//System.out.println("Case #" + n + ": " + getCount());

			// if (n != numCases) {
			// 	tearDown();
			// }
		}
	}

	public static void buildGraph() {
		for (int i = 0; i < numPeople; i++) {
			for (int j = 1; j <= numStickers; j++) {
				if(stickers[i][j] > 1) {
					Trade t = new Trade(i, j);
					people[i].tradesFromMe.add(t);
					for (int k = 0; k < numPeople; k++) {
						if (stickers[k][j] == 0) {
							int dest = 0;
							if (k == 0)
								dest = numPeople;
							else
								dest = k;

							t.targets.add(dest);
							people[dest].tradesToMe.add(t);
						}
					}
				}
			}
		}
	}

	public static int[] getPath() {
		//--path array will a parent for each trade in the path
		int[] path = new int[numPeople + 1];

		LinkedList<Trade> q = new LinkedList<Trade>();
		for (Trade t : people[0].tradesFromMe)
			q.add(t);

		while(!q.isEmpty()) {
			//--Edge may be going forward or backward
			//If edge is going forward its target is set
			//If edge is going backward its target is 0

			Trade current = q.poll();
			int dest = getDestination(current);
			if (dest == numPeople) {
				path[numPeople] = current.source;
				return path;
			}

			for (Trade nextTrade : people[dest].tradesFromMe)
				q.add(nextTrade);

		}

		return null;
	}

	public static int getDestination(Trade t) {
		return -1;
	}

	// public static void tearDown() {
	// 	for (int i = 0; i < numPeople; i++) {
	// 		people[i].trades.clear();
	// 		for (int j = 1; j <= numStickers; j++) {
	// 			stickers[i][j] = 0;
	// 		}
	// 	}

	// 	for (int i = 0; i < numPeople; i++) {
	// 		for (int j = 0; j < numPeople; j++) {
	// 			for (int k = 1; k <= numStickers; k++) {
	// 				graph[i][j][k] = false;
	// 				residual[i][j][k] = 0;
	// 			}
	// 		}
	// 	}
	// }
}
