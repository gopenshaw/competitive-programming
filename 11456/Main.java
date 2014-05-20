//--UVA #11456

import java.util.*;

class Train {
	int min;
	int max;
}

class Main {

	public static int[] weight = new int[2000];
	public static Train[] train = new Train[2001];
	public static int numCars;

	public static void main(String[] args) {
		for (int i = 0; i <= 2001; i++) {
			train[i] = new Train();
		}
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		for (int i = 0; i < numCases; i++) {
			numCars = conIn.nextInt();
			for (int j = 0; j < numCars; j++)
				weight[j] = conIn.nextInt();
				
			System.out.println(solvePuzzle());
		}
	}
	
	public static int solvePuzzle() {
		return 0;
	}
}