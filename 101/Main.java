import java.util.*;

class Main {
	public static int numBlocks;
	public static int[][] blocks;
	public static int[] height;
	public static int row;
	public static int col;
	
	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		numBlocks = conIn.nextInt();
		conIn.nextLine();
		blocks = new int[numBlocks][numBlocks];
		height = new int[numBlocks];
		init();
		
		String c1, c2;
		int i1, i2;
		
		while (true) {
			c1 = conIn.next();
			if (c1.charAt(0) == 'q')
				break;
				
			i1 = conIn.nextInt();
			c2 = conIn.next();
			i2 = conIn.nextInt();
			
			if (c2.equals("onto")) {
				returnAllAboveBlock(i2);
			}
			
			if (c1.equals("move")) {
				returnAllAboveBlock(i1);
			}
			else {
				findBlock(i1);
			}
			
			movePile(i1, getBlockRow(i2));
		}
		
		printBoard();
	}
	
	public static void init() {
		for (int i = 0; i < numBlocks; i++) {
			blocks[i][0] = i;
			height[i] = 1;
		}
	}
	
	public static void findBlock(int b) {
		for (int i = 0; i < numBlocks; i++) {
			for (int j = 0; j < height[i]; j++) {
				if (blocks[i][j] == b) {
					row = i;
					col = j;
					return;
				}
			}
		}
	}
	
	public static int getBlockRow(int b) {
		for (int i = 0; i < numBlocks; i++) {
			for (int j = 0; j < height[i]; j++) {
				if (blocks[i][j] == b) {
					return i;
				}
			}
		}
		
		return -1;
	}
	
	public static void returnAllAboveBlock(int b) {
		findBlock(b);
		int initialHeight = height[row];
		for (int i = col + 1; i < initialHeight; i++) {
			int returnBlock = blocks[row][i];
			blocks[returnBlock][height[returnBlock]] = returnBlock;
			height[returnBlock]++;
			height[row]--;
		}
	}
	
	public static void movePile(int b, int dest) {
		//--Assumes that findBlock has already been called
		int initialHeight = height[row];
		for (int i = col; i < initialHeight; i++) {
			int moveBlock = blocks[row][i];
			blocks[dest][height[dest]] = moveBlock;
			height[dest]++;
			height[row]--;
		}
	}
	
	public static void printBoard() {
		for (int i = 0; i < numBlocks; i++) {
			System.out.print(i + ":");
			for (int j = 0; j < height[i]; j++) {
				System.out.print(" " + blocks[i][j]);
			}
			System.out.println();
		}
	}
}