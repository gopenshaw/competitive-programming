//--UVA #11456
//--Strategy is to find Max(LIS(i) + LDS(i) - 1)

import java.util.*;

class Main {

	public static int[] weight = new int[2000];
	public static int[] inc = new int[2000];
	public static int[] dec = new int[2000];
	public static int numCars;

	public static void main(String[] args) {
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
		int result = 0;
		
		for (int i = 0; i < numCars; i++) {
			//--reset length array
			for (int j = i; j < numCars; j++) {
				inc[j] = 1;
				dec[j] = 1;
			}
				
			int trainLength = getLongestTrain(i);
			
			if (trainLength > result)
				result = trainLength;
		}
		
		
		return result;
	}
	
	public static int getLongestTrain(int n) {
		//--compute LIS starting from index n
		int lisMax = 1;
		int ldsMax = 1;
		for (int i = n + 1; i < numCars; i++) {
			for (int j = n; j < i; j++) {
				if (weight[i] > weight[n]) {
					if (weight[j] < weight[i]) {
						int currentLength = inc[i];
						int newLength = inc[j] + 1;
						if (newLength > currentLength) {
							inc[i] = newLength;
							lisMax = Math.max(newLength, lisMax);
						}
					}
				}
				else if (weight[i] < weight[n]) {
					if (weight[j] > weight[i]) {
						int currentLength = dec[i];
						int newLength = dec[j] + 1;
						if (newLength > currentLength) {
							dec[i] = newLength;
							ldsMax = Math.max(newLength, ldsMax);
						}
					}
				}
			}
		}
		return lisMax + ldsMax - 1;
	}
}