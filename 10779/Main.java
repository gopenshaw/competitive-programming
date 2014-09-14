import java.util.*;

class Main {
	static Scanner input;
	static int numCases;
	static int numPeople;
	static int numStickers;
	static int[][] stickerCount;

	static void getInput() {
		numPeople = input.nextInt();
		numStickers = input.nextInt();

		for (int i = 0; i < numPeople; i++) {
			int numStickersForPerson = input.nextInt();
			for (int j = 0; j < numStickersForPerson; j++) {
				int stickerNumber = input.nextInt();
				stickerCount[i][j]++;
			}
		}
	}

	static void buildGraph() {

	}

	static void doMaxFlowAndPrintResult() {
		
	}

	public static void main(String[] args) {
		input = new Scanner(System.in);
		numCases = input.nextInt();

		for (int i = 0; i < numCases; i++) {
			getInput();
			buildGraph();
			doMaxFlowAndPrintResult();
		}
	}
}