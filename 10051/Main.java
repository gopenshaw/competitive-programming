import java.util.*;

class Main {

	public static int[][] cubes = new int[501][6];
	public static int[][] stack = new int[501][101];
	public static int[][] parent = new int[501][6];
	public static int length;
	public static int numCubes;
	
	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		numCubes = conIn.nextInt();
		int caseNumber = 0;
		while (numCubes != 0) {
			caseNumber++;
			//--Get input
			for (int i = 1; i <= numCubes; i++) {
				for (int j = 0; j < 6; j++) {
					cubes[i][j] = conIn.nextInt();
				}
			}
			
			LIS();
					
			numCubes = conIn.nextInt();
			if (numCubes != 0)
				System.out.println();
		}
	}
	
	public static void LIS() {
	
	}
}

enum Face {
    front(0), back(1), left(2), right(3), top(4), bottom(5);

    private final int value;
    private Face(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}