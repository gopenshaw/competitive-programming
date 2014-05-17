import java.util.*;

class Main {
	public static int[][] blocks;
	
	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numBlocks = conIn.nextInt();
		conIn.nextLine();
		blocks = new int[numBlocks][numBlocks];
		
		String input, c1, c2;
		int i1, i2;
		
		while (true) {
			input = conIn.next();
			if (input.charAt(0) == 'q')
				return;
				
			i1 = conIn.nextInt();
			c2 = conIn.next();
			i2 = conIn.nextInt();
		}
	}
}