// 
// UVA #908 - Re-connecting Computer Sites
// Garrett Openshaw
// 

import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		
		while (conIn.hasNext()) {
			int numSites = conIn.nextInt();
			int originalCost = 0;
			for (int i = 0; i < numSites - 1; i++) {
				conIn.nextInt();
				conIn.nextInt();
				originalCost += conIn.nextInt();
			}
			
			System.out.println(originalCost);
			
			int numNewLines = conIn.nextInt();
			for (int i = 0; i < numNewLines; i++) {
				int source = conIn.nextInt();
				int dest = conIn.nextInt();
				int cost = conIn.nextInt();
			}
			
			int numOriginal = conIn.nextInt();
			for (int i = 0; i < numOriginal; i++) {
				int source = conIn.nextInt();
				int dest = conIn.nextInt();
				int cost = conIn.nextInt();
			}
		}
	}
}