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
		buildBoard();
		
		String s1, s2;
		int i1, i2;
		
		while (true) {
			s1 = conIn.next();
			if (s1.charAt(0) == 'q')
				break;
				
			i1 = conIn.nextInt();
			s2 = conIn.next();
			i2 = conIn.nextInt();
			
			if (i1 == i2
				|| getBlockRow(i1) == getBlockRow(i2))
				continue;
			
			if (s2.equals("onto"))
				returnAllAboveBlock(i2);
			
			if (s1.equals("move"))
				returnAllAboveBlock(i1);
			
			movePile(i1, getBlockRow(i2));
		}
		
		printBoard();
	}
	
	public static void buildBoard() {
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
		findBlock(b);
		int initialHeight = height[row];
		for (int i = col; i < initialHeight; i++) {
			blocks[dest][height[dest]] = blocks[row][i];
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