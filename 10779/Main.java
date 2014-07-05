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
	public static int[][] stickers = new int[10][25];

	//--If a person has a sticker that the other person does not have
	public static boolean[][][] trade = new boolean[10][10][25];

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
			
			Trade finalTrade = getCycle();
			while (finalTrade != null) {
				//--get the cycle and adjust the values, delete the edges
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

	public static void buildGraph() {
		for (int i = 0; i < numPeople; i++) {
			for (int j = 0; j < numStickers; j++) {
				if(stickers[i][j] > 1) {
					connectPeopleWhoWantThisSticker(i, j);
				}
			}
		}

		for (int i = 0; i < numPeople; i++) {
			for (int j = 0; j < numPeople; j++) {
				for (int k = 0; k < numStickers; k++) {
					trade[i][j][k] = false;
				}
			}
		}
	}

	public static void connectPeopleWhoWantThisSticker(int person, int sticker) {
		for (int i = 0; i < numPeople; i++) {
			if (stickers[i][sticker] == 0) {
				trade[person][i][sticker] = true;
			}
		}
	}

	public static Trade getCycle() {

		LinkedList<Trade> trades = new LinkedList<Trade>();

		for (int i = 0; i < numPeople; i++) {
			for (int j = 0; j < numStickers; j++) {
				if (trade[0][i][j]) {
					trades.add(new Trade(0, i, j));
				}
			}
		}

		while (!trades.isEmpty()) {
			Trade current = trades.pop();

			if (current.destination == 0) {
				return current;
			}

			for (int i = 0; i < numPeople; i++) {
				for (int j = 0; j < numStickers; j++) {
					if (trade[current.destination][i][j]) {
						Trade nextTrade = new Trade(0, i, j);
						nextTrade.previous = current;
						trades.add(nextTrade);
					}
				}
			}
		}

		return null;
	}
}
