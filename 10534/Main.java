//--UVA 10534

//--Problem strategy:
//--Given n elements, we must find the index i such that
//--the the length of LIS from 0 to i is equal to
//--the the length of LIS from n-1 to i.
//--The answer result will be 2 * length - 1

import java.util.*;

class Main {

	public static int numElements;
	public static int[] A = new int[10001];
	public static int[] M = new int[10002];

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		while (conIn.hasNext()) {
			numElements = conIn.nextInt();
			for (int i = 0; i < numElements; i++)
				A[i] = conIn.nextInt();
				
			solveProblem();
		}
	}
	
	public static void solveProblem() {
		int low = 0;
		int high = numElements - 1;
		int index = high / 2;
		int leftLength = LIS(0, index);
		int rightLength = LIS(numElements - 1, index);
		
		while (leftLength != rightLength) {
			if (leftLength < rightLength) {
				low = index + 1;
				index = (index + high) / 2;
			}
			else {
				high = index - 1;
				index = (index + low) / 2;
			}
			
			leftLength = LIS(0, index);
			rightLength = LIS(numElements - 1, index);
		}
		
		//--we have now maximized the ascending and descending sequence
		System.out.println(2 * leftLength - 1);
	}
	
	public static int LIS(int low, int high) {
		//--clear M[]
		for (int i = 1; i <= numElements; i++)
			M[i] = 0;
		
		//--setup
		int length = 1;
		M[1] = low;
		
		if (low < high) {
			for (int i = low + 1; i <= high; i++) {
				int index = binarySearch(A[i], length);
				M[index] = i;
				if (index > length)
					length = index;
			}
		}
		else {
			for (int i = low - 1; i >= high; i--) {
				int index = binarySearch(A[i], length);
				M[index] = i;
				if (index > length)
					length = index;
			}

		}
		
		return length;
	}
	
	public static int binarySearch(int value, int length) {
		//--this method searches for the correct index to insert the value
		int low = 1;
		int high = length;
		
		while (low < high) {
			int mid = (low + high) / 2;
			if (value > A[M[mid]])
				low = mid + 1;
			else
				high = mid - 1;
		}
		
		if (value <= A[M[low]])
			return low;
		else
			return low + 1;
	}
}