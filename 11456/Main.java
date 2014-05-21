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
			System.out.println("DEBUG: lis = " + lis);
			
			//--reset length array
			for (int j = i; j < numCars; j++)
				length[j] = 1;
			
			int lds = LDS(i);
			System.out.println("DEBUG: lds = " + lds + "\n");
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
				if (weight[j] < weight[i]) {
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
		int maxLength = 1;
		for (int i = n + 1; i < numCars; i++) {
			if (weight[i] > weight[n])
				continue;
			for (int j = n; j < i; j++) {
				if (weight[j] > weight[i]) {
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
}