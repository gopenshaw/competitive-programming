//--UVA #11456
//--Strategy is to find Max(LIS(i) + LDS(i) - 1)

import java.util.*;

class Main {

	public static int[] weight = new int[2000];
	public static int[] length = new int[2000];
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
			for (int j = i; j < numCars; j++)
				length[j] = 1;
				
			int lis = LIS(i);
			System.out.println("DEBUG: " + lis);
			int lds = LDS(i);
			if (lis + lds - 1 > result)
				result = lis + lds - 1;
		}
		
		
		return result;
	}
	
	public static int LIS(int n) {
		//--compute LIS starting from index n
		int maxLength = 1;
		for (int i = n + 1; i < numCars; i++) {
			if (weight[i] < weight[n])
				continue;
			for (int j = n; j < i; j++) {
				if (j < i) {
					int currentLength = length[i];
					int newLength = length[j] + 1;
					if (newLength > currentLength) {
						length[i] = newLength;
						if (newLength > maxLength)
							maxLength = newLength;
					}
				}
			}
		}
		return maxLength;
	}
	
	public static int LDS(int n) {
		//--compute LDS starting from index n
		return 1;
	}
}