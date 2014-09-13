/*
The first line of input contains the number of cases T (T<=20).
The first line of each case contains two integers n and m (2<=n<=10, 5<=m<=25). n is the number of people involved (including Bob), and m is the number of different stickers available.
The next n lines describe each person's stickers; the first of these lines describes Bob's stickers.
The i-th of these lines starts with a number ki<=50 indicating how many stickers person i has.
Then follows ki numbers between 1 and m indicating which stickers person i possesses.

*/


import java.util.*;

class Trade {
	int source, destination, sticker;
	Trade parent;

	public Trade(int source, int destination, int sticker) {
		this.source = source;
		this.destination = destination;
		this.sticker = sticker;
	}
	
	void print() {
		System.out.printf("src - %d, dest = %d, sticker = %d\n", source, destination, sticker);
	}

}

class Person {
	int[] originalStickers = new int[26];
	int[] currentStickers = new int[26];
	ArrayList<Trade> toHere = new ArrayList<Trade>();
	ArrayList<Trade> fromHere = new ArrayList<Trade>();

}

class Main {

	static Scanner input;
	static Person[] people = new Person[11];

	static int numPeople;
	static int numStickers;

	static int caseNum;

	public static void main(String args[]) {

		input = new Scanner(System.in);

		int numOfCases = input.nextInt();

		for (int i = 0; i < 11; i++) {
			people[i] = new Person();
		}

		for (int i = 0; i < numOfCases; i++) {
			caseNum = i + 1;
			solveProblem();
			tearDown();
		}
	}

	private static void solveProblem() {
		numPeople = input.nextInt();
		numStickers = input.nextInt();
		getInput();
		// printAllStickers();

		buildGraph();
		doMaxFlow();

		int result = getBobsUniqueStickers();
		System.out.println("Case #" + caseNum + ": " +  result);

		// printAllStickers();
	}

	private static void doMaxFlow() {
		// source is person 0, destination is person numPeople
		while (true) {
			Trade finalTrade = null;
			LinkedList<Trade> q = new LinkedList<Trade>();
			boolean visited[] = new boolean[numPeople + 1];
			visited[0] = true;

			for (Trade trade : people[0].fromHere) {
				if (tradeIsValid(trade)) {
					q.add(trade);
				}
			}

			while (!q.isEmpty()) {
				Trade currentTrade = q.poll();	
				if (currentTrade.destination == numPeople) {
					finalTrade = currentTrade;
					break;	
				}
				visited[currentTrade.source] = true;
				
				for (Trade trade : people[currentTrade.destination].fromHere) {
					if (!visited[trade.destination]
						&& tradeIsValid(trade)) {
						trade.parent = currentTrade;
						q.add(trade);
					}
				}
			}

			if (finalTrade != null) {
				processAllTrades(finalTrade);
			}
			else {
				break;
			}
		}
	}

	private static void buildGraph() {
		boolean[][] personDoesNotHaveSticker = new boolean[numPeople][numStickers + 1];

		//--Figure out what stickers people don't have
		for (int i = 0; i < numPeople; i++) {
			for (int j = 1; j <= numStickers; j++) {
				if (people[i].originalStickers[j] == 0)
					personDoesNotHaveSticker[i][j] = true;
			}
		}

		//--Build graph
		for (int i = 0; i < numPeople; i++) {
			for (int j = 1; j <= numStickers; j++) {
				if (people[i].originalStickers[j] > 1) {
					for (int k = 0; k < numPeople; k++) {
						if (personDoesNotHaveSticker[k][j]) {
							int destination = k;

							//--Bob is person 0 and person n
							//trades from bob are from person 0
							//trades to bob are to person n
							if (k == 0) {
								destination = numPeople;
							}

							Trade trade = new Trade(i, destination, j);
							people[i].fromHere.add(trade);
							people[destination].toHere.add(trade);
						}
					}
				}
			}
		}
	}

	private static void getInput() {
		//--get input
		for (int i = 0; i < numPeople; i++) {
			int stickerCount = input.nextInt();
			for (int j = 0; j < stickerCount; j++) {
				int stickerNumber = input.nextInt();
				people[i].originalStickers[stickerNumber]++;

				if (i == 0)
					people[numPeople].originalStickers[stickerNumber]++;
			}
		}

		//--copy original stickers to current stickers
		for (int i = 0; i <= numPeople; i++) {
			for (int j = 1; j <= numStickers; j++) {
				people[i].currentStickers[j] = people[i].originalStickers[j];
			}
		}
	}

	private static void processAllTrades(Trade trade) {
		Trade current = trade;
		while (current != null) {
			adjustStickerCount(current);
			makeResidual(current);
			current = current.parent;
		}
	}

	private static void makeResidual(Trade trade) {
		Trade residual = new Trade(trade.destination, trade.source, trade.sticker);

		people[trade.destination].fromHere.add(residual);
		people[trade.source].toHere.add(residual);

		people[trade.source].fromHere.remove(trade);
		people[trade.destination].fromHere.remove(trade);
	}

	private static boolean tradeIsValid(Trade trade) {
		Person source = people[trade.source];
		Person dest = people[trade.destination];
		int sticker = trade.sticker;
		boolean sourceIsValid = source.currentStickers[sticker] > 1
					|| source.currentStickers[sticker] > source.originalStickers[sticker];
					// || (trade.source == 0 
					// 	&& source.currentStickers[sticker] > 0);
		boolean destinationIsValid = dest.currentStickers[sticker] == 0;
		return sourceIsValid && destinationIsValid;
	}

	private static void adjustStickerCount(Trade trade) {
		int source = trade.source;
		if (source == 0
			|| source == numPeople) {
			people[0].currentStickers[trade.sticker]--;
			people[numPeople].currentStickers[trade.sticker]--;
		}
		else {
			people[source].currentStickers[trade.sticker]--;
		}

		int destination = trade.destination;
		if (destination == 0
			|| destination == numPeople) {
			people[0].currentStickers[trade.sticker]++;
			people[numPeople].currentStickers[trade.sticker]++;
		}
		else {
			people[destination].currentStickers[trade.sticker]++;
		}
	}

	private static void tearDown() {
		for (int j = 0; j <= numPeople; j++) {
			people[j].toHere.clear();
			people[j].fromHere.clear();
		
			for (int k = 1; k <= numStickers; k++) {
				people[j].currentStickers[k] = people[j].originalStickers[k] = 0;
			}
		}
	}

	private static int getBobsUniqueStickers() {
		int result = 0;
		for (int i = 1; i <= numStickers; i++) {
			if (people[numPeople].currentStickers[i] != 0)
				result++;
		}

		return result;
	}

	private static void printAllStickers() {
		for (int i = 0; i <= numPeople; i++) {
			for (int j = 1; j <= numStickers; j++) {
				System.out.printf("%d, ", people[i].currentStickers[j]);
			}
			System.out.println();
		}
	}
}
