// 
// UVA #11790
// Garrett Openshaw
// 

import java.util.*;

class Main {

	//--A[] holds the input
	public static int[] A = new int[1000000];
	
	//--L[] holds the length
	public static int[] L = new int[1000000];
	
	//--M[i] will hold the max length for
	//--a sequence that ends with element i
	public static int[] M = new int[1000000];

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		for (int n = 0; n < numCases; n++) {
			int numBuildings = conIn.nextInt();
			for (int i = 0; i < numBuildings; i++)
				A[i] = conIn.nextInt();
			for (int i = 0; i < numBuildings; i++)
				M[i] = conIn.nextInt();
			int increasing = LIS();
			int decreasing = LDS();
			
			if (increasing > decreasing) {
				System.out.println("Case " + (numCases + 1)
					+ ". Increasing (" + increasing + "(. Decreasing ("
					+ decreasing + ").");
			}
			else {
				System.out.println("Case " + (numCases + 1)
					+ ". Decreasing (" + decreasing + "(. Increasing ("
					+ increasing + ").");
			}
		}
	}
	
	public static int LIS() {
	
	}
	
	public static int LDS() {
	
	}
}