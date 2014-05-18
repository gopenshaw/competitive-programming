import java.util.*;

class Main {
	//--This will hold the provided set of cubes
	public static int[][] cubes = new int[501][6];
	
	//--This array will be used to build out the LIS
	//--Dimensions are block position, color, and face
	//--Value will be block ID
	public static int[][][] stack = new int[501][101][6];
	
	//--parent[i] will return the id of the block that follows i
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
			if (numCubes != 0) {
				System.out.println();
				clearArrays();
			}
		}
	}
	
	public static void LIS() {
	
	}
	
	public static void clearArrays() {
		for (int i = 0; i < 501; i++) {
			for (int j = 0; j < 6; j++) {
				cubes[i][j] = 0;
				parent[i][j] = 0;
			}
			for (int j = 0; j < 101; j++) {
				for (int k = 0; k < 6; k++) {
					stack[i][j][k] = 0;
				}
			}
		}
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