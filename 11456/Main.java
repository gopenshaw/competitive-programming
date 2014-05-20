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
		addFirstTwoCars();
		int length = 2;
		
		for (int i = 0; i < numCars; i++) {
			for (int j = length; j >= 2; j--) {
				if (weight[i] < train[j].min
					&& weight[i] > train[j + 1].min) {
					train[j + 1].max = train[j].max;
					train[j + 1].min = weight[i];
				}
				else if (weight[i] > train[j].max
						&& (weight[i] < train[j + 1].max
							|| train[j + 1].max == 0)) {
					train[j + 1].max = weight[i];
					train[j + 1].min = train[j].min;
				}
			}
		}
		
		return 0;
	}
	
	public static void addFirstTwoCars() {
		int first = 1;
		int second = 2;
		while (weight[first] == weight[second]) {
			second++;
		}
		
		if (weight[first] < weight[second]) {
			train[2].min = weight[first];
			train[2].max = weight[second];
		}
		else {
			train[2].min = weight[second];
			train[2].max = weight[first];
		}
	}
}