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

class Person {
	public ArrayList<Trade> trades;

	public Person() {
		this.trades = new ArrayList<Trade>();
	}
}

class Main {
	
	public static int[][] stickers = new int[10][25];
	public static Person[] people = new Person[10];
	public static boolean[] wasVisited = new boolean[10];
	public static int numPeople;
	public static int numStickers;

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			people[i] = new Person();
		}

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
	}

	public static void connectPeopleWhoWantThisSticker(int person, int sticker) {
		for (int i = 0; i < numPeople; i++) {
			if (stickers[i][sticker] == 0) {
				people[person].trades.add(new Trade(person, i, sticker));
			}
		}
	}

	public static Trade getCycle() {
		for (int i = 0; i < 10; i++)
			wasVisited[i] = false;

		LinkedList<Trade> trades = new LinkedList<Trade>();

		trades.addAll(people[0].trades);

		while (!trades.isEmpty()) {
			Trade current = trades.pop();
			wasVisited[current.destination] = true;

			if (current.destination == 0) {
				return current;
			}

			for (Trade trade : people[current.destination].trades) {
				if (wasVisited[trade.destination])
					continue;

				trade.previous = current;
				trades.add(trade);
			}
		}

		return null;
	}
}
