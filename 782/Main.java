//--UVA #782 Contour Painting
//--Garrett Openshaw

import java.util.*;

class Main {
	public static char[][] grid;
	public static boolean[][] flood;
	public static boolean[][] hasVisited;
	public static int numRows;
	
	public static final int HEIGHT = 32;
	public static final int LENGTH = 82;
	
	public static void main(String[] args) {
	
		grid = new char[HEIGHT][LENGTH];
		hasVisited = new boolean[HEIGHT][LENGTH];
		flood = new boolean[HEIGHT][LENGTH];
		
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		conIn.nextLine();
		for (int n = 0; n < numCases; n++) {
			//--init
			numRows = 0;
			
			//--get input
			String temp;
			while (true) {
				temp = conIn.nextLine();
				int length = temp.length();
							
				if (length > 0 && temp.charAt(0) == '_')
					break;
									
				for (int i = 0; i < length; i++)
					grid[numRows][i] = temp.charAt(i);
					
				numRows++;
			}
			
			//--flood fill
			int starRow = -1;
			outerloop:
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < LENGTH; j++) {
					if (grid[i][j] == '*') {
						starRow = i;
						grid[i][j] = ' ';
						floodFill(i, j);
						break outerloop;
					}
				}
			}
			
			//--shade contour
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < LENGTH; j++) {
					if (grid[i][j] == 'X')
						shadeContour(i, j);	
				}
			}
			
			//--see if empty chars need to be replaced with spaces
			for (int i = 0; i < numRows; i++)
				updateRow(i);
				
			//--In the row that contained the star,
			//replace each space with an empty char
			if (starRow != -1)
				updateStarRow(starRow);
			
			//--print results
			for (int i = 0; i < numRows; i++) {
				System.out.println(grid[i]);
			}
			System.out.println(temp);
			
			//--tear down
			if (n != numCases - 1) {
				clearArrays();
			}
		}
	}

	public static void shadeContour(int x, int y) {
		//--This method will checks all four neighbors 
		//and shades them if appropriate
		
		if (x + 1 < numRows 
			&& flood[x + 1][y]
			&& (grid[x + 1][y] == ' '
				|| grid[x + 1][y] == '\u0000'))
			grid[x + 1][y] = '#';
			
		if (x - 1 >= 0 
			&& flood[x - 1][y] 
			&& (grid[x - 1][y] == ' '
				|| grid[x - 1][y] == '\u0000'))
			grid[x - 1][y] = '#';
			
		if (y + 1 < LENGTH 
			&& flood[x][y + 1] 
			&& (grid[x][y + 1] == ' '
				|| grid[x][y + 1] == '\u0000'))
			grid[x][y + 1] = '#';
			
		if (y - 1 >= 0 
			&& flood[x][y - 1] 
			&& (grid[x][y - 1] == ' '
				|| grid[x][y - 1] == '\u0000'))
			grid[x][y - 1] = '#';
	}

	public static void floodFill(int x, int y) {
		if (x < 0
			|| y < 0
			|| x >= numRows
			|| y >= LENGTH
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
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < LENGTH; j++) {
				hasVisited[i][j] = false;
				flood[i][j] = false;
				grid[i][j] = '\u0000';
			}
		}
	}
	
	public static void updateRow(int r) {
		int endOfLine = 0;
		for (int i = LENGTH - 1; i >= 0; i--) {
			if (grid[r][i] == '#') {
				endOfLine = i;
				break;
			}
		}
		
		for (int i = 0; i < endOfLine; i++) {
			if (grid[r][i] == '\u0000')
				grid[r][i] = ' ';
		}
	}
	
	public static void updateStarRow (int starRow) {
		int position = 0;
		for (int i = LENGTH - 1; i >= 0; i--) {
			if (grid[starRow][i] == 'X'
				|| grid[starRow][i] == '#') {
					position = i;
					break;
			}
		}
		
		for (int i = position; i < LENGTH; i++) {
			if (grid[starRow][i] == ' ')
					grid[starRow][i] = '\u0000';
		}
	}
}