//--UVA #782 Contour Painting
//--Garrett Openshaw

import java.util.*;

class Main {
	public static char[][] grid;
	public static boolean[][] isAdjacent;
	public static boolean[][] flood;
	public static boolean[][] hasVisited;
	public static int maxRow;
	public static int xStar;
	public static int yStar;

	public static void main(String[] args) {
		grid = new char[30][80];
		hasVisited = new boolean[30][80];
		flood = new boolean[30][80];
		isAdjacent = new boolean[30][80];
		
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		for (int n = 0; n < numCases; n++) {
			//--init
			maxRow = 0;
			if (n != 0)
				clearArrays();
			
			//--get input
			String temp;
			while (true) {
				temp = conIn.nextLine();
				int length = temp.length();
									
				for (int i = 0; i < length; i++)
					grid[maxRow][i] = temp.charAt(i);
					
				if (length > 0 && temp.charAt(0) == '_')
					break;
					
				maxRow++;
			}
			
			//--flood fill
			outerloop:
			for (int i = 0; i < maxRow; i++) {
				for (int j = 0; j < 80; j++) {
					if (grid[i][j] == '*') {
						grid[i][j] = ' ';
						floodFill(i, j);
						break outerloop;
					}
				}
			}
			
			//--shade contour
			for (int i = 0; i < maxRow; i++) {
				for (int j = 0; j < 80; j++) {
					if (grid[i][j] == 'X')
						shadeContour(i, j);	
				}
			}
			
			//--print results
			for (int i = 0; i < maxRow; i++) {
				System.out.println(grid[i]);
			}
			System.out.println("__________");
		}
	}

	public static void shadeContour(int x, int y) {
		//--This method will checks all four neighbors 
		//and shades them if appropriate
		
		if (x + 1 <= maxRow && flood[x + 1][y] && grid[x + 1][y] == ' ')
			grid[x + 1][y] = '#';
			
		if (x - 1 >= 0 && flood[x - 1][y] && grid[x - 1][y] == ' ')
			grid[x - 1][y] = '#';
			
		if (y + 1 <= grid[x].length && flood[x][y + 1] && grid[x][y + 1] == ' ')
			grid[x][y + 1] = '#';
			
		if (y - 1 >= 0 && flood[x][y - 1] && grid[x][y - 1] == ' ')
			grid[x][y - 1] = '#';
	}

	public static void floodFill(int x, int y) {
		if (x < 0
			|| y < 0
			|| x >= maxRow
			|| y >= grid[x].length
			|| hasVisited[x][y]
			|| grid[x][y] == 'X')
			return;
		
		hasVisited[x][y] = true;
		flood[x][y] = true;
		
		floodFill(x + 1, y);
		floodFill(x, y + 1);
		floodFill(x - 1, y);
		floodFill(x, y - 1);
	}
	
	public static void clearArrays() {
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 80; j++) {
				hasVisited[i][j] = false;
				flood[i][j] = false;
			}
		}
	}
}