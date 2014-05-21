// 
// UVA #11790
// Garrett Openshaw
// 

import java.util.*;

class Main {

	//--A[] holds the height
	public static int[] H = new int[1000000];
	
	//--L[] holds the length
	public static int[] L = new int[1000000];
	
	//--M[i] will hold the max length for
	//--a sequence that ends with element i
	public static int[] M = new int[1000000];
	
	public static int numBuildings;

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		for (int n = 0; n < numCases; n++) {
			numBuildings = conIn.nextInt();
			for (int i = 0; i < numBuildings; i++)
				H[i] = conIn.nextInt();
			for (int i = 0; i < numBuildings; i++)
				L[i] = conIn.nextInt();
			int increasing = LIS();
			int decreasing = LDS();
			
			if (increasing > decreasing) {
				System.out.println("Case " + (n + 1)
					+ ". Increasing (" + increasing + "). Decreasing ("
					+ decreasing + ").");
			}
			else {
				System.out.println("Case " + (n + 1)
					+ ". Decreasing (" + decreasing + "). Increasing ("
					+ increasing + ").");
			}
		}
	}
	
	public static int LIS() {
		//--initialize every building
		//--this ensures that we start with the longest sequence of one
		int length = 0;
		for (int i = 0; i < numBuildings; i++) {
			M[i] = L[i];
			if (L[i] > length)
				length = L[i];
		}
		
		//--for every building, check every building to its left
		for (int i = 1; i < numBuildings; i++) {
			for (int j = 0; j < i; j++) {
				if (H[j] < H[i]) {
					int newLength = L[i] + M[j];
					if (newLength > M[i])
						M[i] = newLength;
					if (M[i] > length)
						length = M[i];
				}
			}
		}
		return length;
	}
	
	public static int LDS() {
		//--initialize every building
		int length = 0;
		for (int i = 0; i < numBuildings; i++) {
			M[i] = L[i];
			if (L[i] > length)
				length = L[i];
		}
		
		//--for every building, check every building to its left
		for (int i = 1; i < numBuildings; i++) {
			for (int j = 0; j < i; j++) {
				if (H[j] > H[i]) {
					int newLength = L[i] + M[j];
					if (newLength > M[i])
						M[i] = newLength;
					if (M[i] > length)
						length = M[i];
				}
			}
		}
		return length;
	}
}