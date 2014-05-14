import java.util.*;

class Main {

	public static Scanner conIn;
	
	public static int numElements;
	public static int[] A = new int[1000000];

	public static void main(String[] args) {
		conIn = new Scanner(System.in);
		
		int numCases = new Integer(conIn.nextLine());
		conIn.nextLine();
		
		for (int i = 0; i < numCases; i++) {
			solveProblem();
		}
	}
	
	public static void solveProblem() {
		getInput();
	}
	
	public static void getInput() {
		numElements = 0;
		while (conIn.hasNext()) {
			String input = conIn.nextLine();
			if (input.isEmpty())
				break;
				
			A[numElements++] = new Integer(input);
		}
	}
}