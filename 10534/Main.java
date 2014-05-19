//--UVA 10534

//--Problem strategy:
//--Given n elements, we must find the index i such that
//--the max value in the LIS from 0 to i is equal to
//--the LIS from n-1 to i.
//--Once this index if found, we simply print both LIS

import java.util.*;

class Main {

	public static int numElements;
	public static int[] A = new int[10001];

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		while (conIn.hasNext())
			numElements = conIn.nextInt();
			for (int i = 0; i < numElements; i++)
				A[i] = conIn.nextInt();
				
			solveProblem();
	}
	
	public static void solveProblem() {
		int low = 0;
		int high = numElements - 1;
		int index = numElements / 2;
		int ascTopValue = getTopIndex(low, index);
		int descTopValue = getTopIndex(high, index);
		while (ascTopValue != descTopValue) {
			if (ascTopValue < descTopValue) {
				low = index + 1;
			}
			else {
				high = index - 1;
			}
			
			index = (high - low) / 2;
			ascTopValue = getTopIndex(low, index);
			descTopValue = getTopIndex(high, index);
		}
		
		//--now we have the index of the middle value
		//--specifically it is the max of both sequences
	}
	
	public static int getTopIndex(int low, int high) {
		//--Use n log n LIS algorithm and return the highest value
		return -1;
	}
}