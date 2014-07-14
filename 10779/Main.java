//
// UVA #10779 - Collector's Problem
// Garrett Openshaw
//

import java.util.*;

class Trade {
	public ArrayList<Integer> targets;
	public int activeTarget;
	public int sticker;

	public Trade previous;

	public Trade (int sticker) {
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
					Trade t = new Trade(j);
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
