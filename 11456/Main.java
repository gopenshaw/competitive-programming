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
		for (int i = 0; i < 2001; i++) {
			train[i] = new Train();
		}
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		for (int i = 0; i < numCases; i++) {
			numCars = conIn.nextInt();
			for (int j = 0; j < numCars; j++)
				weight[j] = conIn.nextInt();
				
			System.out.println(solvePuzzle());
			
			for (int j = 0; j <= numCars; j++) {
				train[j].min = 0;
				train[j].max = 0;
			}
		}
	}
	
	public static int solvePuzzle() {
		addFirstTwoCars();
		int length = 2;
		
		for (int i = 0; i < numCars; i++) {
			for (int j = length; j >= 2; j--) {
				if (weight[i] < train[j].min
					&& weight[i] > train[j + 1].min) {
					System.out.println("Adding weight " + weight[i] + " to min.");
					train[j + 1].max = train[j].max;
					train[j + 1].min = weight[i];
					if (j + 1 > length)
						length = j + 1;
				}
				else if (weight[i] > train[j].max
						&& (weight[i] < train[j + 1].max
							|| train[j + 1].max == 0)) {
					System.out.println("Adding weight " + weight[i] + " to max.");
					train[j + 1].max = weight[i];
					train[j + 1].min = train[j].min;
					if (j + 1 > length)
						length = j + 1;
				}
			}
		}
		
		return length;
	}
	
	public static void addFirstTwoCars() {
		int first = weight[0];
		int second = weight[1];
		
		if (first < second) {
			train[2].min = first;
			train[2].max = second;
		}
		else {
			train[2].min = second;
			train[2].max = first;
		}
	}
}