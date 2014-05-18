import java.util.*;

class Block {
	int id;
	int topFace;
	Block parent;
	
	public Block(int id, int topFace) {
		this.id = id;
		this.topFace = topFace;
	}
	
	@Override
	public String toString() {
		return id + " " + face(topFace);
	}
	
	private static String face(int i) {
		switch (i) {
			case 0: return "front";
			case 1: return "back";
			case 2: return "left";
			case 3: return "right";
			case 4: return "top";
		}
		
		return "bottom";
	}
}

class Main {
	//--This will hold the provided set of cubes
	public static int[][] cubes = new int[501][6];
	
	//--This array will be used to build out the LIS
	//--Dimensions are block position and color
	public static Block[][] stack = new Block[501][101];
	
	public static int length;
	public static int numCubes;
	public static int caseNumber;
	public static Block topBlock;
	
	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		numCubes = conIn.nextInt();
		caseNumber = 0;
		while (numCubes != 0) {
			caseNumber++;
			//--Get input
			for (int i = 1; i <= numCubes; i++) {
				for (int j = 0; j < 6; j++) {
					cubes[i][j] = conIn.nextInt();
				}
			}
			
			LIS();
			
			printResults();
					
			numCubes = conIn.nextInt();
			if (numCubes != 0) {
				System.out.println();
				clearArrays();
			}
		}
	}
	
	public static void LIS() {
		length = 1;
		//--add the heaviest block on its colors
		for (int i = 0; i < 6; i++)
			stack[1][cubes[1][i]] = new Block(numCubes, opposite(i));
			
		//--for each lighter block
		for (int blockID = numCubes - 1; blockID > 0; blockID--) {
		
			//--see the stack has any blocks that have a top 
			//--that matches this block's bottom
			for (int position = length; position > 0; position--) {
				for (int face = 0; face < 6; face++) {
					if (stack[position][cubes[blockID][face]] != null) {
						//--add this block
						Block newBlock = new Block(blockID, opposite(face));
						stack[position + 1][cubes[blockID][face]] = newBlock;
						//--set this block's parent
						newBlock.parent = stack[position][cubes[blockID][face]];
						if (position == length) {
							length++;
							topBlock = newBlock;
						}
					}
				}
			}
			
			//--add all of it's faces to the first position
			for (int face = 0; face < 6; face++)
				stack[1][cubes[blockID][face]] = new Block(blockID, opposite(face));
		}
	}
	
	public static void printResults() {
// 		System.out.println("Case #" + caseNumber);
// 		System.out.println(length);
// 		
// 		while (length != 1) {
// 			for (int i = 0; i < 6; i ++) {
// 				if (parent[topBlock][length][i] != 0) {
// 					System.out.println(topBlock + " " + face(i));
// 					topBlock = parent[topBlock][length][i];
// 					length--;
// 					break;
// 				}
// 			}
// 			System.out.println(topBlock);
// 		}
	}
	
	public static void clearArrays() {
		for (int i = 0; i <= numCubes; i++) {
			for (int j = 0; j < 101; j++) {
				stack[i][j] = null;
			}
			for (int j = 0; j < 6; j++) {
				cubes[i][j] = 0;
			}
		}
	}
	
	public static int opposite(int i) {
		switch (i) {
			case 0: return 1;
			case 1: return 0;
			case 2: return 3;
			case 3: return 2;
			case 4: return 5;
		}
		
		return 4;
	}
}