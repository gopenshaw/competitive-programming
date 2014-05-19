//--UVA #11003

import java.util.*;

class Box {
	int weight;
	int capacity;
}

class Main {
	//--A array will hold the provided input
	public static Box[] A = new Box[1000];
	
	//--M will hold the current weight capacity
	//--of the box with the smallest remaining capacity
	public static int[] M = new int[1001];
	
	public static int numBoxes;

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			A[i] = new Box();
		}
	
		Scanner conIn = new Scanner(System.in);
		numBoxes = conIn.nextInt();
		
		while (numBoxes != 0) {
		
			for (int i = 0; i < numBoxes; i++) {
				A[i].weight = conIn.nextInt();
				A[i].capacity = conIn.nextInt();
			}
			
			LIS();
			
			numBoxes = conIn.nextInt();
		}
	}
	
	public static void LIS() {
	
	}
}