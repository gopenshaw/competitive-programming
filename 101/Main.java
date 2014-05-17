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
		
		String input, c1, c2;
		int i1, i2;
		
		while (true) {
			input = conIn.next();
			if (input.charAt(0) == 'q')
				break;
				
			i1 = conIn.nextInt();
			c2 = conIn.next();
			i2 = conIn.nextInt();
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
	
	}
	
	public static void returnAllAboveBlock(int b) {
		findBlock(b);
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