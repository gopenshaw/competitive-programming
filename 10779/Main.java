//
// UVA #10779 - Collector's Problem
// Garrett Openshaw
//

import java.util.*;

class Trade {
	public int source;
	public int destination;
	public int sticker;
	public Trade previous;

	public Trade (int source, int destination, int sticker) {
		this.source = source;
		this.destination = destination;
		this.sticker = sticker;
	}
}

class Main {
	
	//--The current sticker count for each person
	public static int[][] stickers = new int[10][26];

	//--If a person has a sticker that the other person does not have
	//--graph[source][destination][sticker]
	public static boolean[][][] graph = new boolean[10][10][26];

	public static boolean[] wasVisited = new boolean[10];
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
			
			buildGraph();
			bellmanFord();
			System.out.println("Case #" + n + ": " + getCount());

			if (n != numCases) {
				tearDown();
			}
		}
	}

	public static void bellmanFord() {
		Trade previousTrade = getCycle();
		while (previousTrade != null) {
			
			//--Update stickers for each trade in the cycle
			do {
				updateStickers(previousTrade);
				previousTrade = previousTrade.previous;
			} while(previousTrade != null);

			previousTrade = getCycle();
		}
	}

	public static Trade getCycle() {
		for (int i = 0; i < numPeople; i++)
			wasVisited[i] = false;


		LinkedList<Trade> trades = new LinkedList<Trade>();

		for (int i = 1; i < numPeople; i++) {
			for (int j = 1; j <= numStickers; j++) {
				if (graph[0][i][j]) {
					trades.add(new Trade(0, i, j));
				}
			}
		}

		while (!trades.isEmpty()) {
			Trade current = trades.pop();

			if (current.destination == 0) {
				return current;
			}

			if (wasVisited[current.destination]) {
				continue;
			}

			wasVisited[current.source] = true;

			for (int i = 0; i < numPeople; i++) {
				for (int j = 1; j <= numStickers; j++) {
					if (graph[current.destination][i][j]) {
						Trade nextTrade = new Trade(current.destination, i, j);
						nextTrade.previous = current;
						trades.add(nextTrade);
					}
				}
			}
		}

		return null;
	}

	public static void updateStickers(Trade trade) {
		graph[trade.source][trade.destination][trade.sticker] = false;
		stickers[trade.source][trade.sticker]--;
		stickers[trade.destination][trade.sticker]++;

		if (stickers[trade.source][trade.sticker] == 1) {
			for (int i = 0; i < numPeople; i++) {
				graph[trade.source][i][trade.sticker] = false;
			}
		}
	}

	public static void buildGraph() {
		for (int i = 0; i < numPeople; i++) {
			for (int j = 1; j <= numStickers; j++) {
				if(stickers[i][j] > 1) {
					for (int k = 0; k < numPeople; k++) {
						if (stickers[k][j] == 0) {
							graph[i][k][j] = true;
						}
					}
				}
			}
		}
	}

	public static int getCount() {
		int count = 0;
		for (int i = 1 ; i <= numStickers; i++) {
			if (stickers[0][i] != 0)
				count++;
		}

		return count;
	}

	public static void tearDown() {
		for (int i = 0; i < numPeople; i++) {
			for (int j = 1; j <= numStickers; j++) {
				stickers[i][j] = 0;
			}
		}

		for (int i = 0; i < numPeople; i++) {
			for (int j = 0; j < numPeople; j++) {
				for (int k = 1; k <= numStickers; k++) {
					graph[i][j][k] = false;
				}
			}
		}
	}
}
